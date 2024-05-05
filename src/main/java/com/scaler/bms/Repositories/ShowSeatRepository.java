package com.scaler.bms.Repositories;

import com.scaler.bms.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat ,Integer> {

    @Lock(value =LockModeType.PESSIMISTIC_READ)
    List<ShowSeat> findAllByShowIdAndSeatIdsInAndSeatStatus_Available(int showId ,List<Integer> seatIds);
}
