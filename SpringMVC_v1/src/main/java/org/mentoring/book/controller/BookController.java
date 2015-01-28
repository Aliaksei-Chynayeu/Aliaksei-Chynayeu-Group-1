package org.mentoring.book.controller;

import java.util.Map;

import org.mentoring.book.form.Book;
import org.mentoring.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class BookController {
 
    @Autowired
    private BookService bookService;
 
    @RequestMapping("/")
    public String listBooks(Map<String, Object> map) {
 
        map.put("book", new Book());
        map.put("bookList", bookService.listBook());
 
        return "book";
    }
 
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book")
    Book book, BindingResult result) {
 
        bookService.addBook(book);
 
        return "redirect:/";
    }
 
    @RequestMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId")
    Integer bookId) {
 
        bookService.removeBook(bookId);
 
        return "redirect:/";
    }
}