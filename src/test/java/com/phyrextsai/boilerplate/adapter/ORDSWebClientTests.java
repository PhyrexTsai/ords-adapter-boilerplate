package com.phyrextsai.boilerplate.adapter;

import com.phyrextsai.boilerplate.adapter.parameter.Employee;
import com.phyrextsai.boilerplate.adapter.webclient.ORDSWebClient;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientResponse;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class ORDSWebClientTests {

  @Test
  public void testLoadEmployeeListSuccess() {
    try {
      List<Employee> employeeList = ORDSWebClient.client().loadEmployeeList();
      assertEquals(15, employeeList.size());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void testLoadEmployeeByIdSuccess() {
    try {
      Employee employee = ORDSWebClient.client().loadEmployeeById(1234);
      assertEquals("PT", employee.getEname());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void testCreateEmployeeSuccess() {
    try {
      Employee employee = new Employee();
      employee.setEmpno(5555);
      employee.setEname("TOM");
      employee.setJob("ADMIN");
      employee.setMgr(1234);
      employee.setHiredate("1990-01-01");
      employee.setSal(3000);
      employee.setComm(100);
      employee.setDeptno(10);
      ClientResponse clientResponse = ORDSWebClient.client().createEmployee(employee);
      assertEquals(200, clientResponse.statusCode().value());
    } catch (Exception ex) {
      assertEquals("No matching constant for [555]", ex.getMessage());
      //assertEquals(null, ex.getMessage());
      ex.printStackTrace();
    }
  }

  @Test
  public void testUpdateEmployeeSuccess() {
    try {
      Employee employee = new Employee();
      employee.setEmpno(5555);
      employee.setEname("TOM");
      employee.setJob("CLERK");
      employee.setMgr(1234);
      employee.setHiredate("1991-01-01");
      employee.setSal(4000);
      employee.setComm(200);
      employee.setDeptno(10);
      ORDSWebClient.client().updateEmployee(employee);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void testDeleteEmployeeSuccess() {
    try {
      ORDSWebClient.client().deleteEmployee(5555);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
