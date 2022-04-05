package com.example.coronastats.rapidapi;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RapidApiResponseDto implements Serializable {

    private boolean error;
    private int statusCode;
    private String message;
    private RapidApiResponseDataDto data;
}
