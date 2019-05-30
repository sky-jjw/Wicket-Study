package njupt.link;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.protocol.http.WebApplication;

public class LinkomaticApplication extends WebApplication {
    public Class<? extends Page> getHomePage(){
        return Home.class;
    }

//    @Override
//    protected void init() {
//        super.init();
//        mountPage("/home", Home.class);
//        getSharedResources().add("cancelButton", new DefaultButtonImageResource("Cancel"));
//    }
}
