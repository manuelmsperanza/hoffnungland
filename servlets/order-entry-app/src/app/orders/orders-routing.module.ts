import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewOrderComponent } from './new-order/new-order.component';
import { ListOrdersComponent } from './list-orders/list-orders.component';


const routes: Routes = [ 
  { path: 'new-order', component: NewOrderComponent },
  { path: 'list-orders', component: ListOrdersComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrdersRoutingModule { }
