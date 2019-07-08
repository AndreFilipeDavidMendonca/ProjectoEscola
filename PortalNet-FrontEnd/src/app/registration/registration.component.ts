import { Component, ViewChild, OnInit} from '@angular/core';
import { NgForm } from '@angular/forms';
import { registerClient } from '../registerClient.model';
import { RegisterClientService } from '../register-client.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from '../client.service';
import { AlertService } from '../alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  genders = ['Male', 'Female'];
  submitted = false;
  newClientForm: FormGroup;
  clientToJSON: string;
  isLoading = false;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private clientService: ClientService,
    private alertService: AlertService) { }



  onReset() {

  }

  ngOnInit(){
    this.newClientForm = this.formBuilder.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      postalCode: ['', Validators.required],
      city: ['', Validators.required],
      password: ['', Validators.required],
      mobilePhone: ['', Validators.required],
      gender: ['', Validators.required],
      birthDate: ['', Validators.required],
      serviceName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  get f() {
    return this.newClientForm.controls;
  }

  get id() {
    return this.newClientForm.get('clientId');
  }
  get email() {
    return this.newClientForm.get('email');
  }

  get password() {
    return this.newClientForm.get('password');
  }

  onClear() {
    this.newClientForm.reset();
  }

  onSubmit() {

    this.newClientForm.value.password = this.password;
    console.log(this.newClientForm.value.password);
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.newClientForm.invalid) {
      return;
    }

    this.isLoading = true;
    // role array to string
    this.newClientForm.value.role = [this.newClientForm.value.role.toString()];
    // user to JSON
    this.clientToJSON = JSON.parse(JSON.stringify(this.newClientForm.value));
    this.clientService.addClient(this.clientToJSON)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('User added successfully', true);
          setTimeout(() => { this.router.navigate(['/users']); }, 1500);
        },
        error => {
          this.alertService.error(JSON.parse(JSON.stringify(error)).message);
          this.isLoading = false;
        });
  }
 
}
