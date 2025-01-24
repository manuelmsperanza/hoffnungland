import { Component, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';


@Component({
  selector: 'app-callback-auth0',
  imports: [],
  templateUrl: './callback-auth0.component.html',
  styleUrl: './callback-auth0.component.css'
})
export class CallbackAuth0Component {
  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    this.auth.handleRedirectCallback().subscribe({
      next: () => {
        window.location.href = '/';
      },
      error: (err) => {
        console.error('Error handling redirect callback', err);
      }
    });
  }
}
