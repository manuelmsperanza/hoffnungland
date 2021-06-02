import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DriveBookingComponent } from './drive-booking/drive-booking.component';
import { DriveTrackingComponent } from './drive-tracking/drive-tracking.component';
import { DrivingSchoolComponent } from './driving-school/driving-school.component';
import { TodoComponent } from './todo/todo.component';

const routes: Routes = [
  { path: 'drive-booking', component: DriveBookingComponent },
  { path: 'drive-tracking', component: DriveTrackingComponent },
  { path: 'driving-school', component: DrivingSchoolComponent },
  { path: '**', component: TodoComponent }];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
