import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title : String = 'ready-to-drink-app';
  
  private _message : String;

  set message (msg : String) {
    this._message = msg;
  }

  get message () : String {
    return this._message;
  }

  closeAlert(){
    this.message = null;
  }

}
