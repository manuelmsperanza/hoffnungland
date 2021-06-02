import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { DrivingSchool } from '../drivingSchool';

@Component({
  selector: 'app-driving-school',
  templateUrl: './driving-school.component.html',
  styleUrls: ['./driving-school.component.css']
})
export class DrivingSchoolComponent implements OnInit {

  DRIVINGSCHOOLS: DrivingSchool[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {

    this.http.get(environment.drivingSchoolsPath).pipe()
    .subscribe(
        data => {
          console.log(JSON.stringify(data));
        }
      );

  }

}
