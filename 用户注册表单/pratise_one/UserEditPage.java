package com.pritice_one;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class UserEditPage extends WebPage {
    private static List favorites = new ArrayList();
    private static List jobs = new ArrayList();
    private static List workYears = new ArrayList();
    private static List marriges = new ArrayList();
    private static List genders = new ArrayList();
    static {
        favorites.add("电影");
        favorites.add("阅读");
        favorites.add("旅行");
        favorites.add("游戏");

        jobs.add("演员");
        jobs.add("老师");
        jobs.add("学生");
        jobs.add("记者");

        for (int i = 0; i < 11; i++) {
            workYears.add(new Integer(i));
        }

        marriges.add(Boolean.TRUE);
        marriges.add(Boolean.FALSE);

        genders.add("男");
        genders.add("女");
    }
    public UserEditPage(){
        super();
        User user = new User();
        //反馈
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        this.add(feedbackPanel);
        //表单
        Form form = new Form("form"){
            @Override
            protected void onSubmit() {
                System.out.println("onSubmit");
                super.onSubmit();
            }
        };
        this.add(form);
        //允许上传文件
        form.setMultiPart(true);
        //上传文件的最大大小
        form.setMaxSize(Bytes.kilobytes(1000));

        //添加基本输入框到form
        TextField accountTextField = new TextField("account");
        //添加验证器  等于添加了一个RequiredValidator ，必须输入
        accountTextField.setRequired(true);
        //添加长度的验证
        accountTextField.add(StringValidator.lengthBetween(6, 20));
        form.add(accountTextField);
        form.add(new TextField("name"));
        form.add(new PasswordTextField("password"));
        //性别的单选组合框控件
        RadioChoice genderchoice = new RadioChoice("genderchoice", genders);
        form.add(genderchoice);

        //第二个参数限制输入的数据类型
        form.add(new TextField("salary", Double.class));
        form.add(new DropDownChoice("job", jobs));
        form.add(new ListChoice("workYear", workYears));
        form.add(new RadioChoice("married",marriges));
        form.add(new CheckBoxMultipleChoice("favorites", favorites));
        //上传文件
        form.add(new FileUploadField("image"));
    }
}
