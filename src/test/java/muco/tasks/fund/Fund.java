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

    public String string() {
        return String.format("基金名称:%s##%s", fundName, fundStatus.string());
    }
}
