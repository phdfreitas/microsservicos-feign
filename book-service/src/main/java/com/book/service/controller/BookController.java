package com.book.service.controller;

import com.book.service.model.Book;
import com.book.service.proxy.CambioProxy;
import com.book.service.repository.BookRepository;
import com.book.service.response.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

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

    /*
      =-=-= Versão usando Rest Template =-=-=
    @GetMapping("/{id}/{currency}")
    public Book findBook(@PathVariable Long id, @PathVariable String currency){

        var book = repository.findById(id).get();

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/cambio-service/{amount}/{from}/{to}",
                        Cambio.class,
                        params);

        var cambio = response.getBody();

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setCurrency(currency);
        book.setPrice(cambio.getConvertedValue());
        return book;
    }*/

}
