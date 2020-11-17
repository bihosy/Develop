package asd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.acl.Group;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Join extends JFrame implements ActionListener, KeyListener {
	//--------DB ���̺� ���� --------//
	String header[] = { "ID", "PW", "�̸�", "����", "�޴���", "�ּ�", "����" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	//--------DB ���̺� ���� --------//

	JLabel lb_ID; // ���̵� ��
	JLabel lb_PW; // �н����� ��
	JLabel lb_PWC; // �н����� Ȯ��(password Check) ��
	JLabel lb_Name; // �̸� ��
	JLabel lb_Gd; //���� ��(Gender)
	JLabel lb_YMD; // ������� ��(Year, Month, Day)
	JLabel lb_M; // �޴�����ȣ �۴��(010-0000-0000���� -)
	JLabel lb_M2; // �޴�����ȣ �۴��(010-0000-0000���� -)
	JLabel lb_Phone; // �޴�����ȣ ��
	JLabel lb_Add; // �ּҶ�(Address)
	JLabel lb_JB; // �ߺ���

	JTextField tf_ID; // ���̵� �ؽ�Ʈ�ʵ�
	JPasswordField pf_PW; // �н������ʵ�
	JPasswordField pf_PWC; // �н����� Ȯ��(password Check) �ʵ�
	JTextField tf_Name; // �̸� �ؽ�Ʈ�ʵ�
	JTextField tf_Phone1; // �޴��� ���ڸ�(010)
	JTextField tf_Phone2; // �޴��� �߰��ڸ�
	JTextField tf_Phone3; // �޴��� ���ڸ�
	JTextField tf_Add1; // �ּ� �ؽ�Ʈ�ʵ�1
	JTextField tf_Add2; // �ּ� �ؽ�Ʈ�ʵ�2
	
	JComboBox cb_Phone1; // �޴��� ���ڸ�(010, 011��)
	String cbData[] = {"010", "011", "012", "015", "016", "017", "018", "019"}; // �޴��� ���ڸ� �迭

	JRadioButton rb_Man; // ���� ������ư
	JRadioButton rb_Woman; // ���� ������ư

	JButton btn_Reg; // ȸ������ ��ư Register
	JButton btn_Cnl; // ȸ������ ��� ��ư Cancel

	JTable tb_Mem;// ���̺� ����

	//----------------���� �������Ʈ-----------//
	BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

	FileReader fr = null;// ���ϰ�ü �о����
	FileWriter fw = null;// ���ϰ�ü ����

	String gr = "Members.txt";// �������

	String l;//���� �о ���ڿ� ����
	
	//----------------���� �������Ʈ-----------//

	public Join() {
		// -----------�޷� �����κ�---------------------//
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		// -----------�޷� �����κ�---------------------//
		this.setLayout(null);
		this.setLocation(700, 100);
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);

		lb_ID = new JLabel("���̵�");
		lb_PW = new JLabel("��й�ȣ");
		tf_ID = new JTextField();
		pf_PW = new JPasswordField();
		lb_PWC = new JLabel("��й�ȣ Ȯ��");
		pf_PWC = new JPasswordField();
		lb_Name = new JLabel("�̸�");
		tf_Name = new JTextField();
		lb_Gd = new JLabel("����");
		lb_M = new JLabel("-");
		lb_M2 = new JLabel("-");
		lb_JB = new JLabel();
		lb_Phone = new JLabel("�޴���ȭ");
		tf_Phone1 = new JTextField();
		tf_Phone2 = new JTextField();
		tf_Phone3 = new JTextField();
		rb_Man = new JRadioButton("����");
		rb_Woman = new JRadioButton("����");
		lb_Add = new JLabel("�ּ�");
		tf_Add1 = new JTextField();
		tf_Add2 = new JTextField();
		btn_Reg = new JButton("ȸ������");
		btn_Cnl = new JButton("���");
		cb_Phone1 = new JComboBox(cbData);

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// �о�� ���� ���ۿ� ��ü ���

			while ((l = br.readLine()) != null) {

				String[] str = l.split("/");

				System.out.print(str[0] + "/");
				System.out.print(str[1] + "/");
				System.out.println(str[2]);

				Table_model.addRow(str);
				// idx = Integer.parseInt(str[0]) + 1;

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
		
		lb_ID.setBounds(130, 50, 50, 25);
		tf_ID.setBounds(130, 80, 200, 25);
		lb_PW.setBounds(130, 130, 70, 25);
		pf_PW.setBounds(130, 160, 200, 25);
		lb_PWC.setBounds(130, 210, 100, 25);
		pf_PWC.setBounds(130, 240, 200, 25);
		lb_Name.setBounds(130, 290, 50, 25);
		tf_Name.setBounds(130, 320, 200, 25);
		lb_Gd.setBounds(130, 370, 50, 25);
		rb_Man.setBounds(125, 400, 80, 25);
		rb_Woman.setBounds(225, 400, 80, 25);
		lb_Phone.setBounds(130, 450, 80, 25);
		cb_Phone1.setBounds(130, 480, 50, 25);
		lb_M.setBounds(190, 480, 10, 25);
		tf_Phone2.setBounds(200, 480, 50, 25);
		lb_M2.setBounds(260, 480, 10, 25);
		tf_Phone3.setBounds(270, 480, 50, 25);
		lb_Add.setBounds(130, 530, 50, 25);
		tf_Add1.setBounds(130, 560, 200, 25);
		tf_Add2.setBounds(130, 590, 200, 25);
		lb_JB.setBounds(170,108, 200, 25);
		btn_Reg.setBounds(125, 690, 100, 40);
		btn_Cnl.setBounds(235, 690, 100, 40);

		JScrollPane sp = new JScrollPane(tb_Mem);
		ButtonGroup bg = new ButtonGroup(); // ������ư �׷� ����

		bg.add(rb_Man);
		bg.add(rb_Woman);

		// this.add(sp);
		this.add(lb_ID);
		this.add(tf_ID);
		this.add(lb_PW);
		this.add(pf_PW);
		this.add(lb_PWC);
		this.add(pf_PWC);
		this.add(lb_Name);
		this.add(tf_Name);
		this.add(lb_Gd);
		this.add(rb_Man);
		this.add(rb_Woman);
		this.add(lb_Phone);
		this.add(cb_Phone1);
		this.add(lb_M);
		this.add(tf_Phone2);
		this.add(lb_M2);
		this.add(tf_Phone3);
		this.add(lb_Add);
		this.add(tf_Add1);
		this.add(tf_Add2);
		this.add(btn_Reg);
		this.add(btn_Cnl);
		this.add(lb_JB);
		//this.add(sp);

		btn_Reg.addActionListener(this);
		rb_Man.addActionListener(this);
		rb_Woman.addActionListener(this);
		tf_ID.addKeyListener(this);
		btn_Cnl.addActionListener(this);

		this.setTitle("ȸ������");
		this.setSize(480, 800);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == rb_Man) {
			s = "0";
		}
		
		if (e.getSource() == rb_Woman) {
			s = "1";
		}
		

		if (e.getSource() == btn_Reg) {

			if (tf_ID.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.", "���̵� �Է�", JOptionPane.ERROR_MESSAGE);
			} else if (lb_JB.getText().equals("�ߺ��� ID �Դϴ�.")) {
				JOptionPane.showMessageDialog(null, "���̵� �̹� �����մϴ�. �ٸ� ���̵� �Է����ּ���.", "���̵� �ߺ�", JOptionPane.ERROR_MESSAGE);
			} else if (pf_PW.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.", "��й�ȣ �Է�", JOptionPane.ERROR_MESSAGE);
			} else if (pf_PWC.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��й�ȣ Ȯ�ζ��� �Է����ּ���.", "��й�ȣ Ȯ�ζ� �Է�", JOptionPane.ERROR_MESSAGE);
			} else if (tf_Name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.", "�̸� �Է�", JOptionPane.ERROR_MESSAGE);
			}
			
			else if (s.equals("")){
				JOptionPane.showMessageDialog(null, "������ üũ���ּ���.", "���� üũ", JOptionPane.ERROR_MESSAGE);
			}

			else if (tf_Phone2.getText().equals("")|| tf_Phone3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�޴���ȭ��ȣ�� �Է����ּ���.", "�޴���ȭ��ȣ �Է�", JOptionPane.ERROR_MESSAGE);
			} else if (tf_Add1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���.", "�ּ� �Է�", JOptionPane.ERROR_MESSAGE);
			}

			else if (pf_PW.getText().equals(pf_PWC.getText())) {

				String[] memstr = { "id", "pw", "name", "s", "phone", "address", "admin" };
				memstr[0] = tf_ID.getText();
				memstr[1] = pf_PWC.getText();
				memstr[2] = tf_Name.getText();
				memstr[3] = s;
				memstr[4] = cb_Phone1.getSelectedItem() + "-" + tf_Phone2.getText() + "-" + tf_Phone3.getText();
				memstr[5] = tf_Add1.getText() + " " + tf_Add2.getText();
				memstr[6] = "false";

				Table_model.addRow(memstr);

				try {
					fw = new FileWriter(gr);
					pw = new PrintWriter(fw);

					for (int i = 0; i < tb_Mem.getRowCount(); i++) {
						for (int j = 0; j < tb_Mem.getColumnCount(); j++) {

							if (j == 6) {
								l = Table_model.getValueAt(i, j).toString();
								pw.print(l);

							} else {
								l = Table_model.getValueAt(i, j).toString();
								pw.print(l);
								pw.print("/");

							}
						}
						pw.println();
					}

				}

				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {

					try {
						fw.close();
						pw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.", "ȸ������ �Ϸ�", JOptionPane.PLAIN_MESSAGE);
				this.setDefaultCloseOperation(3);
				setVisible(false);
				Table_model.setRowCount(0);

			}

			else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.", "��й�ȣ Ȯ�� ����", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == btn_Cnl){
			this.setDefaultCloseOperation(3);
			setVisible(false);
		}
	}
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String args[]) {
		new Join();
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String id = tf_ID.getText();
		for (int k = 0; k < tb_Mem.getRowCount(); k++) {
			if (Table_model.getValueAt(k, 0).equals(id)) {
				lb_JB.setText("�ߺ��� ID �Դϴ�.");
				lb_JB.setForeground(Color.RED);
				break;
			}
			else{
				lb_JB.setText("��밡���� ID �Դϴ�.");
				lb_JB.setForeground(Color.GREEN);
			}
				
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {


		// TODO Auto-generated method stub

	}
}
