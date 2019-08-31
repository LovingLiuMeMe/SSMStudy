package cn.lovingliu.sm.service.impl;

import cn.lovingliu.sm.dao.DepartmentDao;
import cn.lovingliu.sm.entity.Department;
import cn.lovingliu.sm.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource(name="departmentDao")
    private DepartmentDao departmentDao;

    @Override
    public void add(Department department) {
        departmentDao.insert(department);
    }

    @Override
    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    @Override
    public void edit(Department department) {
        departmentDao.update(department);
    }

    @Override
    public Department get(Integer id) {
        return departmentDao.selectById(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
