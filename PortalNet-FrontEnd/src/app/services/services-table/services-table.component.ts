import { Component, OnInit, Input } from '@angular/core';
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
  @Input() serviceID: number;
  services: Service[] = [];
  service: Service;
  serviceName: string;

  constructor(private servicesService: ServicesService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }


fetchServices() {
    this.servicesService.getAll().pipe(first()).subscribe(services => {
      this.services = services;
     });

  }

  onDeleteService(serviceID : number) {
    this.alertService.clear();
    let alert = confirm('Tem a certeza que deseja eliminar o serviço ' + name + '?');
    if (alert) {
      this.servicesService.deleteService(serviceID).subscribe(data => {
        this.alertService.success('Serviço eliminado com sucesso!', true);
        setTimeout(() => { this.alertService.clear(); }, 2000);
        this.fetchServices();
      },
        error => {
          this.alertService.error(error);
          this.fetchServices();
        });
    } 
  }

  ngOnInit() {
    this.fetchServices();
  }


}
