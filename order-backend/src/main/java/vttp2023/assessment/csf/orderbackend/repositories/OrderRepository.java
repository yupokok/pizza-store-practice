package vttp2023.assessment.csf.orderbackend.repositories;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.assessment.csf.orderbackend.models.Order;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate SQLtemplate;
	@Autowired
	private PizzaRepository pizzaRepo;

	public static final String SQL_INSERT_ORDER_SUMMARY= """
			insert into orders (order_id, name, email, pizza_id)
				values (?,?,?,?)
			""";

	// TODO Task 6 - you are free to add parameters and change the return type
	// DO NOT CHANGE THE METHOD NAME
	public String createOrder(Order order) throws Exception {

		String orderId = UUID.randomUUID().toString().substring(0, 8);
		String pizzaId = pizzaRepo.createPizza(order.getSize(), order.isThickCrust(), order.getSauce(), order.getToppings(), order.getComments());
		int orderRows = SQLtemplate.update(SQL_INSERT_ORDER_SUMMARY, orderId, order.getName(), order.getEmail(), pizzaId);

		if (orderRows <= 0) throw new Exception("Order cannot be inserted");
		System.out.printf("Saving OrderSummary,,,", orderId);

		return orderId;

	}

	// TODO Task 7
	
}
