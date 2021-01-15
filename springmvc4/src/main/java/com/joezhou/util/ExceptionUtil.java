package com.joezhou.util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Joezhou
 */
@Component
@ControllerAdvice
public class ExceptionUtil {

	@ResponseBody
	@ExceptionHandler({ArithmeticException.class, ArrayIndexOutOfBoundsException.class})
	public String exceptionHandler(Exception e) {
		System.out.println("ExceptionUtil.exceptionHandler()..." + e);
		return "error";
	}
}