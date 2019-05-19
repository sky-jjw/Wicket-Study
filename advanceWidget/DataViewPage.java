package com.advanceWidget;

import com.njupt.Book;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

public class DataViewPage extends WebPage {
    private static List books=new ArrayList();
    static {
        for (int i = 1; i <100; i++) {
            Book book = new Book();
            book.setId(i);
            book.setAuthor("author" + i);
            book.setTitle("title" + i);
            books.add(book);
        }
    }
    public DataViewPage(){
        super();
        final DataView listView=new DataView("books",new ListDataProvider(books),10) {
            @Override
            protected void populateItem(Item item) {
                final Book book = (Book) item.getModelObject();
                item.add(new CheckBox("selected", new PropertyModel(book, "selected")));
                item.add(new Label("id", Integer.toString(book.getId())));
                item.add(new Label("title", book.getTitle()));
                item.add(new Label("author", book.getAuthor()));
                item.add(new Link("edit") {
                    @Override
                    public void onClick() {
                        //转向编辑页面
                    }
                });
            }
        };
        Form form=new Form("form"){
            protected void onSubmit(){
                //执行操作
            }
        };
        this.add(form);
        form.add(listView);
        form.add(new PagingNavigation("navigator", listView));
    }
}
