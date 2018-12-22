package e.acer_aspire.fooddeliveryservice.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Meal {
    private String id;
    private String name;
    private String description;
    private String ingredients;
    private float rating;
    private float price;

    public Meal() {

    }

    public Meal(String id, String name, String description, String ingredients, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Making model for adding to database as Object
     */
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("description", description);
        result.put("ingredients", ingredients);
        result.put("price", price);
        return result;
    }
}
