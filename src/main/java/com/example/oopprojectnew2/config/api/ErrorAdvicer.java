package com.example.oopprojectnew2.config.api;

import exception.BaseException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdvicer {

    // กำหนดให้จัดการกับ BaseException
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        // สร้าง response สำหรับข้อผิดพลาด
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getMessage());  // ตั้งค่าข้อความข้อผิดพลาดจาก exception
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value()); // ตั้งค่าสถานะเป็น 417 (Expectation Failed)

        // ส่งกลับข้อผิดพลาดที่ไม่มี stack trace
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @Data
    public static class ErrorResponse {
        private LocalDateTime timestamp = LocalDateTime.now(); // เวลาเมื่อเกิดข้อผิดพลาด
        private int status;  // สถานะ HTTP
        private String error;
        // Constructor เปล่า
        public ErrorResponse() {
        }

        // Getter และ Setter สำหรับ timestamp
        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        // Getter และ Setter สำหรับ status
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        // Getter และ Setter สำหรับ error
        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }// ข้อความข้อผิดพลาดที่แสดงให้ผู้ใช้
    }

}