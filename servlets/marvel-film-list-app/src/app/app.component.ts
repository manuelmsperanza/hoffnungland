import { Component, Inject, OnInit, ViewChild, OnChanges, SimpleChanges, AfterContentChecked, AfterViewChecked } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap, map, catchError } from 'rxjs/operators';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnChanges, AfterViewChecked, AfterContentChecked {
  title = 'marvel-film-list-app';

  defaultColDef;
  columnTypes;
  columnDefs = [
    {headerName: 'Title', field: 'title', sortable: true, filter: true},
    {headerName: 'Season', field: 'season', sortable: true, filter: true},
    {headerName: 'Episode #', field: 'episode', sortable: true, filter: true},
    {headerName: 'Episode Name', field: 'episodeName', filter: true},
    {headerName: 'Release Date', field: 'releaseDate', sortable: true, filter: true, type: 'dateColumn'}
];

  rowData : any;
  chartElement : any;
  height = 202;
  width = 640;
  margin = ({top: 20, right: 0, bottom: 0, left: 30});

  constructor(private http: HttpClient,
    @Inject(DOCUMENT) public document: Document,
      public auth: AuthService) {
    this.columnTypes = {dateColumn: {
      filter: 'agDateColumnFilter'}
    }
  }
  ngAfterContentChecked(): void {
    console.log("ngAfterContentChecked");
  }
  ngAfterViewChecked(): void {
    console.log("AfterViewChecked");
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log("ngOnChanges");
  }

  ngOnInit() {
    console.log("ngOnInit");
    console.log(this.document.location.href);
     this.http.get('/marvelFilmListApp/MarvelFilmApplication/MarvelFilmsService').pipe(
      catchError((err, caught) => caught)
     )
      .subscribe(
        data => this.rowData = data
      );
      
  }

}
