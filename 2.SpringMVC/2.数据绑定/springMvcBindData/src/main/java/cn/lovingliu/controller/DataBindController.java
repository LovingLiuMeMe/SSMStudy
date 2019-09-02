package cn.lovingliu.controller;

import cn.lovingliu.dao.CourseDao;
import cn.lovingliu.entity.Course;
import cn.lovingliu.entity.CourseList;
import cn.lovingliu.entity.CourseMap;
import cn.lovingliu.entity.CourseSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator.
 */
@Controller
public class DataBindController {
    @Resource(name = "courseDao")
    private CourseDao courseDao;

    /*
    * 1.基本数据类型
    * */
    @RequestMapping(value = "/baseType")
    @ResponseBody
    public String baseType(@RequestParam(value = "id") int id){
        return "id:"+id;
    }
    /*
     * 2.基本数据类型的包装器
     * */
    @RequestMapping(value = "/packageType")
    @ResponseBody
    public String packageType(@RequestParam(value = "id") Integer id){
        return "id:"+id;
    }
    /*
     * 2.数组类型
     * */
    @RequestMapping(value = "/arrayType")
    @ResponseBody
    public String arrayType(String[] name){
        StringBuffer sbf = new StringBuffer();
        for (String item:name){
            sbf.append(item).append(" ");
        }
        return sbf.toString();
    }

    @RequestMapping(value = "/pojoType")
    public ModelAndView pojoType(Course course){
        courseDao.add(course);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("course",courseDao.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/listType")
    /*
    *
    * 对于参数是List的情况下 需要创建一个属性为List的包装类
    * */
    public ModelAndView pojoType(CourseList courseList){
        for(Course course:courseList.getCourses()){
            courseDao.add(course);
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("course",courseDao.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/mapType")
    public ModelAndView mapType(CourseMap courseMap){
        for (String key : courseMap.getCourseMap().keySet()) {
            courseDao.add(courseMap.getCourseMap().get(key));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("course",courseDao.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/setType")
    public ModelAndView setType(CourseSet courseSet){
        for (Course course : courseSet.getCourseSet()) {
            courseDao.add(course);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("course",courseDao.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/jsonType")
    @ResponseBody
    public Course jsonType(@RequestBody Course course){
        course.setPrice(course.getPrice()+200);
        return course;
    }


}
