-- 
-- Create ORDS Schema
--
BEGIN
  ORDS.ENABLE_SCHEMA(
    p_enabled             => TRUE,
    p_schema              => 'BANKEE',
    p_url_mapping_type    => 'BASE_PATH',
    p_url_mapping_pattern => 'bankee',
    p_auto_rest_auth      => TRUE);  
  COMMIT;
END;

-- 
-- Create HR ORDS Handler
--
BEGIN
  ORDS.define_module(
    p_module_name    => 'api',
    p_base_path      => 'api/',
    p_items_per_page => 0);
  COMMIT;
END;

-- GET employee list
BEGIN
  ORDS.define_template(
   p_module_name    => 'api',
   p_pattern        => 'employees/');

  ORDS.define_handler(
    p_module_name    => 'api',
    p_pattern        => 'employees/',
    p_method         => 'GET',
    p_source_type    => ORDS.source_type_collection_feed,
    p_source         => 'SELECT * FROM emp',
    p_comments       => 'Get employees',
    p_items_per_page => 0);
  COMMIT;
END;

-- GET employee by id
BEGIN
  ORDS.define_template(
    p_module_name    => 'api',
    p_pattern        => 'employees/:empno');

  ORDS.define_handler(
    p_module_name    => 'api',
    p_pattern        => 'employees/:empno',
    p_method         => 'GET',
    p_source_type    => ORDS.source_type_collection_feed,
    p_comments       => 'Get employee by empno',
    p_source         => 'SELECT * FROM emp WHERE empno = :empno',
    p_items_per_page => 0);
  COMMIT;
END;

-- POST create employee
BEGIN
  ORDS.define_handler(
    p_module_name    => 'api',
    p_pattern        => 'employees/',
    p_method         => 'POST',
    p_source_type    => ORDS.source_type_plsql,
    p_source         => 'BEGIN
                          INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
                          VALUES (:empno, :ename, :job, :mgr, TO_DATE(:hiredate, ''YYYY-MM-DD''), :sal, :comm, :deptno);
                         END;',
    p_items_per_page => 0);
  COMMIT;
END;

-- PUT update employee
BEGIN
  ORDS.define_handler(
    p_module_name    => 'api',
    p_pattern        => 'employees/',
    p_method         => 'PUT',
    p_source_type    => ORDS.source_type_plsql,
    p_source         => 'BEGIN
                          UPDATE emp
                          SET ename    = :ename,
                              job      = :job,
                              mgr      = :mgr,
                              hiredate = TO_DATE(:hiredate, ''YYYY-MM-DD''),
                              sal      = :sal,
                              comm     = :comm,
                              deptno   = :deptno
                          WHERE empno  = :empno;
                         END;',
    p_items_per_page => 0);
  COMMIT;
END;

-- DELETE delete employee
BEGIN
  ORDS.define_handler(
    p_module_name    => 'api',
    p_pattern        => 'employees/:empno',
    p_method         => 'DELETE',
    p_source_type    => ORDS.source_type_plsql,
    p_source         => 'BEGIN
                          DELETE FROM emp WHERE empno = :empno;
                         END;',
    p_items_per_page => 0);
  COMMIT;
END;
