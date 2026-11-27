package example.insurance.api.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
public class QuoteDio {
    @NotBlank @Pattern(regexp = "demo-[a-z]{2,12}")
    private String reference;
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}
