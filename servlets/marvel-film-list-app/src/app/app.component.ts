import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap, map, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'marvel-film-list-app';

  defaultColDef;

  columnDefs = [
    {headerName: 'Title', field: 'title', sortable: true, filter: true},
    {headerName: 'Season', field: 'season', sortable: true, filter: true},
    {headerName: 'Episode #', field: 'episode', sortable: true, filter: true},
    {headerName: 'Episode Name', field: 'episodeName', filter: true},
    {headerName: 'Release Date', field: 'releaseDate', sortable: true, filter: true, type: 'dateColumn'}
];

  rowData : any;

  constructor(private http: HttpClient) {
    this.defaultColDef = { resizable: true };
  }

  ngOnInit() {

     this.http.get('/marvelFilmListApp/assets/marvel-film-list.json').pipe(
      catchError((err, caught) => caught)
     )
      .subscribe(
        data => this.rowData = data
      );
  }


}
