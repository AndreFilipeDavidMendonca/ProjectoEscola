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

  onDeleteEmployee(employeeId : number) {
    this.alertService.clear();
    let alert = confirm('Tem a certeza que deseja eliminar o colaborador ' + name + '?');
    if (alert) {
      this.employeesService.deleteEmployee(employeeId).subscribe(data => {
        this.alertService.success('Colaborador eliminado com sucesso!', true);
        setTimeout(() => { this.alertService.clear(); }, 2000);
        this.fetchEmployees();
      },
        error => {
          this.alertService.error(error);
          this.fetchEmployees();
        });
    } 
  }

  ngOnInit() {
    this.fetchEmployees();
  }

}



  



