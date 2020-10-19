import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class View{
	Scanner scan = new Scanner(System.in);

	public View()
	{
	}
	
	public int mainMenu()//���θ޴� ���� ǥ��
	{
		int input;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"**********�������� ****���α׷�**********");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1,"          �� 1. �α���");
		this.consolePrint(1,"          �� 2. ȸ������");
		this.consolePrint(1,"          �� 3. �����ڸ��");
		this.consolePrint(1,"          �� 4. ����");

		input = this.inputKeyboard(4, "�޴� ��ȣ �Է�  : ");
		
		this.consoleClear();
		return input;
	}
	
	public String signinID()//ȸ�� �α��� ǥ��
	{
		String inputID;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******ȸ�� �α���*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		
		inputID = this.inputKeyboard("ID : ");
		return inputID;
	}
	//phase 0 : ���θ޴�
	public String wrongID()//ID ���Է� �䱸
	{
		String inputID;
		this.consolePrint(1, "�߸��� ID�Դϴ�. �ٽ� �Է� ���ּ���.");
		
		inputID = this.inputKeyboard("ID : ");
		return inputID;
	}
	
	//phase 1 : �α��� �κ�
	public String signinPW()//PW �Է� �䱸
	{
		String inputPW;
		inputPW = this.inputKeyboard("PW : ");
		
		return inputPW;
	}
	
	public String wrongPW()//PW ���Է� �䱸
	{
		String inputPW;
		this.consolePrint(1, "�߸��� PW�Դϴ�. �ٽ� �Է� ���ּ���.");
		
		inputPW = this.inputKeyboard("PW : ");
		return inputPW;
	}
	
	public String signupID()//ȸ������ ID �Է�
	{
		String inputID;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******ȸ�� ����*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		
		inputID = this.inputKeyboard("��� ID : ");
		return inputID;
	}
	
	public String wrongsignupID()//ȸ������ ID ���Է� �䱸
	{
		String inputID;
		this.consolePrint(1, "�ߺ��� ���̵� �Դϴ�. �ٸ� ���̵� �Է����ּ���");
		
		inputID = this.inputKeyboard("��� ID : ");
		return inputID;
	}
	
	public String signupPW()//ȸ������ PW �Է�
	{
		String inputPW;
		inputPW = this.inputKeyboard("��� PW : ");
		String confirmPW = this.inputKeyboard("PW ��Ȯ�� : ");
		if(inputPW.equals(confirmPW))
		{
			return inputPW;
		}
		else
		{
			this.consolePrint(1, "��й�ȣ�� Ʋ���ϴ�. �ٽ� �Է� ���ּ���.");
			return this.signupPW();
		}
	}
	
	public String signupName()//ȸ������ �̸� �Է�
	{
		String inputName;
		inputName = this.inputKeyboard("�̸� �Է� : ");
		this.consoleClear();
		return inputName;
	}
	
	public String adminPW()//������ ��� PW �Է�
	{
		String inputPW;
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******������ ���*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		
		inputPW = this.inputKeyboard("������ PW : ");
		return inputPW;
	}
	
	public String wrongamdinPW()//������ ��� PW ���Է�
	{
		String inputPW;
		this.consolePrint(1, "�߸��� ������ ��й�ȣ�Դϴ�. �ٽ��Է����ּ���.");
		
		inputPW = this.inputKeyboard("������ PW : ");
		return inputPW;
	}
	
	//phase2 �α��� ���� ���
	public int memberMenu(String memberName)//USR �޴� ǥ��
	{
		int menuSelect;
		
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t�ȳ��ϼ��� "+memberName+"��\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1,"          �� 1. å �˻�/����");
		this.consolePrint(1,"          �� 2. å �ݳ�");
		this.consolePrint(1,"          �� 3. ȸ�� ����");
		this.consolePrint(1,"          �� 4. �ʱ� �޴�");
		this.consolePrint(1,"          �� 5. ����");
		
		menuSelect = this.inputKeyboard(5,"�޴� ��ȣ �Է� : ");
		return menuSelect; 
	}
	
	public String searchBook()//���� �˻� Ű���� �Է� �� ��ȯ
	{
		String keyWord = "";
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t����\t�˻�\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "å �˻�� �Է����ּ��� (���� �Է� �� å ��ü�� �˻��մϴ�.)");
		
		keyWord = this.inputKeyboard("�˻��� : ");
		return keyWord;
	}
	
	public String wrongsearchBook()//�˻���� ������ ���Է� ��ȯ
	{
		String keyWord = "";
		this.consolePrint(1, "�˻� ����� �����ϴ�. å �˻�� �ٽ� �Է����ּ���");
		this.consolePrint(1, "(���� �Է� �� å ��ü�� �˻��մϴ�.)");
		
		keyWord = this.inputKeyboard("�˻��� : ");
		return keyWord;
	}
	
	public int resultBook(ResultSet rs)//�˻� ��� ǥ�� �� ���� row ��ȯ
	{
		int number = 0;
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| ��ȣ\t| ID\t| å �̸� \t\t\t| �۰� \t\t| ���� \t|");
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
			this.consolePrint(1, "�� "+number+"�� �˻��Ǿ����ϴ�.");//�׳� rs���� ������ num �����ñ�...
			this.consolePrint(1, "�뿩�� ���Ͻô� å�� �������ּ���.");
			return this.inputKeyboard(number, "å ��ȣ �Է� : ");

		}
		catch(SQLException e)
		{
			this.consolePrint(1,"��ȸ ����");
			return -1;
		}
	}
	
	public boolean successRent()//���� �� �޴� ���� ��ȯ
	{
		int input;
		this.consolePrint(1, "���� �Ϸ�. �ٸ� å�� ���������̽ʴϱ�?");
		this.consolePrint(1, "1. �ٸ�å ������");
		this.consolePrint(1, "2. �׸� ������");
		
		input = this.inputKeyboard(2, "�Է� :");
		if(input == 1)
			return false;
		else
			return true;
	}
	
	public boolean failRent()//���� ���� �� �޴� ���� ��ȯ
	{
		int input;
		this.consolePrint(1,"������ �����ϴ�. �ٽ��ѹ� �˻��Ͻðڽ��ϱ�?");
		this.consolePrint(1, "1. �ٸ� å ������");
		this.consolePrint(1, "2. �׸� ������");
		
		input = this.inputKeyboard(2,"�Է� : ");
		if(input == 1)
			return false;
		else
			return true;
	}
	
	public int showRentedbook(String userid,ResultSet rs) //���� ��� ǥ��
	{
		int number = 0;
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t"+userid+"�� ���� ���� ��Ȳ\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| ��ȣ\t| å ��ȣ\t| å �̸� \t\t| �۰� \t\t| ���⳯�� \t\t| �ݳ���Ȳ |");
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
			this.consolePrint(1, "�� "+number+"�� �����̽��ϴ�.");
			this.consolePrint(1, "�ݳ��� ���Ͻô� å�� �������ּ���.");
			return this.inputKeyboard(number, "å ��ȣ �Է� : ");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return -1;//���� ����
		}
	}
	
	public boolean continueReturn()//�ݳ� �Ϸ�
	{
		this.consolePrint(1, "�ݳ��Ϸ�. �߰��� �ݳ��Ͻðڽ��ϱ�?");
		this.consolePrint(1, "1. ��� �ݳ��ϱ�");
		this.consolePrint(1, "2. ������");
		
		if(this.inputKeyboard(2, "�Է� : ") == 1)
			return false;
		else
			return true;
	}
	
	public boolean FailReturn()//�ݳ� ����
	{
		this.consolePrint(1, "�ݳ��� �����Ͽ����ϴ�. �ٽ� �õ��Ͻðڽ��ϱ�?");
		this.consolePrint(1, "1. �ٽ� �õ��ϱ�");
		this.consolePrint(1, "2. ������");
		
		if(this.inputKeyboard(2, "�Է� : ") == 1)
			return false;
		else
			return true;
	}
	
	public boolean nullList()//����å ���� �� 
	{
		this.consolePrint(1, "���� �� å�� �����ϴ�.");
		this.consolePrint(1, "���͸� ������ �޴��� �̵��մϴ�.");
		this.inputKeyboard("");
		return true;
	}
	
	public boolean userinfo(String username, ResultSet memberinfo, ResultSet rentinfo)//userinfo ǥ��, ������ ������ ��ǥ��
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t"+username+"���� ����\t\t*");
		this.consolePrint(1,"*****************************************");
		try
		{
			if(memberinfo.next())
			{
				this.consolePrint(1, "ID : "+memberinfo.getString("memberid"));
				this.consolePrint(1, "�̸� : " +memberinfo.getString("membername"));
			}
			rentinfo.last();
			this.consolePrint(1, "���� ���� �Ǽ� : "+rentinfo.getRow()+"��");
			if(rentinfo.getRow() > 0)
			{
				rentinfo.beforeFirst();
				this.consolePrint(1, "��������");
				this.consolePrint(1, "=========================================================================");
				this.consolePrint(1, "| ��ȣ\t| å ��ȣ\t| å �̸� \t\t| �۰� \t\t| ���⳯�� \t\t| �ݳ���Ȳ |");
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
			this.consolePrint(1, "���͸� �����ø� ����ȭ������ ���ư��ϴ�...");
			this.inputKeyboard("");
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public int adminMenu()//ADMIN �޴� ǥ��
	{
		int menuSelect;
		
		this.consoleClear();
		this.consolePrint(1,"***********************************");
		this.consolePrint(1,"*******�ȳ��ϼ��� �����ڴ�*****************");
		this.consolePrint(1,"***********************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1,"          �� 1. ȸ�� ����Ʈ");
		this.consolePrint(1,"          �� 2. å ����Ʈ");
		this.consolePrint(1,"          �� 3. ȸ�� �˻�");
		this.consolePrint(1,"          �� 4. ȸ�� ����");
		this.consolePrint(1,"          �� 5. �ű� å ���");
		this.consolePrint(1,"          �� 6. å ����");
		this.consolePrint(1,"          �� 7. å �뿩/�ݳ� ���");
		this.consolePrint(1,"          �� 8. �ʱ� �޴�");
		
		menuSelect = this.inputKeyboard(8,"�޴� ��ȣ �Է� : ");
		return menuSelect;
	}
	
	public void memberList(ResultSet memberList)
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\tȸ�� ��ü ����\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| ID\t\t| �̸�\t| PW\t| ������Ȳ\t|");
		
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
				
				this.consolePrint(1, "| "+num +"\t| "+id+"\t\t| "+name+"\t| "+pw+"\t| "+book+"��\t|");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "�� "+num+"���� ȸ���� �ֽ��ϴ�.");
			this.inputKeyboard("���͸� �����ø� �޴��� ���ư��ϴ�");
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
		this.consolePrint(1,"*\t\t������ å ��ü ����\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| å ID\t\t| å �̸�\t\t|  �۰� \t\t| ���� \t|");
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
			this.consolePrint(1, "�� "+num+"���� å�� �ֽ��ϴ�.");
			this.inputKeyboard("���͸� �����ø� �޴��� ���ư��ϴ�");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public String searchMember() //��� �˻� Ű���� ����
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\tȸ�� \t�˻�\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "ȸ���� ID�� �˻��մϴ�. ID�� �Է����ּ���.");
		
		return this.inputKeyboard("�˻��� �Է� : ");
	}
	
	public String wrongMember()
	{
		this.consolePrint(1, "�˻��� ȸ���� �����ϴ�. �ٽ� �Է� ���ּ���.");
		return this.inputKeyboard("�˻��� �Է� : ");
	}
	
	public boolean showSearchingmember(ResultSet memberList)
	{
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| ID\t\t| �̸�\t| PW\t| ������Ȳ\t|");
		
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
				
				this.consolePrint(1, "| "+num +"\t| "+id+"\t\t| "+name+"\t| "+pw+"\t| "+book+"��\t|");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "�� "+num+"���� ȸ���� �ֽ��ϴ�.");
			this.inputKeyboard("���͸� �����ø� �޴��� ���ư��ϴ�");
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//���Ǳ��
	public void consoleClear()
	{
		//�ܼ� â ���� Method
		for(int i = 0; i<255; i++)
			System.out.println();
	}
	
	public void consolePrint(int mode, String s)
	{
		//�ܼ� ��� �κ�
		//0 : ���๮�� ������
		//1 : ���๮�� ����
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
	
	public int inputKeyboard (int range, String message)//�޴� ���ÿ� �޼��� (overrode)
	{
		//�Է� �κ� ����
		String input;
		System.out.print(message);
		input = scan.nextLine();
		if(0<Integer.parseInt(input) && Integer.parseInt(input) <= range)
			return Integer.parseInt(input);
		else
		{
			System.out.println("�޴��� ��ȣ�� �ùٸ��� �������ּ���.");
			return inputKeyboard(range, message);
		}
	}
	
	public String inputKeyboard (String message)//���ڿ� �Է� �޼��� (overrode)
	{
		String input = "";
		System.out.print(message);
		input = scan.nextLine();
		
		return input;
	}
}
