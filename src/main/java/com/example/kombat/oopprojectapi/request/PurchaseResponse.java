package com.example.kombat.oopprojectapi.request;

public class PurchaseResponse {
    private String message;
    private Object[] data; // สามารถเก็บได้ทั้ง Hex[] และ Minion[]

    public PurchaseResponse(String message, Object[] data) {
        this.message = message;
        this.data = data;
    }

    // Getter & Setter
    public String getMessage() {
        return message;
    }

    public Object[] getData() {
        return data;
    }
}
