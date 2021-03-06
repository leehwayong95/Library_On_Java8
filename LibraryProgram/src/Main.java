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
				boolean engnumflag = confirmInput(3, signupID,3,16);//영어, 숫자, 3-16글자
				boolean overlapflag = con.CheckID(signupID);
				while(!overlapflag || !engnumflag)
				{
					if(!engnumflag)
						signupID = view.wrongID();//아이디가 중복되지 않을때까지 ID 계속받기
					else
						signupID = view.wrongsignupID();
					overlapflag = con.CheckID(signupID);
					engnumflag = confirmInput(3, signupID,3,16);
				}
				String PW = view.signupPW();
				boolean pwflag = confirmInput(0,PW,2,0);
				while(!pwflag)
				{
					PW = view.signupPW(1);
					pwflag = confirmInput(0,PW,2,0);
				}
				String Name = view.signupName();//2~5글자,한글
				boolean korflag = confirmInput(4,Name,2,5);
				while(!korflag)
				{
					Name = view.wrongsignupName();
					korflag = confirmInput(4,Name,2,5);
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
	static boolean confirmInput(int mode, String s, int min, int max) // 입력 문자 검사 메서드
    // mode : 조건 검사 4bit로 각비트마다 한글,영문,숫자 허용모드
    // 허용하는 부분을 1로 채워넣는다. 모두허용시 0(검사하지않음)
    // s가 검사하는 문자열.
    // min max는 문자열 길이제한.(0,0입력시 무제한)
	// max 가 0이면 무제한
    {
        String len;
        String option = (mode == 0) ? "." : "^[";
        if ((mode & 1) == 1)// 001
            option += "0-9";
        if ((mode & 2) == 2)// 010
            option += "a-zA-Z";
        if ((mode & 4) == 4)// 100
            option += "ㄱ-ㅎㅏ-ㅣ가-힣";
        option += (mode == 0) ? "" : "]";

        if (min == 0 && max == 0)
            len = "*$";
        else
            len = "{" + min + "," + ((max == 0)?"":max) + "}"+((mode == 0)?"":"$");

        return Pattern.matches(option + len, s);
    }
}
