public enum WorkType {
    DEVELOPMENT(1000.0),
    TESTING(800.0),
    SUPPORT(600.0),
    DOCUMENTATION(500.0);

    private final double rate;

    WorkType(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}