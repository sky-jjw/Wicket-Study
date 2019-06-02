package njupt.template;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;

public class TemplateBasedOnPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public TemplateBasedOnPage(final PageParameters pageParameters){
        super(pageParameters);

        add(new Label("name", pageParameters.get("name").toString("Unknown")));

        CharSequence relativeUrl = urlFor(new PackageResourceReference(MailTemplate.class, "resource.txt"), null);

        String href = getRequestCycle().getUrlRenderer().renderFullUrl(
                Url.parse(relativeUrl.toString()));
        ExternalLink downloadLink = new ExternalLink("downloadLink", href);
        add(downloadLink);

    }
}
