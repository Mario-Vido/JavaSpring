package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;


import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private AuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        Author newAuthor = new Author(request);
        return this.repository.save(newAuthor);
    }

    @Override
    public Author getById(Long id) throws NotFoundException {
        Author a= this.repository.findAuthorById(id);
        if (a == null) {
            throw new NotFoundException();
        }
        return a;
    }

    @Override
    public Author update(Long id, AuthorUpdateRequest request) throws NotFoundException {
        Author author=this.getById(id);
        if(request.getName()!=null){
            author.setName(request.getName());
        }
        if (request.getSurname()!=null){
            author.setSurname(request.getSurname());
        }
        return this.repository.save(author);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Author author= this.getById(id);
        this.repository.delete(author);
    }

    @Override
    public void addBookToAuthor(Long authorID, Book bookID) throws NotFoundException {
        Author a =this.getById(authorID);
        List<Book> b = a.getBooks();
        b.add(bookID);
        this.repository.save(a);
    }
}
