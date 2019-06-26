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
import { CreateServiceComponent } from './create-service/create-service.component';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



const appRoutes: Routes =  [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'client', component: ClientComponent},
  { path: 'registration', component: RegistrationComponent },
  { path: 'clientTable', component: TableClientsComponent },
  { path: 'administrator', component: AdministratorComponent },
  { path: 'createService', component: CreateServiceComponent }
  
   
];


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    TableClientsComponent,
    AdministratorComponent,
    ClientComponent,
    CreateServiceComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    NgbModalModule.forRoot(),
    NgbModule
  ],
  
  providers: [NgbModalConfig, NgbModal, NgbModalModule],
  bootstrap: [AppComponent, HomeComponent],
  exports: [ HomeComponent ]
})
export class AppModule { }
