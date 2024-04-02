package com.scaler.bms.DTO;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Data
public class BookTicketRequestDto {
    private List<Integer> seatId;
    private int showId;
    private int userId;
}
