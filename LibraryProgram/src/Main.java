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
				boolean engnumflag = confirmInput(3, signupID,3,16);//����, ����, 3-16����
				boolean overlapflag = con.CheckID(signupID);
				while(!overlapflag || !engnumflag)
				{
					if(!engnumflag)
						signupID = view.wrongID();//���̵� �ߺ����� ���������� ID ��ӹޱ�
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
				String Name = view.signupName();//2~5����,�ѱ�
				boolean korflag = confirmInput(4,Name,2,5);
				while(!korflag)
				{
					Name = view.wrongsignupName();
					korflag = confirmInput(4,Name,2,5);
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
	static boolean confirmInput(int mode, String s, int min, int max) // �Է� ���� �˻� �޼���
    // mode : ���� �˻� 4bit�� ����Ʈ���� �ѱ�,����,���� �����
    // ����ϴ� �κ��� 1�� ä���ִ´�. ������� 0(�˻���������)
    // s�� �˻��ϴ� ���ڿ�.
    // min max�� ���ڿ� ��������.(0,0�Է½� ������)
	// max �� 0�̸� ������
    {
        String len;
        String option = (mode == 0) ? "." : "^[";
        if ((mode & 1) == 1)// 001
            option += "0-9";
        if ((mode & 2) == 2)// 010
            option += "a-zA-Z";
        if ((mode & 4) == 4)// 100
            option += "��-����-�Ӱ�-�R";
        option += (mode == 0) ? "" : "]";

        if (min == 0 && max == 0)
            len = "*$";
        else
            len = "{" + min + "," + ((max == 0)?"":max) + "}"+((mode == 0)?"":"$");

        return Pattern.matches(option + len, s);
    }
}
