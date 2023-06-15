package com.ifmo.kyoto.data_center.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    private Long id;

    private String cardHash;

    private Date date;

    public Log(String hash) {
        this.cardHash = hash;
        date = new Date();
    }
}
