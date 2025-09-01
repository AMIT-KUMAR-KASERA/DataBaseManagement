
===============================================================================================================================================================================================

Day 1 PL/SQ
============

PL/SQL is an procedure language extension for SQL. oracle 6.0
 introduced PL/SQL.

Basically pl/sql is an block structure programming language.


1.declaring a variable
======================

  in declare section of pl/sql block by using following syntax

syntax:-
=======
variablename datatype(size);

a number(10);



2.storing a value into variable:-
================================

   By using assignment operator(:=) we can also store a value
into variable.


syntax:-
=======
     variablename:=value;

   we can also use this assignment operator(:=) either in declare
section or in executable section in pl/sql block.

EX:-
===
declare
a number(10);
begin
a:=80;
end;
/

3.Display a message (or) variable value:-
======================================

   In pl/sql if we want to display a message or variable value then
we are using following syntax.

syntax:-
=======
dbms_output.put_line('message');

dbms_output.put_line(variablename);
    |          |
package      procedure
name         name

   This package is used in either in executable section or in exception
section of the pl/sql block.

eg:-
===
begin
dbms_output.put_line('welcome');
end;
/

PL/SQL procedure successfully completed

  In pl/sql whenever we are passing any data into dbms_output package 
then that package internally automatically creates a buffer and also passed data automatically stored in buffer but this buffer data does not visible in sqlplus client tool.To overcome this problem then we must set serveroutput environment variable by using following syntax at sql prompt. 

syntax:-
=======
SQL<set serveroutput on;
SQL>/
welcome


 *
*** select....into clause:- 
 *  ======================

   Select.... into clause is used to retrieve data from table then that 
data is storing into pl/sql variable.

  select.... into clause always returns single record or single value
at a time from a table.

syntax:-
=======
select col1,col2....into varname1,varname2.....
from tablename
where condition;

 This clause is used in executable section of the pl/sql block.


--------------------------------------------------------------------------



1.Write a PL/SQL program to print a message 'WELCOME TO PL/SQL  
  CLASSES FOR MY DEAR WONDERFULL BATACH39' on screen?



NOTE:-To avoid of displaying old and new parameters set verify off as
      following

SQL>set verify off;


--------------------------------------------------------------------------

2.Write a PL/SQL program to add 2 numbers. And two numbers are 
  enter from runtime ?


------------------------------------
-------------------------------------

3.Write a PL/SQL program for user entered employee number then display
  name of the employee and his salary from emp table?



  1  DECLARE
  2  V_NAME VARCHAR2(10);
  3  V_SAL1 NUMBER(10);
  4  BEGIN
  5  SELECT ENAME, SAL INTO V_NAME,V_SAL1 FROM EMP WHERE EMPNO=&V_EMPNO;
  6  DBMS_OUTPUT.PUT_LINE(V_NAME||' '||V_SAL1);
  7* END;
SQL> /
Enter value for v_empno: 7934
MILLER 1300


OR


  1  DECLARE
  2  V_EMPNO EMP.EMPNO%TYPE;
  3  V_ENAME EMP.ENAME%TYPE;
  4  V_SAL EMP.SAL%TYPE;
  5  BEGIN
  6  V_EMPNO :=&V_EMPNO;
  7  SELECT ENAME, SAL INTO V_ENAME,V_SAL FROM EMP WHERE EMPNO=V_EMPNO;
  8  DBMS_OUTPUT.PUT_LINE(V_ENAME||' '||V_SAL);
  9* END;
SQL> /
Enter value for v_empno: 7934
MILLER 1300


OR

 1  DECLARE
  2  V_ENAME EMP.ENAME%TYPE;
  3  V_SAL EMP.SAL%TYPE;
  4  BEGIN
  5  SELECT ENAME, SAL INTO V_ENAME,V_SAL FROM EMP WHERE EMPNO=&V_EMPNO;
  6  DBMS_OUTPUT.PUT_LINE(V_ENAME||' '||V_SAL);
  7* END;
SQL> /
Enter value for v_empno: 7934
MILLER 1300

--------------------------------------------------------------------------

4.Write a PL/SQL program which is used to retrieve maximum salary
      from emp table and store it into PL/SQL variable
              and display maximum salary?



  1  DECLARE
  2  V_MAX NUMBER(10);
  3  BEGIN
  4  SELECT MAX(SAL) INTO V_MAX FROM EMP;
  5  DBMS_OUTPUT.PUT_LINE('MAXIMUM SAL OF EMPLOYEE IS:-'||V_MAX);
  6* END;
SQL> /
MAXIMUM SAL OF EMPLOYEE IS:-5000

OR



  1  DECLARE
  2  V_MAX EMP.SAL%TYPE;
  3  BEGIN
  4  SELECT MAX(SAL) INTO V_MAX FROM EMP;
  5  DBMS_OUTPUT.PUT_LINE('MAXIMUM SAL OF EMPLOYEE IS:-'||V_MAX);
  6* END;
SQL> /
MAXIMUM SAL OF EMPLOYEE IS:-5000
--------------------------------------------------------------------------

5.Write a PL/SQL program to find maximum number from 2 numbers
  and store it into another variable?

note:-in pl/sql expressions we are not allowed to use group functions,
===== decode conversion functions. But we are allowed to use number
      functions, character functions, date  functions in pl/sql 
      expressions


  1  DECLARE
  2  NUM1 NUMBER(10);
  3  NUM2 NUMBER(10);
  4  BEGIN
  5  NUM1:=&NUM1;
  6  NUM2:=&NUM2;
  7  IF(NUM1>NUM2)THEN
  8  DBMS_OUTPUT.PUT_LINE('MAX NUMBER IS '||NUM1);
  9  ELSE
 10  DBMS_OUTPUT.PUT_LINE('MAX NUMBER IS '||NUM2);
 11  END IF;
 12* END;
SQL> /
Enter value for num1: 5
Enter value for num2: 10
MAX NUMBER IS 10

OR

 1  DECLARE
  2  NUM1 NUMBER :=&ENTER_FIRST_NUMBER;
  3  NUM2 NUMBER :=&ENTER_SECOND_NUMBER;
  4  MAX_VALUE NUMBER;
  5  BEGIN
  6  IF(NUM1>NUM2)THEN
  7  MAX_VALUE :=NUM1;
  8  ELSE
  9  MAX_VALUE :=NUM2;
 10  END IF;
 11  DBMS_OUTPUT.PUT_LINE('MAXIMUM OF '||NUM1||' AND '||NUM2||' IS -:'||MAX_VALUE);
 12* END;
SQL> /
Enter value for enter_first_number: 5
Enter value for enter_second_number: 10
MAXIMUM OF 5 AND 10 IS -:10
--------------------------------------------------------------------------


6.Write a PL/SQL program for user entered empno then display 
      all details of employee from emp table by using 
               row level attributes?

EG:-
===
declare                 particular cell you want(.notation)
i emp%rowtype;          /
                    i.ename              i.hiredate  i.sal
    i                /                        /       /
  -----     empno  ename    job     mgr   hiredate   sal  comm  deptno
  | . |---->7902   FORD   ANALYST   7566  03-DEC-81  4900        20
  -----



  1  DECLARE
  2  I EMP%ROWTYPE;
  3  V_EMPNO NUMBER;
  4  BEGIN
  5  V_EMPNO:=&V_EMPNO;
  6  SELECT * INTO I FROM EMP WHERE EMPNO=V_EMPNO;
  7  DBMS_OUTPUT.PUT_LINE(I.ENAME||' '||I.ENAME||' '||I.SAL||' '||I.JOB);
  8* END;
SQL> /
Enter value for v_empno: 7934
MILLER MILLER 1300 CLERK


SQL> DECLARE
  2  V_EMPNO NUMBER(4);
  3  V_ENAME VARCHAR2(10);
  4  V_SAL NUMBER(8);
  5  V_JOB VARCHAR2(10);
  6  V_COMM NUMBER(10);
  7  V_MGR NUMBER(10);
  8  V_HIREDATE DATE;
  9  V_DEPTNO NUMBER(10);
 10  BEGIN
 11  SELECT EMPNO,ENAME,SAL,JOB,COMM,MGR,HIREDATE,DEPTNO INTO V_EMPNO,V_ENAME,V_SAL,V_JOB,V_COMM,V_MGR,V_HIREDATE,V_DEPTNO FROM EMP WHERE EMPNO=7788;
 12  DBMS_OUTPUT.PUT_LINE(V_EMPNO||','||V_ENAME||','||V_SAL||','||V_JOB||','||V_COMM||','||V_MGR||','||V_HIREDATE||','||V_DEPTNO);
 13  END;
 14  /



   =====================================================================================================================================================================================================================
   2 PL/SQL                   
   =========


1.Write a PL/SQL program to calculate the experience of the given employee
  based on passed employee number ?

Enter value for empno: 7902
experience is:43years


SQL> DECLARE
  2  NUMBER_OF_EXPERIENCE NUMBER(10);
  3  BEGIN
  4  SELECT TRUNC((SYSDATE-HIREDATE)/365) INTO NUMBER_OF_EXPERIENCE FROM EMP WHERE EMPNO=&EMPNO;
  5  DBMS_OUTPUT.PUT_LINE('EXPERIENCE'||' :-'||NUMBER_OF_EXPERIENCE);
  6  END;
  7  /
Enter value for empno: 7902
old   4: SELECT TRUNC((SYSDATE-HIREDATE)/365) INTO NUMBER_OF_EXPERIENCE FROM EMP WHERE EMPNO=&EMPNO;
new   4: SELECT TRUNC((SYSDATE-HIREDATE)/365) INTO NUMBER_OF_EXPERIENCE FROM EMP WHERE EMPNO=7902;
EXPERIENCE :-43

--------------------------------------------------------------------------

2.Write a PL/SQL program eligible for voting system based on age

if age is>=18 then display a message : You are eligible for voting.

if age is<18 then display a message  : You are not eligible for voting.
                                       Better luck next time.




SQL> ED
Wrote file afiedt.buf

  1  DECLARE
  2  AGE NUMBER(10);
  3  BEGIN
  4  AGE:=&AGE;
  5  IF(AGE>18) THEN
  6  DBMS_OUTPUT.PUT_LINE('You are eligible for voting.');
  7  ELSE
  8  DBMS_OUTPUT.PUT_LINE('You are not eligible for voting. ');
  9  END IF;
 10* END;
SQL> /
Enter value for age: 20
old   4: AGE:=&AGE;
new   4: AGE:=20;
You are eligible for voting.

PL/SQL procedure successfully completed.

SQL> /
Enter value for age: 18
old   4: AGE:=&AGE;
new   4: AGE:=18;
You are not eligible for voting.

PL/SQL procedure successfully completed.

--------------------------------------------------------------------------

3.Write a PL/SQL program that accepts a student ID, fetches the marks from 
  a student111 table, display the grade based on the following conditions

if Marks >= 90 then display a message ---> 

if Marks >= 80 then display a message ---> Grade B

if Marks >= 70 then display a message ---> Grade C

if Marks >= 35 then display a message ---> Grade D

Marks < 35 --->then display a message ---> FAIL



CREATE TABLE STUDENT111
(
SID NUMBER(10),
SNAME VARCHAR2(10),
MARKS NUMBER(10)
);

INSERT INTO STUDENT111 VALUES(1,'SURYA',88);
INSERT INTO STUDENT111 VALUES(2,'KAVYA',74);
INSERT INTO STUDENT111 VALUES(3,'ANUSHKA',92);
INSERT INTO STUDENT111 VALUES(4,'PREETHI',12);
INSERT INTO STUDENT111 VALUES(5,'NITHISH',30);

 

SQL>select * from student111;

      SID      	NAME	  MARKS
     -----    --------   -------
      1	       SURYA	   88
      2	       KAVYA	   74
      3	       ANUSHKA 	   92
      4	       PREETHI	   12
      5	       NITHISH	   30




  1  DECLARE
  2  MARKS VARCHAR2(10);
  3  BEGIN
  4  MARKS:=&MARKS;
  5  IF(MARKS>=90)THEN
  6   DBMS_OUTPUT.PUT_LINE('Grade A');
  7  ELSIF(MARKS>=80)THEN
  8   DBMS_OUTPUT.PUT_LINE('Grade B');
  9  ELSIF(MARKS>=70)THEN
 10   DBMS_OUTPUT.PUT_LINE('Grade C');
 11  ELSIF(MARKS>=35) THEN
 12  DBMS_OUTPUT.PUT_LINE('GRADE D');
 13  ELSE
 14  DBMS_OUTPUT.PUT_LINE('FAIL');
 15  END IF;
 16* END;
SQL> /
Enter value for marks: 90
old   4: MARKS:=&MARKS;
new   4: MARKS:=90;
Grade A

--------------------------------------------------------------------------



4.Write a PL/SQL program to print 1..10 numbers by using
   for loop

NOTE:-in for loops index variable internally behaves like integer variable.
====  that's why when we are using for loops not required to declare index
      variable explicitly.



SQL> DECLARE
  2  VAR VARCHAR(10);
  3  BEGIN
  4  VAR:=&VAR;
  5  FOR I IN 1..VAR
  6  LOOP
  7  DBMS_OUTPUT.PUT_LINE(I);
  8  END LOOP;
  9  END;
 10  /


--------------------------------------------------------------------------

5.Write a PL/SQL program to print 10..1 numbers reverse by
   using for loop ?




  1  DECLARE
  2  VAR VARCHAR(10);
  3  BEGIN
  4  VAR:=&VAR;
  5  FOR I IN REVERSE 1..VAR
  6  LOOP
  7  DBMS_OUTPUT.PUT_LINE(I);
  8  END LOOP;
  9* END;


--------------------------------------------------------------------------
6.Write a PL/SQL program to print 2025 calendar?

01-JAN-25
02-JAN-25
.........
.........
31-DEC-25


  1  DECLARE
  2  VAR1:='01-JAN-2025';
  3  VAR2:='31-DEC-2025'
  4  BEGIN
  5  WHILE(VAR1<=VAR2)
  6  LOOP
  7  DBMS_OUTPUT.PUT_LINE(VAR1);
  8  VAR1:=VAR1+1;
  9  END LOOP;
 10* END;




