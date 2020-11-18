package asd;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class pwSearch extends JFrame implements ActionListener {

	// --------DB ���̺� ���� --------//
	String header[] = { "ID", "PW", "�̸�", "����", "�޴���", "�ּ�", "����" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	// --------DB ���̺� ���� --------//

	// ----------------���� �������Ʈ-----------//
	BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

	FileReader fr = null;// ���ϰ�ü �о����
	FileWriter fw = null;// ���ϰ�ü ����

	String gr = "Members.txt";// �������

	String l;// ���� �о ���ڿ� ����

	// ----------------���� �������Ʈ-----------//
	
	JLabel lb_ID; // ���̵� ��
	JLabel lb_Name; // �̸� ��
	JLabel lb_Phone; // �޴���ȭ ��
	JLabel lb_YMD; // �������(Year, Month, Day) ��
	JLabel lb_M; // �޴�����ȣ �۴��(010-0000-0000���� -)
	JLabel lb_M2; // �޴�����ȣ �۴��(010-0000-0000���� -)

	JTextField tf_ID; // ���̵� �Է� �ؽ�Ʈ�ʵ�
	JTextField tf_Name; // �̸� �Է� �ؽ�Ʈ�ʵ�
	JTextField tf_Phone2; // �޴�����ȣ �߰��ڸ�
	JTextField tf_Phone3; // �޴�����ȣ ���ڸ�

	JComboBox cb_Phone1; // �޴�����ȣ ���ڸ�(010, 011 ��)
	String cbData[] = {"010", "011", "012", "015", "016", "017", "018", "019"}; // �޴��� ���ڸ� �迭

	JButton btn_idS; // idã��
	JButton btn_back; // �ڷΰ���

	public pwSearch() {

		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB ���̺� ���� --------//

		lb_ID = new JLabel("���̵�");
		lb_Name = new JLabel("�̸�");
		lb_Phone = new JLabel("�޴���ȭ");
		lb_YMD = new JLabel("�������");

		this.setTitle("��й�ȣ ã��");
		this.setSize(350, 350);
		this.setLocation(800, 350);
		this.setLayout(null);
		
		tf_ID = new JTextField();
		tf_Name = new JTextField();
		cb_Phone1 = new JComboBox(cbData);
		tf_Phone2 = new JTextField();
		tf_Phone3 = new JTextField();
		lb_M = new JLabel("-");
		lb_M2 = new JLabel("-");

		btn_idS = new JButton("ã��");
		btn_back = new JButton("�ڷΰ���");
		btn_idS.addActionListener(this);
		btn_back.addActionListener(this);
		btn_idS.setBounds(35, 250, 130, 35);
		btn_back.setBounds(180, 250, 130, 35);
		
		lb_ID.setBounds(50, 20, 50, 25);
		tf_ID.setBounds(120, 20, 190, 25);
		lb_Name.setBounds(60, 70, 30, 25);
		tf_Name.setBounds(120, 70, 190, 25);
		lb_Phone.setBounds(30, 120, 60, 25);
		lb_YMD.setBounds(30, 170, 60, 25);
		cb_Phone1.setBounds(120, 120, 50, 25);
		lb_M.setBounds(180, 120, 10, 25);
		tf_Phone2.setBounds(190, 120, 50, 25);
		lb_M2.setBounds(250, 120, 10, 25);
		tf_Phone3.setBounds(260, 120, 50, 25);
		
		
		lb_Name.setHorizontalAlignment(JLabel.RIGHT);
		lb_Phone.setHorizontalAlignment(JLabel.RIGHT);
		lb_YMD.setHorizontalAlignment(JLabel.RIGHT);
		
		this.add(lb_ID);
		this.add(tf_ID);
		this.add(lb_Name);
		this.add(lb_Phone);
		this.add(lb_YMD);
		this.add(tf_Name);
		this.add(cb_Phone1);
		this.add(tf_Phone2);
		this.add(tf_Phone3);
		this.add(btn_idS);
		this.add(btn_back);
		this.add(lb_M);
		this.add(lb_M2);

		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = 0;
		if(e.getSource() == btn_idS){
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
	
			for (int k = 0; k < tb_Mem.getRowCount(); k++) {
				String id = tf_ID.getText();
				String name = tf_Name.getText();
				String phone = cb_Phone1.getSelectedItem() + "-" + tf_Phone2.getText() + "-" + tf_Phone3.getText();
				
				if (Table_model.getValueAt(k, 0).equals(id)&&Table_model.getValueAt(k, 2).equals(name) && Table_model.getValueAt(k, 4).equals(phone)) {
	
					System.out.println(Table_model.getValueAt(k, 1));
					i = 1;
					pwChan.pwChanRow = k;
					this.setVisible(false);
					new pwChan();
					Table_model.setRowCount(0);
					break;
	
				}
			}
			if(i==0){
				JOptionPane.showMessageDialog(null, "�ش� ������ �������� �ʽ��ϴ�.", "��й�ȣ ã�� ����", JOptionPane.ERROR_MESSAGE);
			}
			this.setDefaultCloseOperation(3);
	
		}
		if(e.getSource() == btn_back){
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pwSearch();
	}

}
