package com.example.weather_app.view;

import com.example.weather_app.model.Area;
import com.example.weather_app.service.AreaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

@Route("area/:city")
public class AreaView extends VerticalLayout implements BeforeEnterObserver {

    private final AreaService areaService;

    private IntegerField merkezId;
    private TextField il;
    private TextField ilce;
    private TextField aciklama;
    private NumberField boylam;
    private NumberField enlem;
    private IntegerField gunlukTahminIstNo;
    private IntegerField ilPlaka;
    private IntegerField saatlikTahminIstNo;
    private IntegerField sondurumIstNo;
    private IntegerField yukseklik;

    @Autowired
    public AreaView(AreaService areaService) {
        this.areaService = areaService;
    }

    @SneakyThrows
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Check if the cityName parameter is available in the URL
        if (event.getRouteParameters().get("city").isPresent()) {
            String city = event.getRouteParameters().get("city").get();
            displayArea(city);
        } else {
            // Handle the case where cityName is not present in the URL
            event.forwardTo(MainView.class);
        }
    }

    private void displayArea(String city) {
        Area areaData = areaService.getAreaDetails(city);

        // Display the area data in the UI
        merkezId = new IntegerField("Merkez Id");
        merkezId.setValue(areaData.getMerkezId());
        merkezId.setReadOnly(true);
        add(merkezId);

        il = new TextField("City");
        il.setValue(areaData.getIl());
        il.setReadOnly(true);
        add(il);

        ilce = new TextField("District");
        ilce.setValue(areaData.getIlce());
        ilce.setReadOnly(true);
        add(ilce);

        aciklama = new TextField("Description");
        aciklama.setValue(areaData.getAciklama());
        aciklama.setReadOnly(true);
        add(aciklama);

        boylam = new NumberField("Longitude");
        boylam.setValue(areaData.getBoylam());
        boylam.setReadOnly(true);
        add(boylam);

        enlem = new NumberField("Latitude");
        enlem.setValue(areaData.getEnlem());
        enlem.setReadOnly(true);
        add(enlem);

        gunlukTahminIstNo = new IntegerField("Daily Forecast");
        gunlukTahminIstNo.setValue(areaData.getGunlukTahminIstNo());
        gunlukTahminIstNo.setReadOnly(true);
        add(gunlukTahminIstNo);

        ilPlaka = new IntegerField("City Plate");
        ilPlaka.setValue(areaData.getIlPlaka());
        ilPlaka.setReadOnly(true);
        add(ilPlaka);

        saatlikTahminIstNo = new IntegerField("Hourly Forecast");
        saatlikTahminIstNo.setValue(areaData.getSaatlikTahminIstNo());
        saatlikTahminIstNo.setReadOnly(true);
        add(saatlikTahminIstNo);

        sondurumIstNo = new IntegerField("Last Status");
        sondurumIstNo.setValue(areaData.getSondurumIstNo());
        sondurumIstNo.setReadOnly(true);
        add(sondurumIstNo);

        yukseklik = new IntegerField("Height");
        yukseklik.setValue(areaData.getYukseklik());
        yukseklik.setReadOnly(true);
        add(yukseklik);

        Button backButton = new Button("Back", event1 -> getUI().ifPresent(ui -> ui.navigate(MainView.class)));
        add(backButton);
    }
}
