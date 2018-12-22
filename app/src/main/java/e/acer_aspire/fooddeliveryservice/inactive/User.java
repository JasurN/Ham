package e.acer_aspire.fooddeliveryservice.inactive;

import com.google.firebase.auth.FirebaseAuth;

public class User {

    private int id;
    private String username;
    private String email;
    private int type;
    private int created_at;
    private int updated_at;

    private Profile profile;
    private Favourites favourites;
    private Reviews reviews;
    private Orders orders;

    public User(int id, String username, String email, int type, int created_at, int updated_at, Profile profile, Favourites favourites, Reviews reviews, Orders orders) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.profile = profile;
        this.favourites = favourites;
        this.reviews = reviews;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getType() {
        return type;
    }

    public int getCreatedAt() {
        return created_at;
    }

    public int getUpdatedAt() {
        return updated_at;
    }

    public Profile getProfile() {
        return profile;
    }

    public Favourites getFavourites() {
        return favourites;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public Orders getOrders() {
        return orders;
    }

    public void userSignOut() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
        }
    }
}
