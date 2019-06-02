package njupt.template;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;


import java.util.HashMap;
import java.util.Map;

public class MailTemplate extends WebPage {
    private static final long serialVersionUID=1L;

    private String name="";

    public MailTemplate(final PageParameters pageParameters) {
        super(pageParameters);


        //feedbackpanel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        //form
        final Form<Void> form = new Form<>("form");
        add(form);

        //name textField
        TextField<String> nameTextField = new TextField<>("name", new PropertyModel<String>(MailTemplate.this, "name"));
        nameTextField.setOutputMarkupId(true);
        form.add(nameTextField);

        //result MultiLineLabel
        final MultiLineLabel result = new MultiLineLabel("result", new Model<>());
        result.setOutputMarkupId(true);
        add(result);

        //ajax submit
        AjaxSubmitLink basedOnPageLink = new AjaxSubmitLink("pageBasedLink", form) {
            private static final long serialVersionUID = 1L;
            @Override
            protected void onSubmit(AjaxRequestTarget target) {

                PageParameters pageParameters1 = new PageParameters();
                pageParameters1.set("name", name);
                PageProvider pageProvider = new PageProvider(TemplateBasedOnPage.class, pageParameters1);
                CharSequence pageHtml = ComponentRenderer.renderPage(pageProvider);
                updateResult(result, pageHtml, target);
                target.add(feedbackPanel);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {

                target.add(feedbackPanel);
            }
        };

        AjaxSubmitLink basedOnPanelLink = new AjaxSubmitLink("panelBasedLink", form)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                CharSequence panelHtml = ComponentRenderer.renderComponent(new MailTemplatePanel("someId",
                        new PropertyModel<String>(MailTemplate.this, "name")));

                updateResult(result, panelHtml, target);
                target.add(feedbackPanel);
            }

            @Override
            protected void onError(AjaxRequestTarget target)
            {
                target.add(feedbackPanel);
            }
        };


        AjaxSubmitLink basedOnTextTemplateLink = new AjaxSubmitLink("textTemplateBasedLink", form)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                Map<String, Object> variables = new HashMap<>();
                variables.put("name", name);

                CharSequence relativeUrl = urlFor(new PackageResourceReference(MailTemplate.class,
                        "resource.txt"), null);
                String href = getRequestCycle().getUrlRenderer().renderFullUrl(
                        Url.parse(relativeUrl.toString()));
                variables.put("downloadLink", href);

                PackageTextTemplate template = new PackageTextTemplate(MailTemplate.class, "mail-template.tmpl");
                CharSequence templateHtml = template.asString(variables);
                updateResult(result, templateHtml, target);
                target.add(feedbackPanel);
            }

            @Override
            protected void onError(AjaxRequestTarget target)
            {
                target.add(feedbackPanel);
            }
        };
        add(basedOnPageLink, basedOnPanelLink, basedOnTextTemplateLink);
    }

    private void updateResult(final Component result,final CharSequence mailBody,final AjaxRequestTarget target){
        result.setDefaultModelObject(mailBody);
        target.add(result);
    }
}


