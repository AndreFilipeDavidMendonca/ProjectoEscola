import { Component, ViewChild, OnInit} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from '../client.service';
import { AlertService } from '../alert.service';
import { first } from 'rxjs/operators';
import { AlertComponent } from '../alerts/alert.component';
import { Client } from '../client.model';
import { AuthenticationService } from '../authentication.service';
import { ErrorInterceptor } from '../interceptors/error.interceptor';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  submitted = false;
  ClientForm: FormGroup;
  clientToJSON: string;
  isLoading = false;
  error = '';
  client: Client;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private clientService: ClientService,
    private alertService: AlertService) { }



  onReset() {
    this.ClientForm.reset();
  }

  ngOnInit(){
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
            data => {
              this.alertService.success('User added successfully', true);
              // setTimeout(() => { this.router.navigate(['/client/', this.client.clientId]); }, 1500);
            },
            error => {
              this.alertService.error(JSON.parse(JSON.stringify(error)));
              this.isLoading = false;
      });
      console.log(this.ClientForm.value);
      
  }


 
}
