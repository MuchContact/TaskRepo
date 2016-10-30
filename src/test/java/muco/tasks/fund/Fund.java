package muco.tasks.fund;

public class Fund {
    private String fundName;
    private FundStatus fundStatus;

    public Fund(String fundName) {
        this.fundName = fundName;
    }

    public void updateStatus(FundStatus fundStatus) {
        this.fundStatus = fundStatus;
    }
}
