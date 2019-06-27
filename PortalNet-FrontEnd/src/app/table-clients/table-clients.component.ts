import { Component, OnInit, Input} from '@angular/core';
import { Client } from '../client.model';
import { ClientService } from '../client.service';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../alert.service';
import { ErrorService } from "../error.service";


// const options = {
//   fieldSeparator: ',',
//   quoteStrings: '"',
//   decimalSeparator: '.',
//   showLabels: true,
//   showTitle: true,
//   title: 'Users CSV',
//   useBom: true,
//   useKeysAsHeaders: true,
// };

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


clients: Client [] = [
   new Client('André Mendonça', 123455, 'Algés', 'Rua Francisco', '1495-062', '208786333', '18/01/1989', 'andredmendonca@gmail.com', 913471637, 'male', '25/06/2019', '25/06/2020',
   1, '50€', 'Pacote 4 Serviços', true, false),
   new Client('André Mendonça', 123455, 'Algés', 'Rua Francisco', '1495-062', '208786333', '18/01/1989', 'andredmendonca@gmail.com', 913471637, 'male', '25/06/2019', '25/06/2020',
   1, '50€', 'Pacote 4 Serviços', true, false)
];


// private subscription: Subscription;

constructor(private clientService: ClientService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }

//    getClients(index: number) {
//     this.clientService.onClientSelected.next(index);
//     this.clientService.getAll().pipe(first()).subscribe(clients => {
//     this.clients = this.clients;
//     this.isLoading = false;
//     },
//     error => {
//       this.alertService.error(error);
//       this.isLoading = false;
//     });
// }
//   getClient() {
//   this.clientService.getById(this.id)
//       .pipe(first())
//       .subscribe(client => {
//       this.client = { ...client };
//       });
//     }

ngOnInit() {
//     this.route.paramMap.subscribe(params => {
//       this.id = +params.get('id');
//       this.getClient();

//     }
//     );
 }

//   onNewClient() {
//     this.router.navigate(['new'], { relativeTo: this.route });
//   }

//   onDeleteClient(id: number, fullName: string) {
//     this.alertService.clear();
//     this.isLoading = true;
//     let areYouSure = confirm('Are you sure you want to delete the client ' + fullName);
//     if (areYouSure) {
//       this.clientService.deleteClient(id).subscribe(data => {
//         this.alertService.success('User deleted successfully', true);
//         setTimeout(() => { this.alertService.clear(); }, 2000);
//         this.getClient();
//         this.isLoading = false;
//       },
//         error => {
//           this.alertService.error(error);
//           this.isLoading = false;
//         });
//     } else {
//       this.isLoading = false;
//       this.getClient();
//     }
//   }

 

}
