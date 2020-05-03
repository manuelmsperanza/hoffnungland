import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title : String = 'ready-to-drink-app';
  
  message : String;

  setMessage(msg : String) {
    this.message = msg;
  }

  closeAlert(){
    this.message = null;
  }

}
