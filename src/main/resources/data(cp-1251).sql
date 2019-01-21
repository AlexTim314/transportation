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

insert into Place(name, address) values('��. 10', '��. ������ �.�., �. 7');
insert into Place(name, address) values('��. 18', '��-1');
insert into Place(name, address) values('��. 44', '��-2');
insert into Place(name, address) values('��. 97', '��-3');
insert into Place(name, address) values('��. 23', '��-5');
insert into Place(name, address) values('��. 1', '��17�32');
insert into Place(name, address) values('��. 31', null);
insert into Place(name, address) values('��. 81', null);
insert into Place(name, address) values('��. 200', null);
insert into Place(name, address) values('��. 43', null);
insert into Place(name, address) values('��. 45', null);
insert into Place(name, address) values('��. 71', null);
insert into Place(name, address) values('��. 72', null);

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
    insert into Route_Template(name, department_id) values('��-1 ��. 18 (���������)', (select id from Department where shortname='��� ��'));
        insert into Route_Task(work_name, order_num, place_id) values('������ �������� � ��. 18', 0, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-1 ��. 18 (���������)'), (select id from route_task where work_name='������ �������� � ��. 18'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 18 (50 �������)', 1, (select id from Place where name='��. 18'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-1 ��. 18 (���������)'), (select id from route_task where work_name='�������� �� ��. 18 (50 �������)'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 10 � ��. 18 (50 �������)', 2, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-1 ��. 18 (���������)'), (select id from route_task where work_name='�������� �� ��. 10 � ��. 18 (50 �������)'));
    insert into Route_Template(name, department_id) values('��-2 ��. 44 (���������)', (select id from Department where shortname='��� ��'));
        insert into Route_Task(work_name, order_num, place_id) values('������ �������� � ��. 44', 0, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-2 ��. 44 (���������)'), (select id from route_task where work_name='������ �������� � ��. 44'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 44 (50 �������)', 1, (select id from Place where name='��. 44'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-2 ��. 44 (���������)'), (select id from route_task where work_name='�������� �� ��. 44 (50 �������)'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 10 � ��. 44 (50 �������)', 2, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-2 ��. 44 (���������)'), (select id from route_task where work_name='�������� �� ��. 10 � ��. 44 (50 �������)'));
    insert into Route_Template(name, department_id) values('��-3 ��. 97 (���������)', (select id from Department where shortname='��� ��'));
        insert into Route_Task(work_name, order_num, place_id) values('������ �������� � ��. 97', 0, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-3 ��. 97 (���������)'), (select id from route_task where work_name='������ �������� � ��. 97'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 97 (50 �������)', 1, (select id from Place where name='��. 97'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-3 ��. 97 (���������)'), (select id from route_task where work_name='�������� �� ��. 97 (50 �������)'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 10 � ��. 97 (50 �������)', 2, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-3 ��. 97 (���������)'), (select id from route_task where work_name='�������� �� ��. 10 � ��. 97 (50 �������)'));
    insert into Route_Template(name, department_id) values('��-5 ��. 23 (���������)', (select id from Department where shortname='��� ��'));
        insert into Route_Task(work_name, order_num, place_id) values('������ �������� � ��. 23', 0, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-5 ��. 23 (���������)'), (select id from route_task where work_name='������ �������� � ��. 23'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 23 (50 �������)', 1, (select id from Place where name='��. 23'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-5 ��. 23 (���������)'), (select id from route_task where work_name='�������� �� ��. 23 (50 �������)'));
        insert into Route_Task(work_name, order_num, place_id) values('�������� �� ��. 10 � ��. 23 (50 �������)', 2, (select id from Place where name='��. 10'));
        insert into route_template_route_tasks(route_template_id, route_tasks_id) values((select id from route_template where name='��-5 ��. 23 (���������)'), (select id from route_task where work_name='�������� �� ��. 10 � ��. 23 (50 �������)'));
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
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32050R', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32053', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-320530', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32050S', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32054', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-4234', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-4254', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-695�', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-52564-0000010-01', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-231062', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��������-���� �350', (select id from Vehicle_Type where type_name='�������'));
insert into Vehicle_Type(type_name, specialization) values('������������', 0);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2206', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-220602', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-220694', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-220695', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-32213', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-322132', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI STAREX', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('IVECO 2227UR', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��������-���� 223237', (select id from Vehicle_Type where type_name='������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-320401-01', (select id from Vehicle_Type where type_name='������������'));
insert into Vehicle_Type(type_name, specialization) values('�����������', 1);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PATROL 5.6', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LAND CRUISER', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LC 100', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LC 200', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA LC PRADO', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('TOYOTA HILUX', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TREIL', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TREIL 2.0', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN X-TREIL 2.5', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN PATHFINDER', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('SSANGYONG REXTON', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('HYUNDAI SANTA FE', (select id from Vehicle_Type where type_name='�����������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��� ���������', (select id from Vehicle_Type where type_name='�����������'));
insert into Vehicle_Type(type_name, specialization) values('�����������������', 1);
insert into Vehicle_Type(type_name, specialization) values('���������', 1);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-2217', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN TEANA', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN ALMERA', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('NISSAN MAXIMA 3.0 SE', (select id from Vehicle_Type where type_name='���������'));
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
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-315195', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-21213', (select id from Vehicle_Type where type_name='���������'));
insert into Vehicle_Type(type_name, specialization) values('�������� ������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-330202', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3302', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-330232', (select id from Vehicle_Type where type_name='�������� ������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-33021', (select id from Vehicle_Type where type_name='�������� ������'));
insert into Vehicle_Type(type_name, specialization) values('�������� ������ (��� ��������� ������� ������)', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-33021', (select id from Vehicle_Type where type_name='�������� ������ (��� ��������� ������� ������)'));
insert into Vehicle_Type(type_name, specialization) values('��������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-53', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-33090', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-5301 ��', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-534330', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-48442�', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-4320', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('����-375', (select id from Vehicle_Type where type_name='��������'));
insert into Vehicle_Type(type_name, specialization) values('��������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-4502', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-450650', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3518', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-5551', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('�����-6520', (select id from Vehicle_Type where type_name='��������'));
insert into Vehicle_Type(type_name, specialization) values('���������', 2);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-630300 2121 (� ������)', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-441510', (select id from Vehicle_Type where type_name='���������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('DAEWOOSUPER', (select id from Vehicle_Type where type_name='���������'));
insert into Vehicle_Type(type_name, specialization) values('��������������� ������', 3);
        insert into Vehicle_Model(model_name, vehicle_type_id) values('��-503�-2 (���������)', (select id from Vehicle_Type where type_name='��������������� ������'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('��-503�', (select id from Vehicle_Type where type_name='��������������� ������'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('���-3307 (��-503�-3)', (select id from Vehicle_Type where type_name='��������������� ������'));
        insert into Vehicle_Model(model_name, vehicle_type_id) values('�����-4672�6', (select id from Vehicle_Type where type_name='��������������� ������'));
insert into Vehicle_Type(type_name, specialization) values('���������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('�� 440-4�', (select id from Vehicle_Type where type_name='���������'));
insert into Vehicle_Type(type_name, specialization) values('�������������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��3-7-433362', (select id from Vehicle_Type where type_name='�������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('MERCEDES BENZ ACT.', (select id from Vehicle_Type where type_name='�������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-631705-370', (select id from Vehicle_Type where type_name='�������������'));
insert into Vehicle_Type(type_name, specialization) values('��������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-5337 ��-3577', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��-35715', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('��-5579.2', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('CNT 650', (select id from Vehicle_Type where type_name='��������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('LIBHERR LTM 1130-5.1', (select id from Vehicle_Type where type_name='��������'));
insert into Vehicle_Type(type_name, specialization) values('��������������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-22.04 (�� ���� ����)', (select id from Vehicle_Type where type_name='��������������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-328-02 (�� ���� ������)', (select id from Vehicle_Type where type_name='��������������'));
insert into Vehicle_Type(type_name, specialization) values('�������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-635', (select id from Vehicle_Type where type_name='�������'));
    insert into Vehicle_Model(model_name, vehicle_type_id) values('���-433362', (select id from Vehicle_Type where type_name='�������'));
insert into Vehicle_Type(type_name, specialization) values('����������', 3);
    insert into Vehicle_Model(model_name, vehicle_type_id) values('JOHN DEERE-325 J', (select id from Vehicle_Type where type_name='����������'));
