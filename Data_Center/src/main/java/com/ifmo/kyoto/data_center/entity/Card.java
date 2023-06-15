package com.ifmo.kyoto.data_center.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "cards_white_list")
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    private Long id;

    @Column
    private String hash;

}
