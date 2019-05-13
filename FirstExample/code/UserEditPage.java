package TestWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class UserEditPage extends WebPage {
    private static List favorites = new ArrayList();
    private static List jobs = new ArrayList();
    private static List workYears = new ArrayList();
    private static List marriges = new ArrayList();
    
    //设置对应选项
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
    }
    
    //构造函数
    public UserEditPage(){
        super();
        User user = new User();
        
        //加入输出反馈信息的控件
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        this.add(feedbackPanel);
       
        Form form=new Form("form",new CompoundPropertyModel(user)) {
        	protected void onSubmit() {
        		info("onSubmit");
        		//确认信息
        	}
        };
   
        form.setMultiPart(true);
        form.setMaxSize(Bytes.kilobytes(1000));
        //设置允许上传并设置文件大小
        
        this.add(form);
        
        TextField accountTextField = new TextField("account");
        form.add(accountTextField);
       
        form.add(new TextField("name"));
        form.add(new PasswordTextField("password"));
        form.add( new CheckBox("female"));
        
        form.add(new TextField("salary", Double.class));
        form.add(new DropDownChoice("job", jobs));
        form.add(new ListChoice("workYear", workYears));
        form.add(new RadioChoice("married",marriges));
        form.add(new CheckBoxMultipleChoice("favorites", favorites));
        
    }
}
