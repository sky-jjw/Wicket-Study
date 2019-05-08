package com.njupt.jjw;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

@SuppressWarnings("serial")
public class HelloWorldPage extends WebPage{
	
	public HelloWorldPage() {
		add(new Label("showInfo","Hello World!"));
	}

}
