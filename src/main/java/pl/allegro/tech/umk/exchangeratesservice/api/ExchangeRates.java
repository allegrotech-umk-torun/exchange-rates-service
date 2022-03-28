package pl.allegro.tech.umk.exchangeratesservice.api;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

record ExchangeRates(String currency, ZonedDateTime currentDate, Map<String, BigDecimal> rates) {
}