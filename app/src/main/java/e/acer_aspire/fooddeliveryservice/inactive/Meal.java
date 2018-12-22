package e.acer_aspire.fooddeliveryservice.inactive;

public class Meal {

    private int id;
    private String name;
    private String description;
    private String ingredients;
    private float price;
    private int restaurant_id;

    private Restaurants restaurant;
    private Reviews review;

    public Meal(int id, String name, String description, String ingredients, float price, int restaurant_id, Restaurants restaurant, Reviews review) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
        this.restaurant_id = restaurant_id;
        this.restaurant = restaurant;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public float getPrice() {
        return price;
    }

    public int getRestaurantId() {
        return restaurant_id;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public Reviews getReview() {
        return review;
    }

    public String getNamePrice() {
        return getName() + " - " + getPrice() + " sum";
    }
}
