import { Component, OnInit, ViewChild, OnChanges, SimpleChanges, AfterContentChecked, AfterViewChecked } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap, map, catchError } from 'rxjs/operators';
import * as d3 from "d3";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnChanges, AfterViewChecked, AfterContentChecked {
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
    
  }
  ngAfterContentChecked(): void {
    console.log("ngOnChanges");
    //this.drawChart();
  }
  ngAfterViewChecked(): void {
    console.log("AfterViewChecked");
    //this.drawChart();
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log("ngOnChanges");
    //this.drawChart();
  }

  ngOnInit() {
    console.log("ngOnInit");
     this.http.get('/marvelFilmListApp/assets/marvel-film-list.json').pipe(
      catchError((err, caught) => caught)
     )
      .subscribe(
        data => this.rowData = data
      );
      
      this.drawChart();

  }

  drawChart() : void {
    console.log("drawChart");
    var chartElement = d3.selectAll("#chart");
    console.log(chartElement);
    chartElement.style("color", "blue");
    chartElement.selectAll("p")
    .data([4, 8, 15, 16, 23, 42])
    .enter().append("p")
      .text(function(d) { return "I’m number " + d + "!"; });

  }
  

}
