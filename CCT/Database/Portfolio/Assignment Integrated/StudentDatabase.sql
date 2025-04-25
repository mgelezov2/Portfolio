#THIS DROPS THE DATABASE INCASE OF WISHING ON STARTING A FRESH EMPTY DATABASE
#drop database College_Database;

#CREATES DATABASE IF IT DOES NOT EXIST
create database IF NOT EXISTS College_Database;

#ENABLES USAGE OF THE DATABASE TO EDIT TABLES, MAKE CHANGES AND DISPLAY RESULTS
use College_Database;

#THIS CREATES NECESSARY TABLES TO STORE DATA WHICH ARE OBTAINED FROM A CSV FILE.

#COURSES TABLE
create table Courses(
programID varchar(255),
programName varchar(500),
programPopulation int(255),
moduleID varchar(500) not null primary key,
moduleName varchar(500),
modulePopulation int(255),
roomLocation varchar(500),
lecturerID varchar(500)
);

#STUDENTS TABLE
create table Students(
studentName varchar(100),
studentID varchar(100) not null primary key
);

#LECTURERS TABLE
create table Lecturers(
lecturerID varchar(255) not null primary key,
lecturerName varchar(500),
lecturerRole varchar(500)
);





#ENROLLMENTS TABLE
create table Enrollments(
studentID varchar(255),
programID varchar(255),
moduleID varchar(255),
enrollmentDate date
#foreign key (programID) references Courses(programID),
#foreign key (moduleID) references Courses(moduleID)
);

#GRADES TABLE
CREATE TABLE Grades(
studentID varchar(255),
moduleID varchar(255),
passed_module_ID varchar(255),
grades_pass int(100),
failed_module_id varchar(255),
grades_fail int(100)
#foreign key (studentID) references Students(studentID),
#foreign key (moduleID) references Courses(moduleID)
);

#FEEDBACK TABLE
create table Feedback(
studentID varchar(255),
feedback varchar(500)
#foreign key (studentID) references Students(studentID)
);

#THIS ALTERS TABLES TO ADD PRIMARY KEYS, GIVING UNIQUE IDENTITY TO A ROW AND COLUMN(THEY ARE ALREADY ADDED BY CREATING TABLES BUT YOU MAY TEST THEM BY DROPPING PRIMARY KEYS)
#NOTICE: ERROR OCCURRED THROUGH FOREIGN KEYS, UNABLE TO TEST THEM TRIED ADDING THEM WITHOUT INCLUDING IN TABLE BUT STILL GIVES AN ERROR.

#ALTERS COURSES TABLE WITH PRIMARY KEY (moduleID)
alter table Courses add primary key (moduleID);
#alter table Courses drop primary key;

#ALTERS STUDENTS TABLE WITH PRIMARY KEY (studentID)
alter table Students add primary key (studentID);
#alter table Students drop primary key;

#ALTERS LECTURERS TABLE WITH PRIMARY KEY (lecturerID)
alter table Lecturers add primary key(lecturerID);
#alter table Lecturers drop primary key;

#ALTERS ENROLLMENTS TABLE WITH FOREIGN KEY (programID)
alter table Enrollments add foreign key (programID) references Courses(programID);
#alter table Enrollments drop foreign key (constraint);

#ALTERS ENROLLMENTS TABLE WITH FOREIGN KEY (moduleID) 
alter table Enrollments add foreign key (moduleID) references Courses(moduleID);
#alter table Enrollments drop foreign key (constraint);

#ALTERS GRADES TABLE WITH FOREIGN KEY (studentID)
alter table Grades add foreign key (studentID) references Students(studentID);
#alter table Grades drop foreign key (constraint);

#ALTERS GRADES TABLE WITH FOREIGN KEY (moduleID)
alter table Grades add foreign key (moduleID) references Courses(moduleID);
#alter table Grades drop foreign key (constraint);

#ALTERS FEEDBACK TABLE WITH FOREIGN KEY (studentID)
alter table Feedback add foreign key (studentID) references Students(studentID);
#alter table Feedback drop foreign key (constraint);


#SELECTS STUDENTS FROM A TABLE AND SHOWS WHICH STUDENT IS ENROLLED TO WHAT MODULE AND SUBJECT WITH TWO TABLES JOINED; Enrollments and Students
select students.studentID, students.studentName, enrolled.moduleID, enrolled.programID, enrolled.enrollmentDate
from Students students
left join Enrollments enrolled on students.studentID = enrolled.studentID
group by students.studentID, students.studentName, enrolled.moduleID, enrolled.programID, enrolled.enrollmentDate;

#DISPLAYS GRADES WITH BOTH PASSES AND FAILS OF STUDENTS WITH TWO TABLES JOINED; Grades and Students
select graded_student.studentID, graded_student.studentName, grades.grades_pass, grades.passed_module_ID
from Students graded_student
left join Grades grades on graded_student.studentID = grades.studentID
group by graded_student.studentID, graded_student.studentName, grades.grades_pass, grades.passed_module_ID;

select graded_student.studentID, graded_student.studentName, grades.grades_fail, grades.failed_module_id
from Students graded_student
left join Grades grades on graded_student.studentID = grades.studentID
group by graded_student.studentID, graded_student.studentName, grades.grades_fail, grades.failed_module_id;

#THE BEGINNING OF A TRANSACTION THAT HAS LOCK TABLES FOR WRITE AND READ QUERIES, WRITE UPDATES CHANGES TO THE DATABASE AND READ DISPLAYS DATA TO THE CONSOLE.
#TABLES ARE UNLOCKED AT THE END OF THE QUERY AND COMMITTED AT WRITE IF THERE ARE NO ERRORS, ROLLBACK IS CALLED IF THERE IS AN ERROR.

#START OF TRANSACTION
start transaction;

#COURSES TABLE CHANGES
lock tables Courses write;

#CHANGES STUDENT POPULATION TO ANOTHER NUMBER OF STUDENTS TAKING THE MODULE(Students coming and going through modules)
update Courses set modulePopulation = '31' where moduleName = 'Medicine Module 1';
update Courses set modulePopulation = '25' where moduleName = 'Medicine Module 2';
update Courses set modulePopulation = '30' where moduleName = 'Medicine Module 3';
update Courses set modulePopulation = '50' where moduleName = 'Medicine Module 4';

#CHANGES ROOM LOCATION OR CLASS ARRANGEMENT THROUGH ID OF LECTURER(lecturers changing learning environments)
update Courses set roomLocation = 'CL045' where lecturerID = 'L919';
update Courses set roomLocation = 'Online' where lecturerID = 'L269';
update Courses set roomLocation = 'CL010' where lecturerID = 'L406';
update Courses set roomLocation = 'Online' where lecturerID = 'L649';

#ENROLLMENTS TABLE CHANGES
lock tables Enrollments write;

#CHANGES ENROLLMENT DATE THROUGH MODULE ID(extended date for further enrollment) 
update Enrollments set enrollmentDate = '2024/05/15' where moduleID ='P001_01';
update Enrollments set enrollmentDate = '2023/10/20' where moduleID ='P002_01';

#CHANGES MODULE STAGES THROUGH STUDENT ID(students progressing through the module) 
update Enrollments set moduleID = 'P001_02' where studentID = 'S0001';
update Enrollments set moduleID = 'P001_04' where studentID = 'S0003';

#GRADES TABLE CHANGES
lock tables Grades write;

#CHANGES GRADES THROUGH STUDENT ID(Students who did repeats getting a better grade or students who failed getting a bad grade)
update Grades set grades_pass = 99 where studentID = 'S0001';
update Grades set grades_pass = 90 where studentID = 'S0002';
update Grades set grades_pass = 95 where studentID = 'S0003';

update Grades set grades_fail = 38 where studentID = 'S0004';
update Grades set grades_fail = 30 where studentID = 'S0005';
update Grades set grades_fail = 20 where studentID = 'S0006';

#FEEDBACK TABLE CHANGES
lock tables Feedback write;

#CHANGES FEEDBACK THROUGH STUDENT ID
update Feedback set feedback = 'Great student, very observant' where studentID = 'S0001';
update Feedback set feedback = 'Always present in classes but could improve on his grades' where studentID = 'S0002';
update Feedback set feedback = 'Poor behaviour, pays little attention to the material' where studentID = 'S0003';

#END OF TRANSACTION
commit;

#COURSES TABLE DISPLAY
lock tables Courses read;

#DISPLAYS NAME OF MODULE, NAME OF PROGRAM, CURRENT STUDENT POPULATION ATTENDING THE MODULE, LECTURER ID DESIGNATED TO THAT MODULE WHERE STUDENT POPULATION IS LESS THAN 40
select moduleName, programName, modulePopulation, lecturerID from Courses where modulePopulation < 40;

#DISPLAYS EVERYTHING IN COURSES THAT THE TARGETED LECTURER ID IS TEACHING
select * from Courses where lecturerID = 'L269';

#DISPLAYS DETAILS ABOUT A PARTICULAR MODULE
select roomLocation, programName, lecturerID from Courses where moduleName = 'Medicine Module 1';

#ENROLLMENTS TABLE DISPLAY
lock tables Enrollments read;

#DISPLAYS DETAILS WHICH MODULE STUDENT IS ENROLLED IN BY DATE
select studentID, programID, moduleID from Enrollments where enrollmentDate = '2023/05/03';

#DISPLAYS MODULE DETAILS BY STUDENT ID THAT IS ENROLLING IN THE COURSE
select programID, moduleID, enrollmentDate from Enrollments where studentID = 'S0015';

#DISPLAYS DETAILS A SPECIFIC ENROLLED MODULE THAT THE STUDENT IS TAKING BY PROGRAM ID AND MODULE ID
select studentID, programID, moduleID from Enrollments where programID = 'P002' and moduleID = 'P002_04';

#GRADES TABLE DISPLAY
lock tables Grades read;

#DISPLAYS STUDENT GRADES BY ID
select * from Grades where studentID = 'S0001';
select * from Grades where studentID = 'S0002';
select * from Grades where studentID = 'S0003';

select * from Grades where studentID = 'S0004';
select * from Grades where studentID = 'S0005';
select * from Grades where studentID = 'S0006';

#DISPLAYS STUDENT GRADES THAT ARE LESS THAN 80
select studentID, grades_pass, passed_module_ID from Grades where grades_pass < 80;

#DISPLAYS STUDENT GRADES THAT ARE LESS THAN 40
select studentID, grades_fail, failed_module_id from Grades where grades_fail < 40;

#FEEDBACK TABLE DISPLAY
lock tables Feedback read;

#DISPLAYS STUDENT FEEDBACK THROUGH STUDENT ID
select studentID, feedback from Feedback where studentID = 'S0001';
select studentID, feedback from Feedback where studentID = 'S0002';
select studentID, feedback from Feedback where studentID = 'S0003';
unlock tables;

#CHECKS FOR ERRORS IF THERE IS ONE IT WILL STOP THE ENTIRE PROGRAM AND DISPLAY THE ERROR
rollback;

#TESTS TO CHECK IF THE DATA IS IN THE TABLE
select * from Courses;
select * from Students;
select * from Lecturers;
select * from Enrollments;
select * from Grades;
select * from Feedback;


#FOR OOA ASSIGNMENT
CREATE TABLE College_Database.users (
	colUser_id varchar(5) NOT NULL,
	colUser_full_name varchar(20) NOT NULL,
	colUser_role varchar(30) NOT NULL,
	colUser_login_name varchar(30) NOT NULL,
	colUser_password varchar(16) NOT NULL
);

INSERT INTO users(colUser_id,colUser_full_name,colUser_role,colUser_login_name,colUser_password)VALUES('U003','','lecturer','','');
INSERT INTO users(colUser_id,colUser_full_name,colUser_role,colUser_login_name,colUser_password)VALUES('U002','','admin','','');
INSERT INTO users(colUser_id,colUser_full_name,colUser_role,colUser_login_name,colUser_password)VALUES('U001','','office','','');
UPDATE users SET colUser_full_name = 'Steven Steward', colUser_role = 'admin',colUser_login_name='admin' ,colUser_password = 'java' WHERE colUser_id = 'U001';
UPDATE users SET colUser_full_name = 'Kyle Barley', colUser_role = 'lecturer',colUser_login_name='lecturer' ,colUser_password = 'java' WHERE colUser_id = 'U002';
UPDATE users SET colUser_full_name = 'Lee Ryle', colUser_role = 'office',colUser_login_name='office' ,colUser_password = 'java' WHERE colUser_id = 'U003';




#FOR OOA ASSIGNMENT
CREATE TABLE College_Database.users (
	colUser_id varchar(5) NOT NULL,
	colUser_full_name varchar(20) NOT NULL,
	colUser_role varchar(30) NOT NULL,
	colUser_login_name varchar(30) NOT NULL,
	colUser_password varchar(16) NOT NULL
);

INSERT INTO users(colUser_id,colUser_full_name,colUser_role,colUser_login_name,colUser_password)VALUES('U003','','lecturer','','');
INSERT INTO users(colUser_id,colUser_full_name,colUser_role,colUser_login_name,colUser_password)VALUES('U002','','admin','','');
INSERT INTO users(colUser_id,colUser_full_name,colUser_role,colUser_login_name,colUser_password)VALUES('U001','','office','','');
UPDATE users SET colUser_full_name = 'Steven Steward', colUser_role = 'admin',colUser_login_name='admin' ,colUser_password = 'java' WHERE colUser_id = 'U001';
UPDATE users SET colUser_full_name = 'Kyle Barley', colUser_role = 'lecturer',colUser_login_name='lecturer' ,colUser_password = 'java' WHERE colUser_id = 'U002';
UPDATE users SET colUser_full_name = 'Lee Ryle', colUser_role = 'office',colUser_login_name='office' ,colUser_password = 'java' WHERE colUser_id = 'U003';
