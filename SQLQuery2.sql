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

INSERT INTO UserTable values (1,'yang','123456','系统管理员');
INSERT INTO UserTable values (2,'小明','1111','学生');
INSERT INTO UserTable values (3,'小红','6666','学生');
INSERT INTO UserTable values (4,'杨劲','6666','学生');
INSERT INTO UserTable values (5,'许伟杰','6666','学生');
INSERT INTO UserTable values (6,'yuan','6666','书籍管理员');

INSERT INTO books values (1,'C语言','华南理工出版社','谭浩强','中国广州','2015/1/1','20元','可借出','20','0');
INSERT INTO books values (2,'编译原理','清华大学出版社','王生原','中国北京','207/1/2','18元','可借出','10','0');
INSERT INTO books values (3,'数据结构','华南理工出版社','张三','中国广州','2011/1/1','40元','可借出','20','0');
INSERT INTO books values (4,'算法导论','电子工业出版社','李四','中国深圳','2012/1/1','30元','可借出','20','0');
INSERT INTO books values (5,'Android','华南理工出版社','王五','中国广州','2014/1/1','40元','可借出','20','0');
INSERT INTO books values (6,'计算机图形学','电子工业出版社','赵六','中国香港','2012/1/1','30元','可借出','20','0');

INSERT INTO bookBrowse values (2,'小明',1,'C语言','2018/1/9','2018/1/8','西区','是');
INSERT INTO bookBrowse values (3,'小红',1,'C语言','2018/1/9','2018/1/5','西区','是');
INSERT INTO bookBrowse values (5,'许伟杰',2,'编译原理',null,'2018/1/5','西区','否');


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
