package example.insurance.api;
import example.insurance.api.dto.QuoteDio;
import example.insurance.api.dto.QuoteDto;
import example.insurance.api.dto.ResponseDto;
import example.insurance.application.CarQuoteService;
import example.insurance.application.TravelQuoteService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/demo/quotes")
public class QuoteController {
    private final CarQuoteService car;
    private final TravelQuoteService travel;
    public QuoteController(CarQuoteService car, TravelQuoteService travel) { this.car = car; this.travel = travel; }
    @PostMapping("/car")
    public ResponseDto<QuoteDto> car(@Valid @RequestBody QuoteDio input) { return car.request(input); }
    @PostMapping("/travel")
    public ResponseDto<QuoteDto> travel(@Valid @RequestBody QuoteDio input) { return travel.request(input); }
}
