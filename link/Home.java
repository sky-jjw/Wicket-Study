package njupt.link;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.*;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.time.Duration;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Home extends WebPage {
    private int linkClickCount = 0;
    private int buttonClickCount = 0;

    public Home() {
        //点击actionLink，linkClickCount+1
        final Link<Void> actionLink = new Link<Void>("actionLink") {
            @Override
            public void onClick() {
                linkClickCount++;
            }
        };
        //将Home类的linkClickCount的值作为PropertyModel放到id=linkClickCount的label标签中
        actionLink.add(new Label("linkClickCount", new PropertyModel<Integer>(this, "linkClickCount")));
        add(actionLink);

        //点击按钮,buttonClickCount
        final Link<Void> actionButtonClickLink = new Link<Void>("actionOnClickLink") {
            @Override
            public void onClick() {
                buttonClickCount++;
            }
        };
        add(actionButtonClickLink);
        add(new Label("buttonClickCount", new PropertyModel<Integer>(this, "buttonClickCount")));

        //点击page1的link，返回page1.class
        add(new BookmarkablePageLink<>("page1Link", page1.class));

        //page2的link在Home.html中已经写了href = "Page2.html"，点击即跳转到page2

        //page3是外部链接，且接收参数
        BookmarkablePageLink<Void> page3Link = new BookmarkablePageLink<Void>("page3Link", page3.class);
        page3Link.getPageParameters().add("bookmarkparameter", "987654321");
        add(page3Link);

        //BookDetails page 的link
        add(new Link<Void>("bookDetailsLink") {
            @Override
            public void onClick() {
                //点击bookDetailsLink后，BookDetails页面响应，创建一个Book对象
                setResponsePage(new BookDetails(new Book("The Hobbit")));
            }
        });

        //delayed link to bookdetails page
        add(new Link<Void>("bookDetailsLink2") {
            @Override
            public void onClick() {
                setResponsePage(new BookDetails(new Book("Inside The Matrix")));
            }
        });

        //弹出页面设置
        PopupSettings popupSettings = new PopupSettings("popuppagemap");
        popupSettings.setWidth(500);
        popupSettings.setHeight(500);
        //link，button，点击弹窗，效果一样
        add(new BookmarkablePageLink<>("popupLink", Popup.class).setPopupSettings(popupSettings));
        add(new BookmarkablePageLink<>("popupButtonLink", Popup.class).setPopupSettings(popupSettings));

        //在本页面打开外部链接
        add(new ExternalLink("google", "http://www.google.com", "Click this link to go to Google"));

        //弹窗形式打开外部链接
        //RESIZABLE:可调整大小
        PopupSettings googlepopupSettings = new PopupSettings(PopupSettings.RESIZABLE | PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
        add(new ExternalLink("googlePopup", "http://www.google.com", "Click this link to go to Google by a popup window").setPopupSettings(googlepopupSettings));

        add(new ResourceLink<>("cancelButtonLink", new SharedResourceReference("cancelButton")));
        add(new DownloadLink("downloadLink", new AbstractReadOnlyModel<File>() {
            private static final long serivaVersionUID = 1L;

            @Override
            public File getObject() {
                //创建临时文件
                File tempFile;
                try {
                    tempFile = File.createTempFile("wicket-download-link", ".tmp");
                    InputStream data = new ByteArrayInputStream("some data".getBytes());
                    Files.writeTo(tempFile, data);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return tempFile;
            }


        }, "Download\"here now.tmp").setCacheDuration(Duration.NONE).setDeleteAfterDownload(true));



        Link linkToAnchor = new Link<Void>("linkToAnchor") {
            @Override
            public void onClick() {

            }
        };
        //创建锚点
        Component anchorLabel = new Label("anchorLabel", "this label is here to function as an anchor for a link").setOutputMarkupId(true);
        add(anchorLabel);
        //linkToAnchor与anchorLa建立连接
        linkToAnchor.setAnchor(anchorLabel);
        add(linkToAnchor);


        //另一个锚点
        Link anotherlinkToAnchor=new Link<Void>("anotherlinkToAnchor") {
            @Override
            public void onClick() {

            }
        };
        add(anotherlinkToAnchor);


        Link<Void> linkWithLabel = new Link<Void>("linkWithLabel") {
            @Override
            public void onClick() {

            }
        };
        linkWithLabel.setBody(Model.of("A link tha provides its body with link.setBody(someModel)"));
        add(linkWithLabel);
    }
}



