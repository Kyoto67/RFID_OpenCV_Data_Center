package com.ifmo.kyoto.data_center.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "people_list_in_room")
@Data
public class People {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String cardHash;
}
