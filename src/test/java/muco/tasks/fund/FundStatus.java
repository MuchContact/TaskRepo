package muco.tasks.fund;

public class FundStatus {
    private final String netValue;
    private final String diffInNumber;
    private final String diffInPercentage;
    private final String time;

    public FundStatus(String netValue, String diffInNumber, String diffInPercentage, String time) {
        this.netValue = netValue;
        this.diffInNumber = diffInNumber;
        this.diffInPercentage = diffInPercentage;
        this.time = time;
    }

    public String string() {
        return String.format("净值:%s##日间变化:%s##日间变化百分比:%s##更新时间:%s",
                netValue, diffInNumber, diffInPercentage, time);
    }
}
