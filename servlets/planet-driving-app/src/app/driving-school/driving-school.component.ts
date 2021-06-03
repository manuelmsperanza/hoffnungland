import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { DrivingSchool, DrivingSchoolObject } from '../drivingSchool';

@Component({
  selector: 'app-driving-school',
  templateUrl: './driving-school.component.html',
  styleUrls: ['./driving-school.component.css']
})
export class DrivingSchoolComponent implements OnInit {

  DRIVINGSCHOOLS: DrivingSchool[] = [];
  newDrivingSchool: DrivingSchool = new DrivingSchoolObject();
  constructor(private http: HttpClient) {
    
  }

  ngOnInit(): void {

    this.http.get<DrivingSchool[]>(environment.drivingSchoolsPath).pipe()
    .subscribe(
        data => this.DRIVINGSCHOOLS = data
      );

  }

  addDrivingSchool(){
    this.http.post<DrivingSchool>(environment.drivingSchoolPath, this.newDrivingSchool).pipe()
    .subscribe(element => {
      this.DRIVINGSCHOOLS.push(element)
      this.newDrivingSchool = new DrivingSchoolObject();
    });
  }

  saveDrivingSchool(drivingSchool : DrivingSchool) {
    this.http.put<DrivingSchool>(environment.drivingSchoolPath, drivingSchool).pipe()
    .subscribe(element => this.updateDrivingSchools(element));
  }

  updateDrivingSchools(drivingSchool : DrivingSchool){
    let spliceStartIndex : number = -1;
    this.DRIVINGSCHOOLS.forEach((element, index) => {
      if(element.id === drivingSchool.id){
        spliceStartIndex = index;
      }
    })

    if(spliceStartIndex !== -1){
      this.DRIVINGSCHOOLS.splice(spliceStartIndex, 1, drivingSchool);
    }

  }

  deleteDrivingSchool(drivingSchool : DrivingSchool){
    this.http.delete<DrivingSchool>(environment.drivingSchoolPath + '/' + drivingSchool.id).pipe()
    .subscribe(element => this.removeItemFromDrivingSchools(drivingSchool));

  }

  removeItemFromDrivingSchools(drivingSchool : DrivingSchool) {
    let spliceStartIndex : number = -1;
    this.DRIVINGSCHOOLS.forEach((element, index) => {
      if(element.id === drivingSchool.id){
        spliceStartIndex = index;
      }
    })

    if(spliceStartIndex !== -1){
      this.DRIVINGSCHOOLS.splice(spliceStartIndex, 1);
    }
  }

}
