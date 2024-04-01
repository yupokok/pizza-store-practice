package vttp2023.assessment.csf.orderbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.models.OrderSummary;
import vttp2023.assessment.csf.orderbackend.repositories.OrderRepository;
import vttp2023.assessment.csf.orderbackend.repositories.PizzaRepository;

@Service
public class OrderService {

	@Autowired
	private PizzaRepository pizzaRepo;
	@Autowired
	private OrderRepository orderRepo;

	// TODO: Task 6 Use this method to persist the Order
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown
	// exceptions
	public String createOrder(Order order) throws Exception {
		pizzaRepo.createPizza(order.getSize(), order.isThickCrust(), order.getSauce(), order.getToppings(),
				order.getComments());
		String orderId = orderRepo.createOrder(order);
		return orderId;
	}

	// TODO: Task 7 Use this method to get a list of all the orders
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown
	// exceptions
	public List<OrderSummary> getOrdersByEmail(String email) {

		List<OrderSummary> orderSummaries = orderRepo.getOrdersByEmail(email);

        List<String> pizzaIds = orderRepo.getPizzaIdsByEmail(email);
        
        // Update the amount field of each order summary with the calculated amount
        for (int i = 0; i < orderSummaries.size() && i < pizzaIds.size(); i++) {
            String pizzaId = pizzaIds.get(i);
            Float amount = pizzaRepo.calculateAmountOfPizza(pizzaId);
            orderSummaries.get(i).setAmount(amount);
        }

        return orderSummaries;
    }


}