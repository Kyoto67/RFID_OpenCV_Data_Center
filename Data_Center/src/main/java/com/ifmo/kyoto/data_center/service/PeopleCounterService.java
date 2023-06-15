package com.ifmo.kyoto.data_center.service;

import com.ifmo.kyoto.data_center.util.GetPeopleCountRequester;
import com.ifmo.kyoto.data_center.util.StartPeopleCountRequester;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PeopleCounterService {

    private Queue<String> timestamps = new ConcurrentLinkedQueue<>();
    private final StartPeopleCountRequester startPeopleCountRequester = new StartPeopleCountRequester();
    private final GetPeopleCountRequester getPeopleCountRequester = new GetPeopleCountRequester(timestamps);
    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
    private AtomicInteger approvedPeoples = new AtomicInteger(0);

    public int getApprovedPeoples() {
        return approvedPeoples.get();
    }

    public void startPeopleCount() {
        approvedPeoples.incrementAndGet();
        String timestamp = startPeopleCountRequester.sendRequest();
        timestamps.add(timestamp);
        scheduledExecutorService.schedule(getPeopleCountRequester, 10, TimeUnit.SECONDS);
    }

}
