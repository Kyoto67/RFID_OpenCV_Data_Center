package com.ifmo.kyoto.data_center.service;

import com.ifmo.kyoto.data_center.dao.LogsRepository;
import com.ifmo.kyoto.data_center.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;

    @Transactional
    public void addLog(Log log) {
        logsRepository.save(log);
    }

}
