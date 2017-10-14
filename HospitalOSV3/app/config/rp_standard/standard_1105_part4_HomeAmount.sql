SELECT count(query1.home) AS homeAmount 
                FROM 
                	(SELECT t_health_home.t_health_home_id AS home 
                	FROM t_health_home INNER JOIN t_health_family 
                       			ON t_health_home.t_health_home_id = t_health_family.t_health_home_id 
                                          INNER JOIN t_health_visit_home 
                				ON t_health_visit_home.t_health_family_id = t_health_family.t_health_family_id 
                	WHERE t_health_family.health_family_active = '1' 
                	AND t_health_home.home_active = '1' 
                	AND t_health_visit_home.visit_home_active = '1' 
                	AND (SUBSTRING(t_health_visit_home.visit_home_date,1,10) 
                			BETWEEN ? and ?)  
                	GROUP BY home) AS query1
