- **单选列表控件**：ListChoice
>*添加映射*

```
        IChoiceRenderer renderer=new ChoiceRenderer() {
            public Object getDisplayValue(Object object) {
                return NAMES.get(object);//NAMES是一个HashMap
            }
        };
```


- **多选列表控件**：ListMutltipleChoice
- **单选组合框控件**：RadioChoicex
```
//html放在span中
RadioChoice siteChoice=new RadioChoice("sites",SITES,renderer);
/*SITES是一个列表，存放单选组合框里的选项。
  renderer是一个渲染器，可无
*/
```
- **增强的单选组合按钮**：RadioGroup
```
<tr wicket:id="sites"> 
    <td><input type="radio" wicket:id="radio" /></td>
    <td>
        <span wicket:id="name">这里是名称
        </span>
    </td> 
</tr>
}

```
> *添加映射,然后将sites放入group*
  ```
          ListView sites = new ListView("sites", SITES2){
            //填充项
            protected void populateItem(ListItem item) {
                //获得模型对象
                Object object=item.getModelObject();
                item.add(new Check("check", new Model(Integer.toString(item.getIndex()))));
                //SITES2中的对象与NAMES2中的对象建立一一映射
                item.add(new Label("name", new Model((Serializable)NAMES2.get(object))));
            }
        };
  ```
- **图像控件**

  html中
  ``` <img wicket:id="image">```
  
  java代码中
  ```new Image("image","test.gif")```
  > 可以显示静态图像，也可以在服务器端生成动态图像显示在客户端
  
  > 使用静态图像必须提供一个已有的图像文件，而使用动态图像则只需要重载一个 renderer 方法以动态输出图像
  ```
          this.add(new Image("dynamic", new RenderedDynamicImageResource(100,100) {
            @Override
            protected boolean render(Graphics2D graphics) {
                graphics.drawString("测试图片", 10, 10);
                return true;
            }
        }));
  ```
 - **文件上传控件**
 
 html中 ``` <input wicket:id="image" type="file">```
 java中
 ```
 final FileUploadField fileUploadField = new FileUploadField("image");
 //获取上传的文件
 final FileUpload upload=fileUploadField.getFileUpload();
 //写入位置
 upload.writeTo(new File(path));
 
 ```
 - **数据分页的列表控件**
 ```
 //10表示每页10个数据
 final PageableListView listView=new PageableListView("books",books,10) {
            @Override
            protected void populateItem(ListItem item) {
            //处理item
            }
    }
//处理分页操作
form.add(new new new PagingNavigator("navigator",listView)); 
 ```

#### 为表单程序添加数据验证
- Wicket提供了一个==IValidator==接口对Wicket表单控件中的数据进行验证：
```
public interface IValidator extends Serializable{
    //验证指定控件上的数据
    void validate(final FormComponent component);
}
```
- add(IValidator)方法为一个Form添加一个或多个Validator,默认实现有:==LengthValidator,IntegerValidator,StringValidator,支持正则表达的PatternValidato==r等。
```
        TextField accountTextField = new TextField("account");
        //添加验证器  等于添加了一个RequiredValidator ，必须输入
        accountTextField.setRequired(true);
        //添加长度的验证
        accountTextField.add(StringValidator.lengthBetween(6, 20));
        form.add(accountTextField);
```
- Form验证数据的流程
![Image text](https://github.com/sky-jjw/Wicket-Study/blob/master/%E7%94%A8%E6%88%B7%E6%B3%A8%E5%86%8C%E8%A1%A8%E5%8D%95/resources/1.png)


