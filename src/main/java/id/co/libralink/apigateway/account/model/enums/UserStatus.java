package id.co.libralink.apigateway.account.model.enums;

import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum UserStatus {
    ENABLED(true),
    LOCKED(true),
    DISABLED(false);

    public static final EnumSet<UserStatus> ACTIVE_STATUSES = EnumSet.of(ENABLED, LOCKED);

    private final boolean active;

    UserStatus(boolean active) {
        this.active = active;
    }

}
