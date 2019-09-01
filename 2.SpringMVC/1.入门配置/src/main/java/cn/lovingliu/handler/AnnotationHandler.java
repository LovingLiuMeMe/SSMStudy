package cn.lovingliu.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AnnotationHandler {
    @RequestMapping("hello")
    // 自定义个业务方法 ModelAndView 完成数据的传递和视图的解析
    public ModelAndView hello(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","tangyu");
        modelAndView.setViewName("show");
        return modelAndView;
    }

    // model传值 String进行视图解析
    @RequestMapping("modelTest")
    public String modelTest(Model model){
        // 填充模型数据
        model.addAttribute("name","jerry");
        return "show";
    }
    // map传值 String 进行视图解析
    @RequestMapping("mapTest")
    public String mapTest(Map<String,String> map){
        // 填充模型数据
        map.put("name","mapTest");
        return "show";
    }
}
