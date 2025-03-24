package com.example.kombat.oopprojectapi.request;

import com.example.kombat.oopprojectapi.model.Hex;

public class PurchaseResponse {
    private String message;
    private Hex[] hex;

    public PurchaseResponse(String message, Hex hex[]) {
        this.message = message;
        this.hex = hex;
    }

    // Getter & Setter
    public String getMessage() {
        return message;
    }

    public Hex[] getHex() {
        return hex;
    }
}
