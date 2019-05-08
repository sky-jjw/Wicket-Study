# Wicket-Study
## 什么是Wicket  
什么是Wicket，如果你用谷歌或其他搜索引擎搜索一番之后，就会发现wicket是Java平台下一个面向组件的web应用程序开源框架。它不像基于Action/Request的Struts、WebWork和SpringMVC这类表单提交最终转换为一个单一动作的框架。Wicket采用类似于Asp.NET、Tapestry和JSF这类通过用户触发表单组件从而触发事件监听器中的事件。从本质上讲，如Struts这类网络MVC框架采用的是粗力度的动作，而相比之下，Wicket这类面向组件的框架采用的则是细粒度的动作，这一点非常像桌面应用程序。  
## Wicket特点  
  Wicket的特点非常简单，用一个非常形象的公式来表示Wicket，即Just Java + Just HTML = Wicket。如何理解这句话呢？  
### Just Java
 Wicket能够让我们使用Java编程的方式构造所需的组件和页面。通过使用new关键字创造，为父组件延迟注入子组件，这样的组件就具有了层次结构，同时也可以使用extends关键字来继承其他组件的功能。Wicket提供了决定如何将组件的创建的支持，这给我们提供了非常好的灵活性。
        虽然Java可以很好的实现Web应用程序的行为，但它不能完美维护页面布局和样式。所以在Wicket中，还有一部分'Just HTML'来维护这块内容。
### Just HTML
 当我们使用Wicket进行编码时，我们通过将Web应用程序中布局这类的内容封装在HTML中，就可以为使用者提供一套干净的模板的目的，这套干净的模板仅仅包含一些HTML标记和Wicket占位符。
   说到这里，你可能简单的认为Wicket就是一个简单的HTML静态布局和Java动态实现的结合，那么只能说你只了解了其中非常少的一部分，Wicket还可以做很多的事情，但是这就需要你具有一个很好的抽象思维，这样你才能很好的使用Wicket。
## 资料
[Wicket官网](http://wicket.apache.org/)      
Wicket In Action   
Pro Wicket


