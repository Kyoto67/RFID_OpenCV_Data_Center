package com.ifmo.kyoto.data_center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


public class UnsanctionedAccessChecker {


    private AtomicInteger approvedPeoples;
    private int countedPeoples = 0;
    private boolean updatedInfo = false;
    private boolean alert = false;

    public UnsanctionedAccessChecker(AtomicInteger approvedPeoples) {
        this.approvedPeoples = approvedPeoples;
    }

    public void check(int countedPeoples) {
        this.countedPeoples = countedPeoples;
        updatedInfo = true;
        if (countedPeoples != approvedPeoples.get()) {
            alert = true;
        }
    }

    public int getApprovedPeoples() {
        return approvedPeoples.get();
    }

    public int getCountedPeoples() {
        updatedInfo = false;
        return countedPeoples;
    }

    public boolean isUpdatedInfo() {
        return updatedInfo;
    }

    public boolean isAlert() {
        return alert;
    }

    public boolean getAlertandClear() {
        boolean old = alert;
        alert = false;
        return alert;
    }
}
