package id.co.libralink.core.borrowing.enums;

import lombok.Getter;

@Getter
public enum BorrowPeriod {
    ONE_WEEK(7),
    TWO_WEEKS(14),
    ONE_MONTH(30);

    private final int days;

    BorrowPeriod(int days) {
        this.days = days;
    }

}
