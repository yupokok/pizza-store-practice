package vttp2023.assessment.csf.orderbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.models.OrderSummary;

@Service
public class OrderService {

	// TODO: Task 6 Use this method to persist the Order
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown exceptions
	public String createOrder(Order order) {

		// Return the order_id 
		return "abc123";
	}

	// TODO: Task 7 Use this method to get a list of all the orders
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown exceptions
	public List<OrderSummary> getOrdersByEmail(String email) {
		return List.of();
	}
}
