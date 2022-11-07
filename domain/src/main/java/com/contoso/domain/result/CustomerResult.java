package com.contoso.domain.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResult {

    private Integer id;

    private String firstName;

    private String lastName;

    private String number;

    private String email;
}
