package com.ifmo.kyoto.data_center.dao;

import com.ifmo.kyoto.data_center.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Log, Long> {

}
