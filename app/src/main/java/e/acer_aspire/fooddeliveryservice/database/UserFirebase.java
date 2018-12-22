package e.acer_aspire.fooddeliveryservice.database;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserFirebase {

    private String id;
    private String email;
    private int type; //0 for admin; 1 for user
    private Map created_at;

    public UserFirebase(String id, String email, int type, Map created_at) {
        this.id = id;
        this.email = email;
        this.type = type;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Map<String, String> created_at) {
        this.created_at = created_at;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("email", email);
        result.put("type", type);
        result.put("created_at", created_at);
        return result;
    }
}
