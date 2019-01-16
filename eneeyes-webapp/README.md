21-08-16
Ajsutar o drag ao cancelar, verificar o evento drag pós gravar esta perdendo


1) Estrutura do Sistema:
	a) Definição do Projeto: 
		i- Server: (Java Rest Services com anotações JPA, Spring JPA e Hibernate);
		ii - Client: Java Server Pages, HTML5, Angular e Jquery / Template AdminLTE e Boostrap;  
	b) Criação do projeto Server: J2EE Web Module / IDE Eclipse utilizando Maven;		 
	c) Criação do projeto Cliente: J2EE Web Module / IDE Eclipse;

2) Criação de Masterpage: Usando o Tempalte AdminLTE:
	a) Menu Superior;
	b) Menu Lateral Esquerdo;
	c) Menu Lateral Direito (não implementado);
	d) Conteúdo da Página;
	
3) Criação mecanismo de tabulação de todas as páginas (Será Opcional);

4) Tela login Simples;

5) Implementação de Token de segurança de acesso as URL's;

6) Criação dos Servicos:
	a) Controllers;
	b) Models;
	c) Dto;
	d) Repository

Terminei CRUD para todas as entidades, proximas tarefas:

1) Deleção em cascata: company -> Unit -> Area -> devices -> OK
1.5) Criado abertura de forms em Tabs-> OK
2) Formulários CRUD;
3) Three view Companys;
 
 
* COMANDO PARA EXECUCAO DA APLICACAO
mvn jetty:run -Dspring.profiles.active="dev" -P local

*Atualizar nome em dispositivos
update company_device cd inner join company_detector c on (cd.uid = c.company_device_id) set cd.name = c.name;

/* MYSQL */
	INSERT INTO aln_id_user(ID_, CREATE_DATE_, DISPLAYNAME_, HASH_, LOGIN_, NICKNAME_, STATUS_, CPF_) 
	VALUES (null,'2017-08-17','Joao Jr',sha('123456'),'jrcowboy','Joao Jr','ACTIVE', '13210102813');

/* POSTGRES */
INSERT INTO aln_id_user(CREATE_DATE_, DISPLAYNAME_, HASH_, LOGIN_, NICKNAME_, STATUS_, CPF_) 
VALUES ('2015-09-18','CK Medic','7c4a8d09ca3762af61e59520943dc26494f8941b','joaomotajunior@gmail.com','CK Medic','ACTIVE', '13210102813');


/* LIMPAR TABELAS */
drop table detector_sensors;
drop table company_device;
drop table area;
drop table unit;
drop table company;
drop table controller;
drop table sensor_gases;
drop table gas cascade;
drop table sensor;
drop table detector;
drop table transmitter;
drop table manufacturer;

/* LIMPAR TABELAS DADOS HISTORICOS*/
truncate table historic;
truncate table historic_alarm;
truncate table historic_a;
truncate table historic_b;
truncate table historic_c;
truncate table historic_d;
truncate table log_auditoria;
truncate table log_events;


/* POPULAR UNIDADES DE MEDIDA */
insert into unit_meter values (0, 'DESCONHECIDO', 'Desconhecido');
insert into unit_meter values (1, 'PPM', 'Partes por Milhão');
insert into unit_meter values (2, 'PPB', 'PPB');
insert into unit_meter values (3, 'LEL_PERCENT', 'Percentual LEL');
insert into unit_meter values (4, 'LEL_PERCENT_METRO', 'Percentual LEL por metro');
insert into unit_meter values (5, 'PERCENT_VOLUME', 'Percentual Volume');
insert into unit_meter values (6, 'GRAUS_CELSIUS', 'Graus Celsius');
insert into unit_meter values (7, 'VOLT', 'Volt');
insert into unit_meter values (8, 'AMPERE', 'Ampere');
insert into unit_meter values (9, 'MINUTE', 'Minuto');
insert into unit_meter values (10, 'SECOND', 'Segundo');
insert into unit_meter values (11, 'KWH', 'KWH');
insert into unit_meter values (12, 'OPEN_CLOSE', 'Aberto ou Fechado');
insert into unit_meter values (13, 'M3_HOUR', 'Metros Cúbicos por Hora');
insert into unit_meter values (14, 'M3_MIN', 'Metros Cúbicos por Minuto');

/* POPULAR TIPOS DE DISPOSITIVO*/
insert into device_type values (0,'OUTROS', 'Outros');
insert into device_type values (1,'DETECTOR', 'Detector');
insert into device_type values (2,'PLC', 'PLC');
insert into device_type values (3,'CONTROLLER', 'Controladora');
insert into device_type values (4,'ALARM', 'Alarme');
insert into device_type values (5,'BLOCKER', 'Bloqueador');
insert into device_type values (6,'ELETRICITY', 'Eletricidade');
insert into device_type values (7,'TIME', 'Tempo');
insert into device_type values (8,'TEMPERATURE', 'Temperatura');
insert into device_type values (9,'DIGITAL', 'Digital');
insert into device_type values (10,'OPEN_CLOSE', 'Abre ou Fecha');
insert into device_type values (11,'LIQUID_LEVEL', 'Nível de Água');
insert into device_type values (12,'LIQUID_PRESSURE', 'Pressão da Água');
insert into device_type values (13,'LIQUID_FLOW', 'Vazão da Água');

/* POPULAR PRINCIPAIS GASES */
insert into gas (name, cas, formula) values ('Combustible gases', '99999',	'LEL');
insert into gas (name, cas, formula) values ('Hydrogen','1333-74-0',	'H2');
insert into gas (name, cas, formula) values ('Oxygen','7782-44-7',	'O2');
insert into gas (name, cas, formula) values ('Ammonia',	'7664-41-7',			'NH3');
insert into gas (name, cas, formula) values ('Carbon Monoxide',	'630-08-0',	'CO'	);
insert into gas (name, cas, formula) values ('Hydrogen Sulfide', '2148-87-8',	'H2S');
insert into gas (name, cas, formula) values ('Sulfur Dioxide',	'94336-28-4',	'SO2');
insert into gas (name, cas, formula) values ('Hydrogen Cyanide', '74-90-8',	'HCN');
insert into gas (name, cas, formula) values ('Hydrogen Chloride', '7647-01-0',	'HCL');
insert into gas (name, cas, formula) values ('Phosphine', '7803-51-2', 	'PH3');
insert into gas (name, cas, formula) values ('Nitrogen Dioxide', '10102-44-0',	'NO2');
insert into gas (name, cas, formula) values ('Nitric Oxide', '10102-43-9',		'NO');
insert into gas (name, cas, formula) values ('Chlorine', '7782-50-5', 'Cl2');
insert into gas (name, cas, formula) values ('Chlorine Dioxide', '10049-04-4',	'ClO2');

/* INCLUIR ROLES */
INSERT INTO aln_id_role (name_) VALUES ('ADMINISTRATOR');
INSERT INTO aln_id_role (name_) VALUES ('MANAGER');
INSERT INTO aln_id_role (name_) VALUES ('OPERATOR');

/* EXCLUIR VIEWS CRIADAS COMO TABELAS */

drop table alarm_companydetector_sensor_view;
drop table area_companydetector_alarm_view;
drop table company_detector_view;
drop table company_sumary_view;
drop table company_view;
drop table dash_companies_alarm;
drop table dash_companies_position;
drop table dash_companies;
drop table dash_detectors_maintenance;
drop table historic_view_hour;
drop table historic_view_day;
drop table historic_view;
drop table queue_email_view;
drop table queue_sigma_view;
drop table queue_sms_view;
drop table position_historic_view


/* Falta criar controle pra não cortar tempo de abertura pela metade
   Criar o Job para chamada
   Criar campo em parametro e limpar  a partir de cada  
   usar a view se historic completa  								*/

truncate table enedb.historic_open;
call curdemo(5000);
SELECT * FROM enedb.historic_open;