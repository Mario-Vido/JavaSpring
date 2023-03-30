package sk.stuba.fei.uim.oop.assignment3.ListBooks.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.ListBooks.data.LendList;
import sk.stuba.fei.uim.oop.assignment3.ListBooks.data.LendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class LendingListService implements ILendingListService {

    @Autowired
    private LendingListRepository repository;
    @Autowired
    private IBookService bookService;

    @Override
    public List<LendList> getAllList() {
        return this.repository.findAll();
    }

    @Override
    public LendList create() {
        return this.repository.save(new LendList());
    }

    @Override
    public LendList getListById(Long id) throws NotFoundException {
        LendList list = this.repository.findLendListById(id);
        if (list == null) {
            throw new NotFoundException();
        } else {
            return list;
        }

    }

    @Override
    public void deleteList(Long id) throws NotFoundException {
        int i;
        long a;
        LendList lendList = this.getListById(id);
        if((lendList.isLended())){
            for(i=0;i<(lendList.getLendingList().size());i++) {
                a=lendList.getLendingList().get(i).getId();
                bookService.removeLendCount(a);
            }
        }
        this.repository.delete(lendList);
    }

    @Override
    public LendList addToList(Long listID, Book body) throws NotFoundException, IllegalOperationException {
        LendList list = this.getListById(listID);
        List<Book> b = list.getLendingList();
        var existingEntry = this.findBookEntryWithBook(list.getLendingList(),body.getId());
        if(existingEntry!=null || list.isLended() || bookService.getAmount(body.getId())==bookService.getLend(body.getId())){
            throw new IllegalOperationException();
        }
        b.add(body);
        if (bookService.getById(body.getId()) == null) {
            throw new NotFoundException();
        }
        return this.repository.save(list);
    }

    @Override
    public void remove(Long id, Book body) throws NotFoundException {
        LendList list = this.getListById(id);
        for(int i=0;i<list.getLendingList().size();i++){
            if(list.getLendingList().get(i).getId().equals(body.getId())){
                list.getLendingList().remove(i);
            }
        }
        this.repository.save(list);
    }

    @Override
    public void lendList(Long id) throws NotFoundException, IllegalOperationException {
        int i;
        long a;
        LendList list = this.getListById(id);
        if (list.isLended()) {
            throw new IllegalOperationException();
        } else {
            list.setLended(true);
            for(i=0;i<(list.getLendingList().size());i++) {
                a=list.getLendingList().get(i).getId();
                bookService.addLendCount(a);
            }

        }
        this.repository.save(list);
    }

    private Book findBookEntryWithBook(List<Book> entries, long bookId){
        for (var entry:entries){
            if(entry.getId().equals(bookId)){
                return entry;
            }
        }
        return null;
    }


}


