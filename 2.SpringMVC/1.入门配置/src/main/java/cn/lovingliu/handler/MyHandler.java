package cn.lovingliu.handler;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MyHandler implements Controller {
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //装载模型数据和逻辑视图
        modelAndView.addObject("name","lovingliu");
        modelAndView.setViewName("show");
        return modelAndView;
    }
}
