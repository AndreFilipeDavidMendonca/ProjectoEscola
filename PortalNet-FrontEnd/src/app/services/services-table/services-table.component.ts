import { Component, OnInit, Input, Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Service } from 'src/app/service.model';
import { first } from 'rxjs/operators';
import { AlertService } from 'src/app/alert.service';
import { ServicesService } from 'src/app/services.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';



@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-services-table',
  templateUrl: './services-table.component.html',
  styleUrls: ['./services-table.component.css']
})
export class ServicesTableComponent implements OnInit {
  @Input() serviceID: number;
  @Input() public service: Service;
  services: Service[] = [];
  success = '';
  message: string;

  constructor(private servicesService: ServicesService, private modalService: NgbModal, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }


fetchServices() {
    this.servicesService.getAll().pipe(first()).subscribe(services => {
      this.services = services;
     });

  }

  
  openBackDropCustomClass(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

  openSm(content) {
    this.modalService.open(content, { size: 'sm' });
  }

  openLg(content) {
    this.modalService.open(content, { size: 'lg' });
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }

  openModalService(content, serviceID) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.serviceID = serviceID;
    console.log(this.service);
  }

  onUpdateService(serviceID : number, service: string) {
    console.log(service);
    this.alertService.clear();
    let alert = confirm('Tem a certeza que deseja desactiva o serviço?');
    if (alert) {
      this.servicesService.updateService(serviceID, service).subscribe(success => {
      this.alertService.success(success.message);
      // setTimeout(() => { this.alertService.clear(); }, 2000);
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
