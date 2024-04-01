package vttp2023.assessment.csf.orderbackend.models;

public class SQLOrder {
    String name;
    String orderId;
    String email;
    String pizzaId;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPizzaId() {
        return pizzaId;
    }
    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }
}
