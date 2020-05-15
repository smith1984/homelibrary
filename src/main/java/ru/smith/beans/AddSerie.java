package ru.smith.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import ru.smith.annotations.Entities;
import ru.smith.models.Publisher;
import ru.smith.models.Series;
import ru.smith.services.EntityService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
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

    public List<String> completeSerie(String query) {
        Publisher publisher = (Publisher)UIComponent.
                getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("publisher");
        return ((List<Series>)seriesService.findWithCondition("publisher_id = " + publisher.getId())).
                stream().filter(s -> s.getName().toLowerCase().contains(query.toLowerCase()))
                .map(Series::getName).collect(Collectors.toList());
    }

    public Series onBlur(Publisher publisher) {
        if (publisher == null) return null;
        List<Series> series = ((List<Series>)seriesService.findWithCondition("publisher_id = " + publisher.getId())).stream()
                .filter(s -> s.getName().equals(nameSerie)).collect(Collectors.toList());
        if (!series.isEmpty()) {
            setSerie(series.get(0));
        } else {
            if (nameSerie != null && !nameSerie.equals(""))
            PrimeFaces.current().executeScript("PF('dlgAddSerie').show()");
        }
        return serie;
    }

    public Series add(Publisher publisher) {
        serie = new Series();
        serie.setName(nameSerie);
        serie.setPublisher(publisher);
        seriesService.saveEntity(serie);
        return serie;
    }
}
