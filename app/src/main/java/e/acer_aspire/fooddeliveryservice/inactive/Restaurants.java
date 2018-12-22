package e.acer_aspire.fooddeliveryservice.inactive;

import java.util.ArrayList;

public class Restaurants {

    private int id;
    private String name;
    private String address;
    private String location;
    private float rating;

    private ArrayList<Meals> meals;

    public Restaurants(int id, String name, String address, String location, float rating, ArrayList<Meals> meals) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.location = location;
        this.rating = rating;
        this.meals = meals;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public float getRating() {
        return rating;
    }

    public ArrayList<Meals> getMeals() {
        return meals;
    }
}
