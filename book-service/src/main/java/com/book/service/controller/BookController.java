package com.book.service.controller;

import com.book.service.model.Book;
import com.book.service.proxy.CambioProxy;
import com.book.service.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping("/{id}/{currency}")
    public Book findBook(@PathVariable Long id, @PathVariable String currency){

        var book = repository.findById(id).get();
        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment("Book port: " + port + " FEIGN" + "Cambio port " + cambio.getEnvironment());
        book.setCurrency(currency);
        book.setPrice(cambio.getConvertedValue());
        return book;
    }

}
