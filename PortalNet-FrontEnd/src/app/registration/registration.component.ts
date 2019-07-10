import { Component, ViewChild, OnInit} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from '../client.service';
import { AlertService } from '../alert.service';
import { first } from 'rxjs/operators';
import { AlertComponent } from '../alerts/alert.component';
import { Client } from '../client.model';
import { Service } from '../service.model';
import { ServicesService } from '../services.service';



@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  services: Service[] = [];
  submitted = false;
  ClientForm: FormGroup;
  clientToJSON: string;
  isLoading = false;
  error = '';
  success = '';
  client: Client;
  message: string;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private clientService: ClientService,
    private alertService: AlertService,
    private servicesService: ServicesService) { }



  onReset() {
    this.ClientForm.reset();
  }

  fetchServices() {
    this.servicesService.getAll().pipe(first()).subscribe(services => {
      this.services = services;
     });

  }

  ngOnInit(){
    this.fetchServices();
    this.ClientForm = this.formBuilder.group({
      name: ['', Validators.required],
      nif: ['', Validators.required],
      address: ['', Validators.required],
      postalCode: ['', Validators.required],
      city: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      mobilePhone: ['', Validators.required],
      phone: ['', Validators.required],
      gender: ['', Validators.required],
      birthDate: ['', Validators.required],
      serviceName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  get name() {
    return this.ClientForm.get('name');
  }

  get nif() {
    return this.ClientForm.get('nif');
  }

  get email() {
    return this.ClientForm.get('email');
  }

  get password() {
    return this.ClientForm.get('password');
  }


  onSubmit() {

  
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.ClientForm.invalid) {
      return;
    }

    // user to JSON
    
      this.clientToJSON = JSON.parse(JSON.stringify(this.ClientForm.value));
      this.clientService.addClient(this.clientToJSON)
        .pipe(first())
          .subscribe(
            success => {
              this.alertService.success(success.message);
              console.log(success);
              // setTimeout(() => { this.router.navigate(['/clientTable']); }, 1500);
            },
            error => {
              this.alertService.error(JSON.parse(JSON.stringify(error)));
              this.isLoading = false;
      });
      console.log(this.ClientForm.value);
      
  }


 
}
