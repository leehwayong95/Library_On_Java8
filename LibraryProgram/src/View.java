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
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t���� ���� ���α׷�\t\t*");
		this.consolePrint(1,"*****************************************");
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
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\tȸ�� \t�α���\t\t*");
		this.consolePrint(1,"*****************************************");
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
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\tȸ�� \t����\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "���̵�� ������, ���ڷ� �����Ǹ� 3~16���ڷ� �����˴ϴ�.");
		this.consolePrint(1, "��ҹ��ڴ� �������� �ʽ��ϴ�.");
		
		inputID = this.inputKeyboard("ID : ");
		return inputID;
	}
	
	public String wrongsignupID()//ȸ������ ID ���Է� �䱸
	{
		String inputID;
		this.consolePrint(1, "�ߺ��� ���̵� �Դϴ�. �ٸ� ���̵� �Է����ּ���");
		
		inputID = this.inputKeyboard("ID : ");
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
		inputName = this.inputKeyboard("�ѱ� �̸� �Է� : ");
		return inputName;
	}
	
	public String wrongsignupName()
	{
		this.consolePrint(1, "�߸� �Է��ϼ̽��ϴ�. �ٽ��Է����ּ���");
		return signupName();
	}
	
	
	public String adminPW()//������ ��� PW �Է�
	{
		String inputPW;
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t������ \t���\t\t*");
		this.consolePrint(1,"*****************************************");
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
	
	public int resultBook(ResultSet rs, String message)//�˻� ��� ǥ�� �� ���� row ��ȯ
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
			this.consolePrint(1, "�� "+number+"�� �˻��Ǿ����ϴ�.");
			this.consolePrint(1, message+"�� ���Ͻô� å�� �������ּ���.");
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
		this.consolePrint(1, "���� �Ϸ�. �ٸ� å�� ���������̽ʴϱ�?");
		return !this.inputYorN();
	}
	
	public boolean failRent()//���� ���� �� �޴� ���� ��ȯ
	{
		this.consolePrint(1,"������ �����ϴ�. �ٽ��ѹ� �˻��Ͻðڽ��ϱ�?");
		return !this.inputYorN();
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
			//e.printStackTrace();
			return -1;//���� ����
		}
	}
	
	public boolean continueReturn()//�ݳ� �Ϸ�
	{
		this.consolePrint(1, "�ݳ��Ϸ�. �߰��� �ݳ��Ͻðڽ��ϱ�?");
		return !this.inputYorN();
	}
	
	public boolean FailReturn()//�ݳ� ����
	{
		this.consolePrint(1, "�ݳ��� �����Ͽ����ϴ�. �ٽ� �õ��Ͻðڽ��ϱ�?");
		return !this.inputYorN();
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
			//e.printStackTrace();
			return false;
		}
	}
	
	public int adminMenu()//ADMIN �޴� ǥ��
	{
		int menuSelect;
		
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t�ȳ��ϼ��� \t�����ڴ�\t\t*");
		this.consolePrint(1,"*****************************************");
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
		int num = 0;
		this.consoleClear();
		try
		{
			num = this.showMember(memberList);
		}
		catch (SQLException e)
		{
			//e.printStackTrace();
		}
		this.consolePrint(1, "�� "+num+"���� ȸ���� �ֽ��ϴ�.");
		this.inputKeyboard("���͸� �����ø� �޴��� ���ư��ϴ�");
		
	}
	
	public void showBookListmenu()
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t������ å ��ü ����\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
	}

	public void bookList(ResultSet bookList)
	{
		int num = 0;
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| å ID\t\t| å �̸�\t\t|  �۰� \t\t| ���� \t| �뿩���� �Ǽ� \t|");
		try
		{
			while(bookList.next())
			{
				num = bookList.getInt("num");
				int id = bookList.getInt("bookid");
				String name = bookList.getString("bookname");
				String writer = bookList.getString("writer");
				int count = bookList.getInt("count");
				int rented = bookList.getInt("rented");
				//if(rented == )
					
					
				this.consolePrint(1, "| "+num +"\t| "+id+"\t\t| "+name+"\t| "+writer+"\t| "+count+"\t| "+rented+"\t|");
			}
			this.consolePrint(1, "=========================================================================");
			this.consolePrint(1, "");
			this.consolePrint(1, "�� "+num+"���� å�� �ֽ��ϴ�.");
		}
		catch(SQLException e)
		{
			//e.printStackTrace();
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
		this.consolePrint(1, "���� �Է½� ��ü�˻��� �մϴ�");
		
		return this.inputKeyboard("�˻��� �Է� : ");
	}
	
	public String wrongMember()
	{
		this.consolePrint(1, "�˻��� ȸ���� �����ϴ�. �ٽ� �Է� ���ּ���.");
		return this.inputKeyboard("�˻��� �Է� : ");
	}
	
	public boolean showSearchingmember(ResultSet memberList)
	{
		try
		{
			int num = this.showMember(memberList);
			this.consolePrint(1, "�� "+num+"���� ȸ���� �ֽ��ϴ�.");
			this.inputKeyboard("���͸� �����ø� �޴��� ���ư��ϴ�");
			return true;
		}
		catch (SQLException e)
		{
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteMember(ResultSet memberList) //����, �˻���� 1���� ��
	{
		int num;
		String ID;
		try {
			num = this.showMember(memberList);
			memberList.first();
			ID = memberList.getString("memberid");
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
		this.consolePrint(1, "�� "+num+"���� ȸ���� �ֽ��ϴ�.");
		this.consolePrint(1, "���õ� ȸ���� \""+ID+"\"�Դϴ�");
		this.consolePrint(1, "�� ȸ���� ���� �Ͻðڽ��ϱ�?");
		
		return this.inputYorN();
	}
	
	public int deleteMember(ResultSet memberList,int Index)//����, �˻���� �������� ��
	{
		int num;
		try {
			num = this.showMember(memberList);
		} catch (SQLException e) {
			//e.printStackTrace();
			return 0;
		}
		this.consolePrint(1, "�� "+num+"���� ȸ���� �ֽ��ϴ�.");
		this.consolePrint(1, "������ ���ϴ� ȸ���� ��ȣ�� �Է����ּ���");
			
		return this.inputKeyboard(num, "�Է� : ");
	}
	
	public boolean confirmDelete(String ID)
	{
		this.consolePrint(1, "���õ� ȸ���� \""+ID+"�Դϴ�.");
		this.consolePrint(1, "�� ȸ���� ���� �Ͻðڽ��ϱ�?");
		return this.inputYorN();
	}
	
	public boolean successDelete() // ȸ�� ���� ����
	{
		this.consolePrint(1, "�����Ǿ����ϴ�.");
		this.consolePrint(1, "�� �����Ͻðڽ��ϱ�?");
		return !this.inputYorN();
	}
	
	public boolean failDelete(String reason) // ȸ�� ���� ���� �޼���
	{
		this.consolePrint(1, reason+"�� ������ ���� �Ͽ����ϴ�.");
		this.consolePrint(1, "�ٽ� �õ� �Ͻðڽ��ϱ�?");
		return !this.inputYorN();
	}
	
	public int confirmBookid() //book id input
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t���� \t���\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		this.consolePrint(1, "");
		int id = this.inputKeyboard(9999, "����Ͻ� Book ID �Է�(1~9999) : ");
		return id;
	}
	
	public int wrongBookid() //book id �ߺ�
	{
		this.consolePrint(1, "�ߺ��� book id�Դϴ�. �ٽ��Է����ּ���");
		int id = this.inputKeyboard(9999, "����Ͻ� Book ID �Է�(1~9999) : ");
		return id;
	}

	public book registBook(int id) //���� �޴� �κ�
	{
		book registbook = new book();
		registbook.bookid = id;
		registbook.name = this.inputKeyboard("����� å ���� �Է� : ");
		registbook.writer = this.inputKeyboard("����� å ���� �Է� : ");
		registbook.count = this.inputKeyboard(100, "����� å ���� �Է� (1~100) : ");
		return registbook;
	}
	
	public boolean successInsertbook() //å ���� Insert ����
	{
		this.consolePrint(1, "������ ����ϴµ� �����Ͽ����ϴ�.");
		this.consolePrint(1, "�ٸ� å�� ��� �Ͻðڽ��ϱ�?");
		return !this.inputYorN();
	}
	
	public boolean failInsertbook() //å ���� Insert Fail
	{
		this.consolePrint(1, "������ ����ϴµ� �����Ͽ����ϴ�.");
		this.consolePrint(1, "�ٽ� �ѹ� �õ��Ͻðڽ��ϱ�?");
		return !this.inputYorN();
	}
	
	public boolean confirmDeletebook(int index,String bookname)
	{
		this.consolePrint(1, "���õ� å�� " + index + "��°��\"" + bookname +"\"�Դϴ�.");
		this.consolePrint(1,"������ �����Ͻðڽ��ϱ�?");
		return this.inputYorN();
	}
	
	public void showRenthistory(ResultSet rs)
	{
		this.consoleClear();
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"*\t\t���� \t���\t\t*");
		this.consolePrint(1,"*****************************************");
		this.consolePrint(1,"");
		this.consolePrint(1,"");
		this.consolePrint(1,"");
		this.consolePrint(1,"");
		this.consolePrint(1, "==============================================");
		try
		{
			while(rs.next())
			{
				String memberid = rs.getString("memberid");
				String membername = rs.getString("membername");
				String bookid = rs.getString("bookid");
				String bookname = rs.getString("bookname");
				String rentDate = rs.getString("date");
				String back = rs.getString("back");
				if(rs.wasNull())
					this.consolePrint(1, membername+"("+memberid +")�� "+rentDate+"�� \""+bookname+"\"("+bookid+")�� ������.");
				else
					this.consolePrint(1, membername+"("+memberid +")�� "+rentDate+"�� \""+bookname+"\"("+bookid+")�� ���Ȱ� " + back +"�� �ݳ���.");
			}
			this.consolePrint(1, "==============================================");
			this.consolePrint(1, "");
			this.inputKeyboard("���͸� ������ �޴��� ���ư��ϴ�");
		}
		catch(SQLException e)
		{
			//e.printStackTrace();
			this.consolePrint(1, "���� ���� ����� �����ϴ�");
			this.inputKeyboard("���͸� �����ø� �޴��� ���ư��ϴ�");
		}
	}
	
	/**********���Ǳ��***********/
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
		while(true)
		{
			try
			{
				if(0<Integer.parseInt(input) && Integer.parseInt(input) <= range)
					return Integer.parseInt(input);
				else
				{
					System.out.println("��ȣ�� �ùٸ��� �Է����ּ���.");
					return inputKeyboard(range, message);
				}
			}
			catch(Exception e)
			{
				System.out.println("��ȣ�� �ùٸ��� �Է����ּ���.");
				return inputKeyboard(range, message);
			}
		}
	}
	
	public String inputKeyboard (String message)//���ڿ� �Է� �޼��� (overrode)
	{
		String input = "";
		System.out.print(message);
		input = scan.nextLine();
		
		return input;
	}
	
	public boolean inputYorN()//Y/N�� �Է��ϴ� �޼���
	{
		while(true)
		{
			String input = this.inputKeyboard("�Է� (Y/N): ");
			if(input.equals("Y") || input.equals("y"))
				return true;
			else if(input.equals("N")||input.equals("n"))
				return false;
			else
				this.consolePrint(1, "�߸��Է��ϼ̽��ϴ� �ٽ��Է����ּ���");
		}
	}
	
	public int showMember(ResultSet memberList) throws SQLException //���� ��� ǥ�� �޼���
	{
		this.consolePrint(1, "=========================================================================");
		this.consolePrint(1, "| INDEX\t| ID\t\t| �̸�\t| PW\t| ������Ȳ\t|");
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
		return num;
	}
}
