import { Component, OnInit,Input, EventEmitter, Output, Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Client } from '../client.model';
import { ClientService } from '../client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { AlertService } from '../alert.service';




@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  @Output() passEntry: EventEmitter<any> = new EventEmitter();
  @Input() public client: Client;
  @Input() clientId: number;
  dataToChange: string;
  placeHolder: string;
  nameToChange: string;
  clientToJSON: string;



  // submitted = false;
  
  
  constructor(private modalService: NgbModal, private clientService: ClientService, private router: Router, private route: ActivatedRoute, private alertService: AlertService) {}
  
  openBackDropCustomClass(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
    
  }

  openModalAddress(content) {
    console.log(this.client);
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'address';
    this.nameToChange = 'Morada';
    this.placeHolder = 'Introduza a nova Morada!'
  }
  openModalPostalCode(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'postalCode';
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
    this.nameToChange = 'Estado da conta';
    this.placeHolder = 'Desactivar conta!'
  }

  openModalPhone(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
    this.dataToChange = 'phone';
    this.nameToChange = 'Telefone';
    this.placeHolder = 'Introduza o novo número de telefone!'
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

 
  fetchClientById(){
    this.clientService.getById(this.clientId)
      .pipe(first())
      .subscribe(client => {
        this.client = client[0];
      });
  }

  ngOnInit() {
    this.route.paramMap.subscribe(data => {
    this.clientId = +data.get('clientId');
    this.fetchClientById();
    });   
  }
  
  saveChanges() {
    this.passEntry.emit(this.client);
    console.log(this.client);
  }

 

  // createForm() {
  //   this.ClientUpdateForm = this.formBuilder.group({
  //     name: ['', Validators.required],
  //     clientNumber: ['', Validators.required],
  //     nif: ['', Validators.required],
  //     address: ['', Validators.required],
  //     postalCode: ['', Validators.required],
  //     city: ['', Validators.required],
  //     mobilePhone: [''],
  //     phone: [''],
  //     gender: ['', Validators.required],
  //     birthDate: ['', Validators.required],
  //     serviceName: ['', Validators.required],
  //     email: ['', [Validators.required, Validators.email]],
  //     fraudulent: ['', Validators.required],
  //     status: ['', Validators.required],
  //     numberOfServices: ['', Validators.required],
  //     entryDate: ['', Validators.required],
  //     endContract: ['', Validators.required],
  //     monthlyPay: ['', Validators.required] 
  //   });
  //   this.updateForm();
  // }

  // updateForm() {
  //   this.ClientUpdateForm.setValue({
  //     name: this.client.name,
  //     nif: this.client.nif,
  //     clientNumber: this.client.clientNumber,
  //     address: this.client.address,
  //     city: this.client.city,
  //     email: this.client.email,
  //     postalCode: this.client.postalCode,
  //     fraudulent: this.client.fraudulent,
  //     status: this.client.status,
  //     mobilePhone: this.client.mobilePhone,
  //     phone: this.client.phone,
  //     serviceName: this.client.serviceName,
  //     gender: this.client.gender,
  //     birthDate: this.client.birthDate,
  //     entryDate: this.client.entryDate,
  //     endContract: this.client.endContract,
  //     numberOfServices: this.client.numberOfServices,
  //     monthlyPay: this.client.monthlyPay
  //   });
  // }
  
  onSubmit() {


    // this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    // if (this.ClientUpdateForm.invalid) {
    //   return;
    // }

    // user to JSON

      this.clientToJSON = JSON.parse(JSON.stringify(this.client));
      this.clientService.updateClient(this.clientId, this.clientToJSON)
        .pipe(first())
          .subscribe(
            success => {
              this.alertService.success(success.message);
              // setTimeout(() => { this.router.navigate(['/clientTable']); }, 1500);
            },
            error => {
              this.alertService.error(JSON.parse(JSON.stringify(error)));
      });
  }

 }
   
 

  


