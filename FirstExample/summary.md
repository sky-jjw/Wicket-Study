# 用户注册表程序
此例子是Wicket 开发教程（中文版）上的Wicket最佳实践一，通过这样一个简单但完整的web，我从中学到这样一些东西，同时也存在着待解决的问题，记录在这里等待以后解决  
## html文件
html文件源码请移步这里：[html文件](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/code/UserEditPage.html)  
在这个文件中，我们分析其结构就会发现有许多重复的结构（如下代码段1），然后套放在代码段2的结构中。在这样的结构中，由运行结果，便可以知道这些控件的功能。
```
<tr>                              <form wicket:id="form">
    <td>项目名称</td>                   <table>
    <td>控件描述</td>                   …………
</tr>                                  </table>
                                  </form>
         1                                 2
```
`<tr>`便表示行，而`<td>`表示单元格即方框，而这些都被放于一个table中，最终被form包含。  
而在对FeedBackPane组件的描述`<span style="color:red;" wicket:id="feedback"></span>`中，color属性便是设置信息反馈回来显示的颜色，如下图对比所见，一个为red一个为black
![](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/resources/8.png)
![](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/resources/9.png)
## java文件
web主体源码请移步这里：[web代码源文件](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/code/UserEditPage.java)  
用户实体类请移步这里：[实体类源文件](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/code/User.java)
### 对组件的深入理解
* 在这个程序中，需加入FeedbackPanel控件来输出反馈信息，否则难以实现交互并且对与输入数据的格式、正确适合等都难以把握  
* 且看以下代码：
```
Form form=new Form("form",new CompoundPropertyModel(user)) {
        	protected void onSubmit() {
        		info("onSubmit");
        		//确认信息
        	}
        };
```
在表单form的初始化中必须加入`new CompoundPropertyModel(user)`，其中`user`为用户实体类`User`定义的对象。因为在这个表单中有许多的TextField、checkbox等组件，这些信息在提交后得有地方储存，而model便是一个选择。而且这样还可以实时更新用户实体类的信息。
如果不加这个东西，会出现如下图的错误：
![](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/resources/6.png)
然而假设我们要去解决这个问题，account相关代码补充上了model，但仍然会出现问题。因为下面的控件都会提交信息，所以在“根”组件form初始化的时候加入model，便一劳永逸了，毕竟这些组件都是基于form的。同时也又发现了一个好玩的东西，即form验证数据的流程（如下图），
![](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/resources/1.png)  
依次验证，当出现第一个错误时FeedbackPanel控件便输出反馈信息（如下图），甚至像上面提到的一样发生报错。
![](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/resources/10.png)
### 困难
具体遇到的困难便是`RadioChoice`与`CompoundPropertyModel(user)`的冲突问题。首先后者是不能删除的，而如果我想显示性别的时候像下图一样，那我必然要用到单选框`RadioChoice`。
![](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/resources/4.png)
但是当我使用`RadioChoice`控件、hashmap等来显示男女时，便出现了下图的错误：
![](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/resources/3.png)  
对于这个问题，我现在还没有解决，留待以后发现。不过目前的疑惑是，既然`form.add(new RadioChoice("married",marriges));`这个可以行的通，那为什么那个就不行了呢？难道就因为
```
marriges.add(Boolean.TRUE);
marriges.add(Boolean.FALSE);
```
这样吗？显示字符串、文字就有了问题？
