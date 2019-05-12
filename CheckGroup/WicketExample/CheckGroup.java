package TestWicket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

public class CheckGroupPage extends WebPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List SITES =  new ArrayList();
	private static Map NAMES =  new HashMap();
	static{
		SITES .add("http://www.sina.com.cn");
		SITES .add("http://www.sohu.com");
		SITES .add("http://www.163.com");
		NAMES .put("http://www.sina.com.cn", " ÐÂÀË " );
		NAMES .put("http://www.sohu.com", " ËÑ»¢ " );
		NAMES .put("http://www.163.com", " ÍøÒ× " );
	}
	public CheckGroupPage() {
		super();
		Form form =  new Form("form");
		add(form);
		CheckGroup group =  new CheckGroup("group", new ArrayList());
		form.add(group);
		group.add( new CheckGroupSelector("groupselector"));
		ListView sites =  new ListView("sites", SITES ) {
			protected void populateItem(ListItem item) {
				Object object=item.getModelObject();
				item.add( new Check("check",  new Model(Integer. toString (item.getIndex()))));
				item.add( new Label("name", new Model((Serializable) NAMES .get(object))));
			};
		};
		group.add(sites);
	}
}
