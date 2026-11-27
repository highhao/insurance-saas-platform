package example.insurance.application;
import example.insurance.api.dto.ResponseDto;
import java.time.Instant;
/** 簡化的共用流程；正式專案另有身份與例外處理。 */
public abstract class BaseService<I, O> {
    public final ResponseDto<O> request(I input) {
        Instant startedAt = Instant.now();
        O data = processData(input);
        return generateResult(data, startedAt);
    }
    protected abstract O processData(I input);
    protected abstract ResponseDto<O> generateResult(O data, Instant startedAt);
}
