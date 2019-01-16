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
    shortname varchar(64) not null,
    fullname varchart(255),
    address varchar(255),
    phone varchar(16),
    PRIMARY KEY (id)
);

insert into Department(shortname, fullname, address, phone) values('Служба эксплуатации РКТ', 'Служба эксплуатации ракетно-космической техники', null, null);
insert into Department(shortname, fullname, address, phone) values('СМО', 'Служба медицинского обеспечения', null, null);
insert into Department(shortname, fullname, address, phone) values('Управление', 'Управление', null, null);
insert into Department(shortname, fullname, address, phone) values('УВПО', 'Управление ведомственной пожарной охраны', null, null);
insert into Department(shortname, fullname, address, phone) values('Управление делами', 'Управление делами', null, null);
insert into Department(shortname, fullname, address, phone) values('УКОРИ', 'Управление комплексной оценки результатов испытаний', null, null);
insert into Department(shortname, fullname, address, phone) values('УОЗД', 'Управление обеспечения закупочной деятельности', null, null);
insert into Department(shortname, fullname, address, phone) values('УОК РКТ', 'Управление обеспечения качества ракетно-космической техники', null, null);
insert into Department(shortname, fullname, address, phone) values('УРП', 'Управление по работе с персоналом', null, null);
insert into Department(shortname, fullname, address, phone) values('УПО', 'Управление правового обеспечения', null, null);
insert into Department(shortname, fullname, address, phone) values('ФЭС', 'Финансово-экономическая служба', null, null);
insert into Department(shortname, fullname, address, phone) values('ЦИ КЗ', 'Центр испытаний комплексов заправки', null, null);
insert into Department(shortname, fullname, address, phone) values('ЦИ ТК', 'Центр испытаний технических комплексов', null, null);
insert into Department(shortname, fullname, address, phone) values('ЦИ-1', 'Центр испытаний-1', null, null);
insert into Department(shortname, fullname, address, phone) values('ЦИ-2', 'Центр испытаний-2', null, null);
insert into Department(shortname, fullname, address, phone) values('ЦИ-3', 'Центр испытаний-3', null, null);
insert into Department(shortname, fullname, address, phone) values('ЦИП ИК', 'Центр испытаний и применения измерительного комплекса', null, null);
insert into Department(shortname, fullname, address, phone) values('ЦОКИ РКТ', 'Центр организации и контроля испытаний ракетно-космической техники', null, null);
insert into Department(shortname, fullname, address, phone) values('ЭУ', 'Энергоуправление', null, null);

