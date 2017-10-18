set global event_scheduler = ON

SHOW EVENTS FROM enedb;

SET GLOBAL event_scheduler = ON;

drop EVENT myevent


CREATE EVENT job_move_to_a_by_hours ON SCHEDULE
      EVERY 5 MINUTE
      STARTS CURRENT_TIMESTAMP + INTERVAL 5 minute
    COMMENT 'Limpa tabela Historic, deixando apenas a última hora'
    DO
		call move_to_a_by_hours(1000, 1);
     
CREATE EVENT job_move_to_b_by_day ON SCHEDULE
      EVERY 12 HOUR
      STARTS CURRENT_TIMESTAMP + INTERVAL 5 minute
    COMMENT 'Limpa tabela Historic, deixando apenas o último dia'
    DO
		call move_to_b_by_day(1000, 1);
     
      
	  
SELECT * FROM INFORMATION_SCHEMA.events;


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