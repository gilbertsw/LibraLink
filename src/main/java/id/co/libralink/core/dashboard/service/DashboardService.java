package id.co.libralink.core.dashboard.service;

import id.co.libralink.core.dashboard.model.request.DashboardRequest;
import id.co.libralink.core.dashboard.model.response.DashboardResponse;

public interface DashboardService {

    DashboardResponse getSummary(DashboardRequest request);

}
