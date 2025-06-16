package com.example.entity.controller;
import com.example.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")

public class BookController {
    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("Title one", "Author one", "science"),
                new Book("Title two", "Author two", "science"),
                new Book("Title three", "Author three", "history"),
                new Book("Title four", "Author four", "math"),
                new Book("Title five", "Author five", "math"),
                new Book("Title six", "Author six", "math")
        ));
    }

    // Fetching all books or by category using stream
    @GetMapping
    public List<Book> getBooks(@RequestParam(name = "category", required = false) String category) {
        if (category == null) {
            return books;
        }
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    //  Fetching book by title using stream
    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable(name = "title") String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    //  Creating book if title doesn't exist
    @PostMapping
    public void createBook(@RequestBody Book newBook) {
        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
        if (isNewBook) {
            books.add(newBook);
        }
    }

    // Updating existing book by title
    @PutMapping("/{title}")
    public void updateBook(@PathVariable(name = "title") String title, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    //  Delete book by title
    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable(name = "title") String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }


}
