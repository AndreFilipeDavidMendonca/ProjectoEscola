import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Service } from 'src/app/service.model';
import { first } from 'rxjs/operators';
import { AlertService } from 'src/app/alert.service';
import { ServicesService } from 'src/app/services.service';

@Component({
  selector: 'app-services-table',
  templateUrl: './services-table.component.html',
  styleUrls: ['./services-table.component.css']
})
export class ServicesTableComponent implements OnInit {
  services: Service[] = [];

  constructor(private servicesService: ServicesService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }


fetchServices() {
    this.servicesService.getAll().pipe(first()).subscribe(services => {
      this.services = services;
     });

  }

  ngOnInit() {
    this.fetchServices();
  }


}
