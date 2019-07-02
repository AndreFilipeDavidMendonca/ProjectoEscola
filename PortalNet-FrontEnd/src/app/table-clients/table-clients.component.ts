import { Component, OnInit, Input} from '@angular/core';
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
// @Input() id: number;
// clients: Client[] = [];
// isLoading = true;
// currenClient: Client;
// searchString: string;
// error = '';


clients: Client [] = [];


// private subscription: Subscription;

constructor(private clientService: ClientService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }


fetchUsers() {
    this.clientService.getAll().pipe(first()).subscribe(clients => {
      this.clients = clients;
     });

  }

  ngOnInit() {
    this.fetchUsers();
  }


}
