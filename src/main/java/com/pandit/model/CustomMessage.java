package com.pandit.model;

import lombok.Data;

@Data
public class CustomMessage {
    private String correlation;
    private Person payload;
}
