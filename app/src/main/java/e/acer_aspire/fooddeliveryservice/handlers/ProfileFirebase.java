package e.acer_aspire.fooddeliveryservice.handlers;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ProfileFirebase {
    private String name;
    private String address;
    private String phoneNumber;

    public ProfileFirebase(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("address", address);
        result.put("phoneNumber", phoneNumber);
        return result;
    }
}
