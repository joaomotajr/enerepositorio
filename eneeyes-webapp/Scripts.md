set global event_scheduler = ON

SHOW EVENTS FROM enedb;

SET GLOBAL event_scheduler = ON;

--drop EVENT myevent
DROP EVENT job_move_to_a_by_hours;
DROP EVENT job_move_to_b_by_day;
DROP EVENT job_move_to_c_by_7day;
DROP EVENT job_move_to_d_by_month;


CREATE EVENT job_move_to_a_by_hours ON SCHEDULE
      EVERY 5 MINUTE
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic, deixando apenas a última hora'
    DO
		call move_to_a_by_hours(1000, 1);
		
     
CREATE EVENT job_move_to_b_by_day ON SCHEDULE
      EVERY 12 HOUR
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic A, deixando apenas o último dia'
    DO
		call move_to_b_by_day(1000, 1);
		
		
CREATE EVENT job_move_to_c_by_7day ON SCHEDULE
      EVERY 12 HOUR
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic B, deixando apenas sete dias'
    DO
		call move_to_c_by_7day(1000);
		
		
CREATE EVENT job_move_to_d_by_month ON SCHEDULE
      EVERY 12 HOUR
      STARTS CURRENT_TIMESTAMP + INTERVAL 1 minute
    COMMENT 'Limpa tabela Historic C, deixando apenas 1 mês'
    DO
		call move_to_d_by_month(1000, 1)	
      
	  
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


set @DATA := now() - interval 12 hour;

insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);
insert into historic_a values (NULL, 9,  @DATA, 3, 100);	  