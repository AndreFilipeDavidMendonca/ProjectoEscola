import { Client } from './client.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ErrorService } from './error.service';


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

@Injectable({
    providedIn: 'root'
})

export class ClientService {
    clientSelected = new Subject<Client[]>();
    onClientSelected = new Subject<number>();
    private clients: Client[];
    
    constructor(private http: HttpClient, private errorService: ErrorService) { }

getClients() {
    return this.clients.slice();
}

// addClient(client: Client) {
//     this.clients.push(client);
//     this.clientSelected.next(this.clients.slice());
// }

onClientAdded (clients: Client[]) {
    this.clients.push(...clients);
    this.clientSelected.next(this.clients.slice());
}

API = 'http://localhost:4200';

  getAll() {
    return this.http.get<Client[]>(this.API + '/clients')
  }

  getById(id: number) {
    return this.http.get<Client>(this.API + '/clients/' + id);
  }

  addClient(user: string) {
    return this.http.post(this.API + '/auth/signup', user, httpOptions);
  }

  updateClient(id: number, client: string) {
    return this.http.put(this.API + '/clients/' + id, client);
  }

  deleteClient(id: number): Observable<{}> {
    return this.http.delete(this.API + '/clients/' + id);
  }
}

