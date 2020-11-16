package asd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Login extends JFrame implements ActionListener {
	
	//--------DB ���̺� ���� --------//
	String header[] = { "ID", "PW", "�̸�", "����", "�޴���", "�ּ�", "����" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	//--------DB ���̺� ���� --------//
	
	
	

	JButton btn_Login;// �α��� �ϱ� ��ư
	JButton btn_Join;// ȸ������ �ϱ� ��ư
	JButton btn_idSearch;// ID ã�� ��ư
	JButton btn_pwSearch;// PW ã�� ��ư
	JLabel lb_ID, lb_PW; // ID, PW �̸�
	JTextField tf_ID; // ID �ؽ�Ʈ �ʵ�
	JPasswordField pf_PW;// PW �н������ʵ�
	JPanel pnl_IP; // ID/PW �ǳ�

	
	
	// ----------------���� �������Ʈ-----------//
	BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

	FileReader fr = null;// ���ϰ�ü �о����
	FileWriter fw = null;// ���ϰ�ü ����

	String gr = "Members.txt";// �������

	String l;// ���� �о ���ڿ� ����

	// ----------------���� �������Ʈ-----------//

	
	
	public Login() {
		//--------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		//--------DB ���̺� ���� --------//
		
		
		
		lb_ID = new JLabel("ID");
		lb_PW = new JLabel("PW");
		tf_ID = new JTextField(10);
		pf_PW = new JPasswordField(10);
		btn_Login = new JButton("Login");
		btn_Join = new JButton("ȸ������");
		btn_idSearch = new JButton("���̵� ã��");
		btn_pwSearch = new JButton("�н����� ã��");

		pnl_IP = new JPanel();
		pnl_IP.setLayout(new FlowLayout());

		btn_Login.addActionListener(this);
		btn_Join.addActionListener(this);
		btn_idSearch.addActionListener(this);
		btn_pwSearch.addActionListener(this);

		pnl_IP.add(lb_ID);
		pnl_IP.add(tf_ID);
		pnl_IP.add(lb_PW);
		pnl_IP.add(pf_PW);
		pnl_IP.add(btn_Login);
		pnl_IP.add(btn_Join);
		pnl_IP.add(btn_idSearch);
		pnl_IP.add(btn_pwSearch);

		btn_Join.setBorderPainted(false);
		btn_idSearch.setBorderPainted(false);
		btn_pwSearch.setBorderPainted(false);
		btn_Join.setContentAreaFilled(false);
		btn_idSearch.setContentAreaFilled(false);
		btn_pwSearch.setContentAreaFilled(false);
		
		this.setTitle("�α���");
		this.setSize(400, 150);
		this.setDefaultCloseOperation(3);
		this.add(pnl_IP);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_Join) {
			new Join();
		}
		if(e.getSource()==btn_pwSearch){
			new pwSearch();
		}
		if(e.getSource()==btn_idSearch){
			new idSearch();
		}
		
		
		if (e.getSource() == btn_Login) {
			
			
			
			
			try {
				fr = new FileReader(gr);
				br = new BufferedReader(fr);// �о�� ���� ���ۿ� ��ü ���

				while ((l = br.readLine()) != null) {

					String[] str = l.split("/");


					Table_model.addRow(str);

				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					fr.close();
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
						
			int i = 0;
			String id = tf_ID.getText();
			String pw = pf_PW.getText();

			for (int k = 0; k < tb_Mem.getRowCount(); k++) {

				if (Table_model.getValueAt(k, 0).equals(id) && Table_model.getValueAt(k, 1).equals(pw)) {
					if(Table_model.getValueAt(k, 6).equals("true")){
						System.out.println("������� �����ڴ�");
						i = 1;
						Table_model.setRowCount(0);
						break;
					}
					else{
						System.out.println("���������� �α��� �Ǿ����ϴ�.");
						i = 1;
						Table_model.setRowCount(0);
						break;
					}
					
				}
			}
			if (i == 0) {
				for (int k = 0; k < tb_Mem.getRowCount(); k++) {
					if (Table_model.getValueAt(k, 0).equals(id)) {
						System.out.println("��й�ȣ�� Ʋ���ϴ�");
						i=3;
						Table_model.setRowCount(0);
						break;
					} else {
						i = 2;

					}

				}
			}
			
			if (i == 2) {
				System.out.println("�������� �ʴ� ���̵� �Դϴ�.");
				Table_model.setRowCount(0);
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Login();
	}

}
