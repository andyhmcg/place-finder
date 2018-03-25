package com.amcg.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Venue {

    String id;
    String name;
    Location Location;
}
