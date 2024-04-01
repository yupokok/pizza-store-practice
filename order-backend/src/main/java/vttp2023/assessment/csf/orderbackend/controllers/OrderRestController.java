package vttp2023.assessment.csf.orderbackend.controllers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.repositories.PizzaRepository;
import vttp2023.assessment.csf.orderbackend.services.OrderService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path="/api")
public class OrderRestController {

	@Autowired
	private OrderService orderService;


	// TODO Task 6
	@PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postOrder(@RequestBody String payload) throws Exception {


		JsonReader jsonReader = Json.createReader(new StringReader(payload));
		JsonObject orderObject = jsonReader.readObject();

		System.out.printf("Order received and parsed in SpringBpoots: %s\n", orderObject);

		Order currentOrder = new Order();
		currentOrder.setName(orderObject.getString("name"));
		currentOrder.setEmail(orderObject.getString("email"));
		currentOrder.setSauce(orderObject.getString("sauce"));
		currentOrder.setSize(orderObject.getInt("size"));
		currentOrder.setThickCrust(orderObject.getBoolean("thickCrust"));
		currentOrder.setComments(orderObject.getString("comments"));

		JsonArray toppingsArray = orderObject.getJsonArray("toppings");
		List<String> toppingsList = new ArrayList<>();
		for (int i = 0; i < toppingsArray.size(); i++) {
			toppingsList.add(toppingsArray.getString(i));
		}
		currentOrder.setToppings(toppingsList);
		
		try {
            String orderId = orderService.createOrder(currentOrder);
            return ResponseEntity.status(200).body("{\"orderId\": \"" + orderId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
	}

	// TODO Task 7
	public ResponseEntity<String> getOrderAll() {

		return null;
	}

}
