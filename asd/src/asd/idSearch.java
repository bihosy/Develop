package asd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class idSearch extends JFrame implements ActionListener {

	// --------DB ���̺� ���� --------//
	String header[] = { "ID", "PW", "�̸�", "����", "�������", "�޴���", "�ּ�", "����" };
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
	
	BufferedImage bi;
	JPanel P;
	JPanel pnl_YMD;
	
	//-----------------------�޷»���----------------------//
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	private String datepattern = "yyyy-MM-dd";
	private SimpleDateFormat dateformatter = new SimpleDateFormat(datepattern);
	//-----------------------�޷»���----------------------//

	
	public idSearch() {

		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB ���̺� ���� --------//
		
		try {
		    bi = ImageIO.read(new File("a.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = bi.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		pnl_YMD = new JPanel();
		P = new JPanel(null)
		{
			public void paintComponent(Graphics g){
				g.drawImage(dimg, 0, 0,null);
				setOpaque(false);
				super.paintComponent(g);
				}
			};
		
		lb_Name = new JLabel("�̸�");
		lb_Phone = new JLabel("�޴���ȭ");
		lb_YMD = new JLabel("�������");

		this.setTitle("���̵� ã��");
		this.setSize(350, 300);
		setLocationRelativeTo(null);

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
		pnl_YMD.setBounds(115, 115, 205, 35);
		cb_Phone1.setBounds(120, 70, 50, 25);
		lb_M.setBounds(180, 70, 10, 25);
		lb_M2.setBounds(250, 70, 10, 25);
		tf_Phone2.setBounds(190, 70, 50, 25);
		tf_Phone3.setBounds(260, 70, 50, 25);
		
		
		lb_Name.setHorizontalAlignment(JLabel.RIGHT);
		lb_Phone.setHorizontalAlignment(JLabel.RIGHT);
		lb_YMD.setHorizontalAlignment(JLabel.RIGHT);
		
		lb_Name.setForeground(Color.WHITE);
		lb_Phone.setForeground(Color.WHITE);
		lb_YMD.setForeground(Color.WHITE);
		lb_M.setForeground(Color.WHITE);
		lb_M2.setForeground(Color.WHITE);
		
		pnl_YMD.add(datePicker);
		model.setDate(2000, 1, 1);
		
		pnl_YMD.setBackground(new Color(0, 0, 0, 0));
		
		this.add(P);
		P.add(pnl_YMD);
		P.add(lb_Name);
		P.add(lb_Phone);
		P.add(lb_YMD);
		P.add(tf_Name);
		P.add(cb_Phone1);
		P.add(tf_Phone2);
		P.add(tf_Phone3);
		P.add(btn_idS);
		P.add(lb_M);
		P.add(lb_M2);
		
		this.setVisible(true);
		this.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = 0;
		
		Date selectedDate = (Date) datePicker.getModel().getValue();
		String YMD = (String)dateformatter.format(selectedDate);
		
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
			if (Table_model.getValueAt(k, 2).equals(name) && Table_model.getValueAt(k, 5).equals(phone) && Table_model.getValueAt(k,  4).equals(YMD)) {
				
				this.setDefaultCloseOperation(3);
				this.setVisible(false);				
				
				JOptionPane.showMessageDialog(this, name + "���� ���̵�� " + Table_model.getValueAt(k, 0) + " �Դϴ�.", "���̵� ã��", JOptionPane.PLAIN_MESSAGE);
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
