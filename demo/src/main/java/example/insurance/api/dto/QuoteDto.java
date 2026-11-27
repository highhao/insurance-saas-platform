package example.insurance.api.dto;
public class QuoteDto {
    private final String category;
    private final String message;
    public QuoteDto(String category, String message) { this.category = category; this.message = message; }
    public String getCategory() { return category; }
    public String getMessage() { return message; }
}
