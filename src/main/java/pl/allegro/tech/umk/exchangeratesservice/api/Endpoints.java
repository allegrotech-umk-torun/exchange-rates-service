package pl.allegro.tech.umk.exchangeratesservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.tech.umk.exchangeratesservice.config.ResponseConfig;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

@RestController
record Endpoints(ResponseConfig responseConfig) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Endpoints.class);

    @GetMapping("/exchange-rates/latest")
    ResponseEntity<ExchangeRates> getExchangeRates(@RequestParam String base) throws InterruptedException {
        Thread.sleep(responseConfig.getDelay());
        LOGGER.info("Fetched exchange rates for base = {}", base);
        return ResponseEntity.ok().body(prepareExchangeRates(base));
    }

    @GetMapping("/setDelay/{delay}")
    ResponseEntity<String> setDelay(@PathVariable String delay) {
        responseConfig.setDelay(Long.parseLong(delay));
        return ResponseEntity.ok().body("Set delay to " + responseConfig.getDelay());
    }

    private ExchangeRates prepareExchangeRates(String base) {
        return new ExchangeRates(
                base,
                ZonedDateTime.now(),
                Map.of(
                        "EUR", BigDecimal.valueOf(0.21),
                        "USD", BigDecimal.valueOf(0.22),
                        "GBP", BigDecimal.valueOf(0.23)
                )
        );
    }

}