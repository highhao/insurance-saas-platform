package example.insurance.api.dto;
import javax.validation.constraints.Pattern;
public class ApplicationDio {
    @Pattern(regexp="car|travel") private String category;
    @Pattern(regexp="A|B") private String plan;
    @Pattern(regexp="none|history") private String scenario;
    @Pattern(regexp="success|failure") private String outcome;
    public String getCategory() { return category; }
    public void setCategory(String value) { category = value; }
    public String getPlan() { return plan; }
    public void setPlan(String value) { plan = value; }
    public String getScenario() { return scenario; }
    public void setScenario(String value) { scenario = value; }
    public String getOutcome() { return outcome; }
    public void setOutcome(String value) { outcome = value; }
    public boolean isComplete() { return category != null && plan != null && scenario != null && outcome != null; }
}
