package e.acer_aspire.fooddeliveryservice.inactive;

public class Reviews {

    private int user_id;
    private int meal_id;
    private float rating;
    private String review;

    private User user;
    private Meal meal;

    public Reviews(int user_id, int meal_id, float rating, String review, User user, Meal meal) {
        this.user_id = user_id;
        this.meal_id = meal_id;
        this.rating = rating;
        this.review = review;
        this.user = user;
        this.meal = meal;
    }

    public int getUserId() {
        return user_id;
    }

    public int getMealId() {
        return meal_id;
    }

    public float getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public User getUser() {
        return user;
    }

    public Meal getMeal() {
        return meal;
    }
}
