import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DriveBookingComponent } from './drive-booking/drive-booking.component';
import { DriveTrackingComponent } from './drive-tracking/drive-tracking.component';
import { TodoComponent } from './todo/todo.component';

const routes: Routes = [
  { path: 'drive-booking', component: DriveBookingComponent },
  { path: 'drive-tracking', component: DriveTrackingComponent },
  { path: '**', component: TodoComponent }];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
