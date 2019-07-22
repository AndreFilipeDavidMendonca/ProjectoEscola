import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Client } from './client.model';
import { map } from 'rxjs/operators';
// import * as jwt_decode from 'jwt-decode';
import { Globals } from './globals';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


  currentUser: Client;
  user: string;

  constructor(private http: HttpClient, private globals: Globals) {
    this.persistUser();
  }


  API = this.globals.API;

  public get currentUserValue(): Client {
    return this.currentUser;
  }

  login(email, password) {
    return this.http.post<any>(this.API + '/home', { email, password })
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        console.log(this.currentUser);
        this.persistUser();
        return user;
        }
      )
      );
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('currentUser');
    this.currentUser = null;
  }

//   recoverPassword(body: string) {
//     return this.http.post(this.API + '/auth/forgot-password', body);
//   }

//   getDecodedAccessToken(token: string): any {
//     try {
//       return jwt_decode(token);
//     } catch (Error) {
//       return null;
//     }
//   }

  persistUser() {
    this.user = localStorage.getItem('currentUser');
    this.currentUser = JSON.parse(this.user);
  }
}