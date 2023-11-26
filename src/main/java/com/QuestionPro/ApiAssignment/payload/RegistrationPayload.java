package com.QuestionPro.ApiAssignment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationPayload {
    private String username;
    private String password;
}
