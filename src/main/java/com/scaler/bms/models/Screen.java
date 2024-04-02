package com.scaler.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Screen extends BaseModel{

    private String name;
    @OneToMany
    private List<Seat> seat;
    @ManyToOne
    private Theatre theatre;

}
