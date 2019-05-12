package com.njupt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import entity.Cheese;

public class CheesrApplication extends WebApplication {

	// 模拟数据
	private List<Cheese> cheeses = Arrays.asList(new Cheese("Gouda", "Gouda is a yellowish Dutch", 1.65),
			new Cheese("Edam", "Edam is a yellowish Dutch", 1.25),
			new Cheese("Maasdam", "Maasdam is a yellowish Dutch", 1.05),
			new Cheese("Brie", "Brie is a yellowish Dutch", 2.65),
			new Cheese("Buxton Blue", "Buxton Blue is a yellowish Dutch", 1.66),
			new Cheese("Parmesan", "Parmesan is a yellowish Dutch", 3.96),
			new Cheese("Cheddar", "Cheddar is a yellowish Dutch", 4.62),
			new Cheese("Roquefort", "Roquefort is a yellowish Dutch", 1.85),
			new Cheese("njupt", "njupt is a yellowish Dutch", 1.21),
			new Cheese("apple", "apple is a yellowish Dutch", 9.52),
			new Cheese("gold", "gold is a yellowish Dutch", 3.25),
			new Cheese("wish", "wish is a yellowish Dutch", 1.10));
	//获取集合
	public List<Cheese> getCheeses() {
		return Collections.unmodifiableList(cheeses);
	}
	
	//初始化
	@Override
	protected void init() {
		super.init();
	}
	//get Application
	public static CheesrApplication get() {
		return (CheesrApplication) Application.get();
	}
	
	//初始显示哪个界面
	@Override
	public Class<? extends Page> getHomePage() {

		return CheesrPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		// TODO Auto-generated method stub
		return new CheesrSession(request);
	}
	
	

}
