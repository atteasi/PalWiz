package com.example.application.views.signUp;

import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import com.example.application.data.Role;
import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.views.LanguageSelector;
import com.example.application.views.TranslationUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route("register")
public class RegisterView extends Composite {
    Locale currentLocale = TranslationUtils.getCurrentLocale();
    ResourceBundle messages = ResourceBundle.getBundle("messages", currentLocale);
    private final UserService service;

    public RegisterView(UserService service) {
        this.service = service;
        initContent();

    }

    @Override
    protected Component initContent() {
        LanguageSelector languageSelector = new LanguageSelector();
        languageSelector.setClassName("language-selector");

        TextField firstName = new TextField(messages.getString("name"));
        TextField surName = new TextField(messages.getString("lastName"));
        TextField username = new TextField(messages.getString("username"));
        PasswordField password1 = new PasswordField(messages.getString("password"));
        PasswordField password2 = new PasswordField(messages.getString("makeSure"));
        password1.setErrorMessage(messages.getString("errorEmpty"));

        firstName.setWidth("300px");
        surName.setWidth("300px");
        username.setWidth("300px");
        password1.setWidth("300px");
        password2.setWidth("300px");
        password2.addClassName("save-button");

        H2 otsikko = new H2(messages.getString("register"));
        otsikko.addClassName("otsikko");

        VerticalLayout formLayout = new VerticalLayout(
                otsikko,
                firstName, surName,
                username,
                password1,
                password2,
                new Button(messages.getString("send"), event -> {
                    if (password1.isEmpty()) {
                        Notification notif = new Notification(messages.getString("emptyPassword"), 2000);
                        notif.open();
                    } else if (password2.isEmpty()) {
                        Notification notif = new Notification(messages.getString("emptyPassword"), 2000);
                        notif.open();
                    } else {
                        register(
                                firstName.getValue(),
                                surName.getValue(),
                                username.getValue(),
                                password1.getValue(),
                                password2.getValue());
                    }
                }));

        formLayout.setAlignItems(FlexLayout.Alignment.CENTER);
        formLayout.setPadding(true);
        formLayout.setSpacing(false);
        formLayout.setWidth("400px");
        formLayout.getStyle().set("margin", "auto");
        formLayout.getStyle().set("margin-top", "8px");

        languageSelector.getStyle().set("position", "absolute");
        languageSelector.getStyle().set("top", "10px");
        languageSelector.getStyle().set("left", "25px");

        Div mainLayout = new Div();
        mainLayout.add(languageSelector);
        mainLayout.add(formLayout);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("100%");

        return mainLayout;
    }

    private void register(String firstName, String surName, String username, String password1, String password2) {
        if (username.trim().isEmpty()) {
            Notification.show(messages.getString("addUserName"));
        } else if (service.getByUsername(username) != null) {
            Notification.show(messages.getString("usernameTaken"));
        } else if (password1.isEmpty()) {

        } else if (password2.isEmpty()) {

        } else {
            Set<Role> roles = new HashSet();
            roles.add(Role.ADMIN);
            service.update(new User(firstName, surName, username, password1, roles));
            Notification
                    .show(messages.getString("newUserNamed") + " " + username + " " + messages.getString("created"));

        }
    }
}
