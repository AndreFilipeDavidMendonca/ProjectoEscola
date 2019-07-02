import { Component, OnInit } from '@angular/core';
import { Employe } from 'src/app/employe.model';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employe-table',
  templateUrl: './employe-table.component.html',
  styleUrls: ['./employe-table.component.css']
})
export class EmployeTableComponent implements OnInit {
  employes: Employe[] = [
    new Employe(1, 'André Mendonça', 'andredmendonca@gmail.com', 'Administrador')
  ];
  
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
  }

}
