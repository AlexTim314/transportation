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
    insert into Route_Template(name, department_id) values('ИП-1 пл. 18 (Ежедневно)', (select id from Department where shortname='ЦИП ИК'));
        insert into Route_Task(work_name, order_num, place_id) values('Начало движения к пл. 18', 0, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-1 пл. 18 (Ежедневно)'), (select id from route_task where work_name='Начало движения к пл. 18'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 18 (50 человек)', 1, (select id from Place where name='Пл. 18'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-1 пл. 18 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 18 (50 человек)'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 10 с пл. 18 (50 человек)', 2, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-1 пл. 18 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 10 с пл. 18 (50 человек)'));
    insert into Route_Template(name, department_id) values('ИП-2 пл. 44 (Ежедневно)', (select id from Department where shortname='ЦИП ИК'));
        insert into Route_Task(work_name, order_num, place_id) values('Начало движения к пл. 44', 0, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-2 пл. 44 (Ежедневно)'), (select id from route_task where work_name='Начало движения к пл. 44'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 44 (50 человек)', 1, (select id from Place where name='Пл. 44'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-2 пл. 44 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 44 (50 человек)'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 10 с пл. 44 (50 человек)', 2, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-2 пл. 44 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 10 с пл. 44 (50 человек)'));
    insert into Route_Template(name, department_id) values('ИП-3 пл. 97 (Ежедневно)', (select id from Department where shortname='ЦИП ИК'));
        insert into Route_Task(work_name, order_num, place_id) values('Начало движения к пл. 97', 0, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-3 пл. 97 (Ежедневно)'), (select id from route_task where work_name='Начало движения к пл. 97'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 97 (50 человек)', 1, (select id from Place where name='Пл. 97'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-3 пл. 97 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 97 (50 человек)'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 10 с пл. 97 (50 человек)', 2, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-3 пл. 97 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 10 с пл. 97 (50 человек)'));
    insert into Route_Template(name, department_id) values('ИП-5 пл. 23 (Ежедневно)', (select id from Department where shortname='ЦИП ИК'));
        insert into Route_Task(work_name, order_num, place_id) values('Начало движения к пл. 23', 0, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-5 пл. 23 (Ежедневно)'), (select id from route_task where work_name='Начало движения к пл. 23'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 23 (50 человек)', 1, (select id from Place where name='Пл. 23'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-5 пл. 23 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 23 (50 человек)'));
        insert into Route_Task(work_name, order_num, place_id) values('Доставка на пл. 10 с пл. 23 (50 человек)', 2, (select id from Place where name='Пл. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='ИП-5 пл. 23 (Ежедневно)'), (select id from route_task where work_name='Доставка на пл. 10 с пл. 23 (50 человек)'));
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
insert into Vehicle_Type(type_name, specialization) values('Представительский', 1);
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
