package com.QuestionPro.ApiAssignment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePricePayload extends RegistrationPayload{
    private String itemId;
    private int newPrice;
}
