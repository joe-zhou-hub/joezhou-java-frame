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
            status = 200;
            msg = "ok";
        } else {
            status = 500;
            msg = "err";
        }
        this.data = data;
    }


}
