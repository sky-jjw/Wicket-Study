package com.njupt;

import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import entity.Cart;
import entity.Cheese;

@SuppressWarnings("serial")
public class CheesrPage extends WebPage{
	//get Session
	public CheesrSession getCheesrSession() {
		return (CheesrSession) getSession();
	}
	//从session中获取购物车
	public Cart getCart() {
		return getCheesrSession().getCart();
	}
	//从application中获取cheeses
	public List<Cheese> getCheeses(){
		return CheesrApplication.get().getCheeses();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CheesrPage() {
		add(new ListView<Cheese>("cheeses",getCheeses()) {
			@Override
			protected void populateItem(ListItem<Cheese> item) {
				Cheese cheese = item.getModelObject();
				item.add(new Label("name",cheese.getName()));
				item.add(new Label("description",cheese.getDescription()));
				item.add(new Label("price","$"+cheese.getPrice()));
				item.add(new Link("add",item.getModel()) {
					@Override
					public void onClick() {
						Cheese selectedCheese = (Cheese) getModelObject();
						getCart().getCheeses().add(selectedCheese);
						
					}

					public MarkupContainer setDefaultModel(IModel arg0) {
						// TODO Auto-generated method stub
						return null;
					}
					
				});
				
			}
		});
		add(new ShoppingCartPanel("shoppingPanel", getCart()));
	}
	//添加样式
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem
				.forReference(new CssResourceReference(CheesrPage.class, "base.css")));
//		response.render(JavaScriptHeaderItem
//				.forReference(new JQueryPluginResourceReference(CheesrPage.class, "XXX.js")));
	}

}
