package com.openai.models;

import java.util.List;

import lombok.*;

/**
 * @author madhankumar
 */
@Setter
@Getter
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String model;
    private List<String> messages;
    private int n;
    private double temperature;

    public boolean newMethod(String st1 ,String st2){
        if(st1 == st2){
            return true;
        }
        return false;
    }
}
