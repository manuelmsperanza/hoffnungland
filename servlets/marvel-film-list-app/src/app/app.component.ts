import { Component, Inject, OnInit, ViewChild, OnChanges, SimpleChanges, AfterContentChecked, AfterViewChecked, DoCheck } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap, map, catchError } from 'rxjs/operators';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnChanges, AfterViewChecked, AfterContentChecked, DoCheck {

  private gridColumnApi;
  toggleAutosize : boolean = true;
  title = 'marvel-film-list-app';

  defaultColDef;
  columnTypes;
  columnDefs = [
    {headerName: 'Title', field: 'title', sortable: true, filter: true, resizable: true},
    {headerName: 'Season', field: 'season', sortable: true, filter: true, resizable: true},
    {headerName: 'Episode #', field: 'episode', sortable: true, filter: true, resizable: true},
    {headerName: 'Episode Name', field: 'episodeName', filter: true, resizable: true},
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
    this.autoSizeAll(false);
  }
  ngAfterViewChecked(): void {
    console.log("ngAfterViewChecked");
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log("ngOnChanges");
  }

  ngDoCheck(): void {
    console.log("ngDoCheck");
  }

  ngOnInit() {
    console.log("ngOnInit");
    /*this.http.get('/marvelFilmListApp/MarvelFilmApplication/MarvelFilmsService').pipe(
      catchError((err, caught) => caught)
     )
      .subscribe(
        data => this.rowData = data
      );*/
  }

  onGridReady(params) {
    console.log("onGridReady");
    this.gridColumnApi = params.columnApi;
    this.http.get('/marvelFilmListApp/MarvelFilmApplication/MarvelFilmsService').pipe(
      catchError((err, caught) => caught)
     )
      .subscribe(
        data => this.rowData = data
      );

  }

  autoSizeAll(skipHeader) {
    console.log('autoSizeAll toggle: ' + this.toggleAutosize);
    if(this.gridColumnApi){
      if(this.toggleAutosize){
        this.gridColumnApi.autoSizeAllColumns(skipHeader);
      }
      this.toggleAutosize = !this.toggleAutosize;
    }


  }

}
