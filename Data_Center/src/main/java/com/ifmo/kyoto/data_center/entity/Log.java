package com.ifmo.kyoto.data_center.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name="logs")
public class Log {

    @Id
    private Long id;

    private Date date;

    public Log() {
        date = new Date();
    }
}
