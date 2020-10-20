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
			case 1://회원 리스트
				view.memberList(con.memberList());
				break;
			case 2://책 리스트
				view.bookList(con.bookList());
				break;
			case 3://회원 검색
				ResultSet rs;
				boolean memberSearchflag = false;
				String keyWord = view.searchMember();
				while(!memberSearchflag)
				{
					try
					{
						if (keyWord.equals(""))
							rs = con.memberList();
						else
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
			case 4://회원 삭제
				ResultSet deletemember;
				boolean memberdeleteflag = false;
				String deletekeyWord = view.searchMember();
				
				while(!memberdeleteflag)
				{
					if(deletekeyWord.equals(""))
						deletemember = con.memberList();
					else
						deletemember = con.memberList(deletekeyWord);
					try
					{
						deletemember.last();
						int row = deletemember.getRow();
						if(row == 0)
						{
							//잘못된 검색어
							deletekeyWord = view.wrongMember();
							memberdeleteflag = false;
						}
						else if(row == 1)
						{
							//1명일 때
							deletemember.beforeFirst();
							if(view.deleteMember(deletemember))
							{
								deletemember.first();
								if(con.deleteMember(deletemember.getString("memberid")))
									view.successDelete();
								else
									view.failDelete();
							}
							else
								view.failDelete();
							memberdeleteflag = true;
						}
						else
						{
							//여러명일 때
							deletemember.beforeFirst();
							int deletememberSelect = view.deleteMember(deletemember, row);
							deletemember.beforeFirst();
							for(int i = 0 ; i < deletememberSelect ; i++)
							{
								deletemember.next();
							}
							if(view.confirmDelete(deletemember.getString("memberid")))
							{
								if(con.deleteMember(deletemember.getString("memberid")))
									view.successDelete();
								else
									view.failDelete();
							}
							else
								view.failDelete();
							memberdeleteflag = true;
						}
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
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
