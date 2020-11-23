package asd;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Notice extends JDialog implements ActionListener {
	
	
	String header[] = { "����"};
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_notice;
	
	
	
	
	// ----------------���� �������Ʈ-----------//
		BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
		PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

		FileReader fr = null;// ���ϰ�ü �о����
		FileWriter fw = null;// ���ϰ�ü ����

		String gr = "notice.txt";// �������

		String l;// ���� �о ���ڿ� ����

		// ----------------���� �������Ʈ-----------//
	
	
	
	
	
	
	JPanel pnl_notice = new JPanel();
	JPanel pnl_nae = new JPanel();
	JPanel pnl_exit = new JPanel();
	
	JButton btn_exit = new JButton("�ݱ�");
	JLabel lb_notice = new JLabel("��������");
	JTextArea ta_nae = new JTextArea(50,30);
	
	String[] str = {"������","��Ӹ�"};
	String notice="";
	
	
	public Notice(Shop_nomal sn) {
		super(sn,true);
		
		Table_model = new DefaultTableModel(contents, header);
		tb_notice = new JTable(Table_model);
		
		this.setSize(390, 500);
		this.setAlwaysOnTop(true);
		this.setLayout(new BorderLayout());
		this.setUndecorated(true);
		ta_nae.setEditable(false);
		setLocationRelativeTo(null);
		Font Font_notice = new Font("����", Font.BOLD, 18);
		lb_notice.setFont(Font_notice);
		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// �о�� ���� ���ۿ� ��ü ���
			
			while ((l = br.readLine()) != null) {
				String[] str = l.split("/");
				
				Table_model.addRow(str);
			}
			

			if(notice.equals("")){
				notice=(String) Table_model.getValueAt(0, 0);
			}
			
		
			for(int i =1; i<Table_model.getRowCount(); i++){
				notice = notice + "<br>" +Table_model.getValueAt(i, 0);
			}
			
			
			
		
			notice = notice.replace("<br>", "\n");
			ta_nae.setText(notice);
			
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
		
		
		
		pnl_notice.add(lb_notice);
		pnl_nae.add(ta_nae);
		pnl_exit.add(btn_exit);
		btn_exit.addActionListener(this);
		this.add(pnl_notice, BorderLayout.NORTH);
		this.add(pnl_nae, BorderLayout.CENTER);
		this.add(pnl_exit, BorderLayout.SOUTH);
		this.setVisible(true);
	
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Notice(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(0);
		this.setVisible(false);
	}

}
