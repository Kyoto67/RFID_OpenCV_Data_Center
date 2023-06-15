package com.ifmo.kyoto.data_center.service;

import com.ifmo.kyoto.data_center.util.GetPeopleCountRequester;
import com.ifmo.kyoto.data_center.util.StartPeopleCountRequester;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PeopleCounterService {

    private Queue<String> timestamps;
    private final StartPeopleCountRequester startPeopleCountRequester;
    private AtomicInteger approvedPeoples;

    @Getter
    private UnsanctionedAccessChecker unsanctionedAccessChecker;
    private final GetPeopleCountRequester getPeopleCountRequester;
    private ScheduledExecutorService scheduledExecutorService;

    public PeopleCounterService() {
        this.timestamps = new ConcurrentLinkedQueue<>();
        this.startPeopleCountRequester = new StartPeopleCountRequester();
        this.approvedPeoples = new AtomicInteger(0);
        this.unsanctionedAccessChecker = new UnsanctionedAccessChecker(approvedPeoples);
        this.getPeopleCountRequester = new GetPeopleCountRequester(timestamps, unsanctionedAccessChecker);
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
    }

    public int getApprovedPeoples() {
        return approvedPeoples.get();
    }

    public void startPeopleCount() {
        approvedPeoples.incrementAndGet();
        String timestamp = startPeopleCountRequester.sendRequest();
        timestamps.add(timestamp);
        scheduledExecutorService.schedule(getPeopleCountRequester, 10, TimeUnit.SECONDS);
    }

    public void decApprovedPeoples() {
        approvedPeoples.decrementAndGet();
    }

}
