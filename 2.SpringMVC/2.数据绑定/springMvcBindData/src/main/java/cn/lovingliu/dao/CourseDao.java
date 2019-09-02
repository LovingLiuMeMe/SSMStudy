package cn.lovingliu.dao;

import cn.lovingliu.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository(value="courseDao")
public class CourseDao {
    private Map<Integer, Course> courses = new HashMap<>();

    public void add(Course course){
        courses.put(course.getId(),course);
    }

    public Collection<Course> getAll(){
       return courses.values();
    }
}
