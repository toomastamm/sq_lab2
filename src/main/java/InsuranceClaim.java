public class InsuranceClaim {
    private int id;
    private String claimerName;
    private String description;
    private float claimAmount;
    private boolean approved;

    public InsuranceClaim(String claimerName, String description, float claimAmount, boolean approved) {
        this.claimerName = claimerName;
        this.description = description;
        this.claimAmount = claimAmount;
        this.approved = approved;
    }

    public int getID() { return id; }

    public void setID(int id) { this.id = id; }

    public String getClaimerName() {
        return claimerName;
    }

    public void setClaimerName(String claimerName) {
        this.claimerName = claimerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(float claimAmount) {
        this.claimAmount = claimAmount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
