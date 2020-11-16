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
	
	//--------DB 테이블 관련 --------//
	String header[] = { "ID", "PW", "이름", "성별", "휴대폰", "주소", "권한" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	//--------DB 테이블 관련 --------//
	
	
	

	JButton btn_Login;// 로그인 하기 버튼
	JButton btn_Join;// 회원가입 하기 버튼
	JButton btn_idSearch;// ID 찾기 버튼
	JButton btn_pwSearch;// PW 찾기 버튼
	JLabel lb_ID, lb_PW; // ID, PW 이름
	JTextField tf_ID; // ID 텍스트 필드
	JPasswordField pf_PW;// PW 패스워드필드
	JPanel pnl_IP; // ID/PW 판넬

	
	
	// ----------------파일 리드라이트-----------//
	BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

	FileReader fr = null;// 파일객체 읽어오기
	FileWriter fw = null;// 파일객체 쓰기

	String gr = "Members.txt";// 경로저장

	String l;// 파일 읽어서 문자열 저장

	// ----------------파일 리드라이트-----------//

	
	
	public Login() {
		//--------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		//--------DB 테이블 관련 --------//
		
		
		
		lb_ID = new JLabel("ID");
		lb_PW = new JLabel("PW");
		tf_ID = new JTextField(10);
		pf_PW = new JPasswordField(10);
		btn_Login = new JButton("Login");
		btn_Join = new JButton("회원가입");
		btn_idSearch = new JButton("아이디 찾기");
		btn_pwSearch = new JButton("패스워드 찾기");

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
		
		this.setTitle("로그인");
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
				br = new BufferedReader(fr);// 읽어온 파일 버퍼에 객체 담기

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
						System.out.println("어서오세요 관리자님");
						i = 1;
						Table_model.setRowCount(0);
						break;
					}
					else{
						System.out.println("성공적으로 로그인 되었습니다.");
						i = 1;
						Table_model.setRowCount(0);
						break;
					}
					
				}
			}
			if (i == 0) {
				for (int k = 0; k < tb_Mem.getRowCount(); k++) {
					if (Table_model.getValueAt(k, 0).equals(id)) {
						System.out.println("비밀번호가 틀립니다");
						i=3;
						Table_model.setRowCount(0);
						break;
					} else {
						i = 2;

					}

				}
			}
			
			if (i == 2) {
				System.out.println("존재하지 않는 아이디 입니다.");
				Table_model.setRowCount(0);
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Login();
	}

}
