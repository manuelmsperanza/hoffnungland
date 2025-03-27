import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
//import { ContactFormComponent } from './contact-form/contact-form.component';
import { ChatComponent } from './chat/chat.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material/grid-list';
import { ProjectsComponent } from './projects/projects.component';

// Import the AuthService type from the SDK
import { AuthService } from '@auth0/auth0-angular';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatGridListModule, MatToolbarModule, MatButtonModule, MatIconModule, ChatComponent, ProjectsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title : string = 'Hoffnungland';
  userName : string | null = null; // updated type to allow null
  userEmailAddress : string | null = null; // updated type to allow null
  isAuthenticated : boolean = false;

  constructor(@Inject(DOCUMENT) public document: Document, public auth: AuthService) {
    
    this.auth.user$.subscribe(user => {
      //console.log(user)
      this.userName = user?.name ?? null;
      this.userEmailAddress = user?.email ?? null;
    });

    this.auth.getAccessTokenSilently().subscribe(token => {  
      //console.log('token ' + token);
    });

    this.auth.isAuthenticated$.subscribe(isAuthenticated => {
      this.isAuthenticated = isAuthenticated;
    });
  }
  
}
