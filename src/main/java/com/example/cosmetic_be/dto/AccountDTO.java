package com.example.cosmetic_be.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private String name;
    private String pass;

    public AccountDTO() {
    }

    public AccountDTO(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}
