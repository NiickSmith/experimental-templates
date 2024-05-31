package com.domain.smarthomesystem.data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataManager {

    private static final String FILE_PATH = "users.json";
    private JSONArray users;

    public UserDataManager() {
        this.loadUsers();
    }

    public boolean addUser(String username, String password) {
        if (userExists(username)) {
            return false;
        }
        try {
            JSONObject userObject = new JSONObject();
            userObject.put("username", username);
            userObject.put("password", password);
            users.put(userObject); // Use put instead of add
            saveUsers();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser(String username) {
        try {
            for (int i = 0; i < users.length(); i++) {
                JSONObject userObject = users.getJSONObject(i);
                if (userObject.getString("username").equals(username)) {
                    return new User(userObject.getString("username"), userObject.getString("password"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean userExists(String username) {
        return getUser(username) != null;
    }

    private void loadUsers() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                sb.append((char) i);
            }
            users = new JSONArray(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            users = new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            users = new JSONArray();
        }
    }

    private void saveUsers() {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(users.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }
}
