package com.advanceWidget;

import com.njupt.Book;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation;
import org.apache.wicket.extensions.markup.html.tree.table.IColumn;
import org.apache.wicket.extensions.markup.html.tree.table.PropertyTreeColumn;
import org.apache.wicket.extensions.markup.html.tree.table.TreeTable;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class TreeTablePage extends WebPage {
    public TreeTablePage(){
        super();
//        this.getApplication().getAjaxSettings().setAjaxDebugModeEnable(false);
        //定义列
        IColumn columns[] = new IColumn[]{
                new PropertyTreeColumn(new ColumnLocation(ColumnLocation.Alignment.LEFT, 18, ColumnLocation.Unit.EM),"作者","userObject.author"),
                new PropertyTreeColumn(new ColumnLocation(ColumnLocation.Alignment.LEFT, 12, ColumnLocation.Unit.EM),"标题","userObject.title"),
        };

        final TreeTable tree=new TreeTable("treeTable",buildTreeModel(),columns);
        //允许多选
        tree.getTreeState().setAllowSelectMultiple(true);
        add(tree);

        add(new Link("expandAll") {
            @Override
            public void onClick() {
                tree.getTreeState().expandAll();
            }
        });

        add(new Link("collapseAll") {
            @Override
            public void onClick() {
                tree.getTreeState().collapseAll();
            }
        });
    }

    /*构建又给TreeModel*/
    private TreeModel buildTreeModel(){
        Book rootBook=new Book();
        rootBook.setAuthor("根作者");
        rootBook.setTitle("根标题");

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootBook);
        for (int i = 0; i < 10; i++) {
            Book parentBook = new Book();
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(parentBook);
            parentBook.setAuthor("父作者" + i);
            parentBook.setTitle("父标题" + i);
            for (int j = 0; j < 5; j++) {
                Book childBook = new Book();
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childBook);
                childBook.setAuthor("子作者" + (10 * i + j));
                childBook.setTitle("子标题" + (10 * i + j));
                parentNode.add(childNode);
            }
            rootNode.add(parentNode);
        }
        return new DefaultTreeModel(rootNode);
    }
}
