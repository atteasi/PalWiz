package com.example.application.views.signUp;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route("register")
public class LuoTunnukset extends Composite {
    

    @Override
    protected Component initContent() {
        TextField username = new TextField("Käyttäjätunnus");
        PasswordField password1 = new PasswordField("Salasana");
        PasswordField password2 = new PasswordField("Varmista");
        return new VerticalLayout(
            new H2("Rekisteröidy"),
            username,
            password1,
            password2,
            new Button("Send", event -> register(
                username.getValue(),
                password1.getValue(),
                password2.getValue()
            ))
        );
    }

    private void register(String username, String password1, String password2){
        
    }
}
