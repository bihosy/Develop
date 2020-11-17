package asd;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class idSearch extends JFrame implements ActionListener {

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
	
	JLabel lb_Name; // �̸� ��
	JLabel lb_Phone; // �޴���ȭ ��
	JLabel lb_YMD; // �������(Year, Month, Day) ��
	JLabel lb_M; // �޴�����ȣ �۴��(010-0000-0000���� -)
	JLabel lb_M2; // �޴�����ȣ �۴��(010-0000-0000���� -)

	JTextField tf_Name; // ���̵��Է�
	JTextField tf_Phone2; // �޴�����ȣ �߰��ڸ�
	JTextField tf_Phone3; // �޴�����ȣ ���ڸ�

	JComboBox cb_Phone1; // �޴�����ȣ ���ڸ�(010, 011 ��)
	String cbData[] = {"010", "011", "012", "015", "016", "017", "018", "019"}; // �޴��� ���ڸ� �迭

	JButton btn_idS; // idã��

	public idSearch() {

		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB ���̺� ���� --------//
		
		lb_Name = new JLabel("�̸�");
		lb_Phone = new JLabel("�޴���ȭ");
		lb_YMD = new JLabel("�������");

		this.setTitle("���̵� ã��");
		this.setSize(350, 300);
		this.setLocation(800, 350);
		this.setLayout(null);

		tf_Name = new JTextField();
		cb_Phone1 = new JComboBox(cbData);
		tf_Phone2 = new JTextField();
		tf_Phone3 = new JTextField();
		lb_M = new JLabel("-");
		lb_M2 = new JLabel("-");

		btn_idS = new JButton("ã��");
		btn_idS.addActionListener(this);
		btn_idS.setBounds(35, 190, 275, 35);
		
		lb_Name.setBounds(60, 20, 30, 25);
		lb_Phone.setBounds(30, 70, 60, 25);
		lb_YMD.setBounds(30, 120, 60, 25);
		tf_Name.setBounds(120, 20, 190, 25);
		cb_Phone1.setBounds(120, 70, 50, 25);
		lb_M.setBounds(180, 70, 10, 25);
		tf_Phone2.setBounds(190, 70, 50, 25);
		lb_M2.setBounds(250, 70, 10, 25);
		tf_Phone3.setBounds(260, 70, 50, 25);
		
		
		lb_Name.setHorizontalAlignment(JLabel.RIGHT);
		lb_Phone.setHorizontalAlignment(JLabel.RIGHT);
		lb_YMD.setHorizontalAlignment(JLabel.RIGHT);
		
		this.add(lb_Name);
		this.add(lb_Phone);
		this.add(lb_YMD);
		this.add(tf_Name);
		this.add(cb_Phone1);
		this.add(tf_Phone2);
		this.add(tf_Phone3);
		this.add(btn_idS);
		this.add(lb_M);
		this.add(lb_M2);
		
		this.setVisible(true);
		this.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = 0;

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
			String name = tf_Name.getText();
			String phone = cb_Phone1.getSelectedItem() + "-" + tf_Phone2.getText() + "-" + tf_Phone3.getText();
			if (Table_model.getValueAt(k, 2).equals(name) && Table_model.getValueAt(k, 4).equals(phone)) {

				System.out.println(Table_model.getValueAt(k, 0));
				i = 1;
				Table_model.setRowCount(0);
				break;

			}
		}
		if(i==0){
			JOptionPane.showMessageDialog(null, "�ش� ������ ������ ������ �������� �ʽ��ϴ�.", "���� ã�� ����", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new idSearch();
	}

}
