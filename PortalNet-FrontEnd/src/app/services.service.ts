import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
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

  getById(serviceID: number) {
    return this.http.get<Service>(this.API + '/registration' + serviceID);
  }

  getByName(name: string) {
    return this.http.get<Service>(this.API + '/registration/' + name);
  }

  addService(service: string) {
    return this.http.post<any>(this.API + '/createService', service);
  }

  updateService(serviceID: number, service: string) {
    return this.http.put<any>(this.API + '/servicesTable/' + serviceID, service);
  }
}

