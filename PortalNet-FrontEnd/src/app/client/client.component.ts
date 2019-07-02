import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Client } from '../client.model';


@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  dataToChange: string;
  placeHolder: string;
  nameToChange: string;
  
  clients: Client [] = [
    new Client('André Mendonça', 123455, 'Rua Francisco',  'Algés', '1495-062', '208786333', '18/01/1989', 'andredmendonca@gmail.com', 913471637, 'male', '25/06/2019', '25/06/2020',
    1, '50€', 'Pacote 4 Serviços', true, false),
    
 ];
  
  constructor(private modalService: NgbModal) {}

  openBackDropCustomClass(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
    
  }

  openModalAdress(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'adress';
    this.nameToChange = 'Morada';
    this.placeHolder = 'Introduza a nova Morada!'
  }
  openModalPostalCode(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'postCode';
    this.nameToChange = 'Código-Postal';
    this.placeHolder = 'Introduza o novo Código-Postal!'
  }
  openModalEmail(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'email';
    this.nameToChange = 'E-Mail';
    this.placeHolder = 'Introduza o novo E-Mail!'
  }
  openModalMobilePhone(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'mobilePhone';
    this.nameToChange = 'Telemóvel';
    this.placeHolder = 'Introduza o novo Telemóvel!'
  }

  openModalLocality(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'city';
    this.nameToChange = 'Localidade';
    this.placeHolder = 'Introduza a nova Localidade!'
  }
  openModalService(service) {
    this.modalService.open(service, { windowClass: 'dark-modal' });
    this.dataToChange = 'service';
    this.nameToChange = 'Serviço';
  }
  

  openModalStatus(status) {
    this.modalService.open(status, { windowClass: 'dark-modal' });
    this.dataToChange = 'status';
    this.dataToChange = 'Estado da conta';
    this.placeHolder = 'Desactivar conta!'
  }


  openModalAssociateService(ASModal) {
    this.modalService.open(ASModal, { windowClass: 'dark-modal' });
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

  

  ngOnInit() {
  }

  

}
