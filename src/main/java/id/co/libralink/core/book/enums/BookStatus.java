package id.co.libralink.core.book.enums;

import lombok.Getter;

@Getter
public enum BookStatus {
    AVAILABLE(true),
    NOT_AVAILABLE(false),
    DELETED(false);

    private final boolean isAvailable;

    BookStatus(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
