package com.scaler.bms.Repositories;

import com.scaler.bms.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypRepository extends JpaRepository<ShowSeatType , Integer> {

    List<ShowSeatType> findAllByShowId(int showId);
}
