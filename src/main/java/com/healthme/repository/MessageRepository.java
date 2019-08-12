package com.healthme.repository;

import com.healthme.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query(value = "select LAST_INSERT_ID()",nativeQuery = true)
    Long getLastInsertedId();

}
