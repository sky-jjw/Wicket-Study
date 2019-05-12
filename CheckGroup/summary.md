# 对于多选框控件的学习总结 
## html文件中的“秘密”
先看html文件中的这几行代码：  
```
<table border="1">
	<tr wicket:id="sites">
	<td><input type="checkbox" wicket:id="check"/></td>
	<td><span wicket:id="name">这里是名称</span></td>
	</tr>
</table>
```
1.在运行本例子后，发现结果如下图：
![1](https://github.com/sky-jjw/Wicket-Study/blob/master/CheckGroup/resources/1.png)  
于是便好奇为何会出现方框？由于控件是Control层面的，数据是model层面的，html文件是view层面的，而控件的作用是解析html文件形成视图并将model中的数据放入视图，显然方框的出现与html文件中的描述web结构的信息有关。<td>、</td>便是方框出现的原因，以下是去掉这个描述结构的运行结果： 
![3](https://github.com/sky-jjw/Wicket-Study/blob/master/CheckGroup/resources/3.png)  
2.对于`<table border="1">`的理解，便是将其值进行变化，为5的时候结果如下图：
![2](https://github.com/sky-jjw/Wicket-Study/blob/master/CheckGroup/resources/2.png)  
所以由此猜测border是限定这个table的边界框的“厚度”。  

## Java文件
在代码中，通过定义list数组SITES来存储链接，Map数组NAMES来存储名字，并形成链接和名字直接的映射。最后通过如下代码：
```
ListView sites =  new ListView("sites", SITES ) {
			protected void populateItem(ListItem item) {
				Object object=item.getModelObject();
				item.add( new Check("check",  new Model(Integer. toString (item.getIndex()))));
				item.add( new Label("name", new Model((Serializable) NAMES .get(object))));
			};
		};
```
定义ListView控件来显示checkbox以及对应名字。其中通过匿名类的形式初始化了对象。
