import { Component, OnInit } from '@angular/core';
import { Client } from './client.model';
import { AuthenticationService } from './authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  currentUser: Client;
  user: Client;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/home']);
  }

  isLoggedIn() {
    if (!localStorage.getItem('currentUser')) {
      return false;
    }
    this.currentUser = this.authenticationService.currentUserValue;
    return true;
  }

  isAdmin() {
    for (let authority of this.currentUser.authorities) {
      if (authority.authority === 'ROLE_ADMIN') {
        return true;
      }
    }
    return false;
  }
}