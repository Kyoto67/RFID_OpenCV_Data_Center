package com.ifmo.kyoto.data_center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnsanctionedAccessChecker {

    @Autowired
    PeopleCounterService peopleCounterService;

    public void check(int countedPeoples) {
        System.out.println(peopleCounterService.getApprovedPeoples());
        System.out.println(countedPeoples);
    }
}
