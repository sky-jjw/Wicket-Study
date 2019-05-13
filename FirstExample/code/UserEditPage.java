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
    
    //���ö�Ӧѡ��
    static {
        favorites.add("��Ӱ");
        favorites.add("�Ķ�");
        favorites.add("����");
        favorites.add("��Ϸ");

        jobs.add("��Ա");
        jobs.add("��ʦ");
        jobs.add("ѧ��");
        jobs.add("����");

        for (int i = 0; i < 11; i++) {
            workYears.add(new Integer(i));
        }

        marriges.add(Boolean.TRUE);
        marriges.add(Boolean.FALSE);
    }
    
    //���캯��
    public UserEditPage(){
        super();
        User user = new User();
        
        //�������������Ϣ�Ŀؼ�
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        this.add(feedbackPanel);
       
        Form form=new Form("form",new CompoundPropertyModel(user)) {
        	protected void onSubmit() {
        		info("onSubmit");
        		//ȷ����Ϣ
        	}
        };
   
        form.setMultiPart(true);
        form.setMaxSize(Bytes.kilobytes(1000));
        //���������ϴ��������ļ���С
        
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
