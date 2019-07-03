import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/employee.model';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { first } from 'rxjs/operators';
import { EmployeesService } from 'src/app/employees.service';

@Component({
  selector: 'app-employees-table',
  templateUrl: './employees-table.component.html',
  styleUrls: ['./employees-table.component.css']
})
export class EmployeesTableComponent implements OnInit {
  employees: Employee[] = [];
  
  constructor(private employeesService: EmployeesService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }

  fetchEmployees() {
    this.employeesService.getAll().pipe(first()).subscribe(employees => {
      this.employees = employees;
     });

  }

  ngOnInit() {
    this.fetchEmployees();
  }

}



  



