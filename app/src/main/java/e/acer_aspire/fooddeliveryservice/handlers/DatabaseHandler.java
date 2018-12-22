package e.acer_aspire.fooddeliveryservice.handlers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {
    private DatabaseReference mDatabase;

    public DatabaseHandler() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void signUpNewUser(String email, int type, String name, String address, String phoneNumber) {
        String user_id = mDatabase.child("users").push().getKey();

        //creating current timestamp for new user
        Map<String, Map<String, String>> created_at = new HashMap<>();
        created_at.put("timestamp", ServerValue.TIMESTAMP);


        UserFirebase userFirebase = new UserFirebase(user_id, email, type, created_at);
        ProfileFirebase profileFirebase = new ProfileFirebase(name, address, phoneNumber);

        //simultaneous database insert one user for two places
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + user_id, userFirebase.toMap());
        childUpdates.put("/profiles/" + user_id, profileFirebase.toMap());

        mDatabase.updateChildren(childUpdates);
    }

    public void insertNewMeal(String name, String description, String ingredients,
                              float price) {
        String meal_id = mDatabase.child("meals").push().getKey();

        MealFirebase mealFirebase = new MealFirebase(meal_id, name, description,
                ingredients, price);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/meals/" + meal_id, mealFirebase.toMap());

        mDatabase.updateChildren(childUpdates);

    }

    public void makeOrder(String meal_id, String user_id,
                          float amount, String destination_address) {
        String order_id = mDatabase.child("orders").push().getKey();

        //creating current timestamp for new user
        Map<String, Map<String, String>> created_at = new HashMap<>();
        created_at.put("timestamp", ServerValue.TIMESTAMP);

        OrderFirebase orderFirebase = new OrderFirebase(order_id, meal_id, user_id,
                amount, destination_address, 0, created_at);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/orders/" + order_id, orderFirebase.toMap());

        mDatabase.updateChildren(childUpdates);
    }
}
