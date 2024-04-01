import { inject } from "@angular/core";
import {Order, OrderSummary} from "./models";
import {HttpClient} from "@angular/common/http";
import { firstValueFrom } from "rxjs";

export class PizzaService {

  constructor() { }

  http = inject(HttpClient)

  // TODO Task 3 - Do not change the parameter of this method
  // The method may return any type


  createOrder(order: Order) : Promise<any> {
    console.log("Creating order>>>", order)
    return firstValueFrom(this.http.post<any>('http://localhost:8080/api/order', order))
  }

  // TODO Task 3 - You are free to add addtional parameters to this method
  // Do not change the return type

  
  getOrders(email: string): Promise<OrderSummary[]> {
    this.http.get<OrderSummary[]>(`http://localhost:8080/api/order/${email}/all`)
	  return Promise.resolve<OrderSummary[]>([])
  }

}
