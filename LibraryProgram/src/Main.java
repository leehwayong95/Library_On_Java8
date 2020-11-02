import java.util.regex.Pattern;

public class Main {
	public static void main(String argp[]) 
	{
		View view = new View();
		boolean flag = false;// Àç´ë·Î ¼±ÅÃÇß´ÂÁö È®ÀÎ Flag
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
				//·Î±×ÀÎ
				String signinID = view.signinID(); //ID¹ÝÈ¯, DB °Ë»çÇÊ¿ä
				while(con.CheckID(signinID))
					signinID = view.wrongID();
				String signinPW = view.signinPW(); //PW¹ÝÈ¯, DB °Ë»çÇÊ¿ä
				while(!con.checkPW(signinID, signinPW))
					signinPW = view.wrongPW();
				new usr(signinID);//phase 2 usr °´Ã¼ »ý¼º
				break;
			case 2:
				//È¸¿ø°¡ÀÔ
				String signupID = view.signupID();//Ã³À½ sign up id¹Þ±â
				String engnumfilter = "^[a-z|A-Z|0-9]{3,16}$";
				boolean engnumflag = Pattern.matches(engnumfilter, signupID);//¿µ¾î ¼ýÀÚÀÔ·Â °Ë»ç
				boolean overlapflag = con.CheckID(signupID);
				while(!overlapflag || !engnumflag)
				{
					if(!engnumflag)
						signupID = view.wrongID();//¾ÆÀÌµð°¡ Áßº¹µÇÁö ¾ÊÀ»¶§±îÁö ID °è¼Ó¹Þ±â
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
				boolean korflag = Pattern.matches("^[¤¡-¤¾¤¿-¤Ó°¡-ÆR]{2,5}$", Name);
				while(!korflag)
				{
					Name = view.wrongsignupName();
					korflag = Pattern.matches("^[¤¡-¤¾¤¿-¤Ó°¡-ÆR]{2,5}$", Name);
				}
				if(con.signup(signupID, PW, Name))//DB Insert
					view.consolePrint(1, "°¡ÀÔ ¿Ï·á");
				else {
					view.consolePrint(1, "°¡ÀÔ ½ÇÆÐ");
				}
				break;
			case 3:
				//°ü¸®ÀÚ ¸ðµå
				String adminPW = view.adminPW();
				while(!con.checkPW("admin", adminPW))
					adminPW = view.wrongamdinPW();
				new admin();
				break;
			case 4:
				//Á¾·á
				con.close();
				view.consoleClear();
				System.exit(0);
			default:
				view.consolePrint(1, "¸Þ´º¸¦ Àß¸ø¼±ÅÃÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã¼±ÅÃÇØÁÖ¼¼¿ä.");
			}
		}
	}
}
