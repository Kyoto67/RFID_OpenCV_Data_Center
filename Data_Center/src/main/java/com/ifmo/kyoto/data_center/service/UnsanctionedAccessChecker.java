package com.ifmo.kyoto.data_center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;


//Mock
public class UnsanctionedAccessChecker {


    private AtomicInteger approvedPeoples;
    public UnsanctionedAccessChecker(AtomicInteger approvedPeoples) {
        this.approvedPeoples = approvedPeoples;
    }


    public void check(int countedPeoples) {
        System.out.println(approvedPeoples.get());
        System.out.println(countedPeoples);
    }
}
