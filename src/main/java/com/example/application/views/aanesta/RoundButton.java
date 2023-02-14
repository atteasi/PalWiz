package com.example.application.views.aanesta;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;

public class RoundButton extends Button {

	public RoundButton(Image img) {
		addClassName("round-button");
		img.setWidth("200px");
		Button roundBtn = new Button(img);
		setIcon(img);
	}

}
