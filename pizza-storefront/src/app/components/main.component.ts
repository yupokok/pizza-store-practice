import { Component, inject } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PizzaService } from '../pizza.service';
import { Router } from '@angular/router';
import { Order } from '../models';
import { findIndex } from 'rxjs';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PizzaToppings: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]
@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  


  builder = inject(FormBuilder)
  pizzaSvc = inject(PizzaService)
  router = inject(Router)

  pizzaForm!: FormGroup

  selectedToppings: string[] = []

  
	// TODO: Task 2

  ngOnInit(): void {
    this.pizzaForm = this.createPizzaForm()
    
}

createPizzaForm(): FormGroup {
  return this.pizzaForm = this.builder.group({
    name: this.builder.control<string>('',[Validators.required]),
    email: this.builder.control<string>('', [ Validators.required, Validators.email]),
    size: this.builder.control<string>(SIZES[1], [ Validators.required ]),
    base: this.builder.control<string>('', [ Validators.required ]),
    sauce: this.builder.control<string>('', [ Validators.required ]),
    toppings: [],
    comments: this.builder.control<string>(''),
  })
}

listOrders(){
  const email = this.pizzaForm.get("email")?.value
  console.log("THIS IS THE EMAIL:" , email)
  this.router.navigate(['/orders', email])
}

toppingsCheckbox(event: any) {
  const isChecked = event.target.checked;
  const toppingsValue = event.target.value;
  if (isChecked) {
      this.selectedToppings.push(toppingsValue);
  } else if (!isChecked) {
      const index = this.selectedToppings.indexOf(toppingsValue);
      if (index !== -1) {
          this.selectedToppings.splice(index, 1);
      }
  } else {
      event.target.checked = false;
  }
  console.log("Selected toppings:", this.selectedToppings);
}

  placeOrder(){
    const order: Order = {
      name: this.pizzaForm.get('name')?.value,
      email: this.pizzaForm.get('email')?.value,
      size: this.pizzaForm.get('size')?.value,
      thickCrust: (this.pizzaForm.get('base')?.value == "thick"),
      sauce: this.pizzaForm.get('sauce')?.value,
      toppings: this.selectedToppings,
      comments: this.pizzaForm.get('comments')?.value
    }
    console.info('PLACING THIS ORDER: ', order)
    this.pizzaSvc.createOrder(order)
    return order
  }

  pizzaSize = SIZES[0]

  constructor() { }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

}
