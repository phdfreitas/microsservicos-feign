package com.cambio.service.controller;

import com.cambio.service.model.Cambio;
import com.cambio.service.repository.CambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository repository;

    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to
    ){
        var cambio = repository.findByFromAndTo(from, to);

        if(cambio == null){
            throw new RuntimeException("Currency unsupported");
        }

        var port = environment.getProperty("local.server.port");

        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue   = conversionFactor.multiply(amount);

        cambio.setEnvironment(port);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));

        return cambio;
    }
}
