package com.healthme.repository;

import com.healthme.model.entity.SickNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SickNoteRepository extends JpaRepository<SickNote, Long> {
}
