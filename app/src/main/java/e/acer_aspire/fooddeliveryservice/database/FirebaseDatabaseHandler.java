package e.acer_aspire.fooddeliveryservice.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDatabaseHandler {
    private DatabaseReference mDatabase;

    public FirebaseDatabaseHandler() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void signUpNewUser(String email, int type, String name, String address, String phoneNumber) {
        String user_id = mDatabase.child("users").push().getKey();

        Map<String, Map<String, String>> created_at = new HashMap<>();
        created_at.put("timestamp", ServerValue.TIMESTAMP);
        UserFirebase userFirebase = new UserFirebase(user_id, email, type, created_at);
        ProfileFirebase profileFirebase = new ProfileFirebase(name, address, phoneNumber);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + user_id, userFirebase.toMap());
        childUpdates.put("/profiles/" + user_id, profileFirebase.toMap());

        mDatabase.updateChildren(childUpdates);
    }
}
