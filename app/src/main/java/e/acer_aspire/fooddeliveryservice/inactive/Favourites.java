package e.acer_aspire.fooddeliveryservice.inactive;

public class Favourites {
    private int user_id;
    private int meal_id;

    private Meal meal;
    private User user;

    public Favourites(int user_id, int meal_id) {
        this.user_id = user_id;
        this.meal_id = meal_id;
    }

    public int getUserId() {
        return user_id;
    }

    public int getMealId() {
        return meal_id;
    }

    public User getUser() {
        return user;
    }

    public Meal getMeal() {
        return meal;
    }
}
