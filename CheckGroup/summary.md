# 搭建Wicket项目（使用Maven）  
## 创建一个Maven项目  
1.File -> New ->Maven Project;指定项目保存的目录（G:\myworkspace\Wicket-Study\Hello World）然后点击next  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508145725.jpg)  
2.选择 maven-archetype-webapp这一行，点击next  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508145918.jpg)  
3.GroupID是项目组织唯一的标识符，实际对应java的包的结构，是main目录里java的目录结构。  
ArtifactID就是项目的唯一的标识符，实际对应项目的名称，就是项目根目录的名称,填好之后点击finish  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508150116.jpg)  
4.项目结构如下图所示  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508150216.png)  
5.接着右键项目名 Build Path ->Configure Build Path..，选中“JRE System Library[J2SE-1.5]”  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508150343.jpg)  
然后选择“Edit”，选择WorkSpace default JRE(jdk1.8.0_161)点击finish  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508150448.png)  
6.Java源文件的目录就创建好了，如下图所示  
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508150632.png)  
## 导入wicket核心jar包
1.
```
<dependency>  		
	<groupId>org.apache.wicket</groupId>
	<artifactId>wicket-core</artifactId>
	<version>8.0.0</version>
 </dependency>
 ```
2.在pom.xml里添加上面的依赖
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508151018.png)  
## 配置web.xml
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508151412.png)  
## 建立Application
在src/main/java下新建WicketExampleApplication类
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508151640.jpg)  
## 建立HelloWorldPage.html
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508152249.png)  
## 建立HelloWorldPage.java
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508152008.png)  
## 在WicketExampleApplication类中返回HelloWorldPage.class
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508152100.png)  
## 运行测试
![](https://github.com/sky-jjw/Wicket-Study/blob/master/Hello%20World/resources/TIM%E5%9B%BE%E7%89%8720190508152350.png)

