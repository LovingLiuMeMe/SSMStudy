package cn.lovingliu.entity;

import java.util.HashSet;
import java.util.Set;

public class CourseSet {
    // 区别于其他类型 这个必须要先初始化 且要加入两个空对象
    private Set<Course> courseSet = new HashSet<>();

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    public CourseSet(){
        courseSet.add(new Course());
        courseSet.add(new Course());
    }
}
