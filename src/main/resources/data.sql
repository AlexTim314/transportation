-- Used by Spring Remember Me API.  
CREATE TABLE IF NOT EXISTS Persistent_Logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)   
);

CREATE TABLE IF NOT EXISTS Place (
    id bigint NOT NULL DEFAULT nextval('place_id_seq'::regclass),
    name varchar(255),
    address varchar(255),
    PRIMARY KEY (id)
);

insert into Fuel(mark, type) values('АИ-80', 0);
insert into Fuel(mark, type) values('АИ-92', 0);
insert into Fuel(mark, type) values('АИ-95', 0);
insert into Fuel(mark, type) values('ДТЛ', 1);
insert into Fuel(mark, type) values('ДТЗ', 1);


insert into Place(name, address) values('Аэропорт "Крайний"', null);
insert into Place(name, address) values('Полигон ТБО', null);
insert into Place(name, address) values('КАЗ', null);

insert into Place(name, address) values('Ж/д ст. Разъезд', null);
insert into Place(name, address) values('Ж/д ст. Восточная', null);
insert into Place(name, address) values('Ж/д ст. Северная', null);
insert into Place(name, address) values('Ж/д ст. Западная', null);
insert into Place(name, address) values('Ж/д ст. Жигули', null);

insert into Place(name, address) values('КПП-1', null);
insert into Place(name, address) values('КПП-3', null);
insert into Place(name, address) values('КПП-5', null);
insert into Place(name, address) values('КПП-7', null);

insert into Place(name, address) values('Пл. 1', 'СК17П32');
insert into Place(name, address) values('Пл. 2', null);
insert into Place(name, address) values('Пл. 2А', null);
insert into Place(name, address) values('Пл. 2Б', null);
insert into Place(name, address) values('Пл. 2 (пер.)', null);
insert into Place(name, address) values('Пл. 3Б', null);
insert into Place(name, address) values('Пл. 3Р', null);
insert into Place(name, address) values('Пл. 5', null);
insert into Place(name, address) values('Пл. 6', null);
insert into Place(name, address) values('Пл. 9', null);
insert into Place(name, address) values('Пл. 9 (АЗП)', null);
insert into Place(name, address) values('Пл. 10', 'ул. Титова Г.С., д. 7');
insert into Place(name, address) values('Пл. 15', null);
insert into Place(name, address) values('Пл. 15А', null);
insert into Place(name, address) values('Пл. 15Г', null);
insert into Place(name, address) values('Пл. 17 МЖГ', null);
insert into Place(name, address) values('Пл. 18', 'ИП-1');
insert into Place(name, address) values('Пл. 23', 'ИП-5');
insert into Place(name, address) values('Пл. 31', null);
insert into Place(name, address) values('Пл. 32', null);
insert into Place(name, address) values('Пл. 43', null);
insert into Place(name, address) values('Пл. 44', 'ИП-2');
insert into Place(name, address) values('Пл. 45', null);
insert into Place(name, address) values('Пл. 71', null);
insert into Place(name, address) values('Пл. 72', null);
insert into Place(name, address) values('Пл. 75', null);
insert into Place(name, address) values('Пл. 81', null);
insert into Place(name, address) values('Пл. 90', null);
insert into Place(name, address) values('Пл. 91', null);
insert into Place(name, address) values('Пл. 91А', null);
insert into Place(name, address) values('Пл. 92', null);
insert into Place(name, address) values('Пл. 92А 50', null);
insert into Place(name, address) values('Пл. 95', null);
insert into Place(name, address) values('Пл. 97', 'ИП-3');
insert into Place(name, address) values('Пл. 109', null);
insert into Place(name, address) values('Пл. 110', null);
insert into Place(name, address) values('Пл. 110А', null);
insert into Place(name, address) values('Пл. 111', null);
insert into Place(name, address) values('Пл. 111 (прямая)', null);
insert into Place(name, address) values('Пл. 111 (объездная)', null);
insert into Place(name, address) values('Пл. 112', null);
insert into Place(name, address) values('Пл. 113', null);
insert into Place(name, address) values('Пл. 113Г', null);
insert into Place(name, address) values('Пл. 114А', null);
insert into Place(name, address) values('Пл. 114Б', null);
insert into Place(name, address) values('Пл. 175', null);
insert into Place(name, address) values('Пл. 200', null);
insert into Place(name, address) values('Пл. 250', null);
insert into Place(name, address) values('Пл. 250А', null);
insert into Place(name, address) values('Пл. 251', null);
insert into Place(name, address) values('Пл. 254', null);
insert into Place(name, address) values('Пл. 257А', null);
insert into Place(name, address) values('Пл. 504', null);
insert into Place(name, address) values('Пл. 504-3К', null);
insert into Place(name, address) values('По указанию', null);


CREATE TABLE IF NOT EXISTS Department (
    id bigint NOT NULL DEFAULT nextval('department_id_seq'::regclass),
    shortname varchar(64) not null,
    fullname varchar(255),
    address varchar(255),
    phone varchar(16),
    PRIMARY KEY (id)
);

insert into Department (shortname, fullname, address, phone) values('Служба эксплуатации РКТ', 'Служба эксплуатации ракетно-космической техники', null, null);
insert into Department (shortname, fullname, address, phone) values('СМО', 'Служба медицинского обеспечения', null, null);
insert into Department (shortname, fullname, address, phone) values('Управление', 'Управление', null, null);
insert into Department (shortname, fullname, address, phone) values('УВПО', 'Управление ведомственной пожарной охраны', null, null);
insert into Department (shortname, fullname, address, phone) values('Управление делами', 'Управление делами', null, null);
insert into Department (shortname, fullname, address, phone) values('УКОРИ', 'Управление комплексной оценки результатов испытаний', null, null);
insert into Department (shortname, fullname, address, phone) values('УОЗД', 'Управление обеспечения закупочной деятельности', null, null);
insert into Department (shortname, fullname, address, phone) values('УОК РКТ', 'Управление обеспечения качества ракетно-космической техники', null, null);
insert into Department (shortname, fullname, address, phone) values('УРП', 'Управление по работе с персоналом', null, null);
insert into Department (shortname, fullname, address, phone) values('УПО', 'Управление правового обеспечения', null, null);
insert into Department (shortname, fullname, address, phone) values('ФЭС', 'Финансово-экономическая служба', null, null);
insert into Department (shortname, fullname, address, phone) values('ЦИ КЗ', 'Центр испытаний комплексов заправки', null, null);
insert into Department (shortname, fullname, address, phone) values('ЦИ ТК', 'Центр испытаний технических комплексов', null, null);
insert into Department (shortname, fullname, address, phone) values('ЦИ-1', 'Центр испытаний-1', null, null);
insert into Department (shortname, fullname, address, phone) values('ЦИ-2', 'Центр испытаний-2', null, null);
insert into Department (shortname, fullname, address, phone) values('ЦИ-3', 'Центр испытаний-3', null, null);
insert into Department (shortname, fullname, address, phone) values('ЦИП ИК', 'Центр испытаний и применения измерительного комплекса', null, null);
    insert into Route_Template(name, department_id) values('ИП-1 пл. 18 (Ежедневно)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Начало движения к пл. 18', 0, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 1, (select id from Place where name='Пл. 18'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 2, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('ИП-2 пл. 44 (Ежедневно)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Начало движения к пл. 44', 0, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 1, (select id from Place where name='Пл. 44'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 2, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('ИП-3 пл. 97 (Ежедневно)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Начало движения к пл. 97', 0, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 1, (select id from Place where name='Пл. 97'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 2, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('ИП-5 пл. 23 (Ежедневно)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Начало движения к пл. 23', 0, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 1, (select id from Place where name='Пл. 23'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров - 50 чел.', 2, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('По указанию (Ежедневно)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Начало движения', 0, (select id from Place where name='Пл. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('Перевозка пассажиров', 1, (select id from Place where name='По указанию'), currval('route_template_id_seq'));
insert into Department (shortname, fullname, address, phone) values('ЦОКИ РКТ', 'Центр организации и контроля испытаний ракетно-космической техники', null, null);
insert into Department (shortname, fullname, address, phone) values('ЭУ', 'Энергоуправление', null, null);
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

CREATE TABLE IF NOT EXISTS Transport_dep (
    id bigint NOT NULL DEFAULT nextval('transport_dep_id_seq'::regclass),
    address varchar(255),
    fullname varchar(255),
    phone varchar(16),
    shortname varchar(64) not null,
    PRIMARY KEY (id)
);

insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 1','Отдел транспортных средств №1',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 2','Отдел транспортных средств №2',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 3','Отдел транспортных средств №3',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 4','Отдел транспортных средств №4',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 5','Отдел транспортных средств №5',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 6','Отдел транспортных средств №6',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 7','Отдел транспортных средств №7',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('ОТС 8','Отдел транспортных средств №8',null,null);

CREATE TABLE IF NOT EXISTS Vehicle_Type (
    id bigint NOT NULL DEFAULT nextval('vehicle_type_id_seq'::regclass),
    type_name varchar(64),
    specialization integer,
    PRIMARY KEY (id)
);

insert into Vehicle_Type(type_name, specialization) values('Автобус', 0);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-32050R', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-32053', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-320530', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-32050S', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-32054', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-4234', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-4254', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ЛАЗ-695Д', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ЛИАЗ-52564-0000010-01', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МАЗ-231062', (select id from Vehicle_Type where type_name='Автобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МЕРСЕДЕС-БЕНЦ О350', (select id from Vehicle_Type where type_name='Автобус'));
insert into Vehicle_Type(type_name, specialization) values('Микроавтобус', 0);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-2206', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-220602', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-220694', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-220695', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-32213', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-322132', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI STAREX', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('IVECO 2227UR', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МЕРСЕДЕС-БЕНЦ 223237', (select id from Vehicle_Type where type_name='Микроавтобус'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПАЗ-320401-01', (select id from Vehicle_Type where type_name='Микроавтобус'));
insert into Vehicle_Type(type_name, specialization) values('Внедорожник', 1);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PATROL 5.6', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LC 100', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LC 200', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LC PRADO', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA HILUX', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TREIL', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TREIL 2.0', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TREIL 2.5', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PATHFINDER', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('SSANGYONG REXTON', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI SANTA FE', (select id from Vehicle_Type where type_name='Внедорожник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('КИА СПОРТЭЙДЖ', (select id from Vehicle_Type where type_name='Внедорожник'));
insert into Vehicle_Type(type_name, specialization) values('Городской', 1);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-2217', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN TEANA', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN ALMERA', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN MAXIMA 3.0 SE', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI ACCENT', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI SONATA', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('FORD TRANSIT CONNECT', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-3110', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-3102', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-31105', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-31512', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-31514', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-315142', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-31519', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УАЗ-315195', (select id from Vehicle_Type where type_name='Городской'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ВАЗ-21213', (select id from Vehicle_Type where type_name='Городской'));
insert into Vehicle_Type(type_name, specialization) values('Грузовая газель', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-330202', (select id from Vehicle_Type where type_name='Грузовая газель'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-3302', (select id from Vehicle_Type where type_name='Грузовая газель'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-330232', (select id from Vehicle_Type where type_name='Грузовая газель'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-33021', (select id from Vehicle_Type where type_name='Грузовая газель'));
insert into Vehicle_Type(type_name, specialization) values('Грузовая газель (для перевозки опасных грузов)', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-33021', (select id from Vehicle_Type where type_name='Грузовая газель (для перевозки опасных грузов)'));
insert into Vehicle_Type(type_name, specialization) values('Грузовой', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-53', (select id from Vehicle_Type where type_name='Грузовой'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-33090', (select id from Vehicle_Type where type_name='Грузовой'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ЗИЛ-5301 АО', (select id from Vehicle_Type where type_name='Грузовой'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ЗИЛ-534330', (select id from Vehicle_Type where type_name='Грузовой'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('Амур-48442В', (select id from Vehicle_Type where type_name='Грузовой'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УРАЛ-4320', (select id from Vehicle_Type where type_name='Грузовой'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('УРАЛ-375', (select id from Vehicle_Type where type_name='Грузовой'));
insert into Vehicle_Type(type_name, specialization) values('Самосвал', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ЗИЛ-4502', (select id from Vehicle_Type where type_name='Самосвал'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ЗИЛ-450650', (select id from Vehicle_Type where type_name='Самосвал'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МАЗ-3518', (select id from Vehicle_Type where type_name='Самосвал'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МАЗ-5551', (select id from Vehicle_Type where type_name='Самосвал'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('КАМАЗ-6520', (select id from Vehicle_Type where type_name='Самосвал'));
insert into Vehicle_Type(type_name, specialization) values('Длинномер', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МАЗ-630300 2121 (с тентом)', (select id from Vehicle_Type where type_name='Длинномер'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ЗИЛ-441510', (select id from Vehicle_Type where type_name='Длинномер'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('DAEWOOSUPER', (select id from Vehicle_Type where type_name='Длинномер'));
insert into Vehicle_Type(type_name, specialization) values('Ассенизаторская машина', 3);
        insert into Vehicle_Model(model_name, vehicle_type_id) values('КО-503В-2 (вакуумная)', (select id from Vehicle_Type where type_name='Ассенизаторская машина'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('КО-503В', (select id from Vehicle_Type where type_name='Ассенизаторская машина'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('ГАЗ-3307 (КО-503В-3)', (select id from Vehicle_Type where type_name='Ассенизаторская машина'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('КАМАЗ-4672А6', (select id from Vehicle_Type where type_name='Ассенизаторская машина'));
insert into Vehicle_Type(type_name, specialization) values('Мусоровоз', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('КО 440-4К', (select id from Vehicle_Type where type_name='Мусоровоз'));
insert into Vehicle_Type(type_name, specialization) values('Автозаправщик', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('АТ3-7-433362', (select id from Vehicle_Type where type_name='Автозаправщик'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('MERCEDES BENZ ACT.', (select id from Vehicle_Type where type_name='Автозаправщик'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МАЗ-631705-370', (select id from Vehicle_Type where type_name='Автозаправщик'));
insert into Vehicle_Type(type_name, specialization) values('Автокран', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МАЗ-5337 КС-3577', (select id from Vehicle_Type where type_name='Автокран'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('КС-35715', (select id from Vehicle_Type where type_name='Автокран'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('КС-5579.2', (select id from Vehicle_Type where type_name='Автокран'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('CNT 650', (select id from Vehicle_Type where type_name='Автокран'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('LIBHERR LTM 1130-5.1', (select id from Vehicle_Type where type_name='Автокран'));
insert into Vehicle_Type(type_name, specialization) values('Гидроподъемник', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('АГП-22.04 (на базе ЗИЛа)', (select id from Vehicle_Type where type_name='Гидроподъемник'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('ПМС-328-02 (на базе КАМАЗа)', (select id from Vehicle_Type where type_name='Гидроподъемник'));
insert into Vehicle_Type(type_name, specialization) values('Водовоз', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МАЗ-635', (select id from Vehicle_Type where type_name='Водовоз'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('МДК-433362', (select id from Vehicle_Type where type_name='Водовоз'));
insert into Vehicle_Type(type_name, specialization) values('Экскаватор', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('JOHN DEERE-325 J', (select id from Vehicle_Type where type_name='Экскаватор'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'user',
    'Соколов Вячеслав Владимирович',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='ЦИП ИК'),
    (select id from transport_dep where shortname='ОТС 1'));
insert into app_role(role_name) values('ROLE_USER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin',
    'Тимошенко Александр Александрович',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='ЦИП ИК'),
    (select id from transport_dep where shortname='ОТС 1'));
insert into app_role(role_name) values('ROLE_ADMIN');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin1',
    'Дегтярёв',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='ЦИП ИК'),
    (select id from transport_dep where shortname='ОТС 2'));
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin2',
    'Нестеров',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='ЦИП ИК'),
    (select id from transport_dep where shortname='ОТС 2'));
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin3',
    'Чигорин',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='КЭССТ'),
    (select id from transport_dep where shortname='ОТС 3'));
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'manager',
    'Соколов',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='ЦИП ИК'),
    (select id from transport_dep where shortname='ОТС 1'));
insert into app_role(role_name) values('ROLE_MANAGER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'planner',
    'Вячеслав',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='ЦИП ИК'),
    (select id from transport_dep where shortname='ОТС 1'));
insert into app_role(role_name) values('ROLE_PLANNER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'dispatcher',
    'Владимирович',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='ЦИП ИК'),
    (select id from transport_dep where shortname='ОТС 1'));
insert into app_role(role_name) values('ROLE_DISPATCHER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into car_boss(firstname, name, surname, birthday, address, phone, post, department_id) values(
    'Яковлев', 'Василий', 'Семёнович',
    null,
    null,
    null,
    'Начальник центра',
    (select id from department where shortname='ЦИП ИК'));

insert into car_boss(firstname, name, surname, birthday, address, phone, post, department_id) values(
    'Бекеев', 'Александр', 'Константинович',
    null,
    null,
    null,
    'Главный инженер',
    (select id from department where shortname='ЦИП ИК'));

INSERT INTO public.claim(actual, affirmation_date, creation_date, purpose, specialization, template_name, affirmator_id, car_boss_id, creator_id, department_id, vehicle_type_id)
	VALUES (true, null, '2019-01-30 15:45:08.15', 'Перевозка пассажиров', 0, null, null, 1, 2, 17, 1);
INSERT INTO public.record(end_date, entrance_date, start_date, claim_id)
	VALUES ('2019-01-28 18:00:00', '2019-01-28 07:00:00', '2019-01-28 07:45:00', 1);
INSERT INTO public.record(end_date, entrance_date, start_date, claim_id)
	VALUES ('2019-01-29 18:00:00', '2019-01-29 07:00:00', '2019-01-29 07:45:00', 1);
INSERT INTO public.record(end_date, entrance_date, start_date, claim_id)
	VALUES ('2019-01-30 18:00:00', '2019-01-30 07:00:00', '2019-01-30 07:45:00', 1);
INSERT INTO public.record(end_date, entrance_date, start_date, claim_id)
	VALUES ('2019-01-31 18:00:00', '2019-01-31 07:00:00', '2019-01-31 07:45:00', 1);
INSERT INTO public.record(end_date, entrance_date, start_date, claim_id)
	VALUES ('2019-02-01 18:00:00', '2019-02-01 07:00:00', '2019-02-01 07:45:00', 1);
INSERT INTO public.route_task(order_num, work_name, place_id, claim_id)
	VALUES (0, 'Начало движения к пл. 18', 24, 1);
INSERT INTO public.route_task(order_num, work_name, place_id, claim_id)
	VALUES (1, 'Перевозка пассажиров - 50 чел.', 29, 1);
INSERT INTO public.route_task(order_num, work_name, place_id, claim_id)
	VALUES (2, 'Перевозка пассажиров - 50 чел.', 24, 1);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-30', null, '-x123456789x-', 'Иванов', 'Иван', null, '+77776665544', 0, 'Иванович', 1);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-29', null, '-x123456789x-', 'Петров', 'Иван', null, '+77776665543', 0, 'Иванович', 1);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-28', null, '-x123456789x-', 'Сидоров', 'Иван', null, '+77776665542', 0, 'Иванович', 2);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-27', null, '-x123456789x-', 'Козлов', 'Иван', null, '+77776665541', 0, 'Иванович', 2); 

INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 178 КК 94', 222, 0, (select id from Vehicle_Model where model_name='FORD FOCUS' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 431 КК 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI Accent' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 313 КК 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI SANTA FE 2.4' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 323 КК 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI SONATA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 513 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 179 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 213 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 216 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 113 АА 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN MAXIMA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 208 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN MAXIMA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 008 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATHFINDER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 020 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATHFINDER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 100 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATHFINDER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 111 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATROL 5.6' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 207 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PRIMERA 2,0 ' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 001 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 109 АА 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA 3.5 ' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 110 АА 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA 3.5 ' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 112 АА 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA 3.5 ' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 432 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 202 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2,5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 249 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2,5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 250 КК 94 ', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2,5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 003 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.0 ' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 090 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.5 ' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 005 КК 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.5 ' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 677 АА 99', 222, 0, (select id from Vehicle_Model where model_name='SSANGYONG REXTON' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 500 АН 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA ALPHARD' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 414 КК 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 232 КК 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 100' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 012 КК 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 120' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 071 АН 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 150 (PRADO)' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 072 АН 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 150 (PRADO)' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 777 КК 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 200' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 276 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 785 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 298 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 394 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 576 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 095 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'У 233 ХА 50', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 315 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 357 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 365 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 368 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 465 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 494 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 603 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'У 754 АА 99', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 524 ВМ 197', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 030 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 212 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 282 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 292 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 310 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 311 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 312 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 393 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 274 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 365 АН 94', 222, 0, (select id from Vehicle_Model where model_name='КИА Спортейдж' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 240 АА 99', 222, 0, (select id from Vehicle_Model where model_name='КИА Спортейдж' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Р 579 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31512' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 114 АН 94', 222, 0, (select id from Vehicle_Model where model_name='FORD TRANSIT CONNECT' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 678 АА 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA HILUX' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 594 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ВАЗ-21213' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 625 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2704D6' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 160 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 221 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 134 АН 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'У 259 АА 99', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 305 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 532 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 549 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2834NF' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 627 АА 99', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-330202' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 399 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-33021' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 462 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-33023' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 521 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 119 УН 97', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 248 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 518 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 306 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-22069' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'В 451 АС 50', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31512' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 332 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31512' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 906 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 359 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 390 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 397 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 407 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 297 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 356 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315142' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 362 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315142' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 862 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31519' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 296 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31519' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 292 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31519' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 310 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315192' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 314 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315192' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 224 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315194' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 225 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315194' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 142 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 143 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 144 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 145 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 298 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-37419-210' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 117 УН 97', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-396252' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 628 АА 99', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-39629' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 031 АА 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI STAREX' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 294 КК 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI STAREX' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 024 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2217' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 398 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2217' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 453 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2217' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 452 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-22171' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 456 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-22171' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 490 АА 99', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3221' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 023 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 096 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 027 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 941 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 942 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 279 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 355 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 361 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 367 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 396 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 401 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 512 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 550 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 871 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 872 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 116 УН 97', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 120 УН 97', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 304 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 311 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 312 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 507 КХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 508 КХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 972 СУ 50', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 338 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 340 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 341 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 384 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 385 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 581 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 582 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 395 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32214 ' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 426 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 650 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 688 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 754 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 388 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-2206' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 422 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-2206' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 773 АА 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-2206' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 322 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 324 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 371 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 397 АН 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 395 АН 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-22069' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 295 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-22069' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 155 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 156 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 158 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 159 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 220 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 284 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220695' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 285 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-220695' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 307 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-37419-210' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 504 МТ 197', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-3909' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 391 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-39099' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 648 КК 94', 222, 0, (select id from Vehicle_Model where model_name='IVECO-2227UR' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 649 КК 94', 222, 0, (select id from Vehicle_Model where model_name='IVECO-2227UR' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 650 КК 94', 222, 0, (select id from Vehicle_Model where model_name='IVECO-2227UR' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 419 ЕА 777', 222, 0, (select id from Vehicle_Model where model_name='SETRA S415UL' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 420 ЕА 777', 222, 0, (select id from Vehicle_Model where model_name='SETRA S415UL' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 686 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 835 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 862 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 872 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 883 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 519 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЛИАЗ-525633-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 080 АА 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 994 АА 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Р 411 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 296 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 738 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 748 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 749 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 756 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 845 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ O350' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 792 ХК 777', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ-223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 257 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-320401-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 258 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-320401-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 420 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-320401-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 275 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 317 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 318 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 360 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 387 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 571 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 583 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 599 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 394 АН 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 937 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 938 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 940 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 157 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 163 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 328 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 514 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 515 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 516 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 517 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 135 АН 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 415 УО 97', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 392 АН 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-320530' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 253 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 865 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 089 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 091 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 092 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 093 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 094 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 286 НХ 50', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 566 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 567 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 569 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 215 АН 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'ВЕ 6339 77', 222, 0, (select id from Vehicle_Model where model_name='DITCH WITCH RT 115' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 653 КК 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI-27990А' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'ВМ 4327 77', 222, 0, (select id from Vehicle_Model where model_name='JOHN DEERE 318D' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'ВМ 4326 77', 222, 0, (select id from Vehicle_Model where model_name='JOHN DEERE 325J' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 479 КК 94', 222, 0, (select id from Vehicle_Model where model_name='VOLKSWAGEN 2EKZ CRAFTER' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 880 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ (32597J)' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 430 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ 32214' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'А 398 УС 77', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 429 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32214' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'О 937 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32214-0000010-01' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 025 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32611А' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Р 451 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32611С' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 349 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-33021' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 237 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-53' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 795 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-6601' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Х0000031', 222, 0, (select id from Vehicle_Model where model_name='ДВ-1792' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 585 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-441510' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 593 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-534330' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 604 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-ММЗ 4502' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Р 229 РВ 71', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ 43118-46' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 321 НН 750', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ-43118' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 420 КА 777', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ-43118
мобильный узел связи' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 307 НТ 777', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ-43502-45' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 501 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ-6520' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 502 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ-6520' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АМ 0130 94', 222, 0, (select id from Vehicle_Model where model_name='КЗАП 89944' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 423 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КО-440-4К (мусоровоз)' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 424 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КО-440-4К (мусоровоз)' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 351 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КО-503В-2' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 953 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='КРАЗ-260В' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 354 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-5551-020' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 029 АА 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-630300-2121' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АМ 0148 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-83781-012' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 098 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ АСТРОС 2535' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 099 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ АСТРОС 2535' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 102 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МЕРСЕДЕС-БЕНЦ АСТРОС 2535' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 425 КК 94', 222, 0, (select id from Vehicle_Model where model_name='НЕФАЗ-4208-03' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АМ 0133 94', 222, 0, (select id from Vehicle_Model where model_name='ОДАЗ-9357' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Х0000118', 222, 0, (select id from Vehicle_Model where model_name='ПКСД-5,25' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АР 7297 77', 222, 0, (select id from Vehicle_Model where model_name='ПС-8934' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АР 7298 77', 222, 0, (select id from Vehicle_Model where model_name='ПС-8934' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АЕ 2855 50', 222, 0, (select id from Vehicle_Model where model_name='СЗАП-8357' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 230 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УРАЛ 4320-0011-02' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'ВЕ 8267 77', 222, 0, (select id from Vehicle_Model where model_name='ХТА-220-1' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'ВЕ 8268 77', 222, 0, (select id from Vehicle_Model where model_name='ЭТЦ-201' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'ВЕ 6740 77', 222, 0, (select id from Vehicle_Model where model_name='BALKANKAR BILO ДВ 1788' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 884 КК 94', 222, 0, (select id from Vehicle_Model where model_name='DAEWOO SUPER NOVUS' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Т 237 НХ 750', 222, 0, (select id from Vehicle_Model where model_name='LIEBHERR LTM 1130-5.1' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 415 КК 94', 222, 0, (select id from Vehicle_Model where model_name='АГП-2204' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АТ 2974 77', 222, 0, (select id from Vehicle_Model where model_name='Амкодор-702В' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 416 КК 94', 222, 0, (select id from Vehicle_Model where model_name='АМУР 48442В' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 417 КК 94', 222, 0, (select id from Vehicle_Model where model_name='АТЗ-7 на ш. ЗИЛ-433362' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Р 810 АВ 99', 222, 0, (select id from Vehicle_Model where model_name='АТЗ-7-433362' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 297 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 337 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-33021' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 372 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-33021' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 336 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-330230' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 006 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-330900-0212' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв. № 93400504', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-53-12-016' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв. № 93400511', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-66' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв. № 93400514', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-66-14' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв. № 93400503', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-66-14' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№КС000614', 222, 0, (select id from Vehicle_Model where model_name='ЕК-14' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв. № 93400519', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-431410' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 366 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-433362' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 266 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-433362 (РЖМ-52)' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 155 ЕЕ 99 ', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-433362 АТЗ' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 492 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-450650' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 347 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-5301АО' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв. № 93400516', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ-43101' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 958 АА 94', 222, 0, (select id from Vehicle_Model where model_name='КАМАЗ-4672А6' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 573 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КО-503В' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 435 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КО-503В-3' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Б0001181', 222, 0, (select id from Vehicle_Model where model_name='КО-707 на тракторе ЛТЗ 60АВ' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Б0001182', 222, 0, (select id from Vehicle_Model where model_name='КО-707 на тракторе ЛТЗ 60АВ' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'НН 1607 52', 222, 0, (select id from Vehicle_Model where model_name='Кран CNT 650 ' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 373 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КС-35715 НА Ш. МАЗ-5337' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 374 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КС-35715 НА Ш. МАЗ-5337' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 408 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КС-5579-2 НА Ш. КАМАЗ-53229С' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 327 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЛАЗ-695Д' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 664 АА 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ 631705-370' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 403 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-3518 НА Ш.АМУР-531310' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 483 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-5337-6912 (КС-3577-3)' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 348 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-5551-020' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 295 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-555102-223' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 396 АН 94', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-630305-250' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 418 КК 94', 222, 0, (select id from Vehicle_Model where model_name='МДК-433362' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Б0000685', 222, 0, (select id from Vehicle_Model where model_name='МУП 350.ТМ' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Б0000074', 222, 0, (select id from Vehicle_Model where model_name='П/Г-40816' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 326 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ 32050R' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 319 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ 320530' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 251 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ 32054' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 597 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 162 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 320 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 176 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053S' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 213 АН 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4234' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 296 НН 750', 222, 0, (select id from Vehicle_Model where model_name='ПМС-328-02' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв.№43-17', 222, 0, (select id from Vehicle_Model where model_name='прицеп одноосный' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'АМ 0128 94', 222, 0, (select id from Vehicle_Model where model_name='ПЦ-7-817М1' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№В0001999', 222, 0, (select id from Vehicle_Model where model_name='ТО-49 Б1' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Б0001331', 222, 0, (select id from Vehicle_Model where model_name='ТО-49 на МТЗ-82' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 358 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 437 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31514' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 325 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-390902' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 579 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-390992' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 441 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-3962' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Инв. № 93400513', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-469' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 402 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УРАЛ-4320-0911-40' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Б0001329', 222, 0, (select id from Vehicle_Model where model_name='ЭО-2621В3' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'инв.№Б0001330', 222, 0, (select id from Vehicle_Model where model_name='ЭО-3323А' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 455 КК 94', 222, 0, (select id from Vehicle_Model where model_name='HIGER KLQ 6886Q' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 541 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 762 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 789 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 793 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 801 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'С 816 КТ 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 442 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 290 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-2705' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 553 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-27527' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Х 291 ХХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3102' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 181 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 289 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 291 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 329 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 330 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 191 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-31105' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 460 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3221' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 301 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 459 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Н 413 УО 97', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32213' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 026 АА 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 509 КХ 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 482 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-322132' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 601 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-32214' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 596 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ГАЗ-3240 (2705)' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 577 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-131' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 486 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЗИЛ-133Г42' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 451 КК 94', 222, 0, (select id from Vehicle_Model where model_name='КО-503В' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 491 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ЛИАЗ-52564-0000010-01' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'Е 307 НН 750', 222, 0, (select id from Vehicle_Model where model_name='МАЗ-231062' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 344 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-3205' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 299 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 305 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 306 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 316 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 490 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 315 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 436 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 447 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 588 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 589 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 457 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-320530' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 590 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-320530' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 591 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-320530' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 439 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4234' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'М 214 АН 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4234' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 440 КК 94', 222, 0, (select id from Vehicle_Model where model_name='ПАЗ-4254' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 350 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-2206' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 485 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-2206' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 488 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-2206' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 307 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-22069' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 489 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-315142' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, 'К 449 КК 94', 222, 0, (select id from Vehicle_Model where model_name='УАЗ-31519' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
