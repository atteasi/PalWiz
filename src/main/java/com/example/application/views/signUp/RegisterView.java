package com.example.application.views.signUp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.servlet.mvc.LastModified;

import com.example.application.data.Role;
import com.example.application.data.entity.Palaute;
import com.example.application.data.entity.User;
import com.example.application.data.service.PalauteService;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route("register")
public class RegisterView extends Composite {

	private final UserService service;
	
	
	
	public RegisterView(UserService service) {
		this.service = service;
		initContent();
	}

	@Override
    protected Component initContent() {
    	TextField firstName = new TextField("Nimi");
    	TextField surName = new TextField("Sukunimi");
        TextField username = new TextField("Käyttäjätunnus");
        PasswordField password1 = new PasswordField("Salasana");
        PasswordField password2 = new PasswordField("Varmista");
        password1.setErrorMessage("Kenttä on tyhjä!");
        
        return new VerticalLayout(
            new H2("Rekisteröidy"),
            firstName, surName,
            username,
            password1,
            password2,
            new Button("Send", event -> { 
    		if (password1.isEmpty()) {
    			Notification notif = new Notification("Salasanakenttä tyhjä!", 2000);
    			notif.open();
    		} 
    		else if (password2.isEmpty()) {
    			Notification notif = new Notification("Salasanakenttä tyhjä!", 2000);
    			notif.open();
    		} 
    		else {
            register(
            	firstName.getValue(),
            	surName.getValue(),
                username.getValue(),
                password1.getValue(),
                password2.getValue()
    		);}}
        ));
    }

	private void register(String firstName, String surName, String username, String password1, String password2) {
		if (username.trim().isEmpty()) {
			Notification.show("Lisää Käyttäjänimi");
		} 
		else if(service.getByUsername(username) != null) {
			Notification.show("Käyttäjänimi on varattu");
		}
		else if (password1.isEmpty()) {
		
		} else if (password2.isEmpty()) {

		} else {
			Set<Role> roles = new HashSet();
			roles.add(Role.ADMIN);
			service.update(new User(firstName, surName, username, password1, roles));
			Notification.show("Uusi käyttäjä nimeltä " + username + " luotu");
			
		}
	}
}
