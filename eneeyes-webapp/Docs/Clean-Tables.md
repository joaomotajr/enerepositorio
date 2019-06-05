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