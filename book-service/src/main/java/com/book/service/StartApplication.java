package com.book.service;

import com.book.service.model.Book;
import com.book.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book(null, "Michael C. Feathers", LocalDate.now(), new BigDecimal("8.57"), "Working effectively with legacy code", null, null);
        Book book2 = new Book(null, "Ralph Johnson, Erich Gamma, John Vlissides e Richard Helm", LocalDate.now(), new BigDecimal("7.87"), "Design Patterns", null, null);

        bookRepository.saveAll(List.of(book1, book2));

        /*('Robert C. Martin', '2009-01-10 00:00:00.000000', 13.46, 'Clean Code'),
        ('Crockford', '2017-11-07 15:09:01.674000', 11.71, 'JavaScript'),
        ('Steve McConnell', '2017-11-07 15:09:01.674000', 10.14, 'Code complete'),
        ('Martin Fowler e Kent Beck', '2017-11-07 15:09:01.674000', 15.38, 'Refactoring'),
        ('Eric Freeman, Elisabeth Freeman, Kathy Sierra, Bert Bates', '2017-11-07 15:09:01.674000', 19.23, 'Head First Design Patterns'),
        ('Eric Evans', '2017-11-07 15:09:01.674000', 16.09, 'Domain Driven Design'),
        ('Brian Goetz e Tim Peierls', '2017-11-07 15:09:01.674000', 13.99, 'Java Concurrency in Practice'),
        ('Susan Cain', '2017-11-07 15:09:01.674000', 21.51, 'O poder dos quietos'),
        ('Roger S. Pressman', '2017-11-07 15:09:01.674000', 9.79, 'Engenharia de Software: uma abordagem profissional'),
        ('Viktor Mayer-Schonberger e Kenneth Kukier', '2017-11-07 15:09:01.674000', 9.44, 'Big Data: como extrair volume, variedade, velocidade e valor da avalanche de informação cotidiana'),
        ('Richard Hunter e George Westerman', '2017-11-07 15:09:01.674000', 16.61, 'O verdadeiro valor de TI'),
        ('Marc J. Schiller', '2017-11-07 15:09:01.674000', 7.87, 'Os 11 segredos de líderes de TI altamente influentes'),
        ('Aguinaldo Aragon Fernandes e Vladimir Ferraz de Abreu', '2017-11-07 15:09:01.674000', 9.44, 'Implantando a governança de TI')*/
    }
}
