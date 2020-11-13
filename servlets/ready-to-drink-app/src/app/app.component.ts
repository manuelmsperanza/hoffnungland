import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import {startWith, map} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title : String = 'ready-to-drink-app';
  
  myControl = new FormControl('');
  options: string[] = ['Provider #1', 'Provider #2', 'Provider #3', 'Provider #4', 'Provider #5'];
  
  filteredOptions : Observable<string[]>;

  private _message : String;

  set message (msg : String) {
    this._message = msg;
  }

  get message () : String {
    return this._message;
  }

  closeAlert(){
    this.message = null;
    this.myControl.setValue(null);
  }

  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  private _filter(value: string): string[] {
    const filterValue = this._normalizeValue(value);
    return this.options.filter(street => this._normalizeValue(street).includes(filterValue));
  }

  private _normalizeValue(value: string): string {
    return value.toLowerCase().replace(/\s/g, '');
  }

}
