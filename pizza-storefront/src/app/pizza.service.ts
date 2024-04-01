import { inject } from "@angular/core";
import {Order, OrderSummary} from "./models";
import {HttpClient} from "@angular/common/http";
import { lastValueFrom } from "rxjs";

export class PizzaService {

  constructor() { }

  http = inject(HttpClient)

  // TODO Task 3 - Do not change the parameter of this method
  // The method may return any type
  createOrder(order: Order) : Promise<any> {
    console.log("Creating order>>>", order)
    return lastValueFrom(this.http.post<any>('/api/order', order))
  }

  // TODO Task 3 - You are free to add addtional parameters to this method
  // Do not change the return type
  getOrders(email: string): Promise<OrderSummary[]> {
    this.http.get<OrderSummary[]>(`/api/order/${email}/all`)
	  return Promise.resolve<OrderSummary[]>([])
  }

}
