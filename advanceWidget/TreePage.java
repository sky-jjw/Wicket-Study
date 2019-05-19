package com.advanceWidget;

import org.apache.wicket.extensions.markup.html.tree.Tree;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class TreePage extends WebPage {
    public  TreePage(){
        super();
        //定义根节点
        final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
        rootNode.setUserObject(this);

        //添加父节点和其子节点
        for (int i = 0; i <5; i++) {
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode("parent-" + i);
            for (int j = 0; j < 5; j++) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode("child-" + j);
                //子节点添加到父节点中
                parentNode.add(childNode);
            }
            //父节点添加到根节点中
            rootNode.add(parentNode);
        }

        /*构建一个Model*/
        //以rootNode为根节点
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        final Tree tree=new Tree("tree", treeModel) {
            //重载getNodeLabel方法以显示 相应的字符串
            protected String getNodeLabel(DefaultMutableTreeNode node) {
                if (node.getUserObject() == TreePage.this) {
                    return "root";
                } else {
                    return node.toString();
                }
            }
        };

        this.add(tree);
        add(new Link("expandAll") {
            @Override
            public void onClick() {
                tree.getTreeState().expandAll();
            }
        });
        add(new Link("collapseAll") {
            @Override
            public void onClick() {
                //收缩根节点
                tree.getTreeState().collapseAll();
            }
        });

    }

}
