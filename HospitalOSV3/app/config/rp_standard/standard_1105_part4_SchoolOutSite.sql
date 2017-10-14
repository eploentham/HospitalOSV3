SELECT count(*) 
                FROM 
                ( SELECT t_health_visit_school.visit_school_service_date AS visit_school 
                 FROM t_health_visit_school 
                WHERE t_health_visit_school.visit_school_active = '1' 
                AND (SUBSTRING(t_health_visit_school.visit_school_service_date,1,10) 
                       BETWEEN ? and ?) 
                GROUP BY t_health_visit_school.visit_school_service_date 
                ) as query
