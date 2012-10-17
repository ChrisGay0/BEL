package com.cgsolutions.security.thread;

import java.util.Map;

import com.cgsolutions.security.domain.User;

/**
 * Returns the thread bound logged in user, returns null if user not bound
 */
@SuppressWarnings("unchecked")
public class LoggedInUser extends AbstractStaticThreadLocalDataFinder{
    public static User find() {    
        Map map = (Map)data.get();
        return (User)map.get("loggedInUser");
    }
    
    public static void setLoggedInUser(User loggedInUser) {
        Map map = (Map)data.get();
        map.put("loggedInUser", loggedInUser);
    }
}