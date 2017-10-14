--pu:18/09/2551
ALTER TABLE b_health_epi_group ADD COLUMN health_epi_group_active varchar (255)  default '';

UPDATE b_health_epi_group SET health_epi_group_active = '1' WHERE health_epi_group_active = '';

--pu:19/09/08
ALTER TABLE t_health_anc ADD COLUMN health_anc_visit_office varchar (255)  default '';


