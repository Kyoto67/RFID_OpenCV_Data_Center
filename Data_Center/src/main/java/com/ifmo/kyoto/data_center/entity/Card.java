package com.ifmo.kyoto.data_center.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "cards_while_list")
@Entity
public class Card {
    @Id
    private Integer id;

}
