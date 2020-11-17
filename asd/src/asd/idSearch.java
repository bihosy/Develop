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

	// --------DB 테이블 관련 --------//
	String header[] = { "ID", "PW", "이름", "성별", "휴대폰", "주소", "권한" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	// --------DB 테이블 관련 --------//

	// ----------------파일 리드라이트-----------//
	BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

	FileReader fr = null;// 파일객체 읽어오기
	FileWriter fw = null;// 파일객체 쓰기

	String gr = "Members.txt";// 경로저장

	String l;// 파일 읽어서 문자열 저장

	// ----------------파일 리드라이트-----------//
	
	JLabel lb_Name; // 이름 라벨
	JLabel lb_Phone; // 휴대전화 라벨
	JLabel lb_YMD; // 생년월일(Year, Month, Day) 라벨
	JLabel lb_M; // 휴대폰번호 작대기(010-0000-0000에서 -)
	JLabel lb_M2; // 휴대폰번호 작대기(010-0000-0000에서 -)

	JTextField tf_Name; // 아이디입력
	JTextField tf_Phone2; // 휴대폰번호 중간자리
	JTextField tf_Phone3; // 휴대폰번호 뒷자리

	JComboBox cb_Phone1; // 휴대폰번호 앞자리(010, 011 등)
	String cbData[] = {"010", "011", "012", "015", "016", "017", "018", "019"}; // 휴대폰 앞자리 배열

	JButton btn_idS; // id찾기

	public idSearch() {

		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB 테이블 관련 --------//
		
		lb_Name = new JLabel("이름");
		lb_Phone = new JLabel("휴대전화");
		lb_YMD = new JLabel("생년월일");

		this.setTitle("아이디 찾기");
		this.setSize(350, 300);
		this.setLocation(800, 350);
		this.setLayout(null);

		tf_Name = new JTextField();
		cb_Phone1 = new JComboBox(cbData);
		tf_Phone2 = new JTextField();
		tf_Phone3 = new JTextField();
		lb_M = new JLabel("-");
		lb_M2 = new JLabel("-");

		btn_idS = new JButton("찾기");
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
			JOptionPane.showMessageDialog(null, "해당 정보로 가입한 계정이 존재하지 않습니다.", "계정 찾기 오류", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new idSearch();
	}

}
