package njupt.template;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class MailTemplateApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return MailTemplate.class;
    }
}
