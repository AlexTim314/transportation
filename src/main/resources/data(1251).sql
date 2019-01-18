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

CREATE TABLE IF NOT EXISTS Vehicle_Type (
    id bigint NOT NULL DEFAULT nextval('vehicle_type_id_seq'::regclass),
    type_name varchar(64),
    specialization integer,
    PRIMARY KEY (id)
);

insert into Vehicle_Type(type_name, specialization) values('�������', 0);
insert into Vehicle_Type(type_name, specialization) values('������������', 0);
insert into Vehicle_Type(type_name, specialization) values('�����������', 1);
insert into Vehicle_Type(type_name, specialization) values('�����������������', 1);
insert into Vehicle_Type(type_name, specialization) values('���������', 1);
insert into Vehicle_Type(type_name, specialization) values('��������', 2);
insert into Vehicle_Type(type_name, specialization) values('������', 2);
insert into Vehicle_Type(type_name, specialization) values('��������', 2);
insert into Vehicle_Type(type_name, specialization) values('����� � ��������', 2);
insert into Vehicle_Type(type_name, specialization) values('��������', 3);
insert into Vehicle_Type(type_name, specialization) values('������������������', 3);
insert into Vehicle_Type(type_name, specialization) values('���������', 3);

