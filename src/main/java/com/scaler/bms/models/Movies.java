package com.scaler.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Movies extends BaseModel {

 //   private int id;
    private String name;
    //@Enumerated
    private Genre genre;
    private List<String> director;
    private List<String> actors;


}
