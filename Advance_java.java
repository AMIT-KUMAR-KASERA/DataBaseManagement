=============================================================================
Day #9:	Running SELECT query and retreiving results(rows data) from DB table
=============================================================================
    In this example we will learn
 	-> ResultSet, ResultSetMetaData,
=============================================================================
 				ResultSet
=============================================================================
1. The ResultSet is an interface, it is a cursor object.
   It is used for retrieving records from DB table
   that is returned by select query
 
2. We can obtain ResultSet object by executing SELECT query
   by using stmt.executeQuery(-)
 	public ResultSet executeQuery(String selectQuery)

3. When ResultSet object is created,
   initially its cursor is positioned before the first row.
 
   By calling next() method repeatedly the coursor is moved
   from before first row to after last row and returns false

4. ResultSet interface provides 2 methods 'next()' method and 'getter()' method
   for moving cursor to next row and retrieving colomns data from this row
 
   The methods' prototype is:
 	1. public boolean next()
 		- it moves cursor to the next record
 		- if record exists returns true, else returns false
 
 	2. public xxx getXxx(int columnIndex)  (or)
 	   public xxx getXxx(String columnName)
 		- Here xxx is the Java data type name
 		  for retrieving given column data from this record
 
 	In ResultSet interface we have multiple getter methods
 	for retrieving different type of values from this record
 
 	    For example:
 		public int	getInt(int columnIndex)
 		public long	getLong(int columnIndex)
 		public double	getDouble(int columnIndex)
 		public char	getChar(int columnIndex)
 		public boolean	getBoolean(int columnIndex)
 		public String	getString(int columnIndex)
 	        etc....
 	Note: The column index begins from 1 (not 0)
 
 	Below diagram shows ResultSet object architecture


5. Below is the sample code for retireving data from ResultSet
   and printing on console
 	ResultSet rs = stmt.execureQuery("Select * FROM course");
 	while(rs.next()) {
 		System.out.println(
 			rs.getInt(1)	+ " " +
 			rs.getString(2)	+ " " +
 			rs.getDobule(3)
 		);
 	}

6. Program #9: Develop a program to retrieve data from the Course table
 		and print on console
 	//Test09_Stmt_RS_SelectRows_Course.java
 	package com.nit.hk.jdbc;

 	import java.sql.DriverManager;
 	import java.sql.Connection;
 	import java.sql.Statement;
 	import java.sql.ResultSet;
 	import java.sql.SQLException;

 	public class Test09_Stmt_RS_SelectRows_Course {
 
 		public static void main(String[] args)
 				   throws ClassNotFoundException, SQLException {
 
 			// 1. Loading driver
 			Class.forName("oracle.jdbc.driver.OracleDriver");

 			//2. Establishing connection
 			Connection con = DriverManager.getConnection(
 					   "jdbc:oracle:thin:@localhost:1521:XE",
 					   "hknit11am2", "hari");

 			//3. Creating Statement object
 			Statement stmt = con.createStatement();
 
 			//4. Executing SELECT Query
 			ResultSet rs = stmt.executeQuery("SELECT * FROM course");
 
 			//5. Fetching results from DB by using ResultSet object
 			while(rs.next()) {
 				System.out.println(
 				    rs.getInt(1) + "\t" +
 				    rs.getString(2) + "\t\t" +
 				    rs.getDouble(3) );
 			}
 
 			//6. closing connections
 			rs.close();
 			stmt.close();
 			con.close();

 		}
 	}
 
O/P of the above program

 	1 Core Java            3500
 	2 Oracle               2500
 	3 HTML, CSS, JS        2500
 	4 Adv Java             3500

Problem in the above output or code,
 	- it can not display column names, hence we can not understand the data
Solution:
 	- we must use ResultSetMetaData for reading columns information
 	  like name, type, size, number of columns, etc..

====================================================================================
 				ResultSetMetaData
====================================================================================
1. A metadata is a 'data about data'.
2. The ResultSetMetaData is a MetaData about ResultSet.

3. The ResultSetMetaData is an interface, it is used for
   retrieving the columns information of a table
   like column count, columns name, column type, column size, etc...

4. The ResultSetMetaData has below getter methods for retrieving columns information
 	public int	getColumnCount()
 	public String	getColumnName(int columnsIndex)
 	public String	getColumnTypeName(int columnsIndex)
 	public int	getColumnDisplaySize(int columnsIndex)
 	public int	getPrecision(int columnsIndex)
 
5. We can obtain ResultSetMetaData object by using the factory method getMetaData()
   available in ResultSet interface
 	public ResultSetMetaData getMetaData()
 
 	For example:
 		ResutSet	  rs	= stmt.executeQuery(selectQ)
 		ResultSetMetaData rsmd  = rs.getMetaData();
 		rsmd.getXxx(columnIndex);
 
6. Below program shows retriving and printing columns details
 	//Test10_Stmt_RSMD_ColumnDetails.java
 	package com.nit.hk.jdbc;

 	import java.sql.DriverManager;
 	import java.sql.Connection;
 	import java.sql.Statement;
 	import java.sql.ResultSet;
 	import java.sql.ResultSetMetaData;
 	import java.sql.SQLException;
 
 	public class Test10_Stmt_RSMD_ColumnDetails{
 
 		public static void main(String[] args)
 				throws ClassNotFoundException, SQLException {
 
 			// 1. Loading driver
 			Class.forName("oracle.jdbc.driver.OracleDriver");

 			//2. Establishing connection
 			Connection con = DriverManager.getConnection(
 						"jdbc:oracle:thin:@localhost:1521:XE",
 						"hknit11am2", "hari");

 			//3. Creating Statement object
 			Statement stmt = con.createStatement();
 
 			//4. Executing SELECT Query
 			ResultSet rs = stmt.executeQuery("SELECT * FROM course");
 			ResultSetMetaData rsmd = rs.getMetaData();
 
 			System.out.println(rsmd.getColumnCount());
 			System.out.println();
 
 			System.out.println(rsmd.getColumnName(1));
 			System.out.println(rsmd.getColumnName(2));
 			System.out.println(rsmd.getColumnName(3));
 			System.out.println();

 			System.out.println(rsmd.getColumnTypeName(1));
 			System.out.println(rsmd.getColumnTypeName(2));
 			System.out.println(rsmd.getColumnTypeName(3));
 			System.out.println();

 			System.out.println(rsmd.getColumnDisplaySize(1));
 			System.out.println(rsmd.getColumnDisplaySize(2));
 			System.out.println(rsmd.getColumnDisplaySize(3));
 			System.out.println();

 			System.out.println(rsmd.getPrecision(1));
 			System.out.println(rsmd.getPrecision(2));
 			System.out.println(rsmd.getPrecision(3));
 
 			//try other method available in RSMD by
 			//following API documentation
 
 			//6. closing connections
 			rs.close();
 			stmt.close();
 			con.close();

 		}
 	}

7. Develop a program to display a table's columns names and its row data
   by using ResultSet and ResultSetMetaData
   Output must be printed as below like it is displayed on Sql Plus

   =======================================================================
    COURSE_ID COURSE_NAME                                        COURSE_FEE
   ---------- -------------------------------------------------- ----------
            1 Core Java                                              3500
            2 Oracle                                                 2500
            3 HTML, CSS, JS                                          2500
            4 Adv Java                                               3500
   =================================================================================
 	//Test10_Stmt_RS_RSMD_PrintColsRows_Course.java
 	package com.nit.hk.jdbc;

 	import java.sql.DriverManager;
 	import java.sql.Connection;
 	import java.sql.Statement;
 	import java.sql.ResultSet;
 	import java.sql.ResultSetMetaData;
 	import java.sql.SQLException;

 	public class Test10_Stmt_RS_RSMD_PrintColsRows{
 
 		public static void main(String[] args)
 					throws ClassNotFoundException, SQLException {
 
 			// 1. Loading driver
 			Class.forName("oracle.jdbc.driver.OracleDriver");

 			//2. Establishing connection
 			Connection con = DriverManager.getConnection(
 						"jdbc:oracle:thin:@localhost:1521:XE",
 						"hknit4pm2", "hari");

 			//3. Creating Statement object
 			Statement stmt = con.createStatement();
 
 			//4. Executing SELECT Query and obtains ResultSet and RSMD objects
 			ResultSet 	  rs = stmt.executeQuery("SELECT * FROM course");
 			ResultSetMetaData rsmd = rs.getMetaData();
 
 			//5. Fetching Columns and Rows RSMD and RS
 			int count = 0;
 			if(rs.next()) {
 				for(int i=1; i<=rsmd.getColumnCount(); i++) {
 					System.out.print(rsmd.getColumnName(i) +"\t");
 				}
 				System.out.println("\n-------------------------------------------");

 				do {
 					for(int i=1; i<=rsmd.getColumnCount(); i++) {
 						System.out.print(rs.getString(i) + "\t\t"	);
 					}
 					System.out.println();
 					count++;
 				}while(rs.next());

 				System.out.println("\n"+ count + " rows selected");
 
 			}else {
 				System.out.println("no rows selected");
 			}
 
 			//6. closing connections
 			rs.close();
 			stmt.close();
 			con.close();

 		}
 	}


================================================================================================================================================================================================================================

  ===========================================================================================	
9. Steps to Develop JDBC program
===========================================================================================	
    JDBC program has 7 steps	
	1. Loading driver
	2. Establishing connection
	3. Creating Statement object
	4. Sending and Executing SELECT Query, obtaining ResultSet object
	5. Fetching results from DB by using ResultSet and RSMD objects
	6. Closing connections (rs, stmt, con)
	7. Handling ClassNotFoundException and SQLException

===========================================================================================	
10. JDBC 4.0 new features: Auto Loading Driver 
===========================================================================================	
	From JDBC 4.0 onwards we no need to load driver class 
	It is automatilly loaded by DriverManager class with the passed DB url
	
	Hence from JDBC 4.0v onwards JDBC program has 6 steps 
		1. Establishing connection
		2. Creating Statement object
		3. Executing SELECT Query and obtaining ResultSet object
		4. Fetching results from DB by using ResultSet object
		5. Closing connections
		6. Handling ClassNotFoundException and SQLException


11. Closing JDBC connection (rs, stmt, and con) is it mandatory optional?
	-> it is mandatory 
	-> after those objects usage we must close those connections
	   else the buffer memory allocated in DB server is not destoryed
	   
12. How can we close JDBC objects connections mandatorily?	
	-> place close() method calls inside finally block
	
13. Procedure to create JDBC objects, using and closing them
    by using try, catch, finally blocks?
    
	#1: before try block 
		declare JDBC objects variables with default value null
		
	#2: inside try block 
		created JDBC objects, execute queries, 
		read result and print 
	    
	#3: inside finally block
		close connections by calling close() method
	
	Sample code:
		Connection con = null;
		Statement  stmt = null;
		ResultSet  rs  = null;
		
	        try {
		     con  = DM.gC(url, usn, pwd);
		     stmt = con.cS();
		     rs   = stmt.eQ(selectQ);
		     while(rs.next()) {
		        rs.getXxx(c_index);
		     }
		     
		 } catch(SQLException e) {
		      e.printStackTrace();
		 
		 } finally {
		       rs.close();
		       stmt.close();
		       con.close();
		 }

===========================================================================================	
14. JDBC program for catching exception and closing connections in finally block	
===========================================================================================	
	//Test12_JDBC_Code_With_EH.java 
	package com.nit.hk.jdbc;

	import java.sql.DriverManager;
	import java.sql.Connection;
	import java.sql.Statement;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;

	public class Test12_JDBC_Code_With_EH {
		
		public static void main(String[] args) {

			Connection con = null;
			Statement stmt = null;
			ResultSet rs  = null;
			
			try {
				//1. Loading JDBC driver
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2. Establishing connection
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE",
						"hknit11am", "hari");
				
				//3. Creating Statement object
				stmt = con.createStatement();
				
				//4. Executed SELECT and obtain ResultSet and RSMD object
				rs = stmt.executeQuery("SELECT * FROM student ORDER BY sid ");
				ResultSetMetaData rsmd = rs.getMetaData();
				
				//5. Fetching results from DB
				int count = 0;
				if(rs.next()) {

					for(int i=1; i<=rsmd.getColumnCount(); i++) {
						System.out.print(rsmd.getColumnName(i) +"\t");
					}
					System.out.println("\n-----------------------------------------------");
					
					do{
						System.out.println(
						    rs.getInt(1) + "\t"+ rs.getString(2) + "\t" + 
						    rs.getInt(3) + "\t"+ rs.getDouble(4));
						    
						count++;
					}while(rs.next()); 
				}
				System.out.println("\n"+ count + " rows selected");
				
			}//try close
			//catch(ClassNotFoundException e) {
			//	System.out.println("Driver class is not found");
			//}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(rs != null)
						rs.close();
				}catch(SQLException e) { }
				
				try {
					if(stmt != null)
						stmt.close();
				}catch(SQLException e) { }
				
				try {
					if(con != null)
						con.close();
				}catch(SQLException e) { }
				
			}
		}
	}

===========================================================================================
		JDBC program with try-with-resource
===========================================================================================
	//Test12_JDBC_Code_With_try_with_resource.java
	package com.nit.hk.jdbc;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class Test12_JDBC_Code_With_try_with_resource {
		
		public static void main(String[] args) {

			try(
				//1. Establishing connection
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE",
						"hknit4pm2", "hari");
				
				//2. Creating Statement object
				Statement stmt = con.createStatement();
				
				//3. Executed SELECT and obtain ResultSet and RSMD object
				ResultSet rs = stmt.executeQuery("SELECT * FROM course ORDER BY course_id ");
			){
				ResultSetMetaData rsmd = rs.getMetaData();
				
				//4. Fetching results from DB
				int count = 0;
				if(rs.next()) {

					for(int i=1; i<=rsmd.getColumnCount(); i++) {
						System.out.print(rsmd.getColumnName(i) +"\t");
					}
					System.out.println("\n-----------------------------------------------");
					
					do{
						System.out.println(rs.getInt(1) + "\t"+ rs.getString(2) + "\t"+rs.getDouble(3));
						count++;
					}while(rs.next()); 
				}
				System.out.println("\n"+ count + " rows selected");
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}


===========================================================================================================================================================================================================================
===========================================================================================================================================================================================================================
  Statement object with dynamic values
========================================
#driverinfo.properties
driver=oracle.jdbc.driver.OracleDriver
url=jdbc:oracle:thin:@localhost:1521:xe
usn=hknit11am2
pwd=hari

//Test09_Insert_DynamicValues.java
package com.nit.hk.jdbc;

import java.util.Properties;
import java.util.Scanner;

import java.sql.DriverManager;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Test09_Stmt_Insert_DynamicValues_tc_props {
	
	public static void main(String[] args) {
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			//1. creating empty properties objects
			Properties props = new Properties();
			
			//2. loading properties from properties file into props object
			props.load(new FileReader("driverinfo.properties"));
			
			//3. reading properties from props object and storing local variables
			String driver 	= props.getProperty("driver");
			String url		= props.getProperty("url");
			String usn		= props.getProperty("usn");
			String pwd		= props.getProperty("pwd");
				
			//4. using properties in the next lines of code
			
			//5. loading driver that is mentioned in properties file 
			Class.forName(driver);
			
			//6. establishing connection to the data based mentioned in the properties file
			con = DriverManager.getConnection(url, usn, pwd);
			
			//7. creating statement object
			stmt = con.createStatement();
			
			//8. connecting to KB and reading course_name and course_fee
			Scanner scn = new Scanner(System.in);
			
			String option = "N";
			do {
				System.out.print("\nEnter course name: ");
				String courseName = scn.nextLine();
	
				System.out.print("Enter course fee : ");
				double fee = scn.nextDouble(); 
				
				//9. preparing insert query with above runtime values
				String insertQuery = 
						"INSERT INTO course(course_id, course_name, course_fee)\n" +
						"VALUES(course_seq.nextval, '"+courseName+"', "+fee+")";		
				
				//10. executing above insert query
				int numberOfRows = stmt.executeUpdate(insertQuery);
				System.out.println("\n "+ numberOfRows + " row inserted");
			
				System.out.print("\nDo you want to continue(Y/N)?: ");
				option = scn.next(); scn.nextLine();
				
			}while(option.equalsIgnoreCase("Y"));
			
			System.out.println("\n******* Thank you, Visit again *******");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + " is not found");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)
					stmt.close();
			}catch(SQLException e) { }

			try {
				if(con != null)
					con.close();
			}catch(SQLException e) { }
		}
	
	} //main close
}//class close

Problems with Statement object
===============================
 1. Query syntax with dynamic values is difficult to understand
    because of ' " and + operator combination, and mistakes leads to errors
       
 2. Performance issue because of parsing query and generating SQL plan
    for the same query repeatedly 
    
 3. SQL Injection, it may by pass user authentication
 
 sing Statement with dynamic values has several problems. Let me explain clearly:

 Problems with Statement object (when inserting dynamic values)
=======================================================================

1. SQL Injection Vulnerability(work in mysql)
=================================

=>Because you are directly concatenating user input into SQL:
=============================================================
"VALUES(course_seq.nextval, '"+courseName+"', "+fee+")"
=============================================================
=>If a user enters something malicious like:

courseName = "Java'); DELETE FROM course; --"


→ It can delete entire table data (SQL injection attack).

2. Error with Special Characters
==============================================================
If courseName contains ' (single quote), the query will break.
Example:
Enter course name: C's Java

Generated query:

VALUES(course_seq.nextval, 'C's Java', 5000)


❌ This gives SQL syntax error because of '.

3 . Performance Issues
===============================================================

Each time you call stmt.executeUpdate(query), the database has to re-compile the SQL because it sees a new query string.

If you insert thousands of records → slower performance.

4 Code Readability / Maintainability
==================================================================

Query string becomes messy with multiple dynamic values.

Difficult to debug and maintain.

5 Type Handling Problems
==================================================================

You need to handle quotes manually for String values, no quotes for numbers, etc. Easy to make mistakes.





------------------------------------------------------------------------
Solution to Statement object is PreparedStatement
=========================================================================
 - For executing static SQL query that to only one time
   we must use Statement object
  
 - For executing dynamic SQL query or the same query repeatedly
   we must use PreparedStatement
   
 - With Statement object, 
	1. the query is submitted repeatedly to the DB 
	   every time when we run execute() method
   
 - Whereas with PreparedStatement, 
	1. the query is submitted to DB only once, it is parsed and 
	   SQL Plan is generated only once when PrepatedStatement object is created. 
	   
	2. Then with the repeated calls of execute() method the same SQL plan 
	   is executed, then we will get high performance, fast execution.
 
	3. also for substituting dynamic values we no need to use ' " and + operator
	   PStmt internally adds '' for the VARCHAR type column values automatically
	
	4. Also we will not get SQL Injection problem
	
PreparedStatement interface and its methods
============================================
1. The PreparedStatement is a sub interface of Statement interface

		Statement
		   /|\	
		    |	
		    |	
		PreparedStatement
		
2. It is used for executing a 'Pre-compiled' SQL statement 
   with or without runtime values

3. A pre-compiled SQL statement means, 
    - the SQL query is submitted to the DB only once, 
    - it is compiled, SQL plan is generated and saved in DB server 
    - then further that SQL query plan reference returned and 
      stored in this PreparedStatement object for reusing.

4. We will use PreparedStatement object in 4 situations
	1. for executing a query with dynamic values 
	   with simple syntax with place holders (?)
	2. for executing a query repeatedly without recompiling
	3. for inserting and retrieving images(BLOB) and text files(CLOB) 
	4. for avoiding SQL injection
	
5. PreparedStatement takes SQL query during its object creation
   because it must store precompiled SQL. Hence the factory method for 
   creating the PreparedStatement object is a String parameterized method
   for taking query as argument.
   
6. The Connection interface provides the factory method 
   'prepareStatement(String)' for creating PreparedStatement object 
   with precompiled SQL query in it
   
	public PreparedStatement prepareStatement(String sql) throws SQLE

7. PreparedStatement interface provides 2 sets of methods 
	1. setter methods 
	      - for setting values to the placeholdes 
	        available in precompiled sql statement

   		public void setXxx(int parameterIndex, xxx value)
		  - here xxx is data type
	   
	2. no-parameter overloaded execute() methods 
	      - for running precompiled sql statement

		1. public boolean	execute()
		2. public int		executeUpdate()
		3. public ResultSet     executeQuery()

8. Redevelop above program with PreparedStatement
	1. with JDBC 4.0 feature autoloading driver
	2. with Java 7v feature try-with-resources

#connection-info.properties
url=jdbc:oracle:thin:@localhost:1521:xe
usn=hknit11am2
pwd=hari

//Test10_PStmt_Insert_DynamicValues_ALD_TwR.java
package com.nit.hk.jdbc;

import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Scanner;

public class Test10_PStmt_Insert_DynamicValues_ALD_TwR {
	
	public static void main(String[] args) {

		try {
			//1. creating empty properties objects
			Properties props = new Properties();
			
			//2. loading properties from properties file into props object
			props.load(new FileReader("connection-info.properties"));
			
			//3. reading properties from props object and storing local variables
			String url		= props.getProperty("url");
			String usn		= props.getProperty("usn");
			String pwd		= props.getProperty("pwd");

			String insertQuery = 
					"""
					INSERT INTO course(course_id, course_name, course_fee) 
					VALUES(course_seq.nextval, ?, ?)
					""";		
			//JDBC objects creation with 'try-with-resouces'
			try(
					Connection con = DriverManager.getConnection(url, usn, pwd);
					PreparedStatement pstmt = con.prepareStatement(insertQuery);
			){
				Scanner scn = new Scanner(System.in);
				
				String option = "N";
				do {
					System.out.print("\nEnter course name: ");
					String courseName = scn.nextLine();
		
					System.out.print("Enter course fee : ");
					double courseFee = scn.nextDouble(); 
					
					//setting values to pstmt
					pstmt.setString(1, courseName);
					pstmt.setDouble(2, courseFee);
					
					//executing pstmt query
					int numberOfRows = pstmt.executeUpdate();
					System.out.println("\n "+ numberOfRows + " row inserted");
				
					System.out.print("\nDo you want to continue(Y/N)?: ");
					option = scn.next(); scn.nextLine();
					
				}while(option.equalsIgnoreCase("Y"));
				
				System.out.println("\n******* Thank you, Visit again *******");

			}//try-with-resource close
			catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
























	1. PreparedStatement is a sub interface of Statement interface 
	2. It is used for executing a precompiled sql statement with runtime values 
	3. While create PreparedStatement object creation we must submit sql statement, 
	   but not at the time of execute() method calling 
	4. For creating PreparedStatement object, in the Connection interface we
	   have a factory method called prepareStatement()

		public PreparedStement prepareStatement(String sql)
	   	
	5. By using PreparedStatement we can run both static sql statement and    
	   dynamic sql statement by giving values at runtime 

		- A static SQL query is a query, that doesn't take inputs  
		  or contains fixed value/inputs
			SELECT * FROM student;
			SELECT * FROM student WHERE sno=101;

		- A dynamic SQL query is a query that has placeholders(?) 
		  that takes inputs at runtime at the time of query execution.
		  
		  We can have multiple placeholders based on number of values 
		  we want read 

			SELECT * FROM student
			WHERE sno=?;


			SELECT * FROM student
			WHERE sno=?
			AND   sname='?'

			INSERT INTO student(sno, sname, course, fee)
			VALUES(?, ?, ?, ?);
	6. Below code shows creating a PreparedStatement object 
	   with a Precompiled dynamic sql statement to insert a record 
		
		String insertQuery = 
			"INSERT INTO student(sno, sname, course, fee)" +
			"VALUES(?, ?, ?, ?)";

		PreaparedStatement psmt = 
			con.prepareStatement(insertQuery);

	7. The PreparedStatement interface contains special methods for setting 
	   values to the placeholders available in the query in this 
	   PreparedStatement object and further to execute this query on DB

	   	1. setter methods
			public void setXxx(int placeHolderIndex, xxx value)
							  throws SQLException
		     for example
		        public void setInt(int placeHolderIndex, int value)
		        public void setDouble(int placeHolderIndex, double value)
		        public void setString(int placeHolderIndex, String value)
		        public void setDate(int placeHolderIndex, Date value)

		2. executor methods
			public boolean    execute() throws SQLException
			public int        executeUpdate() throws SQLException
			public ResultSet  executeQuery() throws SQLException
			
9. The steps to execute a precompiled query by using PS
	1. prepare a query with placeholders(for taking runtime value)
	2. create PreparedStatement object by passing this query
	3. set runtime values to the query available in the PS object 
	4. execute query and collect the results
	
	Sammple code						      1         2	
		String query = "SELECT * from student where course_id=? and fee<?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setInt(1, 3);
		pstmt.setDouble(2, 3500);
		
		ResultSet rs = pstmt.executeQuery();
		
10. Develop a program to insert records in student table
    by reading student detials from keyboard
    
    
    
		
		



