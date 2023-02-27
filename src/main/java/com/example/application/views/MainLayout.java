package com.example.application.views;

import java.io.ByteArrayInputStream;
import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.palaute.PalauteView;
import com.example.application.views.aanesta.AanestaView;
import com.example.application.views.koodi.KoodiView;
import com.example.application.views.kurssi.KurssiView;
import com.example.application.views.kurssit.KurssitView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    private AuthenticatedUser authenticatedUser;
    //private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
       // this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
    }

    private void addDrawerContent() {
    	H1 appName = new H1("PalWiz");
    	appName.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)").set("margin", "0")
                .set("position", "absolute");
        Header header = new Header(appName);

        Tabs tabs = createNavigation();
        addToNavbar(header, tabs);
        createFooter();
    }

    private Tabs createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme

        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");
        if (accessChecker.hasAccess(KurssiView.class)) {
        	tabs.add(createTab("Kurssi", KurssiView.class));
        }
        if (accessChecker.hasAccess(KurssitView.class)) {
        	createTab("Kurssilistaus", KurssitView.class);
        }
        
        tabs.add(createTab("Koodi", KoodiView.class), createTab("Äänestä", AanestaView.class),
                createTab("Palaute", PalauteView.class));
        return tabs;
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
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            addToNavbar(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Kirjaudu Sisään");
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
