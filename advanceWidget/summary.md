#### 树形控件
*需导包 wicket-extensions*
- 由根节点，父节点，子节点组成
- 定义方法：

```DefaultMutableTreeNode node=new DefaultMutableTreeNode(para)```
- 根节点需要调用setUserObject方法设置用户对象

#### TreeTable
设置列示例：
```
        IColumn columns[] = new IColumn[]{
                new PropertyTreeColumn(new ColumnLocation(ColumnLocation.Alignment.LEFT, 18, ColumnLocation.Unit.EM),"作者","userObject.author"),
                new PropertyTreeColumn(new ColumnLocation(ColumnLocation.Alignment.LEFT, 12, ColumnLocation.Unit.EM),"标题","userObject.title"),
        };
```
定义TreeTable：
```
        /**
         * para1:wicket-id
         * para2:构建model的方法
         * para3:定义的列
         */
TreeTable tree=new TreeTable("treeTable",buildTreeModel(),columns);

//构建一个TreeModel
private TreeModel buildTreeModel(){
    //定义节点
    /*code*/
    //返回TreeModel
    return new DefaultTreeModel(rootNode)
}
```

#### 数据分页控件
**ListView，PagebleListView，DataView，GridView的数据都可以分页**

- 扩展包中为了解决数据列表中*大数据量*的问题，使用**IDataProvider**接口作为数据分页控件的数据源，通过该数据源获得要显示的数据

接口有三个方法：
```
//从指定序号处在载入指定数量的数据
public Iterator iterator(int first,int count);
//取得Model
public IModel model(Object object)
//取得数据记录总数
public int size()
```
#### RepeatingView控件
- 与ListView差不多，区别在于ListView一定要实现一个populateItem 的抽象方法，而RepeatingView则没有这个约束。可以改用Iterator迭代
####  DataView DataView DataView 控件 
- 而 DataView 与前面的 PageableListView 区别并不大，只是为了处理大数据量的记录， 使用 IDataProvider 而不是 List 作为数据源，其它地方与 PageableListView 基本没有区别