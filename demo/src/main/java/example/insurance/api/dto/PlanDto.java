package example.insurance.api.dto;
public class PlanDto {
    private final String provider;
    private final String plan;
    private final int estimatedAmount;
    public PlanDto(String provider, String plan, int estimatedAmount) {
        this.provider = provider; this.plan = plan; this.estimatedAmount = estimatedAmount;
    }
    public String getProvider() { return provider; }
    public String getPlan() { return plan; }
    public int getEstimatedAmount() { return estimatedAmount; }
}
