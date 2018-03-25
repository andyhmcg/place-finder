package com.amcg.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Venue {

    String id;
    String name;
    Location Location;
}
