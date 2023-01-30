package com.example.application.views.aanesta;

import javax.annotation.security.RolesAllowed;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed (value = { "USER", "ADMIN" }) 
@PageTitle("Äänestä")
@Route(value = "aanesta", layout = MainLayout.class)
public class AanestaView  extends VerticalLayout{
    int counter = 0;

    public AanestaView() {
        setHeight("500px");
        Image greenBtnImg = new Image("images/greenBtn.png", "green button");
        Image redBtnImg = new Image("images/redBtn.png", "red button");
        Image yellowBtnImg = new Image("images/yellowBtn.png", "yellow button");

        greenBtnImg.setWidth("200px");
        redBtnImg.setWidth("200px");
        yellowBtnImg.setWidth("200px");

        Button greenBtn = new Button(greenBtnImg);
        Button yellowBtn = new Button(yellowBtnImg);
        Button redBtn = new Button(redBtnImg);


        TextArea textArea = new TextArea();

        greenBtn.addClickListener(clickEvent -> {
        counter++;
        textArea.setValue(Integer.toString(counter));
        });
        HorizontalLayout hl = new HorizontalLayout();

        hl.add(greenBtn);
        hl.add(yellowBtn);
        hl.add(redBtn);
        //add(textArea);

       // add(new H2("Mitä pidit oppitunnista?"));
       // add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        hl.setWidthFull();
        hl.setSpacing(false);
        hl.setHeight("500px");
        hl.setJustifyContentMode(JustifyContentMode.CENTER);
       // hl.setVerticalComponentAlignment(Alignment.CENTER, redBtn, greenBtn, yellowBtn);
        hl.setAlignItems(Alignment.CENTER);
        add(hl);
        //getStyle().set("text-align", "center");
    }

}