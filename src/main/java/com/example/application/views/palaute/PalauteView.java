package com.example.application.views.palaute;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;
import com.example.application.data.service.KurssiService;
import com.example.application.data.service.PalauteService;
import com.example.application.views.MainLayout;
import com.example.application.views.TranslationUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@AnonymousAllowed
@PageTitle("Palaute")
@Route(value = "palaute", layout = MainLayout.class)

@RolesAllowed("ADMIN")
// @PreserveOnRefresh
public class PalauteView extends Main {
    PalauteService service;
    KurssiService kurssiService;
    DataSeries series2 = new DataSeries();
    Chart chart = new Chart(ChartType.PIE);
    Chart chart2 = new Chart(ChartType.PIE);
    Locale currentLocale = TranslationUtils.getCurrentLocale();
    private ResourceBundle messages;

    public PalauteView(PalauteService service, KurssiService kService) {
        messages = ResourceBundle.getBundle("messages", currentLocale);
        this.service = service;
        this.kurssiService = kService;
        addClassName("palaute-view");

        Board board = new Board();
        board.addRow(palauteNakyma(), luoPiirakkaGraafi(), createResponseTimes());
        add(board);
    }

    // private Component createHighlight(String title, String value, Double
    // percentage) {
    // VaadinIcon icon = VaadinIcon.ARROW_UP;
    // String prefix = "";
    // String theme = "badge";

    // if (percentage == 0) {
    // prefix = "±";
    // } else if (percentage > 0) {
    // prefix = "+";
    // theme += " success";
    // } else if (percentage < 0) {
    // icon = VaadinIcon.ARROW_DOWN;
    // theme += " error";
    // }

    // H2 h2 = new H2(title);
    // h2.addClassNames(FontWeight.NORMAL, Margin.NONE, TextColor.SECONDARY,
    // FontSize.XSMALL);

    // Span span = new Span(value);
    // span.addClassNames(FontWeight.SEMIBOLD, FontSize.XXXLARGE);

    // Icon i = icon.create();
    // i.addClassNames(BoxSizing.BORDER, Padding.XSMALL);

    // Span badge = new Span(i, new Span(prefix + percentage.toString()));
    // badge.getElement().getThemeList().add(theme);

    // VerticalLayout layout = new VerticalLayout(h2, span, badge);
    // layout.addClassName(Padding.LARGE);
    // layout.setPadding(false);
    // layout.setSpacing(false);
    // return layout;
    // }

    // private Component createViewEvents() {
    // // Header
    // Select year = new Select();
    // year.setItems("2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
    // "2019", "2020", "2021");
    // year.setValue("2021");
    // year.setWidth("100px");

    // HorizontalLayout header = createHeader("View events", "City/month");
    // header.add(year);

    // // Chart
    // Chart chart = new Chart(ChartType.AREASPLINE);
    // Configuration conf = chart.getConfiguration();
    // conf.getChart().setStyledMode(true);

    // XAxis xAxis = new XAxis();
    // xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
    // "Sep", "Oct", "Nov", "Dec");
    // conf.addxAxis(xAxis);

    // conf.getyAxis().setTitle("Values");

    // PlotOptionsAreaspline plotOptions = new PlotOptionsAreaspline();
    // plotOptions.setPointPlacement(PointPlacement.ON);
    // plotOptions.setMarker(new Marker(false));
    // conf.addPlotOptions(plotOptions);

    // conf.addSeries(new ListSeries("Berlin", 189, 191, 291, 396, 501, 403, 609,
    // 712, 729, 942, 1044, 1247));
    // conf.addSeries(new ListSeries("London", 138, 246, 248, 348, 352, 353, 463,
    // 573, 778, 779, 885, 887));
    // conf.addSeries(new ListSeries("New York", 65, 65, 166, 171, 293, 302, 308,
    // 317, 427, 429, 535, 636));
    // conf.addSeries(new ListSeries("Tokyo", 0, 11, 17, 123, 130, 142, 248, 349,
    // 452, 454, 458, 462));

    // // Add it all together
    // VerticalLayout viewEvents = new VerticalLayout(header, chart);
    // viewEvents.addClassName(Padding.LARGE);
    // viewEvents.setPadding(false);
    // viewEvents.setSpacing(false);
    // viewEvents.getElement().getThemeList().add("spacing-l");
    // return viewEvents;
    // }

    private Component palauteNakyma() {
        Kurssi kurzzi = kurssiService.findKurssi(kurssiService.getNykyinenKurssiId());
        HorizontalLayout header = createHeader(kurzzi.getNimi(), kurzzi.getKoodi());

        Grid<Palaute> grid = new Grid<>(Palaute.class, false);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        Set<LocalDate> dateSet = new HashSet<>();
        List<Palaute> palautteet = service.findPalautteetByKurssi(kurzzi);
        List<Palaute> distinctPalaute = palautteet.stream().filter(e -> dateSet.add(e.getPaivamaara()))
                .collect(Collectors.toList());
        for (Palaute palaute : distinctPalaute) {
            palaute.setKokonaismaara(service.countAllPalautteetByIDAndDate(kurzzi, palaute.getPaivamaara()));
        }
        grid.addColumn(Palaute::getPaivamaara).setHeader(messages.getString("date"));
        grid.addColumn(Palaute::getKokonaismaara).setHeader(messages.getString("feedbackAmmount"));
        grid.setItems(distinctPalaute);

        if (!palautteet.isEmpty()) {
            service.setNykyinenPalautePvm(palautteet.get(0).getPaivamaara());
        }

        grid.addSelectionListener(selection -> {
            Optional<Palaute> optionalPalautePvm = selection.getFirstSelectedItem();
            if (optionalPalautePvm.isPresent()) {
                Notification.show(optionalPalautePvm.get().getPaivamaara() + " " + messages.getString("chosen"));
                service.setNykyinenPalautePvm(optionalPalautePvm.get().getPaivamaara());
                series2.clear();
                updateGraphSeries(series2);
                chart2.getConfiguration().setSeries(series2);
                chart2.drawChart();
            }
        });

        VerticalLayout palauteNakyma = new VerticalLayout(header, grid);
        palauteNakyma.addClassName(Padding.LARGE);
        palauteNakyma.setPadding(false);
        palauteNakyma.setSpacing(false);
        palauteNakyma.getElement().getThemeList().add("spacing-l");
        return palauteNakyma;
    }

    private DataSeries updateGraphSeries(DataSeries series) {
        Kurssi kurssi = kurssiService.findKurssi(kurssiService.getNykyinenKurssiId());
        series.add(new DataSeriesItem(messages.getString("good"),
                service.findAllGoodByIDAndDate(kurssi, service.getNykyinenPalautePvm()).size()));
        series.add(new DataSeriesItem(messages.getString("neutral"),
                service.findAllNeutralByIDAndDate(kurssi, service.getNykyinenPalautePvm()).size()));
        series.add(new DataSeriesItem(messages.getString("bad"),
                service.findAllBadByIDAndDate(kurssi, service.getNykyinenPalautePvm()).size()));
        return series;
    }

    private Component luoPiirakkaGraafi() {

        HorizontalLayout header = createHeader(messages.getString("feedbackHeader"), "");

        // Chart
        PlotOptionsPie options = new PlotOptionsPie();
        options.setCenter("200", "150");
        options.setSize("65%");
        options.setAllowPointSelect(true);
        options.setCursor(Cursor.POINTER);
        options.setShowInLegend(true);

        Configuration conf = chart2.getConfiguration();
        conf.getChart().setStyledMode(true);
        conf.setPlotOptions(options);
        chart2.setThemeName("gradient");
        conf.getLegend().setLabelFormat("{name} - ({y})");
        conf.getLegend().setAlign(HorizontalAlign.RIGHT);

        DataSeries series = new DataSeries();

        Object valittuKurssiID = kurssiService.getNykyinenKurssiId();

        if (valittuKurssiID != null)

        {

            Kurssi kurssi = kurssiService.findKurssi(kurssiService.getNykyinenKurssiId());
            series2.add(new DataSeriesItem(messages.getString("good"),
                    service.findAllGoodByIDAndDate(kurssi, service.getNykyinenPalautePvm()).size()));
            series2.add(new DataSeriesItem(messages.getString("neutral"),
                    service.findAllNeutralByIDAndDate(kurssi, service.getNykyinenPalautePvm()).size()));
            series2.add(new DataSeriesItem(messages.getString("bad"),
                    service.findAllBadByIDAndDate(kurssi, service.getNykyinenPalautePvm()).size()));
        }

        conf.addSeries(series2);

        // Add it all together
        VerticalLayout palauteNakyma = new VerticalLayout(header, chart2);
        return palauteNakyma;
    }

    // Tämä oli vertailukohtana toiselle piechartille
    private Component createResponseTimes() {

        HorizontalLayout header = createHeader(messages.getString("feedbackHeaderAll"), "");

        // Chart
        PlotOptionsPie options = new PlotOptionsPie();
        options.setCenter("200", "150");
        options.setSize("65%");
        options.setAllowPointSelect(true);
        options.setCursor(Cursor.POINTER);
        options.setShowInLegend(true);

        Configuration conf = chart.getConfiguration();
        conf.getChart().setStyledMode(true);
        conf.setPlotOptions(options);
        chart.setThemeName("gradient");
        conf.getLegend().setLabelFormat("{name} - ({y})");
        conf.getLegend().setAlign(HorizontalAlign.RIGHT);

        DataSeries series = new DataSeries();

        Object valittuKurssiID = kurssiService.getNykyinenKurssiId();
        VaadinSession.getCurrent().setAttribute("kurssiID", valittuKurssiID);

        if (valittuKurssiID == null)

        {
            /*
             * series.add(new DataSeriesItem("Hyvä", service.findAllGood().size()));
             * series.add(new DataSeriesItem("Neutraali", service.findAllNeutral().size()));
             * series.add(new DataSeriesItem("Huono", service.findAllBad().size()));
             */
        } else {
            // Kurssi kurssi = kurssiService.findKurssi((int) valittuKurssiID);
            Kurssi kurssi = kurssiService.findKurssi(kurssiService.getNykyinenKurssiId());
            series.add(new DataSeriesItem(messages.getString("good"), service.findAllGoodByID(kurssi).size()));
            series.add(new DataSeriesItem(messages.getString("neutral"), service.findAllNeutralByID(kurssi).size()));
            series.add(new DataSeriesItem(messages.getString("bad"), service.findAllBadByID(kurssi).size()));
        }

        conf.addSeries(series);

        // Add it all together
        VerticalLayout palauteNakyma = new VerticalLayout(header, chart);
        // palauteNakyma.addClassName(Padding.LARGE);
        // palauteNakyma.setPadding(false);
        // palauteNakyma.setSpacing(false);
        // palauteNakyma.getElement().getThemeList().add("spacing-l");
        return palauteNakyma;
    }

    private HorizontalLayout createHeader(String title, String subtitle) {
        H2 h2 = new H2(title);
        h2.addClassNames(FontSize.XLARGE, Margin.NONE);

        Span span = new Span(subtitle);
        span.addClassNames(TextColor.SECONDARY, FontSize.XSMALL);

        VerticalLayout column = new VerticalLayout(h2, span);
        column.setPadding(false);
        column.setSpacing(false);

        HorizontalLayout header = new HorizontalLayout(column);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.setSpacing(false);
        header.setWidthFull();
        return header;
    }

}
