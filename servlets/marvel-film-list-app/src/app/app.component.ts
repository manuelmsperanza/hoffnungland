import { Component, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap, map, catchError } from 'rxjs/operators';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';
import { environment } from './../environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  private gridApi;
  private gridColumnApi;
  
  rowSelection = 'single';
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
    private cookieService: CookieService,
    @Inject(DOCUMENT) public document: Document,
      public auth: AuthService) {
    this.columnTypes = {dateColumn: {
      filter: 'agDateColumnFilter'}
    }
  }

  gridOptions = {
    // callback tells the grid to use the 'id' attribute for IDs, IDs should always be strings
    getRowNodeId: data => data.id,
  
    // other grid options ...
  }

  onFirstDataRendered(params) {
    this.autoSizeAll(false);

    let cookieExists: boolean = this.cookieService.check('selectedMarvelFilmId');

    if(cookieExists){
      let selectedMarvelFilmId : string = this.cookieService.get( 'selectedMarvelFilmId');
      const rowNode = this.gridApi.getRowNode(selectedMarvelFilmId);
      rowNode.setSelected(true);
      this.gridApi.ensureNodeVisible(rowNode, 'middle');
    }
  }
  onBodyScroll(params) {
    this.autoSizeAll(false);
  }
  onGridReady(params) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.http.get(environment.marvelFilmsServiceUrl).pipe(
      catchError((err, caught) => caught),
     )
      .subscribe(
        data => this.rowData = data
      );

  }

  autoSizeAll(skipHeader) {
    if(this.gridColumnApi){
      this.gridColumnApi.autoSizeAllColumns(skipHeader);
    }
  }


  onRowClicked(event) {
    console.log(JSON.stringify(event.api.getSelectedRows()));
    this.cookieService.set( 'selectedMarvelFilmId', event.api.getSelectedRows()[0].id );  
  }
}

