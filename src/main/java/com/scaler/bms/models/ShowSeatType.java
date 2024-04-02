package com.scaler.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class ShowSeatType extends  BaseModel{

    private  SeatType seatType;
    @ManyToOne
    private Show show;
    private double amount;
}
