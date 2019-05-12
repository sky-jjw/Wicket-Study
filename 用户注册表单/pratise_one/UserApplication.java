package com.pritice_one;

import org.apache.wicket.protocol.http.WebApplication;

public class UserApplication extends WebApplication {
    @Override
    public Class getHomePage() {
        return UserEditPage.class;
    }
}
