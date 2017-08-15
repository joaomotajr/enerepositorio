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

* COMANDOS SQL NECESSÁRIOS PARA EXECUÇÃO DA FUNCIONALIDADE DE AUTÊNTICAÇÃO
insert into aln_id_role values (NULL, 'ADMINISTRATOR', 'ACTIVE', 'ADM');
insert into aln_id_role values (NULL, 'CUSTOMER', 'ACTIVE', 'CST');
insert into aln_id_role values (NULL, 'EXPERT', 'ACTIVE', 'EXT');

*Atualizar nome em dispositivos
update company_device cd inner join company_detector c on (cd.uid = c.company_device_id) set cd.name = c.name;

* INCLUIR USUARIO INICIAL
INSERT INTO `enedb`.`aln_id_user`(`ID_`, `CREATE_DATE_`, `DISPLAYNAME_`, `HASH_`, `LOGIN_`, `NICKNAME_`, `STATUS_`, `CPF_`) 
VALUES (null,'2015-09-18','CK Medic',sha1('123456'),'joaomotajunior@gmail.com','CK Medic','ACTIVE', '13210102813');

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

/* POPUALR PRINCIPAIS GASES */
insert into gas (name, formula) values ('Combustible gases',	'LEL');
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
INSERT INTO `enedb`.`aln_id_role` (`name_`) VALUES ('ADMINISTRATOR');
INSERT INTO `enedb`.`aln_id_role` (`name_`) VALUES ('MANAGER');
INSERT INTO `enedb`.`aln_id_role` (`name_`) VALUES ('OPERATOR');



CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `alarm_companydetector_sensor_view` AS select concat(`cda`.`COMPANY_DETECTOR_ID`,`cda`.`SENSOR_ID`) AS `UID`,`cda`.`ALARM_ID` AS `ALARM_ID`,`cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,`cda`.`SENSOR_ID` AS `SENSOR_ID`,`cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,`cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,`s`.`NAME` AS `SENSOR_NAME`,`s`.`RANGE_MAX` AS `RANGE_MAX` from ((`company_detector_alarms` `cda` join `company_detector` `cd` on((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`))) join `sensor` `s` on((`cda`.`SENSOR_ID` = `s`.`UID`))) order by `cda`.`ALARM_ID`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `company_detector_view` AS select `GETFAKEID`() AS `uid`,`cd`.`UID` AS `company_Detector_id`,`cd`.`NAME` AS `company_Detector_name`,`d`.`UID` AS `detector_id`,`d`.`NAME` AS `detector_name`,`s`.`UID` AS `sensor_id`,`s`.`NAME` AS `sensor_name` from (((`company_detector` `cd` join `detector` `d` on((`cd`.`DETECTOR_ID` = `d`.`UID`))) join `detector_sensors` `ds` on((`d`.`UID` = `ds`.`DETECTOR_ID`))) join `sensor` `s` on((`ds`.`SENSOR_ID` = `s`.`UID`)));

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `company_sumary_view` AS select `a`.`UID` AS `uid`,`a`.`company_name` AS `company_name`,sum((case when isnull(`a`.`unit_name`) then 0 else 1 end)) AS `units`,sum((case when isnull(`a`.`area_name`) then 0 else 1 end)) AS `areas`,sum(`a`.`devices`) AS `devices` from (select `c`.`UID` AS `UID`,`c`.`NAME` AS `company_name`,`u`.`NAME` AS `unit_name`,`a`.`NAME` AS `area_name`,sum((case when isnull(`cd`.`NAME`) then 0 else 1 end)) AS `devices` from (((`enedb`.`company_view` `c` left join `enedb`.`unit` `u` on((`c`.`UID` = `u`.`COMPANY_ID`))) left join `enedb`.`area` `a` on((`u`.`UID` = `a`.`UNIT_ID`))) left join `enedb`.`company_device` `cd` on((`a`.`UID` = `cd`.`AREA_ID`))) group by `c`.`UID`,`c`.`NAME`,`u`.`UID`,`u`.`NAME`,`a`.`UID`,`a`.`NAME`) `a` group by `a`.`UID`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `company_view` AS select `company`.`UID` AS `UID`,`company`.`NAME` AS `NAME`,`company`.`DESCRIPTION` AS `DESCRIPTION`,`company`.`UID` AS `COMPANY_ID` from `company`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dash_companies` AS select `c`.`UID` AS `company_id`,`c`.`NAME` AS `company`,`u`.`NAME` AS `unit`,`u`.`UID` AS `unit_id`,`a`.`NAME` AS `area`,`a`.`UID` AS `area_id`,(case when (`cd`.`DEVICE_TYPE` = '1') then 'DETECTOR' when isnull(`cd`.`DEVICE_TYPE`) then 'NENHUM' else 'OUTROS' end) AS `device`,`cd`.`DEVICE_TYPE` AS `device_type`,`cds`.`NAME` AS `companyDetectorName`,`cds`.`UID` AS `companyDetector_id`,`cds`.`DETECTOR_ID` AS `detector_id` from ((((`company` `c` left join `unit` `u` on((`c`.`UID` = `u`.`COMPANY_ID`))) left join `area` `a` on((`u`.`UID` = `a`.`UNIT_ID`))) left join `company_device` `cd` on((`a`.`UID` = `cd`.`AREA_ID`))) left join `company_detector` `cds` on((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`))) order by `c`.`NAME`,`u`.`NAME`,`a`.`NAME`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dash_companies_alarm` AS select `pa`.`UID` AS `uid`,`c`.`UID` AS `company_id`,`c`.`NAME` AS `company_name`,`u`.`NAME` AS `unit_name`,`a`.`NAME` AS `area_name`,`cds`.`NAME` AS `company_detector_name`,`s`.`NAME` AS `sensor_name`,`s`.`UID` AS `sensor_id`,`pa`.`LAST_VALUE` AS `last_value`,`pa`.`LAST_UPDATE` AS `last_update`,`pa`.`FIRST_UPDATE` AS `first_update`,`pa`.`ALARM_TYPE` AS `alarm_type`,`pa`.`SIGMA_STATUS` AS `sigma_status`,`pa`.`EMAIL_STATUS` AS `email_status`,`pa`.`SMS_STATUS` AS `sms_status`,`pa`.`SOUND_STATUS` AS `sound_status`,`pa`.`ALARM_STATUS` AS `ALARM_STATUS`,`pa`.`ACTION` AS `action`,`g`.`NAME` AS `gas_name`,`s`.`UNIT_METER_GASES` AS `unit_meter_gases` from ((((((((`company` `c` left join `unit` `u` on((`c`.`UID` = `u`.`COMPANY_ID`))) left join `area` `a` on((`u`.`UID` = `a`.`UNIT_ID`))) left join `company_device` `cd` on((`a`.`UID` = `cd`.`AREA_ID`))) left join `company_detector` `cds` on((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`))) left join `position_alarm` `pa` on((`cds`.`UID` = `pa`.`COMPANY_DETECTOR_ID`))) left join `sensor` `s` on((`pa`.`SENSOR_ID` = `s`.`UID`))) left join `sensor_gases` `sg` on((`s`.`UID` = `sg`.`SENSOR_ID`))) left join `gas` `g` on((`sg`.`GASES_ID` = `g`.`UID`))) where (`pa`.`UID` is not null) order by `pa`.`LAST_UPDATE` desc;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dash_companies_position` AS select `pos`.`UID` AS `uid`,`c`.`NAME` AS `company_name`,`c`.`UID` AS `company_id`,`u`.`NAME` AS `unit_name`,`a`.`NAME` AS `area_name`,`cds`.`NAME` AS `company_detector_name`,`s`.`NAME` AS `sensor_name`,`s`.`UID` AS `sensor_id`,`pos`.`UID` AS `position_id`,`pos`.`LAST_VALUE` AS `last_value`,`pos`.`ALARM_TYPE` AS `alarm_type`,`pos`.`LAST_UPDATE` AS `last_update`,`g`.`NAME` AS `gas_name`,`s`.`UNIT_METER_GASES` AS `unit_meter_gases` from ((((((((`company` `c` left join `unit` `u` on((`c`.`UID` = `u`.`COMPANY_ID`))) left join `area` `a` on((`u`.`UID` = `a`.`UNIT_ID`))) left join `company_device` `cd` on((`a`.`UID` = `cd`.`AREA_ID`))) left join `company_detector` `cds` on((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`))) left join `position` `pos` on((`cds`.`UID` = `pos`.`COMPANY_DETECTOR_ID`))) left join `sensor` `s` on((`pos`.`SENSOR_ID` = `s`.`UID`))) left join `sensor_gases` `sg` on((`s`.`UID` = `sg`.`SENSOR_ID`))) left join `gas` `g` on((`sg`.`GASES_ID` = `g`.`UID`))) where (`pos`.`UID` is not null) order by `pos`.`LAST_UPDATE` desc;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dash_detectors_maintenance` AS select `cds`.`UID` AS `UID`,`c`.`NAME` AS `company_name`,`u`.`NAME` AS `unit_name`,`a`.`NAME` AS `area_name`,`cds`.`NAME` AS `company_detector_name`,`d`.`IMAGE` AS `image`,`cds`.`MAINTENANCE_INTERVAL` AS `MAINTENANCE_INTERVAL`,`cds`.`INSTALL_DATE` AS `INSTALL_DATE`,`z`.`LAST_DATE` AS `LAST_DATE` from ((((((`enedb`.`company` `c` join `enedb`.`unit` `u` on((`c`.`UID` = `u`.`COMPANY_ID`))) join `enedb`.`area` `a` on((`u`.`UID` = `a`.`UNIT_ID`))) join `enedb`.`company_device` `cd` on((`a`.`UID` = `cd`.`AREA_ID`))) join `enedb`.`company_detector` `cds` on((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`))) join `enedb`.`detector` `d` on((`cds`.`DETECTOR_ID` = `d`.`UID`))) left join (select `enedb`.`company_detector_maintenance_historic`.`COMPANY_DETECTOR_ID` AS `companyDetectorId`,max(`enedb`.`company_detector_maintenance_historic`.`DATE`) AS `LAST_DATE`,`enedb`.`company_detector_maintenance_historic`.`DESCRIPTION` AS `description` from `enedb`.`company_detector_maintenance_historic` where (`enedb`.`company_detector_maintenance_historic`.`HISTORIC_MAINTENACE_TYPE` = 2) group by `enedb`.`company_detector_maintenance_historic`.`COMPANY_DETECTOR_ID`) `z` on((`cds`.`UID` = `z`.`companyDetectorId`))) where (`cds`.`INSTALL_DATE` is not null);

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `historic_view` AS select `historic`.`UID` AS `UID`,`historic`.`LAST_UPDATE` AS `LAST_UPDATE`,`historic`.`VALUE` AS `VALUE`,`historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,`historic`.`SENSOR_ID` AS `SENSOR_ID` from `historic` order by `historic`.`COMPANY_DETECTOR_ID`,`historic`.`LAST_UPDATE` desc;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `historic_view_day` AS select `getFakeId`() AS `uid`,`historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,`historic`.`SENSOR_ID` AS `SENSOR_ID`,str_to_date(date_format(`historic`.`LAST_UPDATE`,'%Y-%m-%d 00:00:00'),'%Y-%m-%d %H:%i:%s') AS `last_update`,count(0) AS `ticks`,max(`historic`.`VALUE`) AS `value`,max(`historic`.`VALUE`) AS `max_value`,min(`historic`.`VALUE`) AS `min_value` from `historic` group by `historic`.`COMPANY_DETECTOR_ID`,`historic`.`SENSOR_ID`,date_format(`historic`.`LAST_UPDATE`,'%d/%m/%Y') order by `historic`.`COMPANY_DETECTOR_ID`,date_format(`historic`.`LAST_UPDATE`,'%d/%m/%Y');

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `historic_view_hour` AS select `GETFAKEID`() AS `uid`,`historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,`historic`.`SENSOR_ID` AS `SENSOR_ID`,str_to_date(date_format(`historic`.`LAST_UPDATE`,'%Y-%m-%d %H:00:00'),'%Y-%m-%d %H:%i:%s') AS `last_update`,count(0) AS `ticks`,max(`historic`.`VALUE`) AS `value`,max(`historic`.`VALUE`) AS `max_value`,min(`historic`.`VALUE`) AS `min_value` from `historic` group by `historic`.`COMPANY_DETECTOR_ID`,`historic`.`SENSOR_ID`,date_format(`historic`.`LAST_UPDATE`,'%Y-%m-%d %H:00:00') order by `historic`.`COMPANY_DETECTOR_ID`,date_format(`historic`.`LAST_UPDATE`,'%Y-%m-%d %H:00:00');

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `queue_email_view` AS select `pa`.`UID` AS `UID`,`pa`.`EMAIL_STATUS` AS `EMAIL_STATUS`,`a`.`NAME` AS `ALARM_NAME`,`pa`.`LAST_UPDATE` AS `LAST_UPDATE`,`pa`.`LAST_VALUE` AS `LAST_VALUE`,`pa`.`ALARM_TYPE` AS `ALARM_TYPE`,`a`.`NAME` AS `NAME`,`a`.`EMAIL` AS `EMAIL`,`a`.`EMAIL1` AS `EMAIL1`,`cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,`cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,`cda`.`SENSOR_ID` AS `SENSOR_ID`,`g`.`NAME` AS `GAS_NAME`,`s`.`UNIT_METER_GASES` AS `UNIT_METER_GASES` from ((((((`position_alarm` `pa` join `company_detector_alarms` `cda` on(((`pa`.`COMPANY_DETECTOR_ID` = `cda`.`COMPANY_DETECTOR_ID`) and (`pa`.`SENSOR_ID` = `cda`.`SENSOR_ID`)))) join `sensor` `s` on((`pa`.`SENSOR_ID` = `s`.`UID`))) join `sensor_gases` `sg` on((`s`.`UID` = `sg`.`SENSOR_ID`))) join `gas` `g` on((`sg`.`GASES_ID` = `g`.`UID`))) join `alarm` `a` on((`cda`.`ALARM_ID` = `a`.`UID`))) join `company_detector` `cd` on((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`))) where ((`pa`.`EMAIL_STATUS` = 1) or (`pa`.`EMAIL_STATUS` = 3));

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `queue_sigma_view` AS select `pa`.`UID` AS `UID`,`pa`.`SIGMA_STATUS` AS `SIGMA_STATUS`,`a`.`NAME` AS `ALARM_NAME`,`pa`.`LAST_UPDATE` AS `LAST_UPDATE`,`pa`.`LAST_VALUE` AS `LAST_VALUE`,`pa`.`ALARM_TYPE` AS `ALARM_TYPE`,`a`.`NAME` AS `NAME`,`a`.`CELULAR` AS `CELULAR`,`cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,`cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,`cda`.`SENSOR_ID` AS `SENSOR_ID` from (((`position_alarm` `pa` join `company_detector_alarms` `cda` on(((`pa`.`COMPANY_DETECTOR_ID` = `cda`.`COMPANY_DETECTOR_ID`) and (`pa`.`SENSOR_ID` = `cda`.`SENSOR_ID`)))) join `alarm` `a` on((`cda`.`ALARM_ID` = `a`.`UID`))) join `company_detector` `cd` on((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`))) where (`pa`.`SIGMA_STATUS` = 1);

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `queue_sms_view` AS select `pa`.`UID` AS `UID`,`pa`.`SMS_STATUS` AS `SMS_STATUS`,`a`.`NAME` AS `ALARM_NAME`,`pa`.`LAST_UPDATE` AS `LAST_UPDATE`,`pa`.`LAST_VALUE` AS `LAST_VALUE`,`pa`.`ALARM_TYPE` AS `ALARM_TYPE`,`a`.`NAME` AS `NAME`,`a`.`CELULAR` AS `CELULAR`,`a`.`CELULAR1` AS `CELULAR1`,`cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,`cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,`cda`.`SENSOR_ID` AS `SENSOR_ID`,`g`.`NAME` AS `GAS_NAME`,`s`.`UNIT_METER_GASES` AS `UNIT_METER_GASES` from ((((((`position_alarm` `pa` join `company_detector_alarms` `cda` on(((`pa`.`COMPANY_DETECTOR_ID` = `cda`.`COMPANY_DETECTOR_ID`) and (`pa`.`SENSOR_ID` = `cda`.`SENSOR_ID`)))) join `sensor` `s` on((`pa`.`SENSOR_ID` = `s`.`UID`))) join `sensor_gases` `sg` on((`s`.`UID` = `sg`.`SENSOR_ID`))) join `gas` `g` on((`sg`.`GASES_ID` = `g`.`UID`))) join `alarm` `a` on((`cda`.`ALARM_ID` = `a`.`UID`))) join `company_detector` `cd` on((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`))) where ((`pa`.`SMS_STATUS` = 1) or (`pa`.`SMS_STATUS` = 3));
