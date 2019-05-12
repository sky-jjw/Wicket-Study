package com.njupt;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import entity.Address;
import entity.Cart;

@SuppressWarnings("serial")
public class Checkout extends CheesrPage{
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Checkout() {
		
		Form<Void> form = new Form<Void>("form");
		form.add(new FeedbackPanel("feedback"));
		Address address = getCart().getBillAddress();
		form.add(new TextField("name",new PropertyModel(address, "name")).setRequired(true));
		form.add(new TextField("street", new PropertyModel(address, "street")).setRequired(true));
		form.add(new TextField("zipcode", new PropertyModel(address, "zipcode")).setRequired(true));
		form.add(new TextField("city", new PropertyModel(address, "city")).setRequired(true));
		form.add(new Link<Void>("cancel") {

			@Override
			public void onClick() {
				setResponsePage(CheesrPage.class);
			}
		});
		form.add(new Button("order") {
			@Override
			public void onSubmit() {
				Cart cart = getCart();
				cart.getCheeses().clear();
				setResponsePage(CheesrPage.class);
			}
		});
		add(form);
	}

}
