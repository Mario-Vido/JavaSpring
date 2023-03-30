package sk.stuba.fei.uim.oop.assignment3.ListBooks.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.ListBooks.data.LendList;
import sk.stuba.fei.uim.oop.assignment3.ListBooks.web.bodies.ListResponse;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.ListBooks.logic.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/list")
public class LendingListController {

    private final ILendingListService service;

    public LendingListController(ILendingListService service) {
        this.service = service;
    }

    @GetMapping
    public List<ListResponse> getAllList(){
        var result = new ArrayList<ListResponse>();
        for(LendList a:this.service.getAllList()){
            result.add(new ListResponse(a));
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ListResponse addList(){
        return new ListResponse(this.service.create());
    }

    @GetMapping("/{id}")
    public LendList getListById(@PathVariable("id")Long id) throws NotFoundException {
        return this.service.getListById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable("id")Long id) throws NotFoundException {
        this.service.deleteList(id);
    }

    @PostMapping("/{id}/add")
    public ListResponse addBookToList(@PathVariable("id")Long id, @RequestBody Book response) throws NotFoundException, IllegalOperationException {
        return new ListResponse(this.service.addToList(id, response));
    }

    @DeleteMapping("/{id}/remove")
    public void removeBook(@PathVariable("id")Long id,@RequestBody Book response) throws NotFoundException {
        this.service.remove(id,response);
    }

    @GetMapping(value = "/{id}/lend")
    public void lendList(@PathVariable("id")Long id) throws NotFoundException, IllegalOperationException {
        this.service.lendList(id);
    }
}
