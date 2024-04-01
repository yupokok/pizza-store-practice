package vttp2023.assessment.csf.orderbackend.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PizzaRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO Task 6 - you are free to add parameters and change the return type
	// DO NOT CHANGE THE METHOD NAME
	// Write the native MongoDB statement in the commend below. Marks will be 
	// give for the native MongoDB statement
	/*
	 * Native MongoDB statement here
	 * 
	 * db.pizza.insert(
	 * {
	 *		size:1,
	 *		thickCrust: true,
	 *		sauce: "salsa",
	 *		toppings: ["chicken", "seafood"],
	 *		comments: ""
	 *	}
	 *	)
	 * 
	 */

	public String createPizza(int size, boolean thickCrust, String sauce, List<String> toppings, String comments) {
		Document doc = new Document();
		doc.put("size", size);
		doc.put("thickCrust", thickCrust);
		doc.put("sauce", sauce);
		doc.put("toppings", toppings);
		doc.put("comments", comments);

		doc = mongoTemplate.insert(doc, "pizza");
		return doc.getObjectId("_id").toString();
	}

	

	// TODO Task 7
	// Write the native MongoDB statement in the commend below. Marks will be 
	// give for the native MongoDB statement
	/*
	 * Native MongoDB statement here
	 * 
	 */
}
