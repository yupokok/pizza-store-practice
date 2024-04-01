import { Component, Input, OnInit, Output, inject } from '@angular/core';
import { Order } from '../models';
import { ActivatedRoute } from '@angular/router';
import { PizzaService } from '../pizza.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  constructor() { }

  route = inject(ActivatedRoute)
  pizzaSvc = inject(PizzaService)
  email!: string

  ngOnInit(): void {
    
    this.route.params.subscribe(params => {
      this.email = params['email'];})
      this.pizzaSvc.getOrders(this.email)
  }
  

}
