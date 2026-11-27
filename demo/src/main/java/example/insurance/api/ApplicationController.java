package example.insurance.api;
import example.insurance.api.dto.ApplicationDio;
import example.insurance.api.dto.ResponseDto;
import example.insurance.application.MockApplicationService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/demo/application")
public class ApplicationController {
    private final MockApplicationService service;
    public ApplicationController(MockApplicationService service) { this.service = service; }
    @PostMapping("/{stage}")
    public ResponseDto<Map<String, Object>> apply(@PathVariable String stage, @Valid @RequestBody ApplicationDio input) {
        if (!input.isComplete() || !(stage.equals("reprice") || stage.equals("submit"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "示範資料不完整");
        }
        return service.request(new MockApplicationService.Input(stage, input));
    }
}
