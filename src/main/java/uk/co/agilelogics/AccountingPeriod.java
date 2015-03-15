package uk.co.agilelogics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created with IntelliJ IDEA.
 * User: ashishsri
 * Date: 28/02/15
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountingPeriod {
    private String periodStatus;
    private String periodStartDate;
    private String periodEndDate;
    private String period;


    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(String periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public String getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(String periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public String getPeriodStatus() {
        return periodStatus;
    }

    public void setPeriodStatus(String periodStatus) {
        this.periodStatus = periodStatus;
    }
}
