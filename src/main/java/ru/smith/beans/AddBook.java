package ru.smith.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@NoArgsConstructor
public class AddBook implements Serializable {

    @Getter
    @Setter
    private String bookTitle;

    @Getter
    @Setter
    private Integer countPages;

    @Getter
    @Setter
    private Integer yearPublishing;

    public String addBook() {
        return "/library?faces-redirect=true";
    }
}
