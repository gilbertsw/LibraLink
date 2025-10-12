package id.co.libralink.borrowing.model.request;

import id.co.libralink.borrowing.model.enums.BorrowPeriod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookRequest {

    @NotNull(message = "error.param.empty")
    private Long userId;

    @NotNull(message = "error.param.empty")
    private Long bookId;

    @NotNull(message = "error.param.empty")
    private BorrowPeriod borrowPeriod;

}
