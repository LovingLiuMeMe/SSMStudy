package cn.lovovingliu.demo2;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller("personController")
public class PersonController {
    @Resource(name="teacherService")
    private PersonService personService;

    @Resource(name="studentService")
    private PersonService personService2;
    public String say(){
        return  personService.say()+"----"+personService2.say();
    }
}
