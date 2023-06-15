package com.ifmo.kyoto.data_center.dao;

import com.ifmo.kyoto.data_center.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Card, Integer> {
    boolean existsCardByHash(String hash);

}
