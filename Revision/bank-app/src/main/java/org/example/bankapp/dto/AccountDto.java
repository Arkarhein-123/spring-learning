package org.example.bankapp.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private Double balance;
}
