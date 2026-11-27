package example.insurance.api.dto;
import java.util.List;
public class QuoteDto {
    private final String category;
    private final String condition;
    private final List<PlanDto> plans;
    public QuoteDto(String category, String condition, List<PlanDto> plans) {
        this.category = category; this.condition = condition; this.plans = plans;
    }
    public String getCategory() { return category; }
    public String getCondition() { return condition; }
    public List<PlanDto> getPlans() { return plans; }
}
