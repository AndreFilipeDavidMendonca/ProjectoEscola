import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { first } from 'rxjs/operators';
import { AlertComponent } from '../../alerts/alert.component';
import { Employee } from '../../employee.model';
import { EmployeesService } from '../../employees.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee;
  submitted = false;
  EmployeeForm: FormGroup;
  employeeToJSON: string;
  isLoading = false;
  error = '';
  success = '';
  message: string;
  
  constructor(private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private alertService: AlertService,
    private employeesService: EmployeesService) { }
  
  
  
  onReset() {
    this.EmployeeForm.reset();
  }

  ngOnInit() {
    this.EmployeeForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      role: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  
  }


  onSubmit() {
      this.submitted = true;
      
      // reset alerts on submit
      this.alertService.clear();
      
      // stop here if form is invalid
      if (this.EmployeeForm.invalid) {
        return;
      }
      
      // user to JSON
      
        this.employeeToJSON = JSON.parse(JSON.stringify(this.EmployeeForm.value));
        this.employeesService.addEmployee(this.employeeToJSON)
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
        
      }
  

}




  

  






