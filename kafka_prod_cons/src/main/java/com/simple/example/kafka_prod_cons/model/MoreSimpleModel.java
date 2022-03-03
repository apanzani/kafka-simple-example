package com.simple.example.kafka_prod_cons.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoreSimpleModel {

    private String title;
    private String description;
}
