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
				boolean engnumflag = confirmInput(3, signupID,3,16);//¿µ¾î, ¼ýÀÚ, 3-16±ÛÀÚ
				boolean overlapflag = con.CheckID(signupID);
				while(!overlapflag || !engnumflag)
				{
					if(!engnumflag)
						signupID = view.wrongID();//¾ÆÀÌµð°¡ Áßº¹µÇÁö ¾ÊÀ»¶§±îÁö ID °è¼Ó¹Þ±â
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
				String Name = view.signupName();//2~5±ÛÀÚ,ÇÑ±Û
				boolean korflag = confirmInput(4,Name,2,5);
				while(!korflag)
				{
					Name = view.wrongsignupName();
					korflag = confirmInput(4,Name,2,5);
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
	static boolean confirmInput(int mode, String s, int min, int max) // ÀÔ·Â ¹®ÀÚ °Ë»ç ¸Þ¼­µå
    // mode : Á¶°Ç °Ë»ç 4bit·Î °¢ºñÆ®¸¶´Ù ÇÑ±Û,¿µ¹®,¼ýÀÚ Çã¿ë¸ðµå
    // Çã¿ëÇÏ´Â ºÎºÐÀ» 1·Î Ã¤¿ö³Ö´Â´Ù. ¸ðµÎÇã¿ë½Ã 0(°Ë»çÇÏÁö¾ÊÀ½)
    // s°¡ °Ë»çÇÏ´Â ¹®ÀÚ¿­.
    // min max´Â ¹®ÀÚ¿­ ±æÀÌÁ¦ÇÑ.(0,0ÀÔ·Â½Ã ¹«Á¦ÇÑ)
	// max °¡ 0ÀÌ¸é ¹«Á¦ÇÑ
    {
        String len;
        String option = (mode == 0) ? "." : "^[";
        if ((mode & 1) == 1)// 001
            option += "0-9";
        if ((mode & 2) == 2)// 010
            option += "a-zA-Z";
        if ((mode & 4) == 4)// 100
            option += "¤¡-¤¾¤¿-¤Ó°¡-ÆR";
        option += (mode == 0) ? "" : "]";

        if (min == 0 && max == 0)
            len = "*$";
        else
            len = "{" + min + "," + ((max == 0)?"":max) + "}"+((mode == 0)?"":"$");

        return Pattern.matches(option + len, s);
    }
}
