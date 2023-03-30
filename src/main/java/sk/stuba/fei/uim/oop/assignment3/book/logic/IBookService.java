package sk.stuba.fei.uim.oop.assignment3.book.logic;

import javassist.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAll();
    Book create(BookRequest request) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

    Book getById(Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
    Book update(long id, BookUpdateRequest request) throws NotFoundException, sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
    void delete(Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
    int getAmount(Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
    int getLend(Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
    int addAmount(Long id, int increment) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
    void addLendCount(Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
    void removeLendCount(Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
}
