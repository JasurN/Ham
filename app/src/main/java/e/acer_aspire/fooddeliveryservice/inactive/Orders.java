package e.acer_aspire.fooddeliveryservice.inactive;

public class Orders {
    private int id;
    private int user_id;
    private int meal_id;
    private float amount;
    private String destination;
    private int time;
    private int arrived_at;
    private int status;

    public Orders(int id, int user_id, int meal_id, float amount, String destination, int time, int arrived_at, int status) {
        this.id = id;
        this.user_id = user_id;
        this.meal_id = meal_id;
        this.amount = amount;
        this.destination = destination;
        this.time = time;
        this.arrived_at = arrived_at;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public float getAmount() {
        return amount;
    }

    public String getDestination() {
        return destination;
    }

    public int getTime() {
        return time;
    }

    public int getArrived_at() {
        return arrived_at;
    }

    public int getStatus() {
        return status;
    }
}
