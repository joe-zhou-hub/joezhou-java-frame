package com.joezhou.service;

import com.joezhou.dao.CarDao;
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