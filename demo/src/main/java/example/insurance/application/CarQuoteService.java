package example.insurance.application;
import example.insurance.api.dto.*;
import example.insurance.integration.MockQuoteWorker;
import java.time.Instant;
import org.springframework.stereotype.Service;
@Service
public class CarQuoteService extends BaseService<QuoteDio, QuoteDto> {
    private final MockQuoteWorker worker;
    public CarQuoteService(MockQuoteWorker worker) { this.worker = worker; }
    protected QuoteDto processData(QuoteDio input) {
        if (!"demo-sedan".equals(input.getReference()) || !("sedan".equals(input.getVehicleType()) || "suv".equals(input.getVehicleType())) || input.getVehicleYear() == null || input.getVehicleYear() < 2015 || input.getVehicleYear() > 2025) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "不符合示範條件");
        }
        return worker.quote("car", input.getReference());
    }
    protected ResponseDto<QuoteDto> generateResult(QuoteDto data, Instant startedAt) { return new ResponseDto<>(data, startedAt); }
}
