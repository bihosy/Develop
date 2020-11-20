package asd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	String header[] = { "ID", "PW", "�̸�", "����", "�������", "�޴���", "�ּ�", "����" };
	String contents[][] = {};
	String s = "";
	String YMD = ""; // �������
	DefaultTableModel Table_model;
	//--------DB ���̺� ���� --------//
	
	JPanel P; // ���ȭ�� �г�
	JPanel pnl_YMD; // ������� �г�

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
	BufferedImage background = null;

	String l;//���� �о ���ڿ� ����
	
	//-----------------------�޷»���----------------------//
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	private String datepattern = "yyyy-MM-dd";
	private SimpleDateFormat dateformatter = new SimpleDateFormat(datepattern);
	//-----------------------�޷»���----------------------//
	
	//----------------���� �������Ʈ-----------//

	public Join(){
		
		try {
		    background = ImageIO.read(new File("f.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = background.getScaledInstance(480, 900, Image.SCALE_SMOOTH);
		
		P = new JPanel(null)
		{
			public void paintComponent(Graphics g){
				g.drawImage(dimg, 0, 0,null);
				setOpaque(false);
				super.paintComponent(g);
				}
			};
			
		this.add(P);
		
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
		lb_YMD = new JLabel("�������");
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
		pnl_YMD = new JPanel();
		
		
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
		lb_YMD.setBounds(130, 450, 80, 25);
		pnl_YMD.setBounds(130, 480, 205, 35);
		lb_Phone.setBounds(130, 550, 80, 25);
		cb_Phone1.setBounds(130, 580, 50, 25);
		lb_M.setBounds(190, 580, 10, 25);
		tf_Phone2.setBounds(200, 580, 50, 25);
		lb_M2.setBounds(260, 580, 10, 25);
		tf_Phone3.setBounds(270, 580, 50, 25);
		lb_Add.setBounds(130, 630, 50, 25);
		tf_Add1.setBounds(130, 660, 200, 25);
		tf_Add2.setBounds(130, 690, 200, 25);
		lb_JB.setBounds(170,108, 200, 25);
		btn_Reg.setBounds(125, 790, 100, 40);
		btn_Cnl.setBounds(235, 790, 100, 40);
		
		model.setDate(2000, 1, 1);
		pnl_YMD.add(datePicker);
		pnl_YMD.setBackground(new Color(0, 0, 0, 0));
		
		rb_Man.setOpaque(false);
		rb_Woman.setOpaque(false);

		JScrollPane sp = new JScrollPane(tb_Mem);
		ButtonGroup bg = new ButtonGroup(); // ������ư �׷� ����
		
		lb_ID.setForeground(Color.white);
		lb_PW.setForeground(Color.white);
		lb_PWC.setForeground(Color.white);
		lb_Name.setForeground(Color.white);
		lb_Gd.setForeground(Color.white);
		lb_Phone.setForeground(Color.white);
		rb_Man.setForeground(Color.white);
		rb_Woman.setForeground(Color.white);
		lb_YMD.setForeground(Color.white);
		lb_Add.setForeground(Color.white);
		lb_JB.setForeground(Color.white);
		lb_M.setForeground(Color.WHITE);
		lb_M2.setForeground(Color.WHITE);

		bg.add(rb_Man);
		bg.add(rb_Woman);
		// this.add(sp);
		P.add(lb_ID);
		P.add(tf_ID);
		P.add(lb_PW);
		P.add(pf_PW);
		P.add(lb_PWC);
		P.add(pf_PWC);
		P.add(lb_Name);
		P.add(tf_Name);
		P.add(lb_Gd);
		P.add(rb_Man);
		P.add(rb_Woman);
		P.add(lb_YMD);
		P.add(pnl_YMD);
		P.add(lb_Phone);
		P.add(cb_Phone1);
		P.add(lb_M);
		P.add(tf_Phone2);
		P.add(lb_M2);
		P.add(tf_Phone3);
		P.add(lb_Add);
		P.add(tf_Add1);
		P.add(tf_Add2);
		P.add(btn_Reg);
		P.add(btn_Cnl);
		P.add(lb_JB);
		//this.add(sp);

		btn_Reg.addActionListener(this);
		rb_Man.addActionListener(this);
		rb_Woman.addActionListener(this);
		tf_ID.addKeyListener(this);
		btn_Cnl.addActionListener(this);

		this.setTitle("ȸ������");
		this.setSize(480, 900);
		this.setVisible(true);
		setLocationRelativeTo(null);
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
			else if(datePicker.getModel().getValue() == null) {
				JOptionPane.showMessageDialog(null, "��������� �Է����ּ���.", "������� �Է� ", JOptionPane.ERROR_MESSAGE);
			}

			else if (tf_Phone2.getText().equals("")|| tf_Phone3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�޴���ȭ��ȣ�� �Է����ּ���.", "�޴���ȭ��ȣ �Է�", JOptionPane.ERROR_MESSAGE);
			} else if (tf_Add1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���.", "�ּ� �Է�", JOptionPane.ERROR_MESSAGE);
			}

			else if (pf_PW.getText().equals(pf_PWC.getText())) {
				
				Date selectedDate = (Date) datePicker.getModel().getValue();
				String YMD = (String)dateformatter.format(selectedDate);

				String[] memstr = { "id", "pw", "name", "s", "ymd", "phone", "address", "admin" };
				memstr[0] = tf_ID.getText();
				memstr[1] = pf_PWC.getText();
				memstr[2] = tf_Name.getText();
				memstr[3] = s;
				memstr[4] = YMD;
				memstr[5] = cb_Phone1.getSelectedItem() + "-" + tf_Phone2.getText() + "-" + tf_Phone3.getText();
				memstr[6] = tf_Add1.getText() + "=" + tf_Add2.getText();
				memstr[7] = "false";

				Table_model.addRow(memstr);

				try {
					fw = new FileWriter(gr);
					pw = new PrintWriter(fw);

					for (int i = 0; i < tb_Mem.getRowCount(); i++) {
						for (int j = 0; j < tb_Mem.getColumnCount(); j++) {

							if (j == 7) {
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
			System.out.println(YMD);
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
