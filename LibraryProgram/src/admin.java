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
			case 1://ȸ�� ����Ʈ
				view.memberList(con.memberList());
				break;
			case 2://å ����Ʈ
				view.bookList(con.bookList());
				break;
			case 3://ȸ�� �˻�
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
			case 4://ȸ�� ����
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
							//�߸��� �˻���
							deletekeyWord = view.wrongMember();
							memberdeleteflag = false;
						}
						else if(row == 1)
						{
							//1���� ��
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
							//�������� ��
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
				view.consolePrint(1, "�޴��� �߸������ϼ̽��ϴ�. �ٽü������ּ���.");
			}
		}
	}
}
