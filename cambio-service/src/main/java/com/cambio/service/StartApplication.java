package com.cambio.service;

import com.cambio.service.model.Cambio;
import com.cambio.service.repository.CambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class StartApplication implements CommandLineRunner {

    @Autowired
    private CambioRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Cambio cambio1 = new Cambio(null, "USD", "BRL", BigDecimal.valueOf(5.73), null, null);
        Cambio cambio2 = new Cambio(null, "USD", "EUR", BigDecimal.valueOf(0.84), null, null);
        Cambio cambio3 = new Cambio(null, "USD", "GPD", BigDecimal.valueOf(0.73), null, null);
        Cambio cambio4 = new Cambio(null, "USD", "ARS", BigDecimal.valueOf(592.56), null, null);
        Cambio cambio5 = new Cambio(null, "USD", "CLP", BigDecimal.valueOf(713.30), null, null);
        Cambio cambio6 = new Cambio(null, "USD", "COP", BigDecimal.valueOf(3665.00), null, null);
        Cambio cambio7 = new Cambio(null, "USD", "MXN", BigDecimal.valueOf(20.15), null, null);

        repository.saveAll(List.of(cambio1, cambio2, cambio3, cambio4, cambio5, cambio6, cambio7));
    }
}
