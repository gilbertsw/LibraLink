package id.co.libralink.apigateway.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private Instant expiredTime;
    private String role;

}

