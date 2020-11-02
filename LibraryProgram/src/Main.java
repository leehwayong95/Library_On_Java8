import java.util.regex.Pattern;

public class Main {
	public static void main(String argp[]) 
	{
		View view = new View();
		boolean flag = false;// 재대로 선택했는지 확인 Flag
		int menuSelect = 0;
		dbConnector con = new dbConnector();
		//phase 1
		while(!flag)
		{
			view.consoleClear();
			menuSelect = view.mainMenu();
			switch(menuSelect)
			{
			case 1:
				//로그인
				String signinID = view.signinID(); //ID반환, DB 검사필요
				while(con.CheckID(signinID))
					signinID = view.wrongID();
				String signinPW = view.signinPW(); //PW반환, DB 검사필요
				while(!con.checkPW(signinID, signinPW))
					signinPW = view.wrongPW();
				new usr(signinID);//phase 2 usr 객체 생성
				break;
			case 2:
				//회원가입
				String signupID = view.signupID();//처음 sign up id받기
				String engnumfilter = "^[a-z|A-Z|0-9]{3,16}$";
				boolean engnumflag = Pattern.matches(engnumfilter, signupID);//영어 숫자입력 검사
				boolean overlapflag = con.CheckID(signupID);
				while(!overlapflag || !engnumflag)
				{
					if(!engnumflag)
						signupID = view.wrongID();//아이디가 중복되지 않을때까지 ID 계속받기
					else
						signupID = view.wrongsignupID();
					overlapflag = con.CheckID(signupID);
					engnumflag = Pattern.matches(engnumfilter, signupID);
				}
				String PW = view.signupPW();
				engnumflag = Pattern.matches(engnumfilter, PW);
				while(!engnumflag)
				{
					PW = view.wrongsignupPW();
					engnumflag = Pattern.matches(engnumfilter, PW);
				}
				String Name = view.signupName();
				boolean korflag = Pattern.matches("^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,5}$", Name);
				while(!korflag)
				{
					Name = view.wrongsignupName();
					korflag = Pattern.matches("^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,5}$", Name);
				}
				if(con.signup(signupID, PW, Name))//DB Insert
					view.consolePrint(1, "가입 완료");
				else {
					view.consolePrint(1, "가입 실패");
				}
				break;
			case 3:
				//관리자 모드
				String adminPW = view.adminPW();
				while(!con.checkPW("admin", adminPW))
					adminPW = view.wrongamdinPW();
				new admin();
				break;
			case 4:
				//종료
				con.close();
				view.consoleClear();
				System.exit(0);
			default:
				view.consolePrint(1, "메뉴를 잘못선택하셨습니다. 다시선택해주세요.");
			}
		}
	}
}
