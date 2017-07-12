package com.qgstudio.anywork;

import android.app.Application;

/**
 * Created by Yason on 2017/4/14.
 */

public class App extends Application {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
