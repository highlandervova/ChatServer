package user;

import data.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserHolder {
    private static final Map<String, User> registeredUsers = new ConcurrentHashMap<>();

    public static Map<String, User> getRegisteredUsers() {
        return registeredUsers;
    }
}
