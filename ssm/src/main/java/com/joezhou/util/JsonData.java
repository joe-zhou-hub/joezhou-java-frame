package com.joezhou.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JoeZhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonData implements Serializable {

    private Object data;
    private Integer status;
    private String msg;

    public JsonData(Object data) {
        if (data != null) {
            this.status = 200;
            this.msg = "ok";
        } else {
            this.status = 500;
            this.msg = "err";
        }
        this.data = data;
    }

    public JsonData(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }


}
