package com.phyrextsai.boilerplate.adapter.converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.phyrextsai.boilerplate.adapter.entity.CollectionItem;
import com.phyrextsai.boilerplate.adapter.parameter.Employee;
import com.phyrextsai.boilerplate.adapter.parameter.EmployeeRequest;

public class EntityConverter {

  public static List<Employee> toEmployeeList(CollectionItem collectionItem) {
    List<Employee> employeeList = new ArrayList<Employee>();
    for (int i = 0; i < collectionItem.getItems().size(); i++) {
      Employee employee = new Employee();
      LinkedHashMap employeeMap = (LinkedHashMap) collectionItem.getItems().get(i);
      employee.setEmpno((employeeMap.get("empno") != null) ? Integer.parseInt(employeeMap.get("empno").toString()) : null);
      employee.setEname((employeeMap.get("ename") != null) ? employeeMap.get("ename").toString() : null);
      employee.setJob((employeeMap.get("job") != null) ? employeeMap.get("job").toString() : null);
      employee.setHiredate((employeeMap.get("hiredate") != null) ? employeeMap.get("hiredate").toString() : null);
      employee.setMgr((employeeMap.get("mgr") != null) ? Integer.parseInt(employeeMap.get("mgr").toString()) : null);
      employee.setComm((employeeMap.get("comm") != null) ? Integer.parseInt(employeeMap.get("comm").toString()) : null);
      employee.setDeptno((employeeMap.get("deptno") != null) ? Integer.parseInt(employeeMap.get("deptno").toString()) : null);

      employeeList.add(employee);
    }
    return employeeList;
  }

  public static Employee fromEmployeeRequest(EmployeeRequest request) {
    Employee employee = new Employee();
    employee.setEmpno(request.getId());
    employee.setEname(request.getName());
    employee.setJob(request.getJob());
    employee.setHiredate(request.getHireDate());
    employee.setMgr(request.getManager());
    employee.setSal(request.getSalary());
    employee.setDeptno(request.getDepartment());

    return employee;
  }

}
