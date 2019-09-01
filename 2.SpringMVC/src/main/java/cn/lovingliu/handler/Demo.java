package cn.lovingliu.handler;

import cn.lovingliu.entity.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Demo {
    @RequestMapping("/")
    public String index(Model model){
        return "add";
    }

    @RequestMapping("addGoods")
    public ModelAndView addGoods(Goods goods){
        System.out.println(goods);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goods",goods);
        modelAndView.setViewName("show");
        return modelAndView;
    }
}
