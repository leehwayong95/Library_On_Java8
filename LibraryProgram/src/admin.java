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
				view.showBookListmenu();
				view.bookList(con.searchBook());
				view.inputKeyboard("���͸� �����ø� �޴��� ���ư��ϴ�");
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
				String deletekeyWord;
				
				while(!memberdeleteflag)
				{
					deletekeyWord = view.searchMember();
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
								memberdeleteflag = this.cheackrentedBook(deletemember);
							}
							else
								memberdeleteflag = view.failDelete("����� ���");
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
								memberdeleteflag = this.cheackrentedBook(deletemember);
							}
							else
								memberdeleteflag = view.failDelete("����� ���");
						}
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 5://�ű� å ���
				int bookid;
				boolean registflag = false;
				bookid = view.confirmBookid();
				while(!registflag)
				{
					if(con.confirmBookid(bookid))
					{
						if(con.registBook(view.registBook(bookid)))
							registflag = view.successInsertbook();
						else
							registflag = view.failInsertbook();
					}
					else
					{
						bookid = view.wrongBookid();
					}
				}	
				break;
			case 6://å ����
				boolean booksearchflag = false;
				ResultSet booksearch;
				String bookSearchkeyword;
				while(!booksearchflag)
				{
					bookSearchkeyword = view.searchBook();
					if(bookSearchkeyword.equals(""))
						booksearch = con.searchBook();
					else
						booksearch = con.searchBook(bookSearchkeyword);
					try
					{
						booksearch.last();
						if(booksearch.getRow() > 0)
						{
							booksearch.beforeFirst();
							int deleteBookselect = view.resultBook(booksearch, "����");
							booksearch.beforeFirst();
							for(int i = 0 ; i < deleteBookselect ; i++)
								booksearch.next();
							if(booksearch.getInt("rented") > 0)
								booksearchflag = view.failDelete("å�� ��������� ������");
							else
							{
								if(view.confirmDeletebook(deleteBookselect,booksearch.getString("bookname")))
								{
									if(con.deleteBook(booksearch.getInt("bookid")))
										booksearchflag = view.successDelete();
									else
										booksearchflag = view.failDelete("SQL����");
								}
								else
									booksearchflag = view.failDelete("����� ���");							}
						}
						else
							bookSearchkeyword = view.wrongsearchBook();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 7://å �뿩 �ݳ�
				view.showRenthistory(con.renthistory());
				break;
			case 8://����
				flag = true;
				System.out.println(flag);
				con.close();
				break;
			default:
				view.consolePrint(1, "�޴��� �߸������ϼ̽��ϴ�. �ٽü������ּ���.");
			}
		}
	}
	
	boolean cheackrentedBook(ResultSet deletemember) throws SQLException
	{
		if(deletemember.getInt("count") == 0)
		{
			if(deletemember.getString("memberid").equals("admin"))
				return view.failDelete("������ ���� �����õ�");
			else if(con.deleteMember(deletemember.getString("memberid")))
				return view.successDelete();
			else
				return view.failDelete("DB����");
		}
		else
			return view.failDelete("���� ��� ����");
	}
}
