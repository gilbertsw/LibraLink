package id.co.libralink.core.dashboard.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private Integer totalBook;

    private Integer totalUser;

    private Integer totalBorrowing;

}
