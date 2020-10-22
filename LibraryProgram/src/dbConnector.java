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
	
	public dbConnector() //초기 연결 테스트 (생성자)
	{
		try
		{
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("패키지 찾기 실패");
		}
		try
		{
			con = DriverManager.getConnection(mariadbUrl, usrId, usrPw);
			//System.out.println("연결 성공");//Debug
			stmt = con.createStatement();
		}
		catch (Exception e)
		{
			System.out.println("연결 실패");
		}
	}
	
	//phase 1 DB 이용(회원 가입, 로그인)
	public boolean CheckID(String ID) //회원가입 ID 중복 확인을 위한 메서드
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
	
	public boolean signup(String ID, String PW, String NAME)//회원가입 DB 삽입 메서드
	{
		//System.out.println("ID : " + ID +", PW : "+PW+", NAME : "+NAME);//Debug
		String dbQuery = "INSERT member VALUES (\""+ID+"\",\""+PW+"\",\""+NAME+"\");";
		return Query(dbQuery);
	}
	
	public boolean checkPW(String ID ,String PW)//PW, ID 일치 확인 메서드
	{
		String dbQuery = "SELECT password FROM member WHERE memberid =\""+ID +"\" AND password =\""+PW+"\";";
		ResultSet result = SelectQuery(dbQuery);
		try {
			if(result.next())
			{
				//if((PW.equals(result.getString("password").toString())))
				//굳이 비교연산을해야하나 고민..
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
	public String findUsrname(String ID) //로그인한 ID에 따라 이름 인출
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
						"WHERE state = '대출' " + 
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
						"WHERE state = '대출' " + 
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
	
	public boolean rentBook (int index, String keyWord, String ID)//책 대출
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
				//이전 찾은 rs pointer를 옮겨서 파라메터로 받을까....
			if(rs.next())
				if(rs.getInt("count")<=0)
					return false; //수량없음

			selectBookid = rs.getInt("bookid");//해당 책 id 찾아오기
			String rentQuery = "INSERT INTO renthistory(memberid,bookid,state) VALUES(\""+ID+"\","+selectBookid+",\"대출\");";
			//수량 조건 검사 및 감소하는 쿼리
			if(Query(rentQuery))
			{
				Query("UPDATE book SET count = count - 1 WHERE bookid = "+selectBookid+";");
				return true;//대출 성공
			}
			else
			{
				System.out.println("here");
				return false;//대출 실패
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
					+ "WHERE r.state = \"대출\" AND r.memberid = \""+userid+"\";";
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
					+"FROM(SELECT @rownum := @rownum + 1 AS num, r.index, r.bookid FROM renthistory AS r JOIN book AS b ON b.bookid = r.bookid WHERE r.state = \"대출\" AND r.memberid = \""+userid+"\")AS re "
					+"WHERE re.num = "+selectBook+";";
			rs = this.SelectQuery(Query);
			if(rs.next())
				rentIndex = rs.getInt("index");
			String updateQuery = "UPDATE renthistory AS r SET state = \"반납\", back = CURRENT_TIMESTAMP WHERE r.index = "+rentIndex+";";
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
	
	public ResultSet memberList()// 멤버리스트와 대출현황 count의 left outer join
	{
		try
		{
			stmt.execute("SET @rownum = 0;");
			String Query = "SELECT @rownum := @rownum + 1 AS num, m.*,rent.count "
					+ "FROM member AS m "
					+ "LEFT OUTER JOIN (SELECT r.memberid, COUNT(*)AS count "
						+ "FROM renthistory AS r "
						+ "WHERE state = '대출' GROUP BY r.memberid)AS rent "
					+ "ON rent.memberid = m.memberid;";
			return this.SelectQuery(Query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet memberList(String id)//아이디로 검색시 where절 추가 OverLoad
	{
		try
		{
			stmt.execute("SET @rownum = 0;");
			String Query = "SELECT @rownum := @rownum + 1 AS num, m.*,rent.count "
					+ "FROM member AS m "
					+ "LEFT OUTER JOIN (SELECT r.memberid, COUNT(*)AS count "
						+ "FROM renthistory AS r "
						+ "WHERE state = '대출' GROUP BY r.memberid)AS rent "
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
	
	public boolean deleteMember(String ID)//멤버 삭제 명령
	{
			String Query = "DELETE FROM member WHERE memberid = \""+ID+"\";";
			return this.Query(Query);	
	}
	
	public ResultSet bookList() //북 전체 리스트 표츌
	{
		try
		{
			stmt.execute("SET @rownum=0");
			String Query = "SELECT @rownum := @rownum + 1 AS num, b.*,rent.rented " + 
					"FROM book AS b " + 
					"LEFT OUTER JOIN " + 
						"(SELECT r.bookid, COUNT(*)AS rented FROM renthistory AS r " + 
						"WHERE state = '대출' " + 
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
	/*******************편의기능******************/
	public boolean close()//연결 종료 메서드
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
	
	Boolean Query (String Query)//Select를 제외한 쿼리
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
	
	ResultSet SelectQuery(String Query)//결과 반환을 위한 Select쿼리
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
