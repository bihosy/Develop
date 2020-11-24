package asd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import asd.idSearch.JTextFieldLimit;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class pwSearch extends JDialog implements ActionListener , KeyListener{
	
	
	class JTextFieldLimit extends PlainDocument {
		  private int limit;
		  JTextFieldLimit(int limit) {
		    super();
		    this.limit = limit;
		  }

		  JTextFieldLimit(int limit, boolean upper) {
		    super();
		    this.limit = limit;
		  }

		  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		    if (str == null)
		      return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}

	// --------DB ���̺� ���� --------//
	String header[] = { "ID", "PW", "�̸�", "����", "�������", "�޴���", "�ּ�", "����" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	String YMD;
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
	
	BufferedImage bi = null;
	
	JPanel P;
	JPanel pnl_YMD;
	
	//-----------------------�޷»���----------------------//
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	private String datepattern = "yyyy-MM-dd";
	private SimpleDateFormat dateformatter = new SimpleDateFormat(datepattern);
	//-----------------------�޷»���----------------------//

	public pwSearch(Login lg) {
		super(lg,true);

		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB ���̺� ���� --------//
		
		try {
		    bi = ImageIO.read(new File("h.jfif"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = bi.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		
		pnl_YMD = new JPanel();
		P = new JPanel(null)
		{
			public void paintComponent(Graphics g){
				g.drawImage(dimg, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
				}
			};

		lb_ID = new JLabel("���̵�");
		lb_Name = new JLabel("�̸�");
		lb_Phone = new JLabel("�޴���ȭ");
		lb_YMD = new JLabel("�������");
		
		this.add(P);
		this.setTitle("��й�ȣ ã��");
		this.setSize(350, 350);
		setLocationRelativeTo(null);
		
		tf_ID = new JTextField();
		tf_Name = new JTextField();
		cb_Phone1 = new JComboBox(cbData);
		tf_Phone2 = new JTextField();
		tf_Phone3 = new JTextField();
		lb_M = new JLabel("-");
		lb_M2 = new JLabel("-");

		btn_idS = new JButton("ã��");
		btn_idS.addActionListener(this);
		btn_idS.setBounds(35, 250, 275, 35);
		
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
		pnl_YMD.setBounds(115, 165, 205, 35);
		
		lb_Name.setHorizontalAlignment(JLabel.RIGHT);
		lb_Phone.setHorizontalAlignment(JLabel.RIGHT);
		lb_YMD.setHorizontalAlignment(JLabel.RIGHT);
		
		lb_ID.setForeground(Color.WHITE);
		lb_Name.setForeground(Color.WHITE);
		lb_Phone.setForeground(Color.WHITE);
		lb_YMD.setForeground(Color.WHITE);
		lb_M.setForeground(Color.WHITE);
		lb_M2.setForeground(Color.WHITE);
		
		pnl_YMD.add(datePicker);
		model.setDate(2000, 1, 1);
		pnl_YMD.setBackground(new Color(0, 0, 0, 0));
		
		P.add(lb_ID);
		P.add(tf_ID);
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
		P.add(pnl_YMD);
		tf_Phone2.setDocument(new JTextFieldLimit(4));
		tf_Phone3.setDocument(new JTextFieldLimit(4));

		tf_Phone2.addKeyListener(this);
		tf_Phone3.addKeyListener(this);
		
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = 0;
		try {
			Date selectedDate = (Date) datePicker.getModel().getValue();
			YMD = (String)dateformatter.format(selectedDate);
		} catch(Exception e2){}
		
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
			
			if (Table_model.getValueAt(k, 0).equals(id)&&Table_model.getValueAt(k, 2).equals(name) &&
					Table_model.getValueAt(k, 5).equals(phone) && Table_model.getValueAt(k,  4).equals(YMD)) {

				//System.out.println(Table_model.getValueAt(k, 1));
				i = 1;
				pwChan.pwChanRow = k;
				new pwChan(null);
				Table_model.setRowCount(0);
				this.setVisible(false);
				this.setDefaultCloseOperation(1);
				break;

			}
		}
		if(i==0){
			JOptionPane.showMessageDialog(null, "�ش� ������ �������� �ʽ��ϴ�.", "��й�ȣ ã�� ����", JOptionPane.ERROR_MESSAGE);
		}
		this.setVisible(false);
		this.setDefaultCloseOperation(1);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pwSearch(null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == tf_Phone2 || e.getSource() == tf_Phone3) {

			char caracter = e.getKeyChar();
			if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
				e.consume();
			}

		}
	}

}
