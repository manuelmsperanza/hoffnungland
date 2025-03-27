import { Component } from '@angular/core';
import { ChatMessage } from './chat-message.model';
import { FormsModule } from '@angular/forms';
import { MatCardModule}  from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '@auth0/auth0-angular';
import { switchMap } from 'rxjs/operators';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  standalone: true,
  imports: [
    FormsModule,
    MatCardModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatProgressSpinnerModule
  ]
})
export class ChatComponent {
  messages: ChatMessage[] = [];

  userMessage: string = '';
  isWaitingForResponse : boolean = false;

  constructor(private http: HttpClient, private auth: AuthService) {}

  /*testLogin() {
    

    this.auth.getAccessTokenSilently().pipe(
      switchMap(token => {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`).set('X-User-Language', navigator.language);
        return this.http.get('/api/private', { headers });
      })
    ).subscribe(response => {
      console.log('Backend response:', response);
    });
  }*/

    ngOnInit() {
      this.isWaitingForResponse = true;
      this.auth.getAccessTokenSilently().pipe(
        switchMap(token => {
          const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`).set('X-User-Language', navigator.language);
          return this.http.get('/api/retrieveThread', { headers });
        })
      ).subscribe(response => {
        //console.log('Backend response:', response);
        this.isWaitingForResponse = false;
        const replyMessages : Object[] = (response as any) ;

        for (const message of replyMessages) {
          this.messages.push({
            text: (message as any).content,
            sender: (message as any).role,
            timestamp: new Date()
          });
        }
    });
  }

  sendMessage() {
    if (!this.userMessage.trim()) {
      return; // Don’t send empty messages
    }
    this.isWaitingForResponse = true;
    // Add the user’s message to the conversation
    this.messages.push({
      text: [this.userMessage.trim()],
      sender: 'user',
      timestamp: new Date()
    });
    let openAiUserMessage : string = this.userMessage.trim();
    this.auth.getAccessTokenSilently().pipe(
      switchMap(token => {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`).set('X-User-Language', navigator.language);
        return this.http.post('/api/enquiry', { userMessage: openAiUserMessage }, { headers });
      })
    ).subscribe(response => {
      //console.log('Backend response:', response);
      this.isWaitingForResponse = false;
      const replyText  =(response as any).content ;
      this.messages.push({
        text: replyText,
        sender: 'assistant',
        timestamp: new Date()
      });

    });

    // Clear the input
    this.userMessage = '';

    // Here, you could call a backend service or any API to get the assistant's reply.
    // For now, let's just simulate a response:

  }

}
