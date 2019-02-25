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

insert into Fuel(mark, type) values('��-80', 0);
insert into Fuel(mark, type) values('��-92', 0);
insert into Fuel(mark, type) values('��-95', 0);
insert into Fuel(mark, type) values('���', 1);
insert into Fuel(mark, type) values('���', 1);


insert into Place(name, address) values('�������� "�������"', null);
insert into Place(name, address) values('������� ���', null);
insert into Place(name, address) values('���', null);

insert into Place(name, address) values('�/� ��. �������', null);
insert into Place(name, address) values('�/� ��. ���������', null);
insert into Place(name, address) values('�/� ��. ��������', null);
insert into Place(name, address) values('�/� ��. ��������', null);
insert into Place(name, address) values('�/� ��. ������', null);

insert into Place(name, address) values('���-1', null);
insert into Place(name, address) values('���-3', null);
insert into Place(name, address) values('���-5', null);
insert into Place(name, address) values('���-7', null);

insert into Place(name, address) values('��. 1', '��17�32');
insert into Place(name, address) values('��. 2', null);
insert into Place(name, address) values('��. 2�', null);
insert into Place(name, address) values('��. 2�', null);
insert into Place(name, address) values('��. 2 (���.)', null);
insert into Place(name, address) values('��. 3�', null);
insert into Place(name, address) values('��. 3�', null);
insert into Place(name, address) values('��. 5', null);
insert into Place(name, address) values('��. 6', null);
insert into Place(name, address) values('��. 9', null);
insert into Place(name, address) values('��. 9 (���)', null);
insert into Place(name, address) values('��. 10', '��. ������ �.�., �. 7');
insert into Place(name, address) values('��. 15', null);
insert into Place(name, address) values('��. 15�', null);
insert into Place(name, address) values('��. 15�', null);
insert into Place(name, address) values('��. 17 ���', null);
insert into Place(name, address) values('��. 18', '��-1');
insert into Place(name, address) values('��. 23', '��-5');
insert into Place(name, address) values('��. 31', null);
insert into Place(name, address) values('��. 32', null);
insert into Place(name, address) values('��. 43', null);
insert into Place(name, address) values('��. 44', '��-2');
insert into Place(name, address) values('��. 45', null);
insert into Place(name, address) values('��. 71', null);
insert into Place(name, address) values('��. 72', null);
insert into Place(name, address) values('��. 75', null);
insert into Place(name, address) values('��. 81', null);
insert into Place(name, address) values('��. 90', null);
insert into Place(name, address) values('��. 91', null);
insert into Place(name, address) values('��. 91�', null);
insert into Place(name, address) values('��. 92', null);
insert into Place(name, address) values('��. 92� 50', null);
insert into Place(name, address) values('��. 95', null);
insert into Place(name, address) values('��. 97', '��-3');
insert into Place(name, address) values('��. 109', null);
insert into Place(name, address) values('��. 110', null);
insert into Place(name, address) values('��. 110�', null);
insert into Place(name, address) values('��. 111', null);
insert into Place(name, address) values('��. 111 (������)', null);
insert into Place(name, address) values('��. 111 (���������)', null);
insert into Place(name, address) values('��. 112', null);
insert into Place(name, address) values('��. 113', null);
insert into Place(name, address) values('��. 113�', null);
insert into Place(name, address) values('��. 114�', null);
insert into Place(name, address) values('��. 114�', null);
insert into Place(name, address) values('��. 175', null);
insert into Place(name, address) values('��. 200', null);
insert into Place(name, address) values('��. 250', null);
insert into Place(name, address) values('��. 250�', null);
insert into Place(name, address) values('��. 251', null);
insert into Place(name, address) values('��. 254', null);
insert into Place(name, address) values('��. 257�', null);
insert into Place(name, address) values('��. 504', null);
insert into Place(name, address) values('��. 504-3�', null);
insert into Place(name, address) values('�� ��������', null);


CREATE TABLE IF NOT EXISTS Department (
    id bigint NOT NULL DEFAULT nextval('department_id_seq'::regclass),
    shortname varchar(64) not null,
    fullname varchar(255),
    address varchar(255),
    phone varchar(16),
    PRIMARY KEY (id)
);

insert into Department (shortname, fullname, address, phone) values('������ ������������ ���', '������ ������������ �������-����������� �������', null, null);
insert into Department (shortname, fullname, address, phone) values('���', '������ ������������ �����������', null, null);
insert into Department (shortname, fullname, address, phone) values('����������', '����������', null, null);
insert into Department (shortname, fullname, address, phone) values('����', '���������� ������������� �������� ������', null, null);
insert into Department (shortname, fullname, address, phone) values('���������� ������', '���������� ������', null, null);
insert into Department (shortname, fullname, address, phone) values('�����', '���������� ����������� ������ ����������� ���������', null, null);
insert into Department (shortname, fullname, address, phone) values('����', '���������� ����������� ���������� ������������', null, null);
insert into Department (shortname, fullname, address, phone) values('��� ���', '���������� ����������� �������� �������-����������� �������', null, null);
insert into Department (shortname, fullname, address, phone) values('���', '���������� �� ������ � ����������', null, null);
insert into Department (shortname, fullname, address, phone) values('���', '���������� ��������� �����������', null, null);
insert into Department (shortname, fullname, address, phone) values('���', '���������-������������� ������', null, null);
insert into Department (shortname, fullname, address, phone) values('�� ��', '����� ��������� ���������� ��������', null, null);
insert into Department (shortname, fullname, address, phone) values('�� ��', '����� ��������� ����������� ����������', null, null);
insert into Department (shortname, fullname, address, phone) values('��-1', '����� ���������-1', null, null);
insert into Department (shortname, fullname, address, phone) values('��-2', '����� ���������-2', null, null);
insert into Department (shortname, fullname, address, phone) values('��-3', '����� ���������-3', null, null);
insert into Department (shortname, fullname, address, phone) values('��� ��', '����� ��������� � ���������� �������������� ���������', null, null);
    insert into Route_Template(name, department_id) values('��-1 ��. 18 (���������)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('������ �������� � ��. 18', 0, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 1, (select id from Place where name='��. 18'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 2, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('��-2 ��. 44 (���������)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('������ �������� � ��. 44', 0, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 1, (select id from Place where name='��. 44'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 2, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('��-3 ��. 97 (���������)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('������ �������� � ��. 97', 0, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 1, (select id from Place where name='��. 97'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 2, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('��-5 ��. 23 (���������)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('������ �������� � ��. 23', 0, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 1, (select id from Place where name='��. 23'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ���������� - 50 ���.', 2, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
    insert into Route_Template(name, department_id) values('�� �������� (���������)', currval('department_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('������ ��������', 0, (select id from Place where name='��. 10'), currval('route_template_id_seq'));
        insert into Route_Task(work_name, order_num, place_id, template_id) values('��������� ����������', 1, (select id from Place where name='�� ��������'), currval('route_template_id_seq'));
insert into Department (shortname, fullname, address, phone) values('���� ���', '����� ����������� � �������� ��������� �������-����������� �������', null, null);
insert into Department (shortname, fullname, address, phone) values('��', '����������������', null, null);
insert into Department (address, fullname, phone, shortname) values (null,'��������������� "�������� "�������',null,'�������� "�������"');
insert into Department (address, fullname, phone, shortname) values (null,'����������� ��������',null,'����������� ��������');
insert into Department (address, fullname, phone, shortname) values (null,'�������� "�������� ���������"',null,'���');
insert into Department (address, fullname, phone, shortname) values (null,'�������� ����������������� �����������',null,'����');
insert into Department (address, fullname, phone, shortname) values (null,'�������� �������������� �����������',null,'����');
insert into Department (address, fullname, phone, shortname) values (null,'�������� ���������������� �����������',null,'����');
insert into Department (address, fullname, phone, shortname) values (null,'�������� ������������ � ������������� �����������',null,'����');
insert into Department (address, fullname, phone, shortname) values (null,'�������� ������������ ������ � ����������',null,'����');
insert into Department (address, fullname, phone, shortname) values (null,'�������� ������������ ����� ����� � ����������������',null,'�����');
insert into Department (address, fullname, phone, shortname) values (null,'����� ������������ �������������',null,'���');
insert into Department (address, fullname, phone, shortname) values (null,'����������� ����������',null,'����������� ����������');
insert into Department (address, fullname, phone, shortname) values (null,'����� ���������������� ��������',null,'����� ���������������� ��������');
insert into Department (address, fullname, phone, shortname) values (null,'����� ������������� ������������ � ��������������� ���������',null,'����� �� � ��');
insert into Department (address, fullname, phone, shortname) values (null,'������ �� ����������� ����� � ���',null,'������ �� ����������� ����� � ���');
insert into Department (address, fullname, phone, shortname) values (null,'������ ������������',null,'������ ������������');
insert into Department (address, fullname, phone, shortname) values (null,'������ ������ ����� � ����������������� ��������',null,'������ ����');

CREATE TABLE IF NOT EXISTS Transport_dep (
    id bigint NOT NULL DEFAULT nextval('transport_dep_id_seq'::regclass),
    address varchar(255),
    fullname varchar(255),
    phone varchar(16),
    shortname varchar(64) not null,
    PRIMARY KEY (id)
);

insert into Transport_dep (shortname, fullname, address, phone) values ('��� 1','����� ������������ ������� �1',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('��� 2','����� ������������ ������� �2',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('��� 3','����� ������������ ������� �3',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('��� 4','����� ������������ ������� �4',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('��� 5','����� ������������ ������� �5',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('��� 6','����� ������������ ������� �6',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('��� 7','����� ������������ ������� �7',null,null);
insert into Transport_dep (shortname, fullname, address, phone) values ('��� 8','����� ������������ ������� �8',null,null);

CREATE TABLE IF NOT EXISTS Vehicle_Type (
    id bigint NOT NULL DEFAULT nextval('vehicle_type_id_seq'::regclass),
    type_name varchar(64),
    specialization integer,
    PRIMARY KEY (id)
);

insert into Vehicle_Type(type_name, specialization) values('�������', 0);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3205', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32050R', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32053', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-320530', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32050S', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32053S', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32054', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-4234', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-4254', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-695�', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-52564-0000010-01', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-525633-01', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-231062', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��������-���� O350', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('SETRA S415UL', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('YUTONG ZK6122H9', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HIGER KLQ 6886Q', (select id from Vehicle_Type where type_name='�������'));
insert into Vehicle_Type(type_name, specialization) values('������������', 0);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2206', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-220602', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-22069', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-220694', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-220695', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-37419-210', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3909', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-390902', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-39099', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-390992', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-396252', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3962', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-39629', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-22171', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3221', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32213', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32214', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32214-0000010-01', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-322132', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3240', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI STAREX', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('IVECO-2227UR', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��������-���� 223237', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-320401-01', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('VOLKSWAGEN 2EKZ CRAFTER', (select id from Vehicle_Type where type_name='������������'));
insert into Vehicle_Type(type_name, specialization) values('�����������', 1);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PATROL 5.6', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA ALPHARD', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER 100', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER 120', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER 150 (PRADO)', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER 200', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER PRADO', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA HILUX', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TRAIL', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TRAIL 2.0', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TRAIL 2.5', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PATHFINDER', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PATROL 5.6', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('SSANGYONG REXTON', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI SANTA FE', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI SANTA FE 2.4', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��� ���������', (select id from Vehicle_Type where type_name='�����������'));
insert into Vehicle_Type(type_name, specialization) values('���������', 1);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2217', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('FORD FOCUS', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN TEANA', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN TEANA 3.5', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN ALMERA', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN ALMERA CLASSIC', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN MAXIMA', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN MAXIMA 3.0 SE', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PRIMERA 2.0', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI ACCENT', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI SONATA', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('FORD TRANSIT CONNECT', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3110', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3102', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-31105', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-31512', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-31514', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-315142', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-31519', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-315192', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-315194', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-315195', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-21213', (select id from Vehicle_Type where type_name='���������'));
insert into Vehicle_Type(type_name, specialization) values('�������� ������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2705', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-27527', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2704e6', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2834NF', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-330202', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3302', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-33023', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-330230', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-330232', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-33021', (select id from Vehicle_Type where type_name='�������� ������'));
insert into Vehicle_Type(type_name, specialization) values('�������� ������ (��� ��������� ������� ������)', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-33021', (select id from Vehicle_Type where type_name='�������� ������ (��� ��������� ������� ������)'));
insert into Vehicle_Type(type_name, specialization) values('��������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-53', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-33090', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-330900-0212', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-6601', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-131', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-133�42', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-5301��', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-534330', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-48442�', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-4320', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���� 4320-0011-02', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-4320-0911-40', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-375', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI-27990�', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('�����-43118', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('�����-43118-46', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('�����-43502-45', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-630300-2121', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��������-���� ������ 2535', (select id from Vehicle_Type where type_name='��������'));
insert into Vehicle_Type(type_name, specialization) values('��������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-4502', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-��� 4502', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-450650', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3518', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-5551-020', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-555102-223', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('�����-6520', (select id from Vehicle_Type where type_name='��������'));
insert into Vehicle_Type(type_name, specialization) values('���������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-630300 2121 (� ������)', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-441510', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('DAEWOOSUPER', (select id from Vehicle_Type where type_name='���������'));
insert into Vehicle_Type(type_name, specialization) values('��������������� ������', 3);
        insert into Vehicle_Model(model_name, vehicle_type_id) values('��-503�-3', (select id from Vehicle_Type where type_name='��������������� ������'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('��-503�-2', (select id from Vehicle_Type where type_name='��������������� ������'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('��-503�', (select id from Vehicle_Type where type_name='��������������� ������'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('�����-4672�6', (select id from Vehicle_Type where type_name='��������������� ������'));
insert into Vehicle_Type(type_name, specialization) values('���������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��-440-4�', (select id from Vehicle_Type where type_name='���������'));
insert into Vehicle_Type(type_name, specialization) values('�������������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��3-7-433362', (select id from Vehicle_Type where type_name='�������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-433362 ���', (select id from Vehicle_Type where type_name='�������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('MERCEDES BENZ ACT.', (select id from Vehicle_Type where type_name='�������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-631705-370', (select id from Vehicle_Type where type_name='�������������'));
insert into Vehicle_Type(type_name, specialization) values('��������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-5337 ��-3577', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��-35715', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��-5579.2', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('CNT 650', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('LIEBHERR LTM 1130-5.1', (select id from Vehicle_Type where type_name='��������'));
insert into Vehicle_Type(type_name, specialization) values('��������������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2204', (select id from Vehicle_Type where type_name='��������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-328-02', (select id from Vehicle_Type where type_name='��������������'));
insert into Vehicle_Type(type_name, specialization) values('�������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-635', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-433362', (select id from Vehicle_Type where type_name='�������'));
insert into Vehicle_Type(type_name, specialization) values('����������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('JOHN DEERE 318D', (select id from Vehicle_Type where type_name='����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('JOHN DEERE 325J', (select id from Vehicle_Type where type_name='����������'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'user',
    '������� �������� ������������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='��� ��'),
    (select id from transport_dep where shortname='��� 1'));
insert into app_role(role_name) values('ROLE_USER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin',
    '��������� ��������� �������������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='��� ��'),
    (select id from transport_dep where shortname='��� 1'));
insert into app_role(role_name) values('ROLE_ADMIN');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin1',
    '�������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='��� ��'),
    (select id from transport_dep where shortname='��� 2'));
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin2',
    '��������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='��� ��'),
    (select id from transport_dep where shortname='��� 2'));
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'admin3',
    '�������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='�����'),
    (select id from transport_dep where shortname='��� 3'));
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'manager',
    '�������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='��� ��'),
    (select id from transport_dep where shortname='��� 1'));
insert into app_role(role_name) values('ROLE_MANAGER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'planner',
    '��������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='��� ��'),
    (select id from transport_dep where shortname='��� 1'));
insert into app_role(role_name) values('ROLE_PLANNER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'dispatcher',
    '������������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='��� ��'),
    (select id from transport_dep where shortname='��� 1'));
insert into app_role(role_name) values('ROLE_DISPATCHER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));

insert into app_user(username, full_name, encrypted_password, enabled, department_id, transport_dep_id) values(
    'supermanager',
    '���� ������ ��������',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,
    (select id from department where shortname='����������'),
    null);
insert into app_role(role_name) values('ROLE_SUPERMANAGER');
insert into user_role(user_id, role_id) values(currval('app_user_id_seq'), currval('app_role_id_seq'));
update department set super_manager_id = currval('app_user_id_seq') where shortname = '��� ��';
update department set super_manager_id = currval('app_user_id_seq') where shortname = '��-1';
update department set super_manager_id = currval('app_user_id_seq') where shortname = '��-2';
update department set super_manager_id = currval('app_user_id_seq') where shortname = '��-3';
update department set super_manager_id = currval('app_user_id_seq') where shortname = '�� ��';
update department set super_manager_id = currval('app_user_id_seq') where shortname = '�� ��';
update department set super_manager_id = currval('app_user_id_seq') where shortname = '���� ���';
update department set super_manager_id = currval('app_user_id_seq') where shortname = '�����';

insert into car_boss(firstname, name, surname, birthday, address, phone, post, department_id) values(
    '�������', '�������', '��������',
    null,
    null,
    null,
    '��������� ������',
    (select id from department where shortname='��� ��'));

insert into car_boss(firstname, name, surname, birthday, address, phone, post, department_id) values(
    '������', '���������', '��������������',
    null,
    null,
    null,
    '������� �������',
    (select id from department where shortname='��� ��'));

INSERT INTO public.claim(actual, affirmation_date, creation_date, purpose, specialization, template_name, affirmator_id, car_boss_id, creator_id, department_id, vehicle_type_id)
	VALUES (true, null, '2019-01-30 15:45:08.15', '��������� ����������', 0, null, null, 1, 2, 17, 1);
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
	VALUES (0, '������ �������� � ��. 18', 24, 1);
INSERT INTO public.route_task(order_num, work_name, place_id, claim_id)
	VALUES (1, '��������� ���������� - 50 ���.', 29, 1);
INSERT INTO public.route_task(order_num, work_name, place_id, claim_id)
	VALUES (2, '��������� ���������� - 50 ���.', 24, 1);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-30', null, '-x123456789x-', '������', '����', null, '+77776665544', 0, '��������', 1);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-29', null, '-x123456789x-', '������', '����', null, '+77776665543', 0, '��������', 1);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-28', null, '-x123456789x-', '�������', '����', null, '+77776665542', 0, '��������', 2);

INSERT INTO public.driver(address, birthday, driver_class, driver_license, firstname, name, note, phone, status, surname, transport_dep_id)
	VALUES (null, '1979-01-27', null, '-x123456789x-', '������', '����', null, '+77776665541', 0, '��������', 2); 

INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 178 �� 94', 222, 0, (select id from Vehicle_Model where model_name='FORD FOCUS' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 431 �� 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI ACCENT' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 313 �� 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI SANTA FE 2.4' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 323 �� 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI SONATA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 513 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 179 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 213 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 216 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN ALMERA CLASSIC' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 113 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN MAXIMA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 208 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN MAXIMA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 008 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATHFINDER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 020 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATHFINDER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 100 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATHFINDER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 111 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PATROL 5.6' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 207 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN PRIMERA 2.0' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 001 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 109 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA 3.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 110 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA 3.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 112 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN TEANA 3.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 432 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 202 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 249 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 250 �� 94 ', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 003 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.0' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 090 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 005 �� 94', 222, 0, (select id from Vehicle_Model where model_name='NISSAN X-TRAIL 2.5' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 677 �� 99', 222, 0, (select id from Vehicle_Model where model_name='SSANGYONG REXTON' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 500 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA ALPHARD' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 414 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 232 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 100' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 012 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 120' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 071 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 150 (PRADO)' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 072 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 150 (PRADO)' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 777 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA LAND CRUISER 200' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 276 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 785 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 298 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 394 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 576 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 095 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 233 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 315 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 357 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 365 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 368 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 465 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 494 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 603 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 754 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 524 �� 197', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 030 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 212 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 282 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 292 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 310 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 311 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 312 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 393 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 274 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 365 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��� ���������' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 240 �� 99', 222, 0, (select id from Vehicle_Model where model_name='��� ���������' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 579 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-31512' limit 1), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 114 �� 94', 222, 0, (select id from Vehicle_Model where model_name='FORD TRANSIT CONNECT' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 678 �� 94', 222, 0, (select id from Vehicle_Model where model_name='TOYOTA HILUX' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 594 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-21213' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 625 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2704e6' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 160 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 221 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 134 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 259 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 305 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 532 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 549 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2834NF' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 627 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-330202' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 399 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-33021' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 462 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-33023' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 521 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 119 �� 97', 222, 0, (select id from Vehicle_Model where model_name='���-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 248 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 518 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-330232' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 306 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-22069' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 451 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-31512' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 332 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31512' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 906 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 359 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 390 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 397 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 407 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 297 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 356 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315142' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 362 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315142' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 862 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-31519' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 296 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-31519' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 292 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31519' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 310 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315192' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 314 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315192' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 224 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315194' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 225 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315194' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 142 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 143 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 144 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 145 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315195' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 298 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-37419-210' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 117 �� 97', 222, 0, (select id from Vehicle_Model where model_name='���-396252' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 628 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-39629' limit 1), 2);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 031 �� 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI STAREX' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 294 �� 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI STAREX' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 024 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2217' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 398 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2217' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 453 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2217' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 452 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-22171' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 456 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-22171' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 490 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-3221' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 023 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 096 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 027 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 941 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 942 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 279 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 355 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 361 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 367 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 396 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 401 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 512 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 550 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 871 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 872 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 116 �� 97', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 120 �� 97', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 304 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 311 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 312 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 507 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 508 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 972 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 338 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 340 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 341 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 384 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 385 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 581 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 582 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 395 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32214' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 426 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 650 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 688 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 754 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 388 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2206' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 422 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2206' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 773 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2206' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 322 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 324 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 371 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 397 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220602' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 395 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-22069' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 295 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-22069' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 155 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 156 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 158 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 159 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 220 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220694' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 284 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220695' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 285 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-220695' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 307 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-37419-210' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 504 �� 197', 222, 0, (select id from Vehicle_Model where model_name='���-3909' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 391 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-39099' limit 1), 3);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 648 �� 94', 222, 0, (select id from Vehicle_Model where model_name='IVECO-2227UR' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 649 �� 94', 222, 0, (select id from Vehicle_Model where model_name='IVECO-2227UR' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 650 �� 94', 222, 0, (select id from Vehicle_Model where model_name='IVECO-2227UR' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 419 �� 777', 222, 0, (select id from Vehicle_Model where model_name='SETRA S415UL' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 420 �� 777', 222, 0, (select id from Vehicle_Model where model_name='SETRA S415UL' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 686 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 835 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 862 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 872 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 883 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 519 �� 94', 222, 0, (select id from Vehicle_Model where model_name='����-525633-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 080 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 994 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 411 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 296 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-231062' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 738 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 748 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 749 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 756 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 845 �� 99', 222, 0, (select id from Vehicle_Model where model_name='��������-���� O350' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 792 �� 777', 222, 0, (select id from Vehicle_Model where model_name='��������-���� 223237' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 257 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320401-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 258 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320401-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 420 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320401-01' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 275 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 317 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 318 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 360 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 387 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 571 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 583 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 599 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 394 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 937 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 938 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 940 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 157 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 163 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 328 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 514 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 515 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 516 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 517 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 135 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 415 �� 97', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 392 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320530' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 253 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 865 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 089 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 091 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 092 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 093 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 094 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 286 �� 50', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 566 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 567 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 569 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 215 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4234' limit 1), 4);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 653 �� 94', 222, 0, (select id from Vehicle_Model where model_name='HYUNDAI-27990�' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '�� 4327 77', 222, 0, (select id from Vehicle_Model where model_name='JOHN DEERE 318D' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '�� 4326 77', 222, 0, (select id from Vehicle_Model where model_name='JOHN DEERE 325J' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 479 �� 94', 222, 0, (select id from Vehicle_Model where model_name='VOLKSWAGEN 2EKZ CRAFTER' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 430 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32214' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 398 �� 77', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 429 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32214' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 937 �� 99', 222, 0, (select id from Vehicle_Model where model_name='���-32214-0000010-01' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 349 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-33021' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 237 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-53' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 795 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-6601' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 585 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-441510' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 593 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-534330' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 604 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-��� 4502' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 229 �� 71', 222, 0, (select id from Vehicle_Model where model_name='�����-43118-46' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 321 �� 750', 222, 0, (select id from Vehicle_Model where model_name='�����-43118' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 307 �� 777', 222, 0, (select id from Vehicle_Model where model_name='�����-43502-45' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 501 �� 94', 222, 0, (select id from Vehicle_Model where model_name='�����-6520' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 502 �� 94', 222, 0, (select id from Vehicle_Model where model_name='�����-6520' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 423 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-440-4�' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 424 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-440-4�' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 351 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-503�-2' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 354 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-5551-020' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 029 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-630300-2121' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 098 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��������-���� ������ 2535' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 099 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��������-���� ������ 2535' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 102 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��������-���� ������ 2535' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 230 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���� 4320-0011-02' limit 1), 5);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 237 �� 750', 222, 0, (select id from Vehicle_Model where model_name='LIEBHERR LTM 1130-5.1' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 415 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2204' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 416 �� 94', 222, 0, (select id from Vehicle_Model where model_name='����-48442�' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 417 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��3-7-433362' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 810 �� 99', 222, 0, (select id from Vehicle_Model where model_name='��3-7-433362' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 297 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 337 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-33021' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 372 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-33021' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 336 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-330230' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 006 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-330900-0212' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 155 �� 99 ', 222, 0, (select id from Vehicle_Model where model_name='���-433362 ���' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 492 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-450650' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 347 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-5301��' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 958 �� 94', 222, 0, (select id from Vehicle_Model where model_name='�����-4672�6' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 573 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-503�' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 435 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-503�-3' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '�� 1607 52', 222, 0, (select id from Vehicle_Model where model_name='CNT 650' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 373 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-35715' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 374 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-35715' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 408 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-5579.2' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 327 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-695�' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 664 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-631705-370' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 483 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-5337 ��-3577' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 348 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-5551-020' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 295 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-555102-223' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 418 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-433362' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 326 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 319 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320530' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 251 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32054' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 597 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 162 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 320 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 176 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053S' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 213 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4234' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 296 �� 750', 222, 0, (select id from Vehicle_Model where model_name='���-328-02' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 358 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 437 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31514' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 325 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-390902' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 579 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-390992' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 441 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3962' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 402 �� 94', 222, 0, (select id from Vehicle_Model where model_name='����-4320-0911-40' limit 1), 6);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 455 �� 94', 222, 0, (select id from Vehicle_Model where model_name='HIGER KLQ 6886Q' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 541 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 762 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 789 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 793 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 801 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 816 �� 799', 222, 0, (select id from Vehicle_Model where model_name='YUTONG ZK6122H9' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 442 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 290 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2705' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 553 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-27527' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 291 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3102' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 181 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 289 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 291 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 329 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 330 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3110' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 191 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31105' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 460 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3221' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 301 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 459 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 413 �� 97', 222, 0, (select id from Vehicle_Model where model_name='���-32213' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 026 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 509 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 482 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-322132' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 601 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32214' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 596 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3240' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 577 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-131' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 486 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-133�42' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 451 �� 94', 222, 0, (select id from Vehicle_Model where model_name='��-503�' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 491 �� 94', 222, 0, (select id from Vehicle_Model where model_name='����-52564-0000010-01' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 307 �� 750', 222, 0, (select id from Vehicle_Model where model_name='���-231062' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 344 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-3205' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 299 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 305 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 306 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 316 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 490 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32050R' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 315 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 436 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 447 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 588 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 589 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-32053' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 457 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320530' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 590 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320530' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 591 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-320530' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 439 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4234' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 214 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4234' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 440 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-4254' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 350 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2206' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 485 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2206' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 488 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-2206' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 307 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-22069' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 489 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-315142' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)
VALUES (111, 333, null, '� 449 �� 94', 222, 0, (select id from Vehicle_Model where model_name='���-31519' limit 1), 7);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);
insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);
