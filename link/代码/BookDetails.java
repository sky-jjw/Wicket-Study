package njupt.link;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class BookDetails extends WebPage {
    public BookDetails(final Book book){
        add(new Label("title", book.getTitle()));
    }
}
