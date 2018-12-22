package e.acer_aspire.fooddeliveryservice.handlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import e.acer_aspire.fooddeliveryservice.models.Meal;
import e.acer_aspire.fooddeliveryservice.models.Order;
import e.acer_aspire.fooddeliveryservice.models.Profile;
import e.acer_aspire.fooddeliveryservice.models.User;

public class Database {
    private DatabaseReference mDatabase;
    private ArrayList<Meal> mealsArrayList;
    private ArrayList<Order> ordersArrayList;

    public Database() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    /**
     * Creating new user in database. it inserts data to both user and profile places
     *
     * @in new user params
     */
    public void signUpNewUser(String email, int type, String name, String address, String phoneNumber) {
        String user_id = mDatabase.child("users").push().getKey();

        //creating current timestamp for new user
        Map<String, Object> created_at = new HashMap<>();
        created_at.put("timestamp", ServerValue.TIMESTAMP);


        User user = new User(user_id, email, type, created_at);
        Profile profile = new Profile(name, address, phoneNumber);

        //simultaneous database insert one user for two places
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + user_id, user.toMap());
        childUpdates.put("/profiles/" + user_id, profile.toMap());

        mDatabase.updateChildren(childUpdates);
    }

    /**
     * Inserting new meals to FireBase
     *
     * @in meal params
     */

    public void insertNewMeal(String name, String description, String ingredients,
                              float price) {
        String meal_id = mDatabase.child("meals").push().getKey();

        Meal meal = new Meal(meal_id, name, description,
                ingredients, price);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/meals/" + meal_id, meal.toMap());

        mDatabase.updateChildren(childUpdates);

    }

    /**
     * Making order from user and sending it to database
     *
     * @in order params
     */

    public void makeOrder(String meal_id, String user_id,
                          float amount, String destination_address) {
        String order_id = mDatabase.child("orders").push().getKey();

        //creating current timestamp for new usercode
        Map<String, Object> created_at = new HashMap<>();
        created_at.put("timestamp", ServerValue.TIMESTAMP);

        Order order = new Order(order_id, meal_id, user_id,
                amount, destination_address, 0, created_at);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/orders/" + order_id, order.toMap());

        mDatabase.updateChildren(childUpdates);
    }

    /**
     * Retrieving all meals from database to show it to user
     *
     * @return ArrayList with Meal class. Further its elements can be accessed with getters
     */
    public ArrayList<Meal> getAllMeals() {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("meals");

        mealsArrayList = new ArrayList<>();

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot mealsDataSnapshot : dataSnapshot.getChildren()) {
                    Meal note = mealsDataSnapshot.getValue(Meal.class);
                    mealsArrayList.add(note);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return mealsArrayList;
    }

    /**
     * Retrieve Orders with given status
     *
     * @return ArrayList with Order class.
     * @in Status for order see  Defined_Values.java in /helper package
     */
    public ArrayList<Order> getOrderByStatus(int status) {
        ordersArrayList = new ArrayList<>();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Query query = dbRef.child("orders").orderByChild("status").equalTo(status); //query for status

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot orderDataSnapshot : dataSnapshot.getChildren()) {
                    Order tempOrder = orderDataSnapshot.getValue(Order.class);
                    Log.d("ORDER INFO", tempOrder.getMeal_id());
                    ordersArrayList.add(tempOrder);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return ordersArrayList;
    }
}
