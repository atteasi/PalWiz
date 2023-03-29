package com.example.application.views.login;

import java.util.Locale;
import java.util.ResourceBundle;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.TranslationUtils;
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
    Locale currentLocale = TranslationUtils.getCurrentLocale();
	ResourceBundle messages;

    public LoginView(AuthenticatedUser authenticatedUser, UserService service) {
        this.authenticatedUser = authenticatedUser;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));
        messages = ResourceBundle.getBundle("messages", currentLocale);

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nOma = i18n.getForm();
        i18nOma.setTitle(messages.getString("sign"));
        i18nOma.setUsername(messages.getString("username"));
        i18nOma.setPassword(messages.getString("password"));
        i18nOma.setSubmit(messages.getString("login"));
        i18nOma.setForgotPassword(messages.getString("createUser"));
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle(messages.getString("errorUser"));
        i18nErrorMessage.setMessage(
            messages.getString("checkStuff"));

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("PalWiz");
        i18n.getHeader().setDescription(
            messages.getString("loginInfo"));
        i18n.setAdditionalInformation(null);

        addForgotPasswordListener(event -> {
            setOpened(false);
            UI.getCurrent().navigate("register");

        });

        addLoginListener(event -> {
            setOpened(false);
            UI.getCurrent().navigate("kurssit");
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
