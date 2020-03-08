package ru.smith.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import ru.smith.annotations.Entities;
import ru.smith.models.Publisher;
import ru.smith.models.Series;
import ru.smith.services.EntityService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
@NoArgsConstructor
public class AddSerie implements Serializable {

    @Getter
    @Setter
    private String nameSerie;

    @Setter
    @Getter
    private Series serie;

    @Inject
    @ru.smith.annotations.EntityService(Entities.SERIES)
    private EntityService seriesService;

    @Inject
    @ru.smith.annotations.EntityService(Entities.PUBLISHER)
    private EntityService publisherService;

    public List<String> completeSerie(Publisher publisher, String nameSerie) {
        String nameSerieLowerCase = nameSerie.toLowerCase();
        List<Series> series = publisher.getSeries();
        return series.stream().filter(s -> s.getName().toLowerCase().contains(nameSerieLowerCase))
                .map(Series::getName).collect(Collectors.toList());
    }

    public void onBlur(Publisher publisher) {
        List<Series> series = publisher.getSeries().stream()
                .filter(series1 -> series1.getName().equals(nameSerie)).collect(Collectors.toList());
        if (!series.isEmpty()) {
            setSerie(series.get(0));
        } else {
            PrimeFaces.current().executeScript("PF('dlgAddSerie').show()");
        }
    }

    public void add(Publisher publisher){
        serie = new Series();
        serie.setName(nameSerie);
        serie.setPublisher(publisher);
        seriesService.saveEntity(serie);
    }
}
