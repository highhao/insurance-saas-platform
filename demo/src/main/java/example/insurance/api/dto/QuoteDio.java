package example.insurance.api.dto;
import javax.validation.constraints.NotBlank;
public class QuoteDio {
    @NotBlank private String reference;
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}
