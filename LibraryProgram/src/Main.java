
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
				while(!con.CheckID(signupID))
					signupID = view.wrongsignupID();//���̵� �ߺ����� ���������� ID ��ӹޱ�
				if(con.signup(signupID, view.signupPW(), view.signupName()))//DB Insert
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
