import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [ReactiveFormsModule, MatCardModule, MatInputModule, MatFormFieldModule, MatButtonModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class ContactFormComponent {
  email = new FormControl('');
  message = new FormControl('');
  outcome = '';
  
  private _snackBar = inject(MatSnackBar);

  constructor(private http: HttpClient) {}

  submitEmail() {
    
    this.http
      .post('/api/submit-email', { email: this.email.value, message: this.message.value })
      /*.pipe(
        catchError((error) => {
          console.log('Error: ' + JSON.stringify(error));
          let errorMessage = 'An unknown error occurred!';
          if (error.status === 400 || error.status === 500) {
            errorMessage = error.error.message || 'An error occurred!';
          }
          this.outcome = errorMessage;
          
          return throwError(() => error);
        })
      )*/
      .subscribe({
        next: (res) => {
          if((res as any).success){
            this.outcome = 'Email sent, but complete the validation';
          } else {
            this.outcome = 'Error: ' + (res as any).error;
          }
        },
        error: (err) => {
          this.outcome = 'Error: ' + err.message;
          
        },
        complete : () => {
          //this.outcome = 'Email sent, but complete the validation';
          this._snackBar.open('Message archived');
        },
      });
  }

}
