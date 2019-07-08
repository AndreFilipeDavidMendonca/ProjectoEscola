import { Component, ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
import { registerClient } from '../registerClient.model';
import { RegisterClientService } from '../register-client.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  genders = ['Male', 'Female'];
  submitted = false;


  constructor( ) { }

 

  onSubmit(form: NgForm) {
    
    this.submitted = true;
    const value = form.value;
    const newClient = new registerClient(value.fullName, value.postCode, value.adress, value.email, value.password, 
                                          value.birthDate, value.gender, value.phoneNumber, value.service);

  }

  onReset() {

  }
 
}
