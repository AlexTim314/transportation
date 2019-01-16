-- Used by Spring Remember Me API.  
CREATE TABLE IF NOT EXISTS Persistent_Logins (
 
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
     
);


CREATE TABLE IF NOT EXISTS Department (

  id bigint NOT NULL DEFAULT nextval('department_id_seq'::regclass),
  address varchar(255),
  fullname varchar(255),
  phone varchar(16),
  shortname varchar(64) not null,
  PRIMARY KEY (id)
);

insert into Department (address, fullname, phone, shortname) values (null,'Авиапредприятие "Аэропорт "Крайний',null,'Аэропорт "Крайний"');
insert into Department (address, fullname, phone, shortname) values (null,'Гостиничный комплекс',null,'Гостиничный комплекс');
insert into Department (address, fullname, phone, shortname) values (null,'Комплекс "Аэродром Юбилейный"',null,'КАЮ');
insert into Department (address, fullname, phone, shortname) values (null,'Комплекс автотранспортного обеспечения',null,'КАТО');
insert into Department (address, fullname, phone, shortname) values (null,'Комплекс Геофизического обеспечения',null,'КГФО');
insert into Department (address, fullname, phone, shortname) values (null,'Комплекс железнодорожного обеспечения',null,'КЖДО');
insert into Department (address, fullname, phone, shortname) values (null,'Комплекс технического и материального обеспечения',null,'КТМО');
insert into Department (address, fullname, phone, shortname) values (null,'Комплекс эксплуатации зданий и сооружений',null,'КЭЗС');
insert into Department (address, fullname, phone, shortname) values (null,'Комплекс эксплуатации сетей связи и телекоммуникаций',null,'КЭССТ');
insert into Department (address, fullname, phone, shortname) values (null,'Отдел капитального строительства',null,'ОКС');
insert into Department (address, fullname, phone, shortname) values (null,'Оперативное управление',null,'Оперативное управление');
insert into Department (address, fullname, phone, shortname) values (null,'Отдел организационного развития',null,'Отдел организационного развития');
insert into Department (address, fullname, phone, shortname) values (null,'Отдел экономической безопасности и противодействия коррупции',null,'Отдел ЭБ и ПК');
insert into Department (address, fullname, phone, shortname) values (null,'Сектор по организации работ в ГКЦ',null,'Сектор по организации работ в ГКЦ');
insert into Department (address, fullname, phone, shortname) values (null,'Служба безопасности',null,'Служба безопасности');
insert into Department (address, fullname, phone, shortname) values (null,'Служба охраны труда и производственного контроля',null,'Служба ОТПК');


