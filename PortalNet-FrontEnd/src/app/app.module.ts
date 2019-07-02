import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { TableClientsComponent } from './table-clients/table-clients.component';
import { ClientComponent } from './client/client.component';
import { AdministratorComponent } from './administrator/administrator.component';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ServicesTableComponent } from './services/services-table/services-table.component';
import { CreateServiceComponent } from './services/create-service/create-service.component';
import { CreateEmployeComponent } from './employes/create-employe/create-employe.component';
import { EmployeTableComponent } from './employes/employe-table/employe-table.component';
import { ClientService } from './client.service';



const appRoutes: Routes =  [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'client', component: ClientComponent},
  { path: 'registration', component: RegistrationComponent },
  { path: 'clientsTable', component: TableClientsComponent },
  { path: 'administrator', component: AdministratorComponent },
  { path: 'servicesTable', component: ServicesTableComponent },
  { path: 'createService', component: CreateServiceComponent },
  { path: 'createEmploye', component: CreateEmployeComponent },
  { path: 'employeTable', component: EmployeTableComponent }
  
   
];


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    TableClientsComponent,
    AdministratorComponent,
    ClientComponent,
    ServicesTableComponent,
    CreateServiceComponent,
    CreateEmployeComponent,
    EmployeTableComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    NgbModalModule.forRoot(),
    NgbModule
  ],
  
  providers: [NgbModalConfig, NgbModal, NgbModalModule, ClientService],
  bootstrap: [AppComponent, HomeComponent],
  exports: [ HomeComponent ]
})
export class AppModule { }