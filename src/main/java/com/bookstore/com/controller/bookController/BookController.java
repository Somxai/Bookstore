package com.bookstore.com.controller.bookController;

import com.bookstore.com.domain.book.Book;
import com.bookstore.com.dto.bookDto.BookDto;
import com.bookstore.com.services.bookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {


    @Autowired
    BookService bookService;


    @GetMapping( path = "/fetchBooks")
    public ResponseEntity<List<BookDto>> fetchAllBook(@RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                                      @RequestParam(value = "sort",defaultValue = "id") String sort){
        return ResponseEntity.ok(bookService.fetchAll(pageNo,pageSize,sort));
    }


    @GetMapping(path = "/fetchByTitle/{title}")
    public ResponseEntity<BookDto> fetchByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.fetchByTitle(title));

    }

    @GetMapping(path = "/fetchByTitleNormal/{title}")
    public ResponseEntity<Book> fetchByTitleNormal(@PathVariable String title){
        return ResponseEntity.ok(bookService.fetchByTitleNormal(title));

    }

    @GetMapping(path = "/fetchByAuthor/{author}")
    public ResponseEntity<List<BookDto>> fetchByAuthor(@PathVariable String author){
        return ResponseEntity.ok(bookService.fetchByAuthor(author));

    }



    @GetMapping(path = "/fetchById/{id}")
    public ResponseEntity<BookDto> fetchById(@PathVariable Long id){

        BookDto bookDto1 = bookService.fetchById(id);

        return ResponseEntity.ok(bookDto1);
    }


    @PostMapping(path = "createBook")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.createBook(bookDto));

    }

    @DeleteMapping(path = "deleteBook")
    public ResponseEntity<Boolean> book(@RequestBody Book book){
        return ResponseEntity.ok(bookService.removeBook(book));

    }

}
