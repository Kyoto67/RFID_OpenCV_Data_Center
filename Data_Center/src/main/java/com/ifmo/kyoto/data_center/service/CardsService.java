package com.ifmo.kyoto.data_center.service;

import com.ifmo.kyoto.data_center.dao.CardsRepository;
import com.ifmo.kyoto.data_center.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;

    public boolean checkCardInWhiteList(Card card) {
        return cardsRepository.existsCardById(card.getId());
    }
}
