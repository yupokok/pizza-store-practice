package vttp2023.assessment.csf.orderbackend.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2023.assessment.csf.orderbackend.models.OrderSummary;
import vttp2023.assessment.csf.orderbackend.models.Pizza;
import vttp2023.assessment.csf.orderbackend.services.PricingService;

@Repository
public class PizzaRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private PricingService pricingService;

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
		

		System.out.printf("Saving Pizza,,,", doc);
		return doc.getObjectId("_id").toString();
	}

	

	// TODO Task 7
	// Write the native MongoDB statement in the commend below. Marks will be 
	// give for the native MongoDB statement
	/*
	 * db.pizza.find({ _id: ObjectId('660a2cfa88b7e6dd826d1ef9')})
	 * 
	 */

public Float calculateAmountOfPizza(String objectId) {

	Pizza pizza = mongoTemplate.findById(objectId, Pizza.class);
	if(pizza==null){
		
		return null;
	}  
	
	Float sizeCost = pricingService.size(pizza.getSize());
	Float sauceCost = pricingService.sauce(pizza.getSauce());
	Float crustCost = pizza.isThickCrust() ? pricingService.thickCrust() : pricingService.thinCrust();
	Float toppingsCost = 0f;

	for (String topping : pizza.getToppings()) {
		toppingsCost += pricingService.topping(topping);		
}
Float totalCost = sizeCost + sauceCost + crustCost + toppingsCost;
		System.out.printf("PIZZA COSTS: %f",totalCost);
        return totalCost;
}
}