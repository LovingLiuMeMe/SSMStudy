## 对SpringMVC的只是补充
#### 1.重定向 redirect
```java
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("message", "Hello Spring MVC");
		return mav;
	}
	
	@RequestMapping("/jump")
	public ModelAndView jump() {
		ModelAndView mav = new ModelAndView("redirect:/index");
		return mav;
	}	
	
}
```
#### 2.访问Sesssion 
```java
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("message", "Hello Spring MVC");
		return mav;
	}

	@RequestMapping("/jump")
	public ModelAndView jump() {
		ModelAndView mav = new ModelAndView("redirect:/index");
		return mav;
	}

	@RequestMapping("/check")
	public ModelAndView check(HttpSession session) {
		Integer i = (Integer) session.getAttribute("count");
		if (i == null)
			i = 0;
		i++;
		session.setAttribute("count", i);
		ModelAndView mav = new ModelAndView("check");
		return mav;
	}

}
```
#### 3.文件上传
1.配置web.xml允许访问*.jpg
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.4" xmlns="http://java.sun.com/xml/ns/j2ee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">

	<servlet-mapping>
	    <servlet-name>default</servlet-name>
	    <url-pattern>*.jpg</url-pattern>
	</servlet-mapping>
	
	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>
			org.springframework.web.servlet.DispatcherServlet
		</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
    <filter>  
        <filter-name>CharacterEncodingFilter</filter-name>  
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>  
        <init-param>  
            <param-name>encoding</param-name>  
            <param-value>utf-8</param-value>  
        </init-param>  
    </filter>  
    <filter-mapping>  
        <filter-name>CharacterEncodingFilter</filter-name>  
        <url-pattern>/*</url-pattern>  
    </filter-mapping>  	
</web-app>
```
2.配置springmvc-servlet.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
	http://www.springframework.org/schema/context         
	http://www.springframework.org/schema/context/spring-context-3.0.xsd">
	
	<context:component-scan base-package="controller" />
	<bean id="irViewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/page/" />
		<property name="suffix" value=".jsp" />
	</bean>
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
</beans>
```
3.upload.jsp 上传页面
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<form action="uploadImage" method="post" enctype="multipart/form-data">
  选择图片:<input type="file" name="image" accept="image/*" /> <br> 
  <input type="submit" value="上传">
</form>

```
4.准备UploadedImageFile.java
```java
package pojo;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImageFile {
	MultipartFile image;

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
```
5.UploadController 上传控制器
新建类UploadController 作为上传控制器  
准备方法upload 映射上传路径/uploadImage  
1. 方法的第二个参数UploadedImageFile 中已经注入好了 image  
2. 通过 RandomStringUtils.randomAlphanumeric(10);获取一个随机文件名。 因为用户可能上传相同文件名的文件，为了不覆盖原来的文件，通过随机文件名的办法来规避  
3. 根据request.getServletContext().getRealPath 获取到web目录下的image目录，用于存放上传后的文件。  
4. 调用file.getImage().transferTo(newFile);   
5. 把生成的随机文件名提交给视图，用于后续的显示  
```java
package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.UploadedImageFile;

@Controller
public class UploadController {

	@RequestMapping("/uploadImage")
	public ModelAndView upload(HttpServletRequest request, UploadedImageFile file)
			throws IllegalStateException, IOException {
		String name = RandomStringUtils.randomAlphanumeric(10);
		String newFileName = name + ".jpg";
		File newFile = new File(request.getServletContext().getRealPath("/image"), newFileName);
		newFile.getParentFile().mkdirs();
		file.getImage().transferTo(newFile);

		ModelAndView mav = new ModelAndView("showUploadedFile");
		mav.addObject("imageName", newFileName);
		return mav;
	}
}
```
6.showUploadedFile.jsp 显示图片的页面
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<img src="image/${imageName}"/>
```
