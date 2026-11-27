package example.insurance.application;
import example.insurance.api.dto.*;
import example.insurance.integration.MockQuoteWorker;
import java.time.Instant;
import org.springframework.stereotype.Service;
@Service
public class CarQuoteService extends BaseService<QuoteDio, QuoteDto> {
    private final MockQuoteWorker worker;
    public CarQuoteService(MockQuoteWorker worker) { this.worker = worker; }
    protected QuoteDto processData(QuoteDio input) { return worker.quote("car", input.getReference()); }
    protected ResponseDto<QuoteDto> generateResult(QuoteDto data, Instant startedAt) { return new ResponseDto<>(data, startedAt); }
}
