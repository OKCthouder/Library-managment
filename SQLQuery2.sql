   use LibraryBase;
   CREATE TABLE UserTable(
	ID int, 
	UserName char(40) not null, 
	Password char(40) not null, 
	Power char(40) not null, 
	CONSTRAINT ID_User_Containt PRIMARY KEY (ID));

CREATE TABLE books(
	BookID int, 
	BookName char(20) not null, 
	Press char(20), 
	Author char(20), 
	Address char(20),
	PressDate char(10), 
	Price char(10), 
	Com char(20), 
	books_count char(2), 
	borrowed_count char(2), 
	CONSTRAINT ID_Contraint_PK PRIMARY KEY (BookID));

CREATE TABLE bookBrowse(
	ID int identity, 
	StudentID int,
	StudentName char(40), 
	BookID int,
	BookName char(20), 
	ReturnDate char(10), 
	BorrowDate char(10), 
	Com char(40), 
	Is_Returned char(2), 
	CONSTRAINT ID_BookBrowse_Containt PRIMARY KEY (ID),  
	CONSTRAINT FK_StudentID foreign key(StudentID) references UserTable(ID),
	CONSTRAINT FK_BookID foreign key(BookID) references books(BookID),
);

create unique index user_num on UserTable(ID);
create unique index book_num on books(BookID); 

create view Student_Borrow(ID,UserName,BookID,BookName,ReturnDate,BorrowDate,Com)
as 
select UserTable.ID,UserTable.UserName,books.BookID,books.BookName,ReturnDate,BorrowDate,bookBrowse.Com from
UserTable,books,bookBrowse where UserTable.ID = bookBrowse.StudentID 
and	books.BookID = bookBrowse.BookID;

select * from Student_Borrow;
select * from UserTable;
select * from books;
select * from bookBrowse;

delete from bookBrowse where ID = 6;

INSERT INTO UserTable values (1,'yang','123456','ϵͳ����Ա');
INSERT INTO UserTable values (2,'С��','1111','ѧ��');
INSERT INTO UserTable values (3,'С��','6666','ѧ��');
INSERT INTO UserTable values (4,'�','6666','ѧ��');
INSERT INTO UserTable values (5,'��ΰ��','6666','ѧ��');
INSERT INTO UserTable values (6,'yuan','6666','�鼮����Ա');

INSERT INTO books values (1,'C����','������������','̷��ǿ','�й�����','2015/1/1','20Ԫ','�ɽ��','20','0');
INSERT INTO books values (2,'����ԭ��','�廪��ѧ������','����ԭ','�й�����','207/1/2','18Ԫ','�ɽ��','10','0');
INSERT INTO books values (3,'���ݽṹ','������������','����','�й�����','2011/1/1','40Ԫ','�ɽ��','20','0');
INSERT INTO books values (4,'�㷨����','���ӹ�ҵ������','����','�й�����','2012/1/1','30Ԫ','�ɽ��','20','0');
INSERT INTO books values (5,'Android','������������','����','�й�����','2014/1/1','40Ԫ','�ɽ��','20','0');
INSERT INTO books values (6,'�����ͼ��ѧ','���ӹ�ҵ������','����','�й����','2012/1/1','30Ԫ','�ɽ��','20','0');

INSERT INTO bookBrowse values (2,'С��',1,'C����','2018/1/9','2018/1/8','����','��');
INSERT INTO bookBrowse values (3,'С��',1,'C����','2018/1/9','2018/1/5','����','��');
INSERT INTO bookBrowse values (5,'��ΰ��',2,'����ԭ��',null,'2018/1/5','����','��');


create login Yujie_Yang with password='123456', default_database=LibraryBase;
create user user1 for login Yujie_Yang with default_schema=dbo;
exec sp_addrolemember 'db_owner', 'user1';


create login UserLogin with password='666', default_database=LibraryBase;
create user user2 for login UserLogin with default_schema=dbo;

GRANT INSERT, UPDATE, DELETE, SELECT
ON books
TO user2
GO

GRANT INSERT, UPDATE, DELETE, SELECT
ON bookBrowse
TO user2
GO
