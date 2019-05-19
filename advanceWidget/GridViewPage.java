package com.advanceWidget;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

public class GridViewPage extends WebPage {
    private static List counts = new ArrayList();
    static {
        for (int i = 0; i < 10; i++) {
            counts.add(new Integer(i));
        }
    }

    public GridViewPage(){
        super();
        //IDataProvider作为数据源，是为了处理大数据量的记录
        IDataProvider dataProvider = new ListDataProvider(counts);
        //gridView可以动态指定列数，并在数据量不能整出的时候，进行处理
        GridView gridView=new GridView("rows",dataProvider) {
            @Override
            //空的单元格处理
            protected void populateEmptyItem(Item item) {
                Image image=new Image("static","");
                //不显示
                image.setVisible(false);
                item.add(image);
                item.add(new Label("label", "image.jpg"));
            }

            @Override
            //填充项
            protected void populateItem(Item item) {
                final Integer integer = (Integer) item.getModelObject();
                item.add(new Image("static","image.jpg"));
                item.add(new Label("label", "功能" + integer));
            }
        };
        //4行3列
        gridView.setRows(4);
        gridView.setColumns(3);
        add(gridView);
    }

}
