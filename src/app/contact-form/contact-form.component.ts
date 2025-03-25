import { Component, ChangeDetectionStrategy, inject, input, effect } from '@angular/core';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '@auth0/auth0-angular';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [ReactiveFormsModule, MatCardModule, MatInputModule, MatFormFieldModule, MatButtonModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class ContactFormComponent {
  emailValue = input<string>('')
  email = new FormControl('', [Validators.required, Validators.email]);
  message = new FormControl('');
  outcome = '';
  
  private _snackBar = inject(MatSnackBar);

  constructor(private http: HttpClient, private auth: AuthService) {

    effect(() => {
      this.email.setValue(this.emailValue());
    });

    this.email.valueChanges.subscribe((value) => {
      if (this.email.invalid) {
        this.outcome = 'Please enter a valid email address';
        this._snackBar.open(this.outcome);
      }
    });
  }

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
          this._snackBar.open(this.outcome);
        },
      });
  }



}
