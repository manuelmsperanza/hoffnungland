import { Component } from '@angular/core';
import { ChatMessage } from './chat-message.model';
import { FormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

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
    CommonModule
  ]
})
export class ChatComponent {
  messages: ChatMessage[] = [
    { text: 'Hello! How can I assist you today?', sender: 'assistant' }
    // Optionally preload with a greeting or blank if you prefer
  ];

  userMessage: string = '';

  constructor() {}

  sendMessage() {
    if (!this.userMessage.trim()) {
      return; // Don’t send empty messages
    }

    // Add the user’s message to the conversation
    this.messages.push({
      text: this.userMessage.trim(),
      sender: 'user',
      timestamp: new Date()
    });

    // Clear the input
    this.userMessage = '';

    // Here, you could call a backend service or any API to get the assistant's reply.
    // For now, let's just simulate a response:

    this.simulateAssistantReply();
  }

  simulateAssistantReply() {
    setTimeout(() => {
      // In a real-world scenario, you'd use an HTTP call here
      const replyText = 'Thanks for your message. This is a simulated response.';
      this.messages.push({
        text: replyText,
        sender: 'assistant',
        timestamp: new Date()
      });
    }, 1000);

    /*  // Example of how you might integrate with a backend
  this.chatService.getAssistantReply(this.userMessage).subscribe(response => {
    this.messages.push({
      text: response.answer,
      sender: 'assistant',
      timestamp: new Date()
    });
  });
  Where chatService is an Angular service that makes the HTTP call to your Node.js/Express TypeScript backend or any external API.  
  */
  }
}
