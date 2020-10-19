import java.sql.ResultSet;
import java.sql.SQLException;

public class usr {
	View view = new View();
	dbConnector con = new dbConnector();
	boolean flag = false;
	String usrname = "";
	public usr(String ID)
	{
		usrname = con.findUsrname(ID);
		while(!flag)
		{
			int SelectMenu = view.memberMenu(usrname);
			switch(SelectMenu)
			{
			case 1:
				String keyWord;
				ResultSet result;
				boolean searchflag = false;
				while(!searchflag)
				{
					keyWord = view.searchBook();
					if(keyWord.equals(""))
						result = con.searchBook();//å ��ü �˻� overload
					else
						result = con.searchBook(keyWord);//Ű���� �˻� overload	
					try 
					{
						result.last();// Row ����� ����
						if(result.getRow() > 0)
						{
							result.beforeFirst();//row ����� ���� �����͸� ó���� ���� �ű�
							if(con.rentBook(view.resultBook(result),keyWord,ID))
							{
								if(view.successRent())
									searchflag = true;
								else
									searchflag = false;
							}
							else//�������
							{
								if(view.failRent())
									searchflag = true;
								else
									searchflag = false;
							}
						}
						else
							keyWord = view.wrongsearchBook();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
				break;
			case 2:
				boolean returnFlag = false;
				while(!returnFlag)
				{
					ResultSet rs = con.rentedBook(ID);
					try
					{
						rs.last();
						if(rs.getRow() > 0)
						{
							rs.beforeFirst();
							int returnBook = view.showRentedbook(usrname, rs);
							if(con.returnBook(ID,returnBook))
								returnFlag = view.continueReturn();
							else
								returnFlag = view.FailReturn();
						}
						else
						{
							returnFlag = view.nullList();
						}
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 3:
				view.userinfo(usrname, con.userInfo(ID), con.rentedBook(ID));
				break;
			case 4:
				flag = true;
				break;
			case 5:
				con.close();
				view.consoleClear();
				System.exit(0);
			default:
				view.consolePrint(1, "�޴��� �߸������ϼ̽��ϴ�. �ٽü������ּ���.");
			}
		}
	}
}