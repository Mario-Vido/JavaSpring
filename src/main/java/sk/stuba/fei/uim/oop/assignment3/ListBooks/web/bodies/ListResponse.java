package sk.stuba.fei.uim.oop.assignment3.ListBooks.web.bodies;

import lombok.Getter;

import sk.stuba.fei.uim.oop.assignment3.ListBooks.data.LendList;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListResponse {
    private final long id;
    private final List<BookResponse> lendingList;
    private final boolean lended;


    public ListResponse(LendList list){
        this.id=list.getId();
        this.lendingList=list.getLendingList().stream().map(BookResponse::new).collect(Collectors.toList());
        this.lended= list.isLended();
    }
}
