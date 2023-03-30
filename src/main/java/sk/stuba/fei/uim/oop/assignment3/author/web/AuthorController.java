package sk.stuba.fei.uim.oop.assignment3.author.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/author")
public class AuthorController {


    private final IAuthorService service;

    public AuthorController(IAuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuthorResponse> getAllAuthors(){
        return this.service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AuthorResponse>addAuthor(@RequestBody AuthorRequest request){
        return new ResponseEntity<>(new AuthorResponse(this.service.create(request)),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable("id") Long id) throws NotFoundException {
        return new AuthorResponse(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public AuthorResponse updateAuthor(@PathVariable("id")Long id,@RequestBody AuthorUpdateRequest body) throws NotFoundException {
        return new AuthorResponse(this.service.update(id,body));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id")Long id) throws NotFoundException {
        this.service.delete(id);
    }
}
