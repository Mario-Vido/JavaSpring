package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookUpdateRequest extends Amount {
    private String name;
    private String description;
    private Long author;
    private int pages;
}
