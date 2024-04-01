package vttp2023.assessment.csf.orderbackend.repositories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.models.OrderSummary;
import vttp2023.assessment.csf.orderbackend.services.PricingService;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate SQLtemplate;
	@Autowired
	private PizzaRepository pizzaRepo;
	@Autowired
	private PricingService pricingService;

	public static final String SQL_INSERT_ORDER_SUMMARY = """
			insert into orders (order_id, name, email, pizza_id)
				values (?,?,?,?)
			""";

	public static final String SQL_GET_ORDERSUMS_BY_EMAIL = """
			select * from orders where email = ?
				""";

	// TODO Task 6 - you are free to add parameters and change the return type
	// DO NOT CHANGE THE METHOD NAME
	public String createOrder(Order order) throws Exception {

		String orderId = UUID.randomUUID().toString().substring(0, 8);
		String pizzaId = pizzaRepo.createPizza(order.getSize(), order.isThickCrust(), order.getSauce(),
				order.getToppings(), order.getComments());
		int orderRows = SQLtemplate.update(SQL_INSERT_ORDER_SUMMARY, orderId, order.getName(), order.getEmail(),
				pizzaId);

		if (orderRows <= 0)
			throw new Exception("Order cannot be inserted");
		System.out.printf("Saving OrderSummary,,,", orderId);

		return orderId;

	}

	// TODO Task 7
	public List<String> getPizzaIdsByEmail(String email) {
		String sql = "SELECT pizza_id FROM your_table_name WHERE email = ?";
		SqlRowSet rs = SQLtemplate.queryForRowSet(sql, email);

		List<String> pizzaIds = new ArrayList<>();
		while (rs.next()) {
			pizzaIds.add(rs.getString("pizza_id"));
		}
		System.out.println(pizzaIds);
		return pizzaIds;
		
	}

	public List<OrderSummary> getOrdersByEmail(String email) {

		SqlRowSet rs = SQLtemplate.queryForRowSet(SQL_GET_ORDERSUMS_BY_EMAIL, email);

		List<OrderSummary> orderSummaries = new ArrayList<>();
		while (rs.next()) {
			OrderSummary orderSummary = new OrderSummary();
			orderSummary.setName(rs.getString("name"));
			orderSummary.setOrderId(rs.getInt("orderId"));
			orderSummary.setEmail(rs.getString("email"));
			orderSummary.setAmount(0f);
			orderSummaries.add(orderSummary);
		}

		return orderSummaries;
	}
}
