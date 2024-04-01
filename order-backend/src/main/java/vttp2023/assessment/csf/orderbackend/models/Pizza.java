package vttp2023.assessment.csf.orderbackend.models;

import java.util.List;

public class Pizza {
    String _id;
    Integer size;
    boolean thickCrust;

    public Boolean isThickCrust() { return this.thickCrust; }
    
    public void setThickCrust(boolean thickCrust) {
        this.thickCrust = thickCrust;
    }
    String sauce;
    List<String> toppings;
    String comments;

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Boolean getThickCrust() {
        return thickCrust;
    }
    public void setThickCrust(Boolean thickCrust) {
        this.thickCrust = thickCrust;
    }
    public String getSauce() {
        return sauce;
    }
    public void setSauce(String sauce) {
        this.sauce = sauce;
    }
    public List<String> getToppings() {
        return toppings;
    }
    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
}
