package sk.stuba.fei.uim.oop.assignment3.ListBooks.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class LendingListRequest {
    private List<BookResponse> lendingList=new ArrayList<>();
    private boolean lended;
}
