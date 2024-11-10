package classes;

import entities.User;

public class ManageUserInstance {
    
    private static User instance;

    public static void setUserInstance(User user) {
        if(instance == null) {
            instance = user;
        }
    }
    public static User getUserInstance() {
        return instance;
    }
}
