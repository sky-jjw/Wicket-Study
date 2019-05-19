package com.advanceWidget;

import com.njupt.Book;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepeatingViewPage extends WebPage {
    //定义数据
    private static List books=new ArrayList();
    static {
        for (int i = 1; i < 10 ;i++){
            Book book = new Book();
            book.setId(i);
            book.setAuthor("author" + i);
            book.setTitle("title" + i);

            books.add(book);
        }
    }
    public RepeatingViewPage(){
        super();
        //定义表单
        Form form = new Form("form"){
            protected void onSubmit(){
                //指向提交操作
            }
        };
        /**
         * 和ListViewm类似，区别是不需实现populateItem的抽象方法
         */
        RepeatingView listView = new RepeatingView("books");
        Iterator iterator = books.iterator();
        while (iterator.hasNext()) {
            WebMarkupContainer item = new WebMarkupContainer(listView.newChildId());
            listView.add(item);

            final Book book = (Book) iterator.next();
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


            form.add(listView);
            this.add(form);
        }


    }
}
