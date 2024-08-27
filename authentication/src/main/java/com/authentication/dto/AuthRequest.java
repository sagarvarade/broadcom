package com.authentication.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthRequest {
    private String username;
    private String password;
    private String email;
    private Integer userId;

}
