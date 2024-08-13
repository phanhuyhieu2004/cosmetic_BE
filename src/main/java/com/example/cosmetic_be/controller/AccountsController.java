package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.dto.AccountDTO;
import com.example.cosmetic_be.model.Accounts;
import com.example.cosmetic_be.model.ErrorMessage;
import com.example.cosmetic_be.service.imp.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/account")

public class AccountsController {
    @Autowired
    private AccountsService accountsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        try {
            Accounts accounts = accountsService.register(accountDTO.getName(), accountDTO.getPass());
            return ResponseEntity.ok(accounts);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
//            kiểm tra thông báo lỗi xem có chứa chuỗi ... ?
            if (errorMessage.contains("Tài khoản đã tồn tại")) {
                return ResponseEntity.badRequest().body(new ErrorMessage("Tài khoản đã tồn taị"));
            } else {
                return ResponseEntity.badRequest().body(new ErrorMessage("Lỗi không xác định"));

            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
        try {
            Accounts accounts = accountsService.login(accountDTO.getName(), accountDTO.getPass());
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Không có tài khoản")) {
                return new ResponseEntity<>(new ErrorMessage("Không có tài khoản"),HttpStatus.BAD_REQUEST);
            }  if (errorMessage.contains("Sai mat khau")) {
                return new ResponseEntity<>(new ErrorMessage("Sai mật khẩu"), HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(new ErrorMessage("Lỗi không xác đinh"),HttpStatus.BAD_REQUEST);

            }

        }
    }
}
