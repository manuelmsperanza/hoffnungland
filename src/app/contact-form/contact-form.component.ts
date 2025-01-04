import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css'
})

export class ContactFormComponent {
  email = new FormControl('');
  message = new FormControl('');
  outcome = '';

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
          this.outcome = 'Email sent!';
        },
        error: (err) => {
          this.outcome = 'Error: ' + err.message;
        },
        complete : () => {
          this.outcome = 'Email sent!, but complete';
        },
      });
  }

}
