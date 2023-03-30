package sk.stuba.fei.uim.oop.assignment3.book.web;


import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;


import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/book")
public class BookController {


    private final IBookService service;

    public BookController(IBookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        var resultB = new ArrayList<BookResponse>();
        for (Book b : this.service.getAll()) {
            resultB.add(new BookResponse(b));
        }
        return resultB;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        return new ResponseEntity<>(new BookResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable("id")Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        return new BookResponse(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable("id")Long id,@RequestBody BookUpdateRequest body) throws NotFoundException, sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        return new BookResponse(this.service.update(id,body));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id")Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        this.service.delete(id);
    }

    @GetMapping("/{id}/amount")
    public Amount getAmount(@PathVariable("id") Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        return new Amount(this.service.getAmount(id));
    }
    @GetMapping("/{id}/lendCount")
    public Amount getLend(@PathVariable("id") Long id) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        return new Amount(this.service.getLend(id));
    }

    @PostMapping("/{id}/amount")
    public Amount addAmount(@PathVariable("id")Long id,@RequestBody Amount body) throws sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException {
        return new Amount(this.service.addAmount(id, body.getAmount()));
    }
}
