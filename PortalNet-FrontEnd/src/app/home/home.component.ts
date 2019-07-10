import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../authentication.service';
import { ErrorInterceptor } from '../interceptors/error.interceptor';
import { ServicesService } from '../services.service';
import { AlertService } from '../alert.service';
import { Service } from '../service.model';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
 
})

export class HomeComponent implements OnInit {
  services: Service[] = [];

  images = [
    {
      image: "../assets/img/servicesBackground0.png",
    },
    {
      image: "../assets/img/servicesBackground1.png",
    },
    {
      image: "../assets/img/servicesBackground2.png",
    },
    {
      image: "../assets/img/servicesBackground3.png",
    },
    {
      image: "../assets/img/servicesBackground4.png",
    }

  ];

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';


    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private servicesService: ServicesService,
        private alertService: AlertService
    ) { }

    
    fetchServices() {
      this.servicesService.getAll().pipe(first()).subscribe(services => {
        this.services = services;
       });
  
    }
  
  ngOnInit() {
    this.fetchServices();
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
  });


  // reset login status
  this.authenticationService.logout();

  // get return url from route parameters or default to '/'
  this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.loginForm.invalid) {
          return;
      }

      this.loading = true;
      this.authenticationService.login(this.f.email.value, this.f.password.value)
          .pipe(first())
          .subscribe(
              data => {
                  this.router.navigate([this.returnUrl]);
              },
              error => {
                  this.error = error;
                  this.loading = false;
              });
  }
}




  




 


    


   
