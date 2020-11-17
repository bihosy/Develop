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
	//--------DB 테이블 관련 --------//
	String header[] = { "ID", "PW", "이름", "성별", "휴대폰", "주소", "권한" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	//--------DB 테이블 관련 --------//

	JLabel lb_ID; // 아이디 라벨
	JLabel lb_PW; // 패스워드 라벨
	JLabel lb_PWC; // 패스워드 확인(password Check) 라벨
	JLabel lb_Name; // 이름 라벨
	JLabel lb_Gd; //성별 라벨(Gender)
	JLabel lb_YMD; // 생년월일 라벨(Year, Month, Day)
	JLabel lb_M; // 휴대폰번호 작대기(010-0000-0000에서 -)
	JLabel lb_M2; // 휴대폰번호 작대기(010-0000-0000에서 -)
	JLabel lb_Phone; // 휴대폰번호 라벨
	JLabel lb_Add; // 주소라벨(Address)
	JLabel lb_JB; // 중복라벨

	JTextField tf_ID; // 아이디 텍스트필드
	JPasswordField pf_PW; // 패스워드필드
	JPasswordField pf_PWC; // 패스워드 확인(password Check) 필드
	JTextField tf_Name; // 이름 텍스트필드
	JTextField tf_Phone1; // 휴대폰 앞자리(010)
	JTextField tf_Phone2; // 휴대폰 중간자리
	JTextField tf_Phone3; // 휴대폰 끝자리
	JTextField tf_Add1; // 주소 텍스트필드1
	JTextField tf_Add2; // 주소 텍스트필드2
	
	JComboBox cb_Phone1; // 휴대폰 앞자리(010, 011등)
	String cbData[] = {"010", "011", "012", "015", "016", "017", "018", "019"}; // 휴대폰 앞자리 배열

	JRadioButton rb_Man; // 남자 라디오버튼
	JRadioButton rb_Woman; // 여자 라디오버튼

	JButton btn_Reg; // 회원가입 버튼 Register
	JButton btn_Cnl; // 회원가입 취소 버튼 Cancel

	JTable tb_Mem;// 테이블 생성

	//----------------파일 리드라이트-----------//
	BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

	FileReader fr = null;// 파일객체 읽어오기
	FileWriter fw = null;// 파일객체 쓰기

	String gr = "Members.txt";// 경로저장

	String l;//파일 읽어서 문자열 저장
	
	//----------------파일 리드라이트-----------//

	public Join() {
		// -----------달력 생성부분---------------------//
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		// -----------달력 생성부분---------------------//
		this.setLayout(null);
		this.setLocation(700, 100);
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);

		lb_ID = new JLabel("아이디");
		lb_PW = new JLabel("비밀번호");
		tf_ID = new JTextField();
		pf_PW = new JPasswordField();
		lb_PWC = new JLabel("비밀번호 확인");
		pf_PWC = new JPasswordField();
		lb_Name = new JLabel("이름");
		tf_Name = new JTextField();
		lb_Gd = new JLabel("성별");
		lb_M = new JLabel("-");
		lb_M2 = new JLabel("-");
		lb_JB = new JLabel();
		lb_Phone = new JLabel("휴대전화");
		tf_Phone1 = new JTextField();
		tf_Phone2 = new JTextField();
		tf_Phone3 = new JTextField();
		rb_Man = new JRadioButton("남자");
		rb_Woman = new JRadioButton("여자");
		lb_Add = new JLabel("주소");
		tf_Add1 = new JTextField();
		tf_Add2 = new JTextField();
		btn_Reg = new JButton("회원가입");
		btn_Cnl = new JButton("취소");
		cb_Phone1 = new JComboBox(cbData);

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// 읽어온 파일 버퍼에 객체 담기

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
		ButtonGroup bg = new ButtonGroup(); // 라디오버튼 그룹 생성

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

		this.setTitle("회원가입");
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
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "아이디 입력", JOptionPane.ERROR_MESSAGE);
			} else if (lb_JB.getText().equals("중복된 ID 입니다.")) {
				JOptionPane.showMessageDialog(null, "아이디가 이미 존재합니다. 다른 아이디를 입력해주세요.", "아이디 중복", JOptionPane.ERROR_MESSAGE);
			} else if (pf_PW.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "비밀번호 입력", JOptionPane.ERROR_MESSAGE);
			} else if (pf_PWC.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호 확인란을 입력해주세요.", "비밀번호 확인란 입력", JOptionPane.ERROR_MESSAGE);
			} else if (tf_Name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "이름 입력", JOptionPane.ERROR_MESSAGE);
			}
			
			else if (s.equals("")){
				JOptionPane.showMessageDialog(null, "성별을 체크해주세요.", "성별 체크", JOptionPane.ERROR_MESSAGE);
			}

			else if (tf_Phone2.getText().equals("")|| tf_Phone3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "휴대전화번호를 입력해주세요.", "휴대전화번호 입력", JOptionPane.ERROR_MESSAGE);
			} else if (tf_Add1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요.", "주소 입력", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "회원가입 완료", JOptionPane.PLAIN_MESSAGE);
				this.setDefaultCloseOperation(3);
				setVisible(false);
				Table_model.setRowCount(0);

			}

			else {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 입력해주세요.", "비밀번호 확인 오류", JOptionPane.ERROR_MESSAGE);
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
				lb_JB.setText("중복된 ID 입니다.");
				lb_JB.setForeground(Color.RED);
				break;
			}
			else{
				lb_JB.setText("사용가능한 ID 입니다.");
				lb_JB.setForeground(Color.GREEN);
			}
				
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {


		// TODO Auto-generated method stub

	}
}
