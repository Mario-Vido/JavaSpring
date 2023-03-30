package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository repository;
    @Autowired
    private IAuthorService authorService;

    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }



    @Override
    public Book create(BookRequest request) throws NotFoundException {
        Author author=authorService.getById(request.getAuthor());
        if(author!=null) {
            Book newBook = new Book();
            newBook.setName(request.getName());
            newBook.setDescription(request.getDescription());
            newBook.setAuthor(request.getAuthor());
            newBook.setPages(request.getPages());
            newBook.setAmount(request.getAmount());
            newBook.setLendCount(request.getLendCount());
            this.repository.save(newBook);
            authorService.addBookToAuthor(request.getAuthor(), newBook);
            return this.repository.save(newBook);
        }
        return null;
    }


    @Override
    public Book getById(Long bookId) throws NotFoundException {
        return this.repository.findById(bookId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Book update(long id, BookUpdateRequest request) throws NotFoundException {
        Book book = this.getById(id);
        if (request.getName() != null) {
            book.setName(request.getName());
        }
        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
        if (request.getAuthor() != 0) {
            book.setAuthor(request.getAuthor());
            authorService.addBookToAuthor(request.getAuthor(), book);
        }
        if (request.getPages() != 0) {
            book.setPages(request.getPages());
        }
        return this.repository.save(book);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Book book= this.getById(id);
        Author author=authorService.getById(book.getAuthor());
        author.getBooks().remove(book);
        this.repository.delete(book);
    }

    @Override
    public int getAmount(Long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int getLend(Long id) throws NotFoundException {
        return this.getById(id).getLendCount();
    }

    @Override
    public int addAmount(Long id, int increment) throws NotFoundException {
        Book book=this.getById(id);
        book.setAmount(book.getAmount()+increment);
        this.repository.save(book);
        return book.getAmount();
    }

    @Override
    public void addLendCount(Long id) throws NotFoundException {
        Book book =this.getById(id);
        book.setLendCount(book.getLendCount()+1);
        this.repository.save(book);
    }

    @Override
    public void removeLendCount(Long id) throws NotFoundException {
        Book book =this.getById(id);
        book.setLendCount(book.getLendCount()-1);
        this.repository.save(book);
    }

}
