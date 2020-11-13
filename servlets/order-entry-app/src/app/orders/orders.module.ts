import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrdersRoutingModule } from './orders-routing.module';
import { ListOrdersComponent } from './list-orders/list-orders.component';
import { NewOrderComponent } from './new-order/new-order.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    ListOrdersComponent,
    NewOrderComponent],
  imports: [
    CommonModule,
    NgbModule,
    OrdersRoutingModule
  ]
})
export class OrdersModule { }
