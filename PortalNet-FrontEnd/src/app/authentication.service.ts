import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Client } from './client.model';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<Client>;
    public currentUser: Observable<Client>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<Client>(JSON.parse(localStorage.getItem('currentClient')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): Client {
        return this.currentUserSubject.value;
    }

    login(email: string, password: string) {
        return this.http.post<any>(`${config.apiUrl}/home/authenticate`, { email, password })
            .pipe(map(client => {
                // login successful if there's a jwt token in the response
                if (client && client.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentClient', JSON.stringify(client));
                    this.currentUserSubject.next(client);
                }

                return client;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentClient');
        this.currentUserSubject.next(null);
    }
}