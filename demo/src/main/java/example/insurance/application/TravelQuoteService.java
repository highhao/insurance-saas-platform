package example.insurance.application;
import example.insurance.api.dto.*;
import example.insurance.integration.MockQuoteWorker;
import java.time.Instant;
import org.springframework.stereotype.Service;
@Service
public class TravelQuoteService extends BaseService<QuoteDio, QuoteDto> {
    private final MockQuoteWorker worker;
    public TravelQuoteService(MockQuoteWorker worker) { this.worker = worker; }
    protected QuoteDto processData(QuoteDio input) {
        if (!"demo-trip".equals(input.getReference())) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "不符合示範條件");
        }
        if (!("JP".equals(input.getCountry()) || "SG".equals(input.getCountry()))) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "請選擇示範目的地");
        }
        try {
            java.time.LocalDate start = java.time.LocalDate.parse(input.getDepartureDate());
            java.time.LocalDate end = java.time.LocalDate.parse(input.getReturnDate());
            if (end.isBefore(start)) throw new IllegalArgumentException("date order");
        } catch (java.time.format.DateTimeParseException | NullPointerException ex) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "請填寫有效的示範旅程日期");
        } catch (IllegalArgumentException ex) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "返程日期不可早於出發日期");
        }
        return worker.quote("travel", input.getReference());
    }
    protected ResponseDto<QuoteDto> generateResult(QuoteDto data, Instant startedAt) { return new ResponseDto<>(data, startedAt); }
}
