package e.acer_aspire.fooddeliveryservice.models;

public class Orders {
    private int id;
    private int user_id;
    private int meal_id;
    private float amount;
    private String destination;
    private String time;
    private int arrived_at;
    private int status;

    private User user;
    private Meals meal;

    public Orders(int id, int user_id, int meal_id, float amount, String destination, String time, int arrived_at, int status, User user, Meals meal) {
        this.id = id;
        this.user_id = user_id;
        this.meal_id = meal_id;
        this.amount = amount;
        this.destination = destination;
        this.time = time;
        this.arrived_at = arrived_at;
        this.status = status;
        this.user = user;
        this.meal = meal;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public int getMealId() {
        return meal_id;
    }

    public float getAmount() {
        return amount;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    public int getArrivedAt() {
        return arrived_at;
    }

    public int getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Meals getMeal() {
        return meal;
    }
}
