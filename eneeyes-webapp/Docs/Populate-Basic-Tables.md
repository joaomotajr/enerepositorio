/* USER */
INSERT INTO aln_id_user(ID_, CREATE_DATE_, DISPLAYNAME_, HASH_, LOGIN_, NICKNAME_, STATUS_, CPF_) 
	VALUES (null,'2017-08-17','Joao Jr',sha('123456'),'jrcowboy','Joao Jr','ACTIVE', '13210102813');
	
/* INCLUIR ROLES */
INSERT INTO aln_id_role (name_) VALUES ('ADMINISTRATOR');
INSERT INTO aln_id_role (name_) VALUES ('MANAGER');
INSERT INTO aln_id_role (name_) VALUES ('OPERATOR');

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

/* DEVICE_TYPE */
INSERT INTO device_type (uid, type, descritpion, symbol, ghaphicType)  values (0,'OUTROS','Outros','fa-exclamation-triangle',3);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (1,'DETECTOR','Detector','fa-th-large',4);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (2,'PLC','PLC','fa-laptop',4);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (3,'CONTROLLER','Controladora','fa-server',0);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (4,'ALARM','Alarme','fa-bullhorn',4);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (5,'BLOCKER','Bloqueador','fa-stop',0);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (6,'ELETRICITY','Eletricidade','fa-plug',2);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (7,'TIME','Tempo','fa-clock-o',0);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (8,'THERMOMETER','Temperatura','fa-thermometer',6);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (9,'DIGITAL','Digital','fa-flash',0);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (11,'VOLUME','Volume','fa-flask',8);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (12,'PRESSURE','Pressão','fa-compress',5);
INSERT INTO device_type (uid, type, description, symbol, graphicType)  values (13,'FLOW','Vazão','fa-database',1);

/* UNIT-METER */
INSERT INTO unit_meter values (0, '0/1', 'Digital');
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





