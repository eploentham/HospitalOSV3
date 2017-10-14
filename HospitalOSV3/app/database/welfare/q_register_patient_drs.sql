SELECT '' as drid	
    , '' as drname	
    , '' as active	
    , '' as stat	
FROM b_employee
WHERE b_employee.f_employee_authentication_id = '3'
    AND b_employee.employee_active = '1'
ORDER BY b_employee.employee_firstname;
