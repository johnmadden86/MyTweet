package wit.mytweet.model;

import org.json.JSONException;
import org.json.JSONObject;

import static wit.mytweet.model.Tweet.unsignedLong;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    public String email;
    public String password;

    private static final String JSON_ID = "id";
    private static final String JSON_FIRSTNAME = "firstName";
    private static final String JSON_LASTNAME = "lastName";
    private static final String JSON_EMAIL = "email";
    private static final String JSON_PASSWORD = "password";

    public User(String firstName, String lastName, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        this.id = unsignedLong();
    }

    User(JSONObject json) throws JSONException {
        id = json.getLong(JSON_ID);
        firstName = json.getString(JSON_FIRSTNAME);
        lastName = json.getString(JSON_LASTNAME);
        email = json.getString(JSON_EMAIL);
        password = json.getString(JSON_PASSWORD);
    }

    JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, id);
        json.put(JSON_FIRSTNAME, firstName);
        json.put(JSON_LASTNAME, lastName);
        json.put(JSON_EMAIL, email);
        json.put(JSON_PASSWORD, password);
        return json;
    }


    private String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
