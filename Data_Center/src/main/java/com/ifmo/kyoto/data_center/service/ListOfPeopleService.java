package com.ifmo.kyoto.data_center.service;

import com.ifmo.kyoto.data_center.dao.PeopleRepository;
import com.ifmo.kyoto.data_center.entity.Card;
import com.ifmo.kyoto.data_center.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListOfPeopleService {

    @Autowired
    PeopleRepository peopleRepository;

    @Transactional
    public void registerEntry(Card card) {
        People people = new People();
        people.setCardHash(card.getHash());
        peopleRepository.save(people);
    }

    @Transactional
    public void registerExit(Card card) {
        peopleRepository.removeAllByCardHash(card.getHash());
    }

    @Transactional
    public boolean checkPeopleInRoom(Card card) {
        return peopleRepository.existsPeopleByCardHash(card.getHash());
    }
}
