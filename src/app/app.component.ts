import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ContactFormComponent } from './contact-form/contact-form.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';

// Import the AuthService type from the SDK
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ContactFormComponent, MatToolbarModule, MatButtonModule, MatIconModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'hoffnungland';

  constructor(public auth: AuthService) {}
}
