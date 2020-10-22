import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConnector 
{
	String mariadbUrl = "jdbc:mariadb://localhost:3306/library";
	String usrId = "root";
	String usrPw = "1122";
	Connection con;
	java.sql.Statement stmt;
	
	public dbConnector() //�ʱ� ���� �׽�Ʈ (������)
	{
		try
		{
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("��Ű�� ã�� ����");
		}
		try
		{
			con = DriverManager.getConnection(mariadbUrl, usrId, usrPw);
			//System.out.println("���� ����");//Debug
			stmt = con.createStatement();
		}
		catch (Exception e)
		{
			System.out.println("���� ����");
		}
	}
	
	//phase 1 DB �̿�(ȸ�� ����, �α���)
	public boolean CheckID(String ID) //ȸ������ ID �ߺ� Ȯ���� ���� �޼���
	{
		String dbQuery = "Select memberid from member where memberid = \"" + ID + "\";";
		ResultSet result = SelectQuery(dbQuery);
		try {
			if(result.next())
				return false;
			else
				return true;
		} catch (SQLException e) {
			//e.printStackTrace();//Debug
			return false;
		}
	}
	
	public boolean signup(String ID, String PW, String NAME)//ȸ������ DB ���� �޼���
	{
		//System.out.println("ID : " + ID +", PW : "+PW+", NAME : "+NAME);//Debug
		String dbQuery = "INSERT member VALUES (\""+ID+"\",\""+PW+"\",\""+NAME+"\");";
		return Query(dbQuery);
	}
	
	public boolean checkPW(String ID ,String PW)//PW, ID ��ġ Ȯ�� �޼���
	{
		String dbQuery = "SELECT password FROM member WHERE memberid =\""+ID +"\" AND password =\""+PW+"\";";
		ResultSet result = SelectQuery(dbQuery);
		try {
			if(result.next())
			{
				//if((PW.equals(result.getString("password").toString())))
				//���� �񱳿������ؾ��ϳ� ���..
					return true;
				//else
					//return false;
			}
			else
				return false;
		}
		catch (SQLException e)
		{
			//e.printStackTrace();DEBUG
			return false;
		}
	}
	
	//phase 2
	public String findUsrname(String ID) //�α����� ID�� ���� �̸� ����
	{
		
		try {
			ResultSet rs = SelectQuery("SELECT membername FROM member WHERE memberid = \""+ID+"\";");
			if(rs.next())
				return rs.getString("membername").toString();
			else
				return null;
		}
		catch(SQLException e)
		{
			//e.printStackTrace();DEBUG
			return null;
		}
	}
	
	public ResultSet searchBook(String keyword)
	{
		try
		{
			stmt.execute("SET @rownum=0;");
			String Query = "SELECT @rownum := @rownum + 1 AS num, b.*,rent.rented " + 
					"FROM book AS b " + 
					"LEFT OUTER JOIN " + 
						"(SELECT r.bookid, COUNT(*)AS rented FROM renthistory AS r " + 
						"WHERE state = '����' " + 
						"GROUP BY r.bookid)AS rent " + 
					"ON rent.bookid = b.bookid " +
					"WHERE bookname LIKE \"%"+keyword+"%\"";
			ResultSet rs = this.SelectQuery(Query);
			//this.SelectQuery("SELECT @rownum:=@rownum+1 AS num,b.* FROM book b WHERE bookname LIKE \"%" + keyword + "%\";");
			return rs;
		}
		catch (SQLException e)
		{
			return null;
		}
	}
	
	public ResultSet searchBook()
	{
		try
		{
			stmt.execute("SET @rownum=0;");
			String Query = "SELECT @rownum := @rownum + 1 AS num, b.*,rent.rented " + 
					"FROM book AS b " + 
					"LEFT OUTER JOIN " + 
						"(SELECT r.bookid, COUNT(*)AS rented FROM renthistory AS r " + 
						"WHERE state = '����' " + 
						"GROUP BY r.bookid)AS rent " + 
					"ON rent.bookid = b.bookid ";
			ResultSet rs = this.SelectQuery(Query);
			//ResultSet rs = this.SelectQuery("SELECT @rownum:=@rownum+1 AS num,b.* FROM book b;");
			return rs;
		}
		catch (SQLException e)
		{
			//e.printStackTrace();DEBUG
			return null;
		}
	}
	
	public boolean rentBook (int index, String keyWord, String ID)//å ����
	{
		ResultSet rs;
		try
		{
			int selectBookid;
			stmt.execute("Set @rownum=0;");
			if(keyWord.equals(""))
				rs = this.SelectQuery("SELECT bookid,count FROM (SELECT @rownum := @rownum+1 AS num, b.* FROM book b)AS search WHERE num = " + index +";");
			else
				rs = this.SelectQuery("SELECT bookid,count FROM (SELECT @rownum:= @rownum+1 AS num, b.* FROM book b WHERE bookname LIKE \"%"+keyWord+"%\") AS search WHERE num = " + index +";");
				//���� ã�� rs pointer�� �Űܼ� �Ķ���ͷ� ������....
			if(rs.next())
				if(rs.getInt("count")<=0)
					return false; //��������

			selectBookid = rs.getInt("bookid");//�ش� å id ã�ƿ���
			String rentQuery = "INSERT INTO renthistory(memberid,bookid,state) VALUES(\""+ID+"\","+selectBookid+",\"����\");";
			//���� ���� �˻� �� �����ϴ� ����
			if(Query(rentQuery))
			{
				Query("UPDATE book SET count = count - 1 WHERE bookid = "+selectBookid+";");
				return true;//���� ����
			}
			else
			{
				System.out.println("here");
				return false;//���� ����
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace(); //DEBUG
			return false;
		}
	}
	
	public ResultSet rentedBook (String userid)
	{
		ResultSet rs;
		try
		{
			stmt.execute("SET @rownum=0");
			String Query = "SELECT @rownum := @rownum + 1 AS num, r.index,r.bookid, b.bookname, b.writer, r.date, r.state "
					+ "FROM renthistory AS r JOIN book AS b ON b.bookid = r.bookid "
					+ "WHERE r.state = \"����\" AND r.memberid = \""+userid+"\";";
			rs = this.SelectQuery(Query);
			return rs;
		}
		catch (SQLException e)
		{
			e.printStackTrace();//Debug
			return null;
		}
	}
	
	public boolean returnBook(String userid,int selectBook)
	{
		try
		{
			ResultSet rs;
			int rentIndex = 0;
			int bookid = 0;
			stmt.execute("SET @rownum=0");
			String Query = "SELECT re.index, re.bookid "
					+"FROM(SELECT @rownum := @rownum + 1 AS num, r.index, r.bookid FROM renthistory AS r JOIN book AS b ON b.bookid = r.bookid WHERE r.state = \"����\" AND r.memberid = \""+userid+"\")AS re "
					+"WHERE re.num = "+selectBook+";";
			rs = this.SelectQuery(Query);
			if(rs.next())
				rentIndex = rs.getInt("index");
			String updateQuery = "UPDATE renthistory AS r SET state = \"�ݳ�\", back = CURRENT_TIMESTAMP WHERE r.index = "+rentIndex+";";
			if(this.Query(updateQuery))
			{
				bookid = rs.getInt("bookid");
				String bookRestore = "UPDATE book AS b SET count = count + 1 WHERE bookid = "+bookid+";";
				if(this.Query(bookRestore))
					return true;
				else
					return false;
			}
			else
				return false;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet userInfo(String ID)
	{
		String Query = "SELECT * FROM member WHERE memberid = \""+ID+"\";";
		return this.SelectQuery(Query);
	}
	
	public ResultSet memberList()// �������Ʈ�� ������Ȳ count�� left outer join
	{
		try
		{
			stmt.execute("SET @rownum = 0;");
			String Query = "SELECT @rownum := @rownum + 1 AS num, m.*,rent.count "
					+ "FROM member AS m "
					+ "LEFT OUTER JOIN (SELECT r.memberid, COUNT(*)AS count "
						+ "FROM renthistory AS r "
						+ "WHERE state = '����' GROUP BY r.memberid)AS rent "
					+ "ON rent.memberid = m.memberid;";
			return this.SelectQuery(Query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet memberList(String id)//���̵�� �˻��� where�� �߰� OverLoad
	{
		try
		{
			stmt.execute("SET @rownum = 0;");
			String Query = "SELECT @rownum := @rownum + 1 AS num, m.*,rent.count "
					+ "FROM member AS m "
					+ "LEFT OUTER JOIN (SELECT r.memberid, COUNT(*)AS count "
						+ "FROM renthistory AS r "
						+ "WHERE state = '����' GROUP BY r.memberid)AS rent "
					+ "ON rent.memberid = m.memberid "
					+ "WHERE m.memberid LIKE \"%"+id+"%\";";
			return this.SelectQuery(Query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteMember(String ID)//��� ���� ���
	{
			String Query = "DELETE FROM member WHERE memberid = \""+ID+"\";";
			return this.Query(Query);	
	}
	
	public ResultSet bookList() //�� ��ü ����Ʈ ǥ��
	{
		try
		{
			stmt.execute("SET @rownum=0");
			String Query = "SELECT @rownum := @rownum + 1 AS num, b.*,rent.rented " + 
					"FROM book AS b " + 
					"LEFT OUTER JOIN " + 
						"(SELECT r.bookid, COUNT(*)AS rented FROM renthistory AS r " + 
						"WHERE state = '����' " + 
						"GROUP BY r.bookid)AS rent " + 
					"ON rent.bookid = b.bookid;";
			return this.SelectQuery(Query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean confirmBookid(int id)
	{
		try
		{
			String Query = "SELECT * FROM BOOK WHERE bookid =" + id+";";
			ResultSet rs = this.SelectQuery(Query);
			if(rs.next())
				return false;
			else
				return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean registBook(book b)
	{
		String Query = "INSERT INTO book VALUES ("+b.bookid+",\""+b.name+"\",\""+b.writer+"\","+b.count+");";
		return this.Query(Query);
	}
	
	public boolean deleteBook(int bookid)
	{
		String Query = "DELETE FROM book WHERE bookid = "+bookid+";";
		return this.Query(Query);
	}
	
	public ResultSet renthistory()
	{
		//String Query = "SELECT * FROM renthistory";
		String Query = "SELECT r.*,b.bookname,m.membername "
				+ "FROM renthistory AS r, book AS b, member AS m "
				+ "WHERE r.bookid = b.bookid AND r.memberid = m.memberid "
				+ "ORDER BY r.index";
		return this.SelectQuery(Query);
	}
	/*******************���Ǳ��******************/
	public boolean close()//���� ���� �޼���
	{
		try
		{
			con.close();
			stmt.close();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}
	
	Boolean Query (String Query)//Select�� ������ ����
	{
		try
		{
			stmt.execute(Query);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();//Debug
			return false;
		}
	}
	
	ResultSet SelectQuery(String Query)//��� ��ȯ�� ���� Select����
	{
		ResultSet result = null;
		try
		{
			result = stmt.executeQuery(Query);
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();//Debug
			return result;
		}
	}
}
