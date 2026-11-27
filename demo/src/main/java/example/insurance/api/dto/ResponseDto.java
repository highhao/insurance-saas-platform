package example.insurance.api.dto;
import java.time.Instant;
public class ResponseDto<T> {
    private final T data;
    private final Instant startedAt;
    public ResponseDto(T data, Instant startedAt) { this.data = data; this.startedAt = startedAt; }
    public T getData() { return data; }
    public Instant getStartedAt() { return startedAt; }
}
