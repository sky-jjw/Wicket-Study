# 对奶酪商店案例的学习总结
在这个Example中包含两部分：[com.njupt包](https://github.com/sky-jjw/Wicket-Study/tree/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt)和
[entity包](https://github.com/sky-jjw/Wicket-Study/tree/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/entity)
下面是它两的目录结构：

com.njupt | entity
-------- |------
[Checkout.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/Checkout.java)  [Checkout.html](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/Checkout.html) | [Address.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/entity/Address.java)
[CheesrPage.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/CheesrPage.java)   [CheesrPage.html](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/CheesrPage.html) | [Cart.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/entity/Cart.java)
[CheesrSession.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/CheesrSession.java)   [CheesrApplication.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/CheesrApplication.java) | [Cheese.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/entity/Cheese.java)
[ShoppingCartPanel.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/ShoppingCartPanel.java)  [ShoppingCartPanel.html](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/ShoppingCartPanel.html) | [User.java](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/entity/User.java)
[base.css](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/base.css)  

这是本案例包括的所有代码，下面将会从CheesrApplication来着手逐步分析。  
## 1.CheesrApplication.java
[跳转源码](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/CheesrApplication.java)  
* 在这个入口类中定义并实例化了`cheeses`对象，类型为`List<Cheese>`，这些是在`CheesrPage`类表示的购物界面上所显示商品信息的来源。
相当于内置了商品数据。然后自定义两个函数：
```
public static CheesrApplication get() {
		return (CheesrApplication) Application.get();
}
public List<Cheese> getCheeses() {
	  return Collections.unmodifiableList(cheeses);
}
```
前者为静态类型，脱于对象存在，便于在不同类之间使用该方法。在本例中用于在`CheesrPage`类中“隔空”获取当前的Application，并结合后者方法进而获得存放商品信息的`cheeses`，最终应用于其它（下面详说）。
  
  
* 当然，关键还在于这三个重写的方法：
```
protected void init() {
		super.init();
}
public Class<? extends Page> getHomePage() {
		return CheesrPage.class;
}
public Session newSession(Request request, Response response) {
		return new CheesrSession(request);
}
```
这三者均为系统调用，当入口类与服务器建立连接时，便会调用这三者。第一个方法是初始化一些配置，第二个方法是控制显示的初始界面，第三个方法是建立了相关的Session
对象，用来表征客户端浏览器与服务器之间建立的互动信息状态。但需注意这里是由类`CheesrSession`实例化的对象，其中存放着关于购物车的相关信息，达到了通过Session对象间接掌握
购物车信息的目的。（具体由类`CheesrSession`可知）正因为它的生命周期贯穿到页面始终，用它来作为获取购物车信息的媒介十分合适。
  
    
## 2.CheesrSession.java
[跳转源码](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/CheesrSession.java)  
这个类比较简单，就是定义了私有变量cart，存放购物车信息。以及一个构造函数，开放对外接口，便于入口类调用。  
  
  
  
## 3.CheesrPage.java
[跳转源码](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/CheesrPage.java)  
这个类是控制显示界面的，用来输出商品信息、购物车信息、用户结账信息到界面。  
* 输出商品信息  
先是通过方法`public List<Cheese> getCheeses()`来获取商品信息，正如第1点提到的，然后在该类构造函数中将商品信息通过ListView控件显示到界面上。  
其中商品名、商品价值通过控件Label被添加到ListView中，而*添加到购物车*这一选项通过控件Link来实现。每当点击这个链接的时候，会触发其中的*onClick*方法
从而将该商品加入到购物车。其中操作正是通过上面定义的两个方法实现。效果图如下：
![](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E6%88%AA%E5%9B%BE/TIM%E5%9B%BE%E7%89%8720190512140005.png)
* 输出购物车信息和用户信息  
为了达到这一点的需求，是通过*自定义控件*ShoppingCartPanel实现的，下面会详细说明。
* 自定义样式表  
```
public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem
				.forReference(new CssResourceReference(CheesrPage.class, "base.css")));
//		response.render(JavaScriptHeaderItem
//				.forReference(new JQueryPluginResourceReference(CheesrPage.class, "XXX.js")));
	}
```
这个方法是重写的，并由系统调用。样式表的定义通过*base.css*来外部实现，然后通过方法载入。
## 4.ShoppingCartPanel.java
[跳转源码](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E4%BB%A3%E7%A0%81/com.njupt/ShoppingCartPanel.java)  
* 构造函数  
观察构造函数的参数，结合类CheesrPage中显示购物车信息和用户信息的方法`add(new ShoppingCartPanel("shoppingPanel", getCart()));`，便可知道
界面类中的购物车信息被完全复制到了本类中，并在下面的操作使用中派到用场。
* 显示购物车信息  
具体显示的过程和显示商品信息的过程大同小异，这里就不在赘述。需注意的一点是：在显示商品信息的时候，是直接通过cheeses对象来提供信息；而在本类中没有这个
具体的对象，有的是购物车，而购物车这个容器包含的是完整的cheese对象，故需要用到PropertyModel来将信息提取出来。  
还有就是要显示总价钱，下面是它的功能代码：
```
add(new Label("total",new Model() {

			@Override
			public Serializable getObject() {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				return nf.format(getCart().getTotal());
			}
			
		}));
```
以Label的形式呈现出去。
* 显示结账用户信息  
再贴代码：
```
add(new Link<Void>("checkout") {

			@Override
			public void onClick() {
				setResponsePage(Checkout.class);
			}
			//购物车不空的时候显示
			@Override
			public boolean isVisible() {
				return !getCart().getCheeses().isEmpty();
			}
		});
```
通过匿名类的形式来实现接口方法，完成相关功能。当像点击“添加购物车”一样时，便会触发onLink方法，完成里面的东西。这里是实现了**页面转向**，转向Checkout对应的页面。
下面通过重写内置方法来实现条件可见的功能。  
**最终，所有的这些显示控件等操作，均由系统调用相关方法。**  
效果图如下：
![](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E6%88%AA%E5%9B%BE/TIM%E5%9B%BE%E7%89%8720190512140041.png)
## 5.Checkout.java
* 在这个部分是去上传用户的姓名、地址等信息，所以表单控件form是必须使用的。    
* 需要对没填完整信息便提交信息的状况实施一些信息反馈，所以用到feedBackPanel控件。  
* 接下来便是加入对应信息框，这里需注意的是：*需要用到PropertyModel*，给每个控件分配一个，用于信息存储、更新等。
* 最终便是Cancel和order选项。Cancel通过页面跳转到初始页面，这里必须是返回CheesrPage，因为只有它继承自WebPage；
order选项是只要点击之后，便会清空购物车信息，模拟完成依次交易，然后退回到初始界面。  
效果图如下：
![](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E6%88%AA%E5%9B%BE/TIM%E5%9B%BE%E7%89%8720190512140101.png)  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/%E5%A5%B6%E9%85%AA%E5%95%86%E5%BA%97%E6%A1%88%E4%BE%8B/%E6%88%AA%E5%9B%BE/TIM%E5%9B%BE%E7%89%8720190512140151.png)  
## 6.关于html文件中的问题
由java代码和对应html文件的格式，可以知道shoppingCartPanel是一个自定义的、被封装起来的控件。下面是cheesrPage的部分html元素：
```
<div id="cart">
		<div wicket:id="shoppingPanel"></div>
		<wicket:child></wicket:child>
</div>
```
这是checkout.html的文档格式：
```
<wicket:extend>
…………
</wicket:extend>
```
下面是引用wicket教程中的：  
> 接下来编写的一个 ExtendPage 的 Html，就相当于 Java 的实现类，它继承了上面的BasePage，然后通过<wicket:extend>这个标签来表示它要覆盖 BasePage 中<wicket:child>的内容。另外为了方便设计时的处理，也可以使用下面给出的 Html 内容，包含<title>，<body>等信息，但是在输出的时候，只有<wicket:extend>中的内容是有效的，其它的内容都不会被输出  

由这些联系，便可知道具体工作原理。
## 自己的心得
* 
```
Form<Void> form = new Form<Void>("form");
		form.add(new FeedbackPanel("feedback"));
		Address address = getCart().getBillAddress();
		form.add(new TextField("name",new PropertyModel(address, "name")).setRequired(true));
		form.add(new TextField("street", new PropertyModel(address, "street")).setRequired(true));
		form.add(new TextField("zipcode", new PropertyModel(address, "zipcode")).setRequired(true));
		form.add(new TextField("city", new PropertyModel(address, "city")).setRequired(true));
 ```
 对于上面的代码，我觉得还可以这样写：
 ```
 Address address = getCart().getBillAddress();
		Form<Void> form = new Form<Void>("form",new CompoundPropertyModel(address)) {};
		form.add(new FeedbackPanel("feedback"));
		
		form.add(new TextField("name"));
		form.add(new TextField("street"));
		form.add(new TextField("zipcode"));
		form.add(new TextField("city"));
 ```
 其他均不变，其中原理不在赘述，在[此处](https://github.com/sky-jjw/Wicket-Study/blob/master/FirstExample/summary.md)有解释。
 * 对于shoppingCartPanel封装成控件，我觉得十分的好用。毕竟它的作用只是显示购物车信息即可，作为一个控件来使用十分的便利。
 * 对于这些由系统自动调用、实时更新的类、方法、控件等，还有待学习并掌握。    
 
 目前就这些了。**未完待续……**



















