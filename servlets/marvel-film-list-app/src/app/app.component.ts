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


  constructor(private http: HttpClient) {
    this.columnTypes = {dateColumn: {
      filter: 'agDateColumnFilter'}
    }
  }
  ngAfterContentChecked(): void {
    console.log("ngAfterContentChecked");
    //this.drawChart();
  }
  ngAfterViewChecked(): void {
    console.log("AfterViewChecked");
    this.drawChart();
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log("ngOnChanges");
    //this.drawChart();
  }

  ngOnInit() {
    console.log("ngOnInit");
     this.http.get('/marvelFilmListApp/MarvelFilmApplication/MarvelFilmsService').pipe(
      catchError((err, caught) => caught)
     )
      .subscribe(
        data => this.rowData = data
      );
      
      //this.chartElement = d3.selectAll("#chart");
      //console.log(this.chartElement);
      //this.chartElement.style("color", "blue");
      //this.drawChart();

  }

  drawChart() : void {
    console.log("drawChart");
    //console.log(d3.min(this.rowData, (d: { releaseDate: Date; }) => d.releaseDate));
    //console.log(d3.max(this.rowData, (d: { releaseDate: Date; }) => d.releaseDate));
    
    var svg = d3.create("svg")
      .attr("viewBox", [0, 0, this.width, this.height]);
    
    var x = d3.scaleLinear()
    .domain(d3.extent(this.rowData)).nice()
    .range([this.margin.left, this.width - this.margin.right]);

    var xAxis = g => g
    .attr("transform", `translate(0,${this.height - this.margin.bottom})`)
    .call(d3.axisBottom(x).ticks(this.width / 80 ).tickSizeOuter(0))
    .call(g => g.append("text")
        .attr("x", this.width - this.margin.right)
        .attr("y", -4)
        .attr("fill", "currentColor")
        .attr("font-weight", "bold")
        .attr("text-anchor", "end")
        .text(this.rowData.releaseDate));

    svg.append("g").call(xAxis);

    this.chartElement.selectAll("svg").exit().remove();
    this.chartElement.selectAll("svg").enter().append(svg.node());
    /*this.chartElement.selectAll("p").exit().remove();

    this.chartElement.selectAll("p")
    .data([4, 8, 15, 16, 23, 42])
    .enter().append("p")
      .text(function(d) { return "I’m number " + d + "!"; });*/
    
  }
  

}
