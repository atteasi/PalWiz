package com.example.application.views;

import java.util.Locale;
import java.util.ResourceBundle;
import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.koodi.KoodiView;
import com.example.application.views.kurssi.KurssiView;
import com.example.application.views.kurssit.KurssitView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;
    Locale currentLocale = TranslationUtils.getCurrentLocale();
    private ResourceBundle messages;
    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        messages = ResourceBundle.getBundle("messages", currentLocale);
        // LanguageSelector languageSelector = new LanguageSelector();
        // addToNavbar(languageSelector);

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
    }

    private void addDrawerContent() {
        LanguageSelector languageSelector = new LanguageSelector();
        languageSelector.setClassName("language-selector");

        H1 appName = new H1("PalWiz");
        appName.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0")
                .set("font-size", "30px");

        // Create a HorizontalLayout to hold appName and languageSelector
        com.vaadin.flow.component.orderedlayout.HorizontalLayout layout = new com.vaadin.flow.component.orderedlayout.HorizontalLayout();
        layout.add(appName, languageSelector);
        layout.setAlignItems(com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.BETWEEN);
        layout.setWidth("100%");
        layout.setPadding(true);

        Header header = new Header(layout);

        Tabs tabs = createNavigation();
        addToNavbar(header, tabs);
        createFooter();
    }

    private Tabs createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme

        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");
        java.util.Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            tabs.add(createTab(messages.getString("courses"), KurssitView.class), /*
                                                                                   * createTab("Koodi",
                                                                                   * KoodiView.class),
                                                                                   * createTab("Äänestä",
                                                                                   * AanestaView.class),
                                                                                   */
                    createTab(messages.getString("addCourse"), KurssiView.class));
            return tabs;
        } else {
            tabs.add(createTab(messages.getString("code"), KoodiView.class));
            return tabs;
        }
    }

    private void createFooter() {

        java.util.Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(user.getUsername());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem(messages.getString("signOut"), e -> {
                authenticatedUser.logout();
            });

            addToNavbar(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", messages.getString("login"));
            loginLink.getStyle().set("font-size", "var(--lumo-font-size-l)")
                    .set("right", "var(--lumo-space-l)").set("margin", "0")
                    .set("position", "absolute");
            addToNavbar(loginLink);
        }

    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

    private Tab createTab(String viewName, Class viewClass) {
        RouterLink link = new RouterLink();
        link.add(viewName);
        link.setRoute(viewClass);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}
