package com.ifmo.kyoto.data_center.dao;

import com.ifmo.kyoto.data_center.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {

    boolean existsPeopleByCardHash(String cardHash);

    void deleteAllByCardHash(String cardHash);

    void removeAllByCardHash(String cardHash);

}
