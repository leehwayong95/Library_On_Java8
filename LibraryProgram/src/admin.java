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
				view.showBookListmenu();
				view.bookList(con.searchBook());
				view.inputKeyboard("엔터를 누르시면 메뉴로 돌아갑니다");
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
								memberdeleteflag = this.cheackrentedBook(deletemember);
							}
							else
								memberdeleteflag = view.failDelete("사용자 취소");
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
								memberdeleteflag = this.cheackrentedBook(deletemember);
							}
							else
								memberdeleteflag = view.failDelete("사용자 취소");
						}
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 5://신규 책 등록
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
			case 6://책 삭제
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
							int deleteBookselect = view.resultBook(booksearch, "삭제");
							booksearch.beforeFirst();
							for(int i = 0 ; i < deleteBookselect ; i++)
								booksearch.next();
							if(booksearch.getInt("rented") > 0)
								booksearchflag = view.failDelete("책을 빌린사람이 있으므");
							else
							{
								if(view.confirmDeletebook(deleteBookselect,booksearch.getString("bookname")))
								{
									if(con.deleteBook(booksearch.getInt("bookid")))
										booksearchflag = view.successDelete();
									else
										booksearchflag = view.failDelete("SQL오류");
								}
								else
									booksearchflag = view.failDelete("사용자 취소");							}
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
			case 7://책 대여 반납
				view.showRenthistory(con.renthistory());
				break;
			case 8://종료
				flag = true;
				System.out.println(flag);
				con.close();
				break;
			default:
				view.consolePrint(1, "메뉴를 잘못선택하셨습니다. 다시선택해주세요.");
			}
		}
	}
	
	boolean cheackrentedBook(ResultSet deletemember) throws SQLException
	{
		if(deletemember.getInt("count") == 0)
		{
			if(deletemember.getString("memberid").equals("admin"))
				return view.failDelete("관리자 계정 삭제시도");
			else if(con.deleteMember(deletemember.getString("memberid")))
				return view.successDelete();
			else
				return view.failDelete("DB오류");
		}
		else
			return view.failDelete("대출 기록 존재");
	}
}
