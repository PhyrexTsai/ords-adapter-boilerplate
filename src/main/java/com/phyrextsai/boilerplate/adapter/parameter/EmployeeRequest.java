package com.phyrextsai.boilerplate.adapter.parameter;

import javax.validation.constraints.NotEmpty;

public class EmployeeRequest {

  @NotEmpty(message = "Employee id is undefined.")
  private Integer id;
  private String name;
  private String job;
  private Integer manager;
  private String hireDate;
  private Integer salary;
  private Integer comm;
  private Integer department;

  public Integer getId() {
    return id;
  }

  public Integer getDepartment() {
    return department;
  }

  public void setDepartment(Integer department) {
    this.department = department;
  }

  public Integer getComm() {
    return comm;
  }

  public void setComm(Integer comm) {
    this.comm = comm;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  public String getHireDate() {
    return hireDate;
  }

  public void setHireDate(String hireDate) {
    this.hireDate = hireDate;
  }

  public Integer getManager() {
    return manager;
  }

  public void setManager(Integer manager) {
    this.manager = manager;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}
