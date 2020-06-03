package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sebastian
 * @date 2020/5/10 21:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;


    public CommonResult<T> success(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
        return this;
    }

    public CommonResult<T> success() {
        this.code = 200;
        this.message = "success";
        this.data = null;
        return this;
    }

    public CommonResult<T> success(String message) {
        this.code = 200;
        this.message = message;
        this.data = null;
        return this;
    }

    public CommonResult<T> fail(String message) {
        this.code = 300;
        this.message = message;
        this.data = null;
        return this;
    }
}
