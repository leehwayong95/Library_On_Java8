import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class View{
	Scanner scan = new Scanner(System.in);

	public View()
	{
	}
	
	public int mainMenu()//메인메뉴 선택 표출
	{
		int input;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"**********도서관리 ****프로그램**********");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1,"          ▶ 1. 로그인");
		this.consolePrint(1,"          ▶ 2. 회원가입");
		this.consolePrint(1,"          ▶ 3. 관리자모드");
		this.consolePrint(1,"          ▶ 4. 종료");

		input = this.inputKeyboard(4, "메뉴 번호 입력  : ");
		
		this.consoleClear();
		return input;
	}
	
	public String signinID()//회원 로그인 표출
	{
		String inputID;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******회원 로그인*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		
		inputID = this.inputKeyboard("ID : ");
		return inputID;
	}
	//phase 0 : 메인메뉴
	public String wrongID()//ID 재입력 요구
	{
		String inputID;
		this.consolePrint(1, "잘못된 ID입니다. 다시 입력 해주세요.");
		
		inputID = this.inputKeyboard("ID : ");
		return inputID;
	}
	
	//phase 1 : 로그인 부분
	public String signinPW()//PW 입력 요구
	{
		String inputPW;
		inputPW = this.inputKeyboard("PW : ");
		
		return inputPW;
	}
	
	public String wrongPW()//PW 재입력 요구
	{
		String inputPW;
		this.consolePrint(1, "잘못된 PW입니다. 다시 입력 해주세요.");
		
		inputPW = this.inputKeyboard("PW : ");
		return inputPW;
	}
	
	public String signupID()//회원가입 ID 입력
	{
		String inputID;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******회원 가입*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		
		inputID = this.inputKeyboard("희망 ID : ");
		return inputID;
	}
	
	public String wrongsignupID()//회원가입 ID 재입력 요구
	{
		String inputID;
		this.consolePrint(1, "중복된 아이디 입니다. 다른 아이디를 입력해주세요");
		
		inputID = this.inputKeyboard("희망 ID : ");
		return inputID;
	}
	
	public String signupPW()//회원가입 PW 입력
	{
		String inputPW;
		inputPW = this.inputKeyboard("희망 PW : ");
		String confirmPW = this.inputKeyboard("PW 재확인 : ");
		if(inputPW.equals(confirmPW))
		{
			return inputPW;
		}
		else
		{
			this.consolePrint(1, "비밀번호가 틀립니다. 다시 입력 해주세요.");
			return this.signupPW();
		}
	}
	
	public String signupName()//회원가입 이름 입력
	{
		String inputName;
		inputName = this.inputKeyboard("이름 입력 : ");
		this.consoleClear();
		return inputName;
	}
	
	public String adminPW()//관리자 모드 PW 입력
	{
		String inputPW;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******관리자 모드*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		
		inputPW = this.inputKeyboard("관리자 PW : ");
		return inputPW;
	}
	
	public String wrongamdinPW()//관리자 모드 PW 재입력
	{
		String inputPW;
		this.consolePrint(1, "잘못된 관리자 비밀번호입니다. 다시입력해주세요.");
		
		inputPW = this.inputKeyboard("관리자 PW : ");
		return inputPW;
	}
	
	//phase2 로그인 이후 기능
	public int memberMenu(String memberName)//USR 메뉴 표출
	{
		int menuSelect;
		
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t안녕하세요 "+memberName+"님\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1,"          ▶ 1. 책 검색/대출");
		this.consolePrint(1,"          ▶ 2. 책 반납");
		this.consolePrint(1,"          ▶ 3. 회원 정보");
		this.consolePrint(1,"          ▶ 4. 초기 메뉴");
		this.consolePrint(1,"          ▶ 5. 종료");
		
		menuSelect = this.inputKeyboard(5,"메뉴 번호 입력 : ");
		return menuSelect; 
	}
	
	public String searchBook()//도서 검색 키워드 입력 및 반환
	{
		String keyWord = "";
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t도서\t검색\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "책 검색어를 입력해주세요 (공백 입력 시 책 전체를 검색합니다.)");
		
		keyWord = this.inputKeyboard("검색어 : ");
		return keyWord;
	}
	
	public String wrongsearchBook()//검색결과 없을시 재입력 반환
	{
		String keyWord = "";
		this.consolePrint(1, "검색 결과가 없습니다. 책 검색어를 다시 입력해주세요");
		this.consolePrint(1, "(공백 입력 시 책 전체를 검색합니다.)");
		
		keyWord = this.inputKeyboard("검색어 : ");
		return keyWord;
	}
	
	public int resultBook(ResultSet rs)//검색 결과 표출 및 선택 row 반환
	{
		int number = 0;
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| 번호\t| ID\t| 책 이름 \t\t\t| 작가 \t\t| 수량 \t|");
		try
		{	
			while(rs.next())
			{
				number = rs.getInt("num");
				String bookid = rs.getString("bookid");
				String bookname = rs.getString("bookname");
				String writer = rs.getString("writer");
				String count = rs.getString("count");
				this.consolePrint(1, "| "+ number +"\t| "+bookid+"\t|"+bookname+"\t\t|"+writer+"\t|"+count+"\t|");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "");
			this.consolePrint(1, "");
			this.consolePrint(1, "");
			this.consolePrint(1, "총 "+number+"권 검색되었습니다.");//그냥 rs에서 마지막 num 가져올까...
			this.consolePrint(1, "대여를 원하시는 책을 선택해주세요.");
			return this.inputKeyboard(number, "책 번호 입력 : ");

		}
		catch(SQLException e)
		{
			this.consolePrint(1,"조회 실패");
			return -1;
		}
	}
	
	public boolean successRent()//대출 후 메뉴 선택 반환
	{
		int input;
		this.consolePrint(1, "대출 완료. 다른 책도 빌릴예정이십니까?");
		this.consolePrint(1, "1. 다른책 빌리기");
		this.consolePrint(1, "2. 그만 빌리기");
		
		input = this.inputKeyboard(2, "입력 :");
		if(input == 1)
			return false;
		else
			return true;
	}
	
	public boolean failRent()//대출 실패 후 메뉴 선택 반환
	{
		int input;
		this.consolePrint(1,"수량이 없습니다. 다시한번 검색하시겠습니까?");
		this.consolePrint(1, "1. 다른 책 빌리기");
		this.consolePrint(1, "2. 그만 빌리기");
		
		input = this.inputKeyboard(2,"입력 : ");
		if(input == 1)
			return false;
		else
			return true;
	}
	
	public int showRentedbook(String userid,ResultSet rs) //대출 목록 표출
	{
		int number = 0;
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t"+userid+"님 도서 대출 현황\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| 번호\t| 책 번호\t| 책 이름 \t\t| 작가 \t\t| 대출날자 \t\t| 반납현황 |");
		try
		{
			while(rs.next())
			{
				number = rs.getInt("num");
				String bookid = rs.getString("bookid");
				String bookname = rs.getString("bookname");
				String writer = rs.getString("writer");
				String date = rs.getString("date");
				String state = rs.getString("state");
				this.consolePrint(1, "| "+ number +"\t| "+bookid+"\t|"+bookname+"\t|"+writer+"\t|"+date+"\t| "+state+" |");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "");
			this.consolePrint(1, "");
			this.consolePrint(1, "");
			this.consolePrint(1, "총 "+number+"권 빌리셨습니다.");
			this.consolePrint(1, "반납을 원하시는 책을 선택해주세요.");
			return this.inputKeyboard(number, "책 번호 입력 : ");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return -1;//인출 실패
		}
	}
	
	public boolean continueReturn()//반납 완료
	{
		this.consolePrint(1, "반납완료. 추가로 반납하시겠습니까?");
		this.consolePrint(1, "1. 계속 반납하기");
		this.consolePrint(1, "2. 나가기");
		
		if(this.inputKeyboard(2, "입력 : ") == 1)
			return false;
		else
			return true;
	}
	
	public boolean FailReturn()//반납 실패
	{
		this.consolePrint(1, "반납에 실패하였습니다. 다시 시도하시겠습니까?");
		this.consolePrint(1, "1. 다시 시도하기");
		this.consolePrint(1, "2. 나가기");
		
		if(this.inputKeyboard(2, "입력 : ") == 1)
			return false;
		else
			return true;
	}
	
	public boolean nullList()//대출책 없을 때 
	{
		this.consolePrint(1, "대출 한 책이 없습니다.");
		this.consolePrint(1, "엔터를 누르면 메뉴로 이동합니다.");
		this.inputKeyboard("");
		return true;
	}
	
	public boolean userinfo(String username, ResultSet memberinfo, ResultSet rentinfo)//userinfo 표출, 대출기록 없으면 안표출
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t"+username+"님의 정보\t\t*");
		this.consolePrint(1,"*****************************************");
		try
		{
			if(memberinfo.next())
			{
				this.consolePrint(1, "ID : "+memberinfo.getString("memberid"));
				this.consolePrint(1, "이름 : " +memberinfo.getString("membername"));
			}
			rentinfo.last();
			this.consolePrint(1, "대출 중인 권수 : "+rentinfo.getRow()+"권");
			if(rentinfo.getRow() > 0)
			{
				rentinfo.beforeFirst();
				this.consolePrint(1, "대출정보");
				this.consolePrint(1, "=========================================================================");
				this.consolePrint(1, "| 번호\t| 책 번호\t| 책 이름 \t\t| 작가 \t\t| 대출날자 \t\t| 반납현황 |");
				while(rentinfo.next())
				{
					int number = rentinfo.getInt("num");
					String bookid = rentinfo.getString("bookid");
					String bookname = rentinfo.getString("bookname");
					String writer = rentinfo.getString("writer");
					String date = rentinfo.getString("date");
					String state = rentinfo.getString("state");
					this.consolePrint(1, "| "+ number +"\t| "+bookid+"\t|"+bookname+"\t|"+writer+"\t|"+date+"\t| "+state+" |");
				}
				this.consolePrint(1, "=========================================================================");
			}
			this.consolePrint(1, "");
			this.consolePrint(1, "");
			this.consolePrint(1, "엔터를 누르시면 이전화면으로 돌아갑니다...");
			this.inputKeyboard("");
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public int adminMenu()//ADMIN 메뉴 표출
	{
		int menuSelect;
		
		this.consoleClear();
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******안녕하세요 관리자님*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1,"          ▶ 1. 회원 리스트");
		this.consolePrint(1,"          ▶ 2. 책 리스트");
		this.consolePrint(1,"          ▶ 3. 회원 검색");
		this.consolePrint(1,"          ▶ 4. 회원 삭제");
		this.consolePrint(1,"          ▶ 5. 신규 책 등록");
		this.consolePrint(1,"          ▶ 6. 책 삭제");
		this.consolePrint(1,"          ▶ 7. 책 대여/반납 기록");
		this.consolePrint(1,"          ▶ 8. 초기 메뉴");
		
		menuSelect = this.inputKeyboard(8,"메뉴 번호 입력 : ");
		return menuSelect;
	}
	
	public void memberList(ResultSet memberList)
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t회원 전체 정보\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| ID\t\t| 이름\t| PW\t| 대출현황\t|");
		
		try
		{
			int num = 0;
			while(memberList.next())
			{
				num = memberList.getInt("num");
				String id = memberList.getString("memberid");
				String name = memberList.getString("membername");
				String pw = memberList.getString("password");
				int book = memberList.getInt("count");
				
				this.consolePrint(1, "| "+num +"\t| "+id+"\t\t| "+name+"\t| "+pw+"\t| "+book+"권\t|");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "총 "+num+"명의 회원이 있습니다.");
			this.inputKeyboard("엔터를 누르시면 메뉴로 돌아갑니다");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void bookList(ResultSet bookList)
	{
		int num=0;
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t도서관 책 전체 정보\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| 책 ID\t\t| 책 이름\t\t|  작가 \t\t| 수량 \t|");
		try
		{
			while(bookList.next())
			{
				num = bookList.getInt("num");
				int id = bookList.getInt("bookid");
				String name = bookList.getString("bookname");
				String writer = bookList.getString("writer");
				int count = bookList.getInt("count");
				this.consolePrint(1, "| "+num +"\t| "+id+"\t\t| "+name+"\t| "+writer+"\t| "+count+"\t|");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "총 "+num+"권의 책이 있습니다.");
			this.inputKeyboard("엔터를 누르시면 메뉴로 돌아갑니다");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public String searchMember() //멤버 검색 키워드 전달
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t회원 \t검색\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "회원의 ID를 검색합니다. ID를 입력해주세요.");
		
		return this.inputKeyboard("검색어 입력 : ");
	}
	
	public String wrongMember()
	{
		this.consolePrint(1, "검색된 회원이 없습니다. 다시 입력 해주세요.");
		return this.inputKeyboard("검색어 입력 : ");
	}
	
	public boolean showSearchingmember(ResultSet memberList)
	{
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| ID\t\t| 이름\t| PW\t| 대출현황\t|");
		
		try
		{
			int num = 0;
			while(memberList.next())
			{
				num = memberList.getInt("num");
				String id = memberList.getString("memberid");
				String name = memberList.getString("membername");
				String pw = memberList.getString("password");
				int book = memberList.getInt("count");
				
				this.consolePrint(1, "| "+num +"\t| "+id+"\t\t| "+name+"\t| "+pw+"\t| "+book+"권\t|");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "총 "+num+"명의 회원이 있습니다.");
			this.inputKeyboard("엔터를 누르시면 메뉴로 돌아갑니다");
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//편의기능
	public void consoleClear()
	{
		//콘솔 창 정리 Method
		for(int i = 0; i<255; i++)
			System.out.println();
	}
	
	public void consolePrint(int mode, String s)
	{
		//콘솔 출력 부분
		//0 : 개행문자 미포함
		//1 : 개행문자 포함
		switch(mode)
		{
		case 0:
			System.out.print(s);
			break;
		case 1:
			System.out.println(s);
			break;
		}
	}
	
	public int inputKeyboard (int range, String message)//메뉴 선택용 메서드 (overrode)
	{
		//입력 부분 정리
		String input;
		System.out.print(message);
		input = scan.nextLine();
		if(0<Integer.parseInt(input) && Integer.parseInt(input) <= range)
			return Integer.parseInt(input);
		else
		{
			System.out.println("메뉴의 번호를 올바르게 선택해주세요.");
			return inputKeyboard(range, message);
		}
	}
	
	public String inputKeyboard (String message)//문자열 입력 메서드 (overrode)
	{
		String input = "";
		System.out.print(message);
		input = scan.nextLine();
		
		return input;
	}
}
