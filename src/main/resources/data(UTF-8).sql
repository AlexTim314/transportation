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

CREATE TABLE IF NOT EXISTS Place (
    id bigint NOT NULL DEFAULT nextval('place_id_seq'::regclass),
    name varchar(255),
    address varchar(255),
    PRIMARY KEY (id)
);

insert into Place(name, address) values('Пл. 10', 'ул. Титова Г.С., д. 7');
insert into Place(name, address) values('Пл. 18', 'ИП-1');
insert into Place(name, address) values('Пл. 44', 'ИП-2');
insert into Place(name, address) values('Пл. 97', 'ИП-3');
insert into Place(name, address) values('Пл. 23', 'ИП-5');
insert into Place(name, address) values('Пл. 1', 'СК17П32');
insert into Place(name, address) values('Пл. 31', null);
insert into Place(name, address) values('Пл. 81', null);
insert into Place(name, address) values('Пл. 200', null);
insert into Place(name, address) values('Пл. 43', null);
insert into Place(name, address) values('Пл. 45', null);
insert into Place(name, address) values('Пл. 71', null);
insert into Place(name, address) values('Пл. 72', null);

CREATE TABLE IF NOT EXISTS Vehicle_Type (
    id bigint NOT NULL DEFAULT nextval('vehicle_type_id_seq'::regclass),
    type_name varchar(64),
    specialization integer,
    PRIMARY KEY (id)
);

insert into Vehicle_Type(type_name, specialization) values('Автобус', 0);
insert into Vehicle_Type(type_name, specialization) values('Микроавтобус', 0);
insert into Vehicle_Type(type_name, specialization) values('Внедорожник', 1);
insert into Vehicle_Type(type_name, specialization) values('Представительский', 1);
insert into Vehicle_Type(type_name, specialization) values('Городской', 1);
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
