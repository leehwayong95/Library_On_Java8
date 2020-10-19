import java.sql.ResultSet;
import java.sql.SQLException;

public class admin {
	View view = new View();
	dbConnector con = new dbConnector();
	boolean flag = false;

	public admin()
	{
		while(!flag)
		{
			int SelectMenu = view.adminMenu();
			switch(SelectMenu)
			{
			case 1:
				view.memberList(con.memberList());
				break;
			case 2:
				view.bookList(con.bookList());
				break;
			case 3:
				ResultSet rs;
				boolean memberSearchflag = false;
				String keyWord = view.searchMember();
				while(!memberSearchflag)
				{
					try
					{
						rs = con.memberList(keyWord);
						rs.last();
						if(rs.getRow() > 0)
						{
							rs.beforeFirst();
							memberSearchflag = view.showSearchingmember(rs);
						}
						else
							keyWord = view.wrongMember();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				flag = true;
				con.close();
				view.consoleClear();
				break;
			default:
				view.consolePrint(1, "메뉴를 잘못선택하셨습니다. 다시선택해주세요.");
			}
		}
	}
}
