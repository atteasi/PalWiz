package com.example.application.views.aanesta;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class RoundButtons extends Button {



	public RoundButtons(Image img) {
		addClassName("round-button");
//		Image greenBtnImg = new Image("images/greenBtn.png", "green button");
//		Image redBtnImg = new Image("images/redBtn.png", "red button");
//		Image yellowBtnImg = new Image("images/yellowBtn.png", "yellow button");
		
//		greenBtnImg.setWidth("200px");
//        redBtnImg.setWidth("200px");
//        yellowBtnImg.setWidth("200px");
		img.setWidth("200px");
        
//		Button greenBtn = new Button(greenBtnImg);
//		Button yellowBtn = new Button(yellowBtnImg);
//		Button redBtn = new Button(redBtnImg);
		Button roundBtn = new Button(img);
		setIcon(img);
		//add(roundBtn);
		
		//createButtonsLayout();
	}

//	private Component createButtonsLayout() {
//		return new HorizontalLayout(greenBtn, yellowBtn, redBtn);
//
//	}
}
