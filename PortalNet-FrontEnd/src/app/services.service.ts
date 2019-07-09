import { Client } from './client.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Service } from './service.model';


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

@Injectable({
    providedIn: 'root'
})

export class ServicesService {
      
    constructor(private http: HttpClient) { }



API = 'http://localhost:8080';

  getAll() {
    return this.http.get<Service[]>(this.API + '/servicesTable')
  }

  addService(service: string) {
    return this.http.post(this.API + '/servicesTable', service, httpOptions);
  }

  deleteService(serviceId: number): Observable<{}> {
    return this.http.delete(this.API + '/servicesTable/' + serviceId);
  }
}

