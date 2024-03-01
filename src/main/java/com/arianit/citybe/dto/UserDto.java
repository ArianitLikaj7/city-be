package com.arianit.citybe.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
}
