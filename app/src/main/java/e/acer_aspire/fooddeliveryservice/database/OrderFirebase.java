package e.acer_aspire.fooddeliveryservice.database;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class OrderFirebase {
    private String id;
    private String meal_id;
    private String user_id;
    private float amount;
    private String destination_address;
    private int status; //see Defined_Values.java in helper package for statuses
    private Map created_at;

    public OrderFirebase(String id, String meal_id, String user_id,
                         float amount, String destination_address,
                         int status, Map created_at) {
        this.id = id;
        this.meal_id = meal_id;
        this.user_id = user_id;
        this.amount = amount;
        this.created_at = created_at;
        this.destination_address = destination_address;
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(String meal_id) {
        this.meal_id = meal_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Map getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Map created_at) {
        this.created_at = created_at;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("meal_id", meal_id);
        result.put("user_id",user_id);
        result.put("amount",amount);
        result.put("destination_address", destination_address);
        result.put("status", status);
        result.put("created_at", created_at);
        return result;
    }
}
