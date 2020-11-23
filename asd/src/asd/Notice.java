package asd;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Notice extends JDialog implements ActionListener {
	
	
	String header[] = { "내용"};
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_notice;
	
	
	
	
	// ----------------파일 리드라이트-----------//
		BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
		PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

		FileReader fr = null;// 파일객체 읽어오기
		FileWriter fw = null;// 파일객체 쓰기

		String gr = "notice.txt";// 경로저장

		String l;// 파일 읽어서 문자열 저장

		// ----------------파일 리드라이트-----------//
	
	
	
	
	
	
	JPanel pnl_notice = new JPanel();
	JPanel pnl_nae = new JPanel();
	JPanel pnl_exit = new JPanel();
	
	JButton btn_exit = new JButton("닫기");
	JLabel lb_notice = new JLabel("공지사항");
	JTextArea ta_nae = new JTextArea(50,30);
	
	String[] str = {"전성훈","대머리"};
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
		Font Font_notice = new Font("돋움", Font.BOLD, 18);
		lb_notice.setFont(Font_notice);
		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// 읽어온 파일 버퍼에 객체 담기
			
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
