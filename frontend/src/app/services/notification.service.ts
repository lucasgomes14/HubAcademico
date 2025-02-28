import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private client: Client;

  constructor() {
    this.client = new Client({
      brokerURL: 'ws://localhost:8080/ws-notifications',
      reconnectDelay: 5000
    });

    this.client.onConnect = () => {
      this.client.subscribe('/topic/notifications', message => {
        const notification = JSON.parse(message.body);
        console.log('Nova notificação:', notification);
        alert(`${notification.user} ${notification.message}: ${notification.post}`);
      });
    };

    this.client.activate();
  }
}
