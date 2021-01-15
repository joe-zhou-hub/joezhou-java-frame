package com.joezhou.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JoeZhou
 */
@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MyNotFoundException: 页面走丢了！")
public class MyNotFoundException extends Exception {
}