package com.jepp.icaoConverter.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class ICAOcode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String letter;

    private String iCode;
    private String foneticCode;
}
