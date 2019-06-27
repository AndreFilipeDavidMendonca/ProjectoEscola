import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Service } from 'src/app/service.model';

@Component({
  selector: 'app-services-table',
  templateUrl: './services-table.component.html',
  styleUrls: ['./services-table.component.css']
})
export class ServicesTableComponent implements OnInit {
  services: Service[] = [
    new Service(1, 'Pacote 4 Serviços', '100 MB/s', '100 canais', '3GB', 'Grátis para redes fixas', '50€', '3 anos', '27/06/2019', true)
  ];

  
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
  }

}
