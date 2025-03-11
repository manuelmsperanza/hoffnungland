import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { ChatComponent } from './chat/chat.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatGridListModule} from '@angular/material/grid-list';

// Import the AuthService type from the SDK
import { AuthService } from '@auth0/auth0-angular';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ContactFormComponent, MatGridListModule, MatToolbarModule, MatButtonModule, MatIconModule, ChatComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'hoffnungland';

  constructor(public auth: AuthService) {}
}
