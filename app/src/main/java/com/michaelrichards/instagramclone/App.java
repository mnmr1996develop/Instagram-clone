package com.michaelrichards.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("5OJQWQSMnKPhcFZehOPDpIwinQ9W7wryWwwDWaAo")
                // if defined
                .clientKey("JRcim2vgKlE7q5BGFnNxrFhaaSmusEShZfU0mMVv")
                .server("https://parseapi.back4app.com/")
                .build());
    }
}
