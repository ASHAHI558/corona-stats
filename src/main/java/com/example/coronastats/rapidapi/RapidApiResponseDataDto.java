package com.example.coronastats.rapidapi;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RapidApiResponseDataDto implements Serializable {

    private String lastChecked;
    private Object covid19Stats;
}
