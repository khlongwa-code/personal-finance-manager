package database;

public class BudgetDAO {
    private float needsPercentage;
    private float wantsPercentage;
    private float savingsPercentage;

    public BudgetDAO(float needsPercentage, float wantsPercentage, float savingsPercentage) {
        this.needsPercentage = needsPercentage;
        this.wantsPercentage = wantsPercentage;
        this.savingsPercentage = savingsPercentage;
    }

    public float getNeedsPercentage() {
        return needsPercentage;
    }

    public float getWantsPercentage() {
        return wantsPercentage;
    }

    public float getSavingsPercentage() {
        return savingsPercentage;
    }
}
