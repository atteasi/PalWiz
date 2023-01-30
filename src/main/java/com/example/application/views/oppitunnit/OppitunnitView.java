package com.example.application.views.oppitunnit;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.apache.commons.lang3.StringUtils;

@RolesAllowed(value = { "ADMIN", "USER" }) 

@PageTitle("Oppitunnit")
@Route(value = "oppitunnit", layout = MainLayout.class)
public class OppitunnitView extends Div {

    private GridPro<Oppitunti> grid;
    private GridListDataView<Oppitunti> gridListDataView;

    private Grid.Column<Oppitunti> oppituntiColumn;
    private Grid.Column<Oppitunti> statusColumn;
    private Grid.Column<Oppitunti> dateColumn;

    public OppitunnitView() {
        addClassName("oppitunnit-view");
        setSizeFull();
        createGrid();
        add(grid);
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new GridPro<>();
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        List<Oppitunti> oppitunnit = getOppitunnit();
        gridListDataView = grid.setItems(oppitunnit);
    }

    private void addColumnsToGrid() {
        createOppituntiColumn();
        createStatusColumn();
        createDateColumn();
    }

    private void createOppituntiColumn() {
        oppituntiColumn = grid.addColumn(new ComponentRenderer<>(oppitunti -> {
            HorizontalLayout hl = new HorizontalLayout();
            hl.setAlignItems(Alignment.CENTER);
            Span span = new Span();
            span.setClassName("name");
            span.setText(oppitunti.getNimi());
            hl.add(span);
            return hl;
        })).setComparator(oppitunti -> oppitunti.getNimi()).setHeader("Oppitunti");
    }

    private void createStatusColumn() {
        statusColumn = grid.addEditColumn(Oppitunti::getNimi, new ComponentRenderer<>(ot -> {
            Span span = new Span();
            span.setText(ot.getStatus());
            span.getElement().setAttribute("theme", "badge " + ot.getStatus().toLowerCase());
            return span;
        })).select((item, newValue) -> item.setStatus(newValue), Arrays.asList("Ei Vastattu", "Vastattu", "Virhe"))
                .setComparator(ot -> ot.getStatus()).setHeader("Status");
    }

    private void createDateColumn() {
        dateColumn = grid
                .addColumn(new LocalDateRenderer<>(ot -> LocalDate.parse(ot.getDate()),
                        () -> DateTimeFormatter.ofPattern("M/d/yyyy")))
                .setComparator(ot -> ot.getDate()).setHeader("Date").setWidth("180px").setFlexGrow(0);
    } 
    
    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField oppituntiFilter = new TextField();
        oppituntiFilter.setPlaceholder("Filter");
        oppituntiFilter.setClearButtonVisible(true);
        oppituntiFilter.setWidth("100%");
        oppituntiFilter.setValueChangeMode(ValueChangeMode.EAGER);
        oppituntiFilter.addValueChangeListener(event -> gridListDataView
                .addFilter(ot -> StringUtils.containsIgnoreCase(ot.getNimi(), oppituntiFilter.getValue())));
        filterRow.getCell(oppituntiColumn).setComponent(oppituntiFilter);

        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setItems(Arrays.asList("Ei Vastattu", "Vastattu", "Virhe"));
        statusFilter.setPlaceholder("Filter");
        statusFilter.setClearButtonVisible(true);
        statusFilter.setWidth("100%");
        statusFilter.addValueChangeListener(
                event -> gridListDataView.addFilter(ot -> areStatusesEqual(ot, statusFilter)));
        filterRow.getCell(statusColumn).setComponent(statusFilter);

        DatePicker dateFilter = new DatePicker();
        dateFilter.setPlaceholder("Filter");
        dateFilter.setClearButtonVisible(true);
        dateFilter.setWidth("100%");
        dateFilter.addValueChangeListener(
                event -> gridListDataView.addFilter(ot -> areDatesEqual(ot, dateFilter)));
        filterRow.getCell(dateColumn).setComponent(dateFilter);

        
    }

    private boolean areStatusesEqual(Oppitunti ot, ComboBox<String> statusFilter) {
        String statusFilterValue = statusFilter.getValue();
        if (statusFilterValue != null) {
            return StringUtils.equals(ot.getStatus(), statusFilterValue);
        }
        return true;
    }

    private boolean areDatesEqual(Oppitunti ot, DatePicker dateFilter) {
        LocalDate dateFilterValue = dateFilter.getValue();
        if (dateFilterValue != null) {
            LocalDate otDate = LocalDate.parse(ot.getDate());
            return dateFilterValue.equals(otDate);
        }
        return true;
    }

    private List<Oppitunti> getOppitunnit() {
        return Arrays.asList(
                createOppitunti(1, "Suunnittelumallit", "Ei vastattu", "2023-05-09")
        );
    }

    private Oppitunti createOppitunti(int id, String nimi, String status, String date) {
        Oppitunti ot = new Oppitunti();
        ot.setId(id);
        ot.setNimi(nimi);
        ot.setStatus(status);
        ot.setDate(date);

        return ot;
    }
};
