package example.insurance.api.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
public class QuoteDio {
    @NotBlank @Pattern(regexp = "demo-[a-z]{2,12}") private String reference;
    private String vehicleType;
    private Integer vehicleYear;
    private String country;
    private String departureDate;
    private String returnDate;
    public String getReference() { return reference; }
    public void setReference(String v) { reference = v; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String v) { vehicleType = v; }
    public Integer getVehicleYear() { return vehicleYear; }
    public void setVehicleYear(Integer v) { vehicleYear = v; }
    public String getCountry() { return country; }
    public void setCountry(String v) { country = v; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String v) { departureDate = v; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String v) { returnDate = v; }
}
