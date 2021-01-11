# springmvc对比struts2

1. springmvc的入口是一个servlet即前端控制器，而struts2入口是一个filter过滤器。
2. springmvc是基于方法开发(一个url对应一个方法)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)，struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。
3. Struts2采用值栈存储请求和响应的数据，通过OGNL存取数据， springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成 `ModelAndView` 对象，最后又将 `ModelAndView` 中的模型数据通过request域传输到页面，Jsp视图解析器默认使用JSTL。
4. 从效率上讲，servlet是最快的，其次是strut1（过时），然后是springmvc，最后是struts2（值栈）。