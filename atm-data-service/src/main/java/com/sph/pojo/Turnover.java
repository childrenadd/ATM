package com.sph.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turnover {
    private Integer id;
    private String fk;
    private String type;
    private Integer amount;
//  @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date date;
}
