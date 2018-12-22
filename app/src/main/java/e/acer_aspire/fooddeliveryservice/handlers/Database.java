package e.acer_aspire.fooddeliveryservice.handlers;

import android.support.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private DatabaseReference mDatabase;
    private ArrayList<MealFirebase> mealsArrayList;

    public Database() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    /**
     * Creating new user in database. it inserts data to both user and profile places
     */
    public void signUpNewUser(String email, int type, String name, String address, String phoneNumber) {
        String user_id = mDatabase.child("users").push().getKey();

        //creating current timestamp for new user
        Map<String, Object> created_at = new HashMap<>();
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

    /**
     * Making order from user and sending it to database
     */

    public void makeOrder(String meal_id, String user_id,
                          float amount, String destination_address) {
        String order_id = mDatabase.child("orders").push().getKey();

        //creating current timestamp for new user
        Map<String, Object> created_at = new HashMap<>();
        created_at.put("timestamp", ServerValue.TIMESTAMP);

        OrderFirebase orderFirebase = new OrderFirebase(order_id, meal_id, user_id,
                amount, destination_address, 0, created_at);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/orders/" + order_id, orderFirebase.toMap());

        mDatabase.updateChildren(childUpdates);
    }

    /**
        Retrieving all meals from database to show it to user
        @return ArrayList with MealFirebase. Further its elements can be accessed with getters
     */
    public ArrayList<MealFirebase> getAllMeals() {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("meals");

        mealsArrayList = new ArrayList<>();

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot mealsDataSnapshot : dataSnapshot.getChildren()) {
                    MealFirebase note = mealsDataSnapshot.getValue(MealFirebase.class);
                    mealsArrayList.add(note);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return mealsArrayList;
    }
}
