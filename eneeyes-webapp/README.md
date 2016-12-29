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

* INCLUIR USUARIO INICIAL
INSERT INTO `conciliador`.`aln_id_user`(`ID_`, `ACCESS_TOKEN_`, `CREATE_DATE_`, `DISPLAYNAME_`, `EXPIRE_TIME_`, `HASH_`, `IMAGE_URL_`, `LOGIN_`, `NICKNAME_`, `PROFILE_URL_`, `PROVIDER_ID_`, `PROVIDER_USER_ID_`, `REFRESH_TOKEN_`, `SECRET_`, `STATUS_`, `CNPJ_`) VALUES (null,null,'2015-09-18','CK Medic',null,sha1('123456'),null,'joaomotajunior@gmail.com','CK Medic',null,null,null,null,null,'ACTIVE', '13210102813');

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
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('ADMINISTRATOR', 'ACTIVE', 'ADM');
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('CONTRACTOR', 'ACTIVE', 'CTR');
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('MANAGER', 'ACTIVE', 'MNG');
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('OPERATOR', 'ACTIVE', 'OPR');

