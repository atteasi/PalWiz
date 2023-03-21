package com.example.application.views.kurssit;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.application.data.service.KurssiService;
import com.example.application.data.service.PalauteService;
import com.example.application.data.service.UserService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.User;

@RolesAllowed(value = { "ADMIN" })
@PageTitle("Kurssilistaus")
@Route(value = "kurssit", layout = MainLayout.class)

public class KurssitView extends VerticalLayout {
	KurssiService kurssiService;
	PalauteService palauteService;
	private User user;
	Grid<Kurssi> grid;
	List<Kurssi> kurssit;
	private static Div hint;
	private Kurssi poistettavaKurssi = new Kurssi();
	private Kurssi muokattavaKurssi = new Kurssi();


	public KurssitView(KurssiService service, UserService userService, PalauteService palauteService) {
		this.kurssiService = service;
		this.palauteService = palauteService;

		ConfirmDialog dialog = new ConfirmDialog();

		dialog.setText(
				"Haluatko varmasti poistaa kurssin ja siihen liittyvät palautteet pysyvästi?");

		dialog.setCancelable(true);
		dialog.setCancelText("Peru");
		// dialog.addCancelListener(event -> setStatus("Canceled"));

		dialog.setConfirmText("Poista");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(event -> {
			this.poistaKurssi(poistettavaKurssi);
		});

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			user = userService.getByUsername(authentication.getName());
		}

		setSpacing(false);

		Image img = new Image("images/empty-plant.png", "placeholder plant");
		img.setWidth("150px");
		add(img);

		add(new H2("Kurssit"));
		grid = new Grid<>(Kurssi.class, false);
		// grid.addColumn(Kurssi::getId).setHeader("ID");
		grid.addColumn(Kurssi::getNimi).setHeader("Nimi");
		grid.addColumn(Kurssi::getKoodi).setHeader("Koodi");
		grid.addColumn(Kurssi::getAloitusPvm).setHeader("Aloitus pvm");
		grid.addColumn(Kurssi::getLopetusPvm).setHeader("Lopetus pvm");

		grid.addColumn(
				new ComponentRenderer<>(Button::new, (button, kurssi) -> {
					button.addThemeVariants(ButtonVariant.LUMO_ICON,
							ButtonVariant.LUMO_ERROR,
							ButtonVariant.LUMO_TERTIARY);
					button.addClickListener(e -> {
						this.setPoistettavaKurssi(kurssi);
						dialog.setHeader("Poista kurssi " + poistettavaKurssi.getNimi() + "?");
						dialog.open();
					});
					button.setIcon(new Icon(VaadinIcon.TRASH));
				})).setHeader("Poista");

		grid.addColumn(
				new ComponentRenderer<>(Button::new, (button, kurssi) -> {
					button.addThemeVariants(ButtonVariant.LUMO_ICON,
							ButtonVariant.LUMO_CONTRAST,
							ButtonVariant.LUMO_TERTIARY);
					button.addClickListener(e -> {
						this.setMuokattavaKurssi(kurssi);
						ComponentUtil.setData(UI.getCurrent(), Kurssi.class, muokattavaKurssi);
						kurssiService.setNykyinenKurssiId(muokattavaKurssi.getId());
						button.getUI().ifPresent(ui -> ui.navigate("kurssi"));
					});
					button.setIcon(new Icon(VaadinIcon.ADJUST));
				})).setHeader("Muokkaa");

		
		kurssit = service.findUserKurssit(user.getId());
		grid.setItems(kurssit);

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");

		grid.addSelectionListener(selection -> {
			Optional<Kurssi> valittuKurssi = selection.getFirstSelectedItem();
			if (valittuKurssi.isPresent()) {
				Notification.show(valittuKurssi.get().getNimi() + " valittu, " + valittuKurssi.get().getId());
				ComponentUtil.setData(UI.getCurrent(), "kurssi", valittuKurssi.get().getId());
				service.setNykyinenKurssiId(valittuKurssi.get().getId());

				getUI().ifPresent(ui -> ui.navigate("palaute"));
			}
		});

		hint = new Div();
		hint.setText("Ei näytettäviä kursseja");
		hint.getStyle().set("padding", "var(--lumo-size-l)")
				.set("text-align", "center").set("font-style", "italic")
				.set("color", "var(--lumo-contrast-70pct)");

		add(hint, grid, dialog);
		refreshGrid();
	}

	private void poistaKurssi(Kurssi kurssi) {
		if (kurssi == null)
			return;
		kurssit.remove(kurssi);
		palauteService.poistaPalauteet(kurssi);
		kurssiService.poistaKurssi(kurssi);
		refreshGrid();
	}

	private void refreshGrid() {
		if (kurssit.size() > 0) {
			grid.setVisible(true);
			hint.setVisible(false);
			grid.getDataProvider().refreshAll();
		} else {
			grid.setVisible(false);
			hint.setVisible(true);
		}
	}

	private void setPoistettavaKurssi(Kurssi kurssi) {
		this.poistettavaKurssi = kurssi;
	}
	
	private void setMuokattavaKurssi(Kurssi kurssi) {
		muokattavaKurssi = kurssi;
	}

}
