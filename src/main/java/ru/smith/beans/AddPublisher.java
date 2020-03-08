package ru.smith.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import ru.smith.annotations.Entities;
import ru.smith.models.Publisher;
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
public class AddPublisher implements Serializable {

    @Getter
    @Setter
    private String namePublisher;

    @Getter
    @Setter
    private String cityPublisher;

    @Getter
    @Setter
    private Publisher publisher;

    @Inject
    @ru.smith.annotations.EntityService(Entities.PUBLISHER)
    private EntityService publisherService;

    public List<String> completePublisher(String namePublisher) {
        String namePublisherLowerCase = namePublisher.toLowerCase();
        List<Publisher> publishers =
                (List<Publisher>) publisherService.findAll();
        return publishers.stream().filter(p -> p.getName().toLowerCase().contains(namePublisherLowerCase))
                .map(Publisher::getName).collect(Collectors.toList());
    }

    public void onBlur() {
        List<Publisher> publishers =
                (List<Publisher>) publisherService.findWithCondition("name = '" + namePublisher + "'");
        if (!publishers.isEmpty()) {
            setPublisher(publishers.get(0));
        } else {
            PrimeFaces.current().executeScript("PF('dlgAddPublisher').show()");
        }
    }

    public void add(){
        publisher = new Publisher();
        publisher.setName(namePublisher);
        publisher.setCity(cityPublisher);
        publisherService.saveEntity(publisher);
    }
}
