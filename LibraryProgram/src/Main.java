import java.util.regex.Pattern;

public class Main {
	public static void main(String argp[]) 
	{
		View view = new View();
		boolean flag = false;// ���� �����ߴ��� Ȯ�� Flag
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
				//�α���
				String signinID = view.signinID(); //ID��ȯ, DB �˻��ʿ�
				while(con.CheckID(signinID))
					signinID = view.wrongID();
				String signinPW = view.signinPW(); //PW��ȯ, DB �˻��ʿ�
				while(!con.checkPW(signinID, signinPW))
					signinPW = view.wrongPW();
				new usr(signinID);//phase 2 usr ��ü ����
				break;
			case 2:
				//ȸ������
				String signupID = view.signupID();//ó�� sign up id�ޱ�
				String engnumfilter = "^[a-z|A-Z|0-9]{3,16}$";
				boolean engnumflag = Pattern.matches(engnumfilter, signupID);//���� �����Է� �˻�
				boolean overlapflag = con.CheckID(signupID);
				while(!overlapflag || !engnumflag)
				{
					if(!engnumflag)
						signupID = view.wrongID();//���̵� �ߺ����� ���������� ID ��ӹޱ�
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
				boolean korflag = Pattern.matches("^[��-����-�Ӱ�-�R]{2,5}$", Name);
				while(!korflag)
				{
					Name = view.wrongsignupName();
					korflag = Pattern.matches("^[��-����-�Ӱ�-�R]{2,5}$", Name);
				}
				if(con.signup(signupID, PW, Name))//DB Insert
					view.consolePrint(1, "���� �Ϸ�");
				else {
					view.consolePrint(1, "���� ����");
				}
				break;
			case 3:
				//������ ���
				String adminPW = view.adminPW();
				while(!con.checkPW("admin", adminPW))
					adminPW = view.wrongamdinPW();
				new admin();
				break;
			case 4:
				//����
				con.close();
				view.consoleClear();
				System.exit(0);
			default:
				view.consolePrint(1, "�޴��� �߸������ϼ̽��ϴ�. �ٽü������ּ���.");
			}
		}
	}
}
