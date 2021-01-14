package com.joezhou.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JoeZhou
 */
public class StringToDateConverter implements Converter<String, Date> {
    
    @Override
    public Date convert(String source) {
        System.out.println("convert()...");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}