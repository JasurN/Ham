package e.acer_aspire.fooddeliveryservice.inactive;

public class Profile {

    private int user_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private int birth_date;
    private String address;
    private String bio;
    private String phone;
    private String website;

    private User user;

    public Profile(int user_id, String first_name, String middle_name, String last_name, int birth_date, String address, String bio, String phone, String website, User user) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.address = address;
        this.bio = bio;
        this.phone = phone;
        this.website = website;
        this.user = user;
    }

    public int getUserId() {
        return user_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getMiddleName() {
        return middle_name;
    }

    public String getLastName() {
        return last_name;
    }

    public int getBirthDate() {
        return birth_date;
    }

    public String getAddress() {
        return address;
    }

    public String getBio() {
        return bio;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public User getUser() {
        return user;
    }
}
