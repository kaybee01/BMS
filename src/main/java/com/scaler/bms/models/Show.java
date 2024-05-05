package com.scaler.bms.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "shows")
public class Show extends BaseModel{


    @ManyToOne
    private Movies movies;

    @ManyToOne
    private Screen screen;
    private Date startTime;
    private Date endTime;


}
