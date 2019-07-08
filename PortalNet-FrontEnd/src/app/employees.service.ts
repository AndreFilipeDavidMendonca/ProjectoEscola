import { Client } from './client.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ErrorService } from './error.service';
import { Service } from './service.model';
import { Employee } from './employee.model';


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

@Injectable({
    providedIn: 'root'
})

export class EmployeesService {
      
    constructor(private http: HttpClient) { }



API = 'http://localhost:8080';

  getAll() {
    return this.http.get<Employee[]>(this.API + '/employeesTable')
  }

  getById(employeeId: number) {
    return this.http.get<Employee>(this.API + '/client/' + employeeId);
  }

  addEmployee(employee: string) {
    return this.http.post(this.API + '/auth/signup', employee, httpOptions);
  }

  updateEmployee(employeeId: number, employee: string) {
    return this.http.put(this.API + '/employee/' + employeeId, employee);
  }

  deleteEmployee(employeeId: number): Observable<{}> {
    return this.http.delete(this.API + '/employee/' + employeeId);
  }
}

