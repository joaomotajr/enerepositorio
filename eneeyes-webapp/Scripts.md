set global event_scheduler = ON

SHOW EVENTS FROM enedb;
SELECT * FROM INFORMATION_SCHEMA.EVENTS

--drop EVENT myevent
DROP event job_move_to_a_by_minutes
DROP EVENT job_move_to_a_by_hours;
DROP EVENT job_move_to_b_by_day;
DROP EVENT job_move_to_c_by_7day;
DROP EVENT job_move_to_d_by_month;

=======================================CRIAR============================================
CREATE EVENT job_move_to_a_by_20_minutes ON SCHEDULE
      EVERY 5 MINUTE
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic, deixando apenas 20 minutos'
    DO
		call move_to_a_by_minutes(1000, 20);
========================================================================================		

------------------------------------------------------------------------	

CREATE EVENT job_move_to_a_by_one_hour ON SCHEDULE
      EVERY 30 MINUTE
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic, deixando apenas a última hora'
    DO
		call move_to_a_by_hours(1000, 1);
------------------------------------------------------------------------			

CREATE EVENT job_move_to_b_by_one_day ON SCHEDULE
      EVERY 12 HOUR
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic A, deixando apenas o último dia'
    DO
		call move_to_b_by_days(1000, 1);
------------------------------------------------------------------------		
		
CREATE EVENT job_move_to_c_by_seven_days ON SCHEDULE
      EVERY 12 HOUR
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic B, deixando apenas sete dias'
    DO
		call move_to_c_by_days(1000);

------------------------------------------------------------------------			
		
CREATE EVENT job_move_to_d_by_month ON SCHEDULE
      EVERY 12 HOUR
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic C, deixando apenas 1 mês'
    DO
		call move_to_d_by_months(1000, 1)	
      
------------------------------------------------------------------------	
	  
SELECT * FROM INFORMATION_SCHEMA.events;

--check historic
select max(uid), min(uid), 'h' as name from historic
union
select max(uid), min(uid), 'a' as name  from historic_a
union
select max(uid), min(uid), 'b' as name  from historic_b
union
select max(uid), min(uid), 'c' as name   from historic_c
union
select max(uid), min(uid), 'd' as name   from historic_d


--check historic
select max(last_update), min(last_update), 'h' as name from historic
union
select max(last_update), min(last_update), 'a' as name  from historic_a
union
select max(last_update), min(last_update), 'b' as name  from historic_b
union
select max(last_update), min(last_update), 'c' as name   from historic_c
union
select max(last_update), min(last_update), 'd' as name   from historic_d

set @DATA := now() - interval 12 hour;

insert into historic_a values (NULL, 9,  @DATA, 3, 100);