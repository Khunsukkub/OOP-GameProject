package com.kombat.repository;

import com.kombat.model.Minion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinionRepository extends JpaRepository<Minion, Long> {
}
