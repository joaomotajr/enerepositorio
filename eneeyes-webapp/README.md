* COMANDO PARA EXECUCAO DA APLICACAO
mvn jetty:run -Dspring.profiles.active="dev" -P local

* COMANDOS SQL NECESSÁRIOS PARA EXECUÇÃO DA FUNCIONALIDADE DE AUTÊNTICAÇÃO
insert into aln_id_role values (NULL, 'ADMINISTRATOR', 'ACTIVE', 'ADM');
insert into aln_id_role values (NULL, 'CUSTOMER', 'ACTIVE', 'CST');
insert into aln_id_role values (NULL, 'EXPERT', 'ACTIVE', 'EXT');

* INCLUIR USUARIO INICIAL
INSERT INTO `conciliador`.`aln_id_user`(`ID_`, `ACCESS_TOKEN_`, `CREATE_DATE_`, `DISPLAYNAME_`, `EXPIRE_TIME_`, `HASH_`, `IMAGE_URL_`, `LOGIN_`, `NICKNAME_`, `PROFILE_URL_`, `PROVIDER_ID_`, `PROVIDER_USER_ID_`, `REFRESH_TOKEN_`, `SECRET_`, `STATUS_`, `CNPJ_`) VALUES (null,null,'2015-09-18','CK Medic',null,sha1('123456'),null,'joaomotajunior@gmail.com','CK Medic',null,null,null,null,null,'ACTIVE', '13210102813');

INSERT INTO `conciliador`.`taxa` (`CREDITO_`, `CREDITO_2_6_`, `CREDITO_7_12_`, `DT_INICIO_`, `DEBITO_`) VALUES ('4', '4.5', '4.9', '2015-11-25', '2.9');
INSERT INTO `conciliador`.`adquirente` (`DESCRICAO_`, `TAXA_ID_`) VALUES ('Cielo', '1');
UPDATE `conciliador`.`aln_id_user` SET `ADQUIRENTE_ID_`='1' WHERE `ID_`='1';

/* LIMPAR TABELAS */
drop table detector_sensors;
drop table area;
drop table unit;
drop table company;
drop table controller;
drop table gases;
drop table sensor;
drop table transmitter;
drop table detector;
drop table device;
drop table device_type;
drop table company_devices;



/* INCLUIR CODE DATA TIPO OPCAO EXTRATO */
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('01','Venda com CV');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('02','Venda sem CV');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('03','Venda com CV + Parcelado Futuro');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('04','Pagamento com CV');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('05','Pagamento sem CV');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('06','Antecipação de Recebíveis com CV ou sem CV');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('07','Cessão de Recebíveis');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('08','Parcelas Pendentes');
INSERT INTO `tipo_opcao_extrato`(`COD_`, `DESCRICAO_`) VALUES ('09','Saldo em abertotipo_opcao_extrato');

/* INCLUIR CODE DATA TIPO TRANSACAO */
INSERT INTO `tipo_transacao`(`COD_`, `DESCRICAO_`) VALUES ('01','Venda');
INSERT INTO `tipo_transacao`(`COD_`, `DESCRICAO_`) VALUES ('02','Ajuste a crédito');
INSERT INTO `tipo_transacao`(`COD_`, `DESCRICAO_`) VALUES ('03','Ajuste a débito');
INSERT INTO `tipo_transacao`(`COD_`, `DESCRICAO_`) VALUES ('04','Pacote Cielo');
INSERT INTO `tipo_transacao`(`COD_`, `DESCRICAO_`) VALUES ('05','Reagendamento');

/* INCLUIR CODE DATA STATUS PAGAMENTO */
INSERT INTO `status_pagamento`(`COD_`, `DESCRICAO_`) VALUES ('00','Agendado: identifica a captura de um lançamento (resumo de venda ou ajuste) e informa a previsão de pagamento do lançamento. A data prevista poderá ser alterada.');
INSERT INTO `status_pagamento`(`COD_`, `DESCRICAO_`) VALUES ('01','Pago: identifica que o pagamento foi realizado pelo banco domicílio na data informada.');
INSERT INTO `status_pagamento`(`COD_`, `DESCRICAO_`) VALUES ('02','Enviado para o banco: identifica que a ordem de pagamento foi enviada pela Cielo ao banco domicílio e que ainda não houve a confi rmação do pagamento pelo banco. ');
INSERT INTO `status_pagamento`(`COD_`, `DESCRICAO_`) VALUES ('03','A confirmar: identifica que a ordem de pagamento já foi enviada pela Cielo ao banco domicílio há alguns dias e que ainda não houve a confirmação do pagamento por parte do banco.');

/* INCLUIR ROLES */
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('ADMINISTRATOR', 'ACTIVE', 'ADM');
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('CONTRACTOR', 'ACTIVE', 'CTR');
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('MANAGER', 'ACTIVE', 'MNG');
INSERT INTO `conciliador`.`aln_id_role` (`NAME_`, `STATUS_`, `VALUE_`) VALUES ('OPERATOR', 'ACTIVE', 'OPR');

/* INCLUIR CODE DATA CODIGO PRODUTO */
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('001','Agiplan crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('002','Agiplan parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('003','Banescard crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('004','Banescard parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('005','Esplanada crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('006','Credz crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('007','Esplanada parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('008','Credz parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('009','Elo Crediário', 'Crediário');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('010','Mastercard crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('011','Maestro', 'Débito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('012','Mastercard parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('013','Elo Construcard', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('014','Elo Agro Débito', 'Débito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('015','Elo Agro Custeio', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('016','Elo Agro Investimento', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('017','Elo Agro Custeio + Débito', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('018','Elo Agro Investimento + Débito', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('019','Discover crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('020','Diners crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('021','Diners parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('022','Agro Custeio + Electron', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('023','Agro Investimento + Electron', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('024','FCO Investimento', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('025','Agro Electron', 'Débito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('026','Agro Custeio', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('027','Agro Investimento', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('028','FCO Giro', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('033','JCB', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('036','Saque com cartão de Débito VISA', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('037','Flex Car Visa Vale', 'Benefício');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('038','Credsystem crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('039','Credsystem parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('040','Visa Crédito à Vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('041','Visa Electron Débito à Vista', 'Débito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('042','Visa Pedágio', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('043','Visa Parcelado Loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('044','Visa Electron Pré-Datado', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('045','Alelo Refeição (Bandeira Visa/Elo)', 'Benefício');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('046','Alelo Alimentação (Bandeira Visa/Elo)', 'Benefício');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('058','Elo Cultura', 'Benefício');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('059','Alelo Auto', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('061','Sorocred crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('062','Sorocred parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('064','Visa Crediário', 'Crediário');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('065','Alelo Refeição (Bandeira Elo)', 'Benefício');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('066','Alelo Alimentação (Bandeira Elo)', 'Benefício');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('067','Visa Capital de Giro', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('068','Visa Crédito Imobiliário', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('069','Cultura Visa Vale', 'Benefício');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('070','Elo Crédito', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('071','Elo Débito a Vista', 'Débito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('072','Elo Parcelado Loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('079','Pagamento Carnê Visa Electron', 'Crediário');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('080','Visa Crédito Conversor de Moeda', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('081','Elo Crédito Especializado', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('089','Elo Crédito Imobiliário', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('091','Mastercard Crédito Especializado', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('094','Banescard Débito', 'Débito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('096','Cabal crédito à vista', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('097','Cabal Débito', 'Débito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('098','Cabal parcelado loja', 'Crédito');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('342','Master Pedágio', 'Outros');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('377','Elo Carnê', 'Crediário');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('378','Master Carnê', 'Crediário');
INSERT INTO `codigo_produto`(`COD_`, `DESCRICAO_`, `TIPO_`) VALUES ('380','Mastercard Crédito Conversor de Moeda', 'Outros');


/* INCLUIR CODE DATA ORIGEM AJUSTE */
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('01','Acerto de correção monetária','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('02','Acerto de data de pagamento','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('03','Acerto de taxa de comissão','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('04','Acerto de valores não processados','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('05','Acerto de valores não recebidos','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('06','Acerto de valores não reconhecidos','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('07','Acerto de valores negociados','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('08','Acerto de valores processados indevidamente','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('09','Acerto de lançamento não compensado em conta-corrente','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('10','Acerto referente valores contestados','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('11','Acerto temporário de valores contestados','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('12','Acertos diversos','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('13','Acordo de cobrança','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('14','Acordo jurídico','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('15','Aplicação de multa Programa Monitoria Chargeback','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('16','Bloqueio de valor por ordem judicial','Bloqueio');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('17','Cancelamento da venda','Cancelamento');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('18','Cobrança de tarifa operacional','Cobrança');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('19','Cobrança mensal Lynx Comércio','Cobrança');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('20','Cobrança Plano Cielo','Cobrança');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('21','Contrato de caução','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('22','Crédito de devolução do cancelamento - banco emissor','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('23','Crédito EC - referente contestação portador','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('24','Crédito por cancelamento rejeitado - Cielo','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('25','Processamento do débito duplicado - Visa Pedágio','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('26','Débito por venda realizada sem a leitura do chip','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('27','Débito por venda rejeitada no sistema - Cielo','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('28','Débito referente à contestação do portador','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('29','Estorno de acordo jurídico','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('30','Estorno de contrato de caução','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('31','Estorno de acordo de cobrança','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('32','Estorno de bloqueio de valor por ordem judicial','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('33','Estorno de cancelamento de venda','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('34','Estorno de cobrança de tarifa operacional','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('35','Estorno de cobrança mensal Lynx Comércio','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('36','Estorno de cobrança Plano Cielo','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('37','Estorno de débito venda sem a leitura do Chip','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('38','Estorno de incentivo comercial','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('39','Estorno de Multa Programa Monitoria Chargeback','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('40','Estorno de rejeição ARV','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('41','Estorno de reversão de duplicidade do pagamento - ARV','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('42','Estorno de tarifa de cadastro','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('43','Estorno de extrato no papel','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('44','Estorno de processamento duplicado de débito - Visa Pedágio','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('45','Incentivo comercial','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('46','Incentivo por venda de Recarga','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('47','Regularização de rejeição ARV','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('48','Reversão de duplicidade do pagamento - ARV','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('49','Tarifa de cadastro','Cobrança');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('50','Tarifa de extrato no papel','Cobrança');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('51','Aceleração de débito de antecipação','Acerto');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('52','Débito por descumprimento de cláusula contratual','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('53','Débito por cancelamento de venda','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('54','Débito por não reconhecimento de compra','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('55','Débito por venda com cartão com validade vencida','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('56','Débito por não reconhecimento de compra','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('57','Débito por cancelamento e/ou devolução dos serviços','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('58','Débito por transação irregular','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('59','Débito por não entrega da mercadoria','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('60','Débito por serviços não prestados','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('61','Débito efetuado por venda sem código de autorização','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('62','Débito efetuado por venda com número de cartão inválido','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('63','Débito por cópia de CV e/ou documento não atendido','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('64','Débito por venda efetuada com autorização negada','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('65','Débito por envio de CV e/ou documento ilegível','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('66','Débito por venda efetuada sem leitura de chip','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('67','Débito por venda em outra moeda','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('68','Débito por venda processada incorretamente','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('69','Débito por cancelamento de venda','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('70','Débito por crédito em duplicidade','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('71','Débito por documentos solicitados e não recebidos','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('72','Débito por envio de CV e/ou documento incorreto','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('73','Débito por envio de CV e/ou documento fora do prazo','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('74','Débito por não reconhecimento de despesa','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('75','Débito por documentação solicitada incompleta','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('76','Débito por estabelecimento não possui CV e/ou doc.','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('77','Programa de monitoria de chargeback','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('78','Serviços Score','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('79','Reagendamento do débito de antecipação','Chargeback');
INSERT INTO `origem_ajuste`(`COD_`, `DESCRICAO_`,`TIPO_AJUSTE_`) VALUES ('80','Ajuste do débito de cessão','Chargeback');


/* INCLUIR CODE DATA CODIGO BANDEIRA */
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('001','VISA');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('002','Mastercard');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('006','SoroCred');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('007','ELO');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('009','Diners');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('011','Agiplan');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('015','Banescard');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('023','Cabal');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('029','Credsystem');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('035','Esplanada');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('064','Credz');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('1','Maestro');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('3','Visa Electron');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('4','Cabal');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('9','Sicredi');
INSERT INTO `codigo_bandeira`(`COD_`, `DESCRICAO_`) VALUES ('H','Hiper');


/* INCLUIR CODE DATA MEIO CAPTURA */
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('01','POS - Point of Sale');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('02','PDV - Ponto de Venda ou TEF (Transferência Eletrônica de Fundos)');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('03','E-Commerce - Comércio Eletrônico');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('04','EDI - Troca Eletrônica de Dados');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('05','ADP/BSP - Empresa Capturadora');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('06','Manual');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('07','URA/CVA');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('08','Mobile');
INSERT INTO `meio_captura`(`COD_`, `DESCRICAO_`) VALUES ('09','Moedeiro eletrônico em rede');

/* INCLUIR CODE DATA MOTIVO REJEICAO */
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('002','Cartão Inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('023','Outros Erros');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('031','Transação de saque com cartão Electron valor zerado');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('039','Banco emissor inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('044','Data da transação inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('045','Código de Autorização inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('055','Número de parcelas inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('056','Transação fi nanciada para estabelecimento não autorizado');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('057','Cartão em boletim protetor');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('061','Número de cartão inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('066','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('067','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('069','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('070','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('071','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('072','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('073','Transação inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('074','Valor de transação inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('075','Número de cartão inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('077','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('078','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('079','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('080','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('081','Cartão vencido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('082','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('083','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('084','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('086','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('092','Banco emissor sem comunicação');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('093','Desbalenceamento no plano parcelado');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('094','Venda parcelada para cartão emitido no exterior');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('097','Valor de parcela menor do que o permitido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('099','Banco emissor inválido');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('100','Transação não autorizada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('101','Transação duplicada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('102','Transação duplicada');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('124','BIN não cadastrado');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('126','Transação de saque com cartão Electron inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('128','Transação de saque com cartão Electron inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('129','Transação de saque com cartão Electron inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('130','Transação de saque com cartão Electron inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('133','Transação de saque com cartão Electron inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('134','Transação de saque com cartão Electron inválida');
INSERT INTO `motivo_rejeicao`(`COD_`, `DESCRICAO_`) VALUES ('145','Estabelecimento inválido para distribuição');
