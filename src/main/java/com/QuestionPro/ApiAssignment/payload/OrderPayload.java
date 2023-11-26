package com.QuestionPro.ApiAssignment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPayload extends RegistrationPayload{
    private Map<String, Integer> orders;
}
