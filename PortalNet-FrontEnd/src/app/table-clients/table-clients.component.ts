import { Component, OnInit, Input, Output} from '@angular/core';
import { Client } from '../client.model';
import { ClientService } from '../client.service';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../alert.service';
import { ErrorService } from "../error.service";


const options = {
    fieldSeparator: ',',
    quoteStrings: '"',
    decimalSeparator: '.',
    showLabels: true,
    showTitle: true,
    title: 'Users CSV',
    useBom: true,
    useKeysAsHeaders: true,
};

@Component({
    selector: 'app-table-clients',
    templateUrl: './table-clients.component.html',
    styleUrls: ['./table-clients.component.css']
})

export class TableClientsComponent implements OnInit {
@Input() clientId: number;
// isLoading = true;
currenClient: Client;

searchText: string;


filter = { fraudulent: false, status: false};

clients: Client [] = []; 
filteredClients: Client[] = [];
notFilteredClients: Client[] = []; 

  filterChange() {
    this.filteredClients = this.clients.filter(x => 
       (x.fraudulent === true && this.filter.fraudulent)
       || (x.status === true && this.filter.status)
    );

    this.notFilteredClients = this.clients.filter(x => 
      (x.fraudulent === false && this.filter.fraudulent)
      || (x.status === false && this.filter.status)
   );

  }


// error = '';




constructor(private clientService: ClientService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }


fetchClients() {
    this.clientService.getAll().pipe(first()).subscribe(clients => {
      this.clients = clients;
     });
  }

  ngOnInit() {
    this.fetchClients();
  }


}
