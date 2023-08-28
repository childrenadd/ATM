package com.sph.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private Integer balance;
    private String phoneNumber;


}
