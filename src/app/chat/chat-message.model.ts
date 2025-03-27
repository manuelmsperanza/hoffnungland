// chat-message.model.ts
export interface ChatMessage {
    text: string[];
    sender: 'user' | 'assistant'; // or you could use boolean isUser: boolean
    timestamp?: Date;
  }
  