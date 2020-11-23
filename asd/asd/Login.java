package asd;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Login extends JFrame implements ActionListener, MouseListener {
	
	
	
	
	
	
	// --------DB 테이블 관련 --------//
	String header[] = { "ID", "PW", "이름", "성별", "생년월일","휴대폰", "주소", "권한" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	ImageIcon background;
	ImageIcon mark;
	// --------DB 테이블 관련 --------//

	JButton btn_Login;// 로그인 하기 버튼
	JLabel lb_Join;// 회원가입 하기 라벨
	JLabel lb_Mark;// 마크
	JLabel lb_idSearch;// ID 찾기 라벨
	JLabel lb_pwSearch;// PW 찾기 라벨
	JLabel lb_ID, lb_PW; // ID, PW 이름
	JTextField tf_ID; // ID 텍스트 필드
	JPasswordField pf_PW;// PW 패스워드필드
	JPanel pnl_IP; // ID/PW 판넬
	JPanel pnl_Main;
	JLabel lb_SH; // 슬래시(/), 찾기(Search) 라벨
	Color back = new Color(0, 0, 0, 180);

	String spp = "/";

	// ----------------파일 리드라이트-----------//
	BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

	FileReader fr = null;// 파일객체 읽어오기
	FileWriter fw = null;// 파일객체 쓰기

	String gr = "Members.txt";// 경로저장

	String l;// 파일 읽어서 문자열 저장

	// ----------------파일 리드라이트-----------//
	static String name;
	public Login() {
		
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		
		
		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB 테이블 관련 --------//

		background = new ImageIcon("background.jpg");
		
		this.setTitle("로그인");
		this.setSize(750, 470);
		
		this.setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		lb_ID = new JLabel("ID");
		lb_PW = new JLabel("PW");
		lb_SH = new JLabel("/");
		

		tf_ID = new JTextField();
		pf_PW = new JPasswordField();
		btn_Login = new JButton("Login");
		btn_Login.setCursor(cursor);
		lb_Join = new JLabel("회원가입");
		lb_Join.setCursor(cursor);
		lb_idSearch = new JLabel("아이디");
		lb_idSearch.setCursor(cursor);
		lb_pwSearch = new JLabel("패스워드 찾기");
		lb_pwSearch.setCursor(cursor);

		pnl_Main = new JPanel(new GridLayout(0,3)) {
			public void paintComponent(Graphics g) {
				g.drawImage(background.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		pnl_IP = new JPanel();
		pnl_IP.setLayout(null);
		pnl_IP.setBackground(back);

		btn_Login.addActionListener(this);
		lb_Join.addMouseListener(this);
		lb_idSearch.addMouseListener(this);
		lb_pwSearch.addMouseListener(this);

		lb_ID.setBounds(7, 80, 40, 40);
		tf_ID.setBounds(25, 120, 200, 25);
		lb_PW.setBounds(15, 145, 40, 40);
		pf_PW.setBounds(25, 185, 200, 25);
		btn_Login.setBounds(25, 250, 200, 33);
		lb_Join.setBounds(25, 300, 60, 25);
		lb_idSearch.setBounds(92, 300, 40, 25);
		lb_SH.setBounds(134, 299, 20, 25);
		lb_SH.setForeground(Color.white);
		lb_pwSearch.setBounds(145, 300, 125, 25);
		lb_SH.setFont(new Font("Serif", Font.BOLD, 20));
		lb_ID.setFont(new Font("Serif", Font.BOLD, 20));
		lb_ID.setForeground(Color.white);
		lb_PW.setFont(new Font("Serif", Font.BOLD, 20));
		lb_PW.setForeground(Color.white);
		lb_Join.setForeground(Color.white);
		lb_idSearch.setForeground(Color.white);
		lb_pwSearch.setForeground(Color.white);
		pnl_IP.add(lb_ID);
		pnl_IP.add(tf_ID);
		pnl_IP.add(lb_PW);
		pnl_IP.add(pf_PW);
		pnl_IP.add(btn_Login);
		pnl_IP.add(lb_Join);
		pnl_IP.add(lb_idSearch);
		pnl_IP.add(lb_pwSearch);
		pnl_IP.add(lb_SH);
		
		
		
		Color c1 = new Color(255, 255, 255);
		Color c2 = new Color(0, 102, 204);
		btn_Login.setBackground(c2);
		btn_Login.setForeground(c1);
		btn_Login.setFont(new Font("Serif", Font.BOLD, 15));

		lb_ID.setHorizontalAlignment(JLabel.RIGHT);
		lb_PW.setHorizontalAlignment(JLabel.RIGHT);

		pnl_Main.add(pnl_IP);
		
		this.add(pnl_Main);
		
		

		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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
					if (Table_model.getValueAt(k, 7).equals("true")) {
						System.out.println("어서오세요 관리자님");
						name=Table_model.getValueAt(k, 2).toString();
						i = 1;
						Table_model.setRowCount(0);
						this.setDefaultCloseOperation(3);
						this.setVisible(false);
						new Shop_admin();
						break;
					} else {
						System.out.println("성공적으로 로그인 되었습니다.");
						Shop_nomal.userid=Table_model.getValueAt(k, 0).toString();
						name=Table_model.getValueAt(k, 2).toString();
						i = 1;
						Table_model.setRowCount(0);
						this.setDefaultCloseOperation(3);
						this.setVisible(false);
						new Shop_nomal();
						new Notice(null);

						break;
					}

				}
			}
			if (i == 0) {
				for (int k = 0; k < tb_Mem.getRowCount(); k++) {
					if (Table_model.getValueAt(k, 0).equals(id)) {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
						i = 3;
						Table_model.setRowCount(0);
						break;
					} else {
						i = 2;

					}

				}
			}

			if (i == 2) {
				JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.", "아이디 오류", JOptionPane.ERROR_MESSAGE);
				Table_model.setRowCount(0);
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Login();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		/*if (e.getSource() == lb_Join) {
			Font font = lb_Join.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			lb_Join.setFont(font.deriveFont(attributes));
		}*/
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		/*if (e.getSource() == lb_Join) {
			Font font = lb_Join.getFont();
			
			
			lb_Join.setFont(DEFAULT);
		}*/
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == lb_Join) {
			new Join(null);
		}
		if (e.getSource() == lb_pwSearch) {
			new pwSearch(null);
		}
		if (e.getSource() == lb_idSearch) {
			new idSearch(null);
		}
	}
}