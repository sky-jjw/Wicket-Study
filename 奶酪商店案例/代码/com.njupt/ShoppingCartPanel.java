package com.njupt;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import entity.Cart;
import entity.Cheese;

@SuppressWarnings("serial")
public class ShoppingCartPanel extends Panel{
	
	private Cart cart;

	public ShoppingCartPanel(String id, Cart cart) {
		super(id);
		this.cart = cart;
	}
	
	private Cart getCart() {
		return cart;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new ListView("cart",new PropertyModel(this, "cart.cheeses")) {
			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese) item.getModelObject();
				item.add(new Label("name",cheese.getName()));
				item.add(new Label("price",cheese.getPrice()));
				item.add(new Link("remove",item.getModel()) {
					@Override
					public void onClick() {
						Cheese selectedCheese = (Cheese) getModelObject();
						getCart().getCheeses().remove(selectedCheese);
						
					}

					public MarkupContainer setDefaultModel(IModel arg0) {
						return null;
					}
					
				});
			}
			
		});
		add(new Label("total",new Model() {

			@Override
			public Serializable getObject() {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				return nf.format(getCart().getTotal());
			}
			
		}));
		add(new Link<Void>("checkout") {

			@Override
			public void onClick() {
				setResponsePage(Checkout.class);
			}
			//购物车不空的时候显示
			@Override
			public boolean isVisible() {
				return !getCart().getCheeses().isEmpty();
			}
		});
	}
	
	

}
