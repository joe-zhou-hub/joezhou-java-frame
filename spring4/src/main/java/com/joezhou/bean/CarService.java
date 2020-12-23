package com.joezhou.bean;

import lombok.Data;

/**
 * @author JoeZhou
 */
@Data
public class CarService {
    private CarDao carDao;

    public void info() {
        carDao.info();
    }
}