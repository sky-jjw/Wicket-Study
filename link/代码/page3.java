package njupt.link;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.lang.reflect.Parameter;

public class page3  extends WebPage {
    //接收外部传来的参数
    public page3(PageParameters parameters){

        add(new Label("bookmarkparameter",
                parameters.get("bookmarkparameters").toOptionalString()));
    }

}
