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
      
    constructor(private http: HttpClient) { }



API = 'http://localhost:8080';

  getAll() {
    return this.http.get<Client[]>(this.API + '/clientsTable')
  }

  getById(id: number) {
    return this.http.get<Client>(this.API + '/client/' + id);
  }

  addClient(user: string) {
    return this.http.post(this.API + '/auth/signup', user, httpOptions);
  }

  updateClient(id: number, client: string) {
    return this.http.put(this.API + '/client/' + id, client);
  }

  deleteClient(id: number): Observable<{}> {
    return this.http.delete(this.API + '/client/' + id);
  }
}

