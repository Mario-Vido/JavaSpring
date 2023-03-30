package sk.stuba.fei.uim.oop.assignment3.ListBooks.logic;

import sk.stuba.fei.uim.oop.assignment3.ListBooks.data.LendList;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface ILendingListService {
    List<LendList> getAllList();
    LendList create();
    LendList getListById(Long id) throws NotFoundException;
    void deleteList(Long id) throws NotFoundException;
    LendList addToList(Long listID, Book body) throws NotFoundException, IllegalOperationException;
    void remove(Long id,Book body) throws NotFoundException;
    void lendList(Long id) throws NotFoundException, IllegalOperationException;
}
