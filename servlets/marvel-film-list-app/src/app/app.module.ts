import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgGridModule } from 'ag-grid-angular';
import { HttpClientModule } from '@angular/common/http';
import { AuthModule } from '@auth0/auth0-angular';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgGridModule.withComponents([]),
    HttpClientModule,
    AuthModule.forRoot({
      domain: 'hoffnungland.us.auth0.com',
      clientId: 'oGkoTO7htixNRqf8o0iY6EElZuoMMdEj'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
