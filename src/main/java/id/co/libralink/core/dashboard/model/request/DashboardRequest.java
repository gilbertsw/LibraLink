package id.co.libralink.core.dashboard.model.request;

import id.co.libralink.apigateway.account.enums.UserRole;
import id.co.libralink.apigateway.account.enums.UserStatus;
import id.co.libralink.core.book.enums.BookStatus;
import id.co.libralink.core.borrowing.enums.BorrowStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardRequest implements Serializable {

    private Set<UserRole> userRoles = new HashSet<>();

    private Set<UserStatus> userStatuses = new HashSet<>();

    private Set<BookStatus> bookStatuses = new HashSet<>();

    private Set<BorrowStatus> borrowStatuses = new HashSet<>();

}
