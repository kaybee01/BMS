package com.scaler.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ShowSeat extends  BaseModel{

    @ManyToOne
    private  Show show;
    @ManyToOne
    private  Seat seat;
    @ManyToOne
    private User user;
    private  SeatStatus seatStatus;
}
