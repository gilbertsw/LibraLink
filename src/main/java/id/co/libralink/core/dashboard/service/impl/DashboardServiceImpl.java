package id.co.libralink.core.dashboard.service.impl;

import id.co.libralink.apigateway.account.service.UserService;
import id.co.libralink.core.book.service.BookService;
import id.co.libralink.core.borrowing.service.BorrowingService;
import id.co.libralink.core.dashboard.model.request.DashboardRequest;
import id.co.libralink.core.dashboard.model.response.DashboardResponse;
import id.co.libralink.core.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserService userService;

    private final BookService bookService;

    private final BorrowingService borrowingService;

    @Override
    public DashboardResponse getSummary(DashboardRequest request) {
        int totalUser = userService.countByRoleAndStatus(request.getUserRoles(), request.getUserStatuses());
        int totalBook = bookService.countByStatus(request.getBookStatuses());
        int totalBorrowing = borrowingService.countByStatus(request.getBorrowStatuses());
        return DashboardResponse.builder()
                .totalUser(totalUser)
                .totalBook(totalBook)
                .totalBorrowing(totalBorrowing)
                .build();
    }

}
