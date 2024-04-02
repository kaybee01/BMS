package com.scaler.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{
    private  String name;
    @Enumerated
    private SeatType seatType;
}
