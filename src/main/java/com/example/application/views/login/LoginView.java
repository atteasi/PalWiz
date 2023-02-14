package com.example.application.views.login;

import com.example.application.security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

    private final AuthenticatedUser authenticatedUser;

    public LoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

        
        
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nOma = i18n.getForm();
        i18nOma.setTitle("Kirjaudu");
        i18nOma.setUsername("Käyttäjänimi");
        i18nOma.setPassword("Salasana");
        i18nOma.setSubmit("Kirjaudu sisään");
        i18nOma.setForgotPassword("Luo tunnukset");
        
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Väärä käyttäjätunnus tai salasana");
        i18nErrorMessage.setMessage(
        "Tarkista että käyttäjätunnus ja salasana ovat oikein ja yritä uudestaan.");
        

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("PalWiz");
        i18n.getHeader().setDescription("Kirjaudu käyttäen tunnuksia user/user or admin/admin");
        i18n.setAdditionalInformation(null);
        
        addForgotPasswordListener(event -> {
            setOpened(false);
            UI.getCurrent().navigate("register");
            
      }); 
        
        setI18n(i18n);
        
        setForgotPasswordButtonVisible(true);
        setOpened(true);
        
        
        
        
    }
    
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            setOpened(false);
            event.forwardTo("");
        }

        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}
