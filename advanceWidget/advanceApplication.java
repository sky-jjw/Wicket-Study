package com.advanceWidget;

import org.apache.wicket.protocol.http.WebApplication;

public class advanceApplication extends WebApplication {
    @Override
    public Class getHomePage(){
      return DataViewPage.class;
    }
}
