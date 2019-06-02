package njupt.template;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.PackageResourceReference;


public class MailTemplatePanel extends Panel {
    private static final long serialVersionUID=1L;

    public MailTemplatePanel(String id, IModel<String> nameModel) {
        super(id);

        add(new Label("name", nameModel));

        CharSequence relativeUrl = urlFor(new PackageResourceReference(MailTemplate.class, "resource.txt"), null);
        //获取链接地址
        String href = getRequestCycle().getUrlRenderer().renderFullUrl(Url.parse(relativeUrl.toString()));

        ExternalLink downloadLink = new ExternalLink("downloadLink", href);

        add(downloadLink);
    }
}
