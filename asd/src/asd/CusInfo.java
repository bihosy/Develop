package asd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/*
		//우편번호 찾기 창
class Postcode extends JFrame implements ActionListener {
	JPanel pn_north, pn_south, pn_entire, pn_center, pn_north2;
	JButton btn_search;
	JLabel lb_title, lb_example, lb_area, lb_example2, lb_search;
	JTextField fd_area;
	JTable table ;
	DefaultTableModel model;
	JScrollPane sp;
	String header [] ={"시","구/군","동","우편번호"};
	String contents [][]  ={};

	public Postcode() {
		this.setSize(400, 510);

		pn_north = new JPanel();
		pn_north2 = new JPanel();
		pn_center = new JPanel();
		pn_south = new JPanel();
		pn_entire = new JPanel(null);

		btn_search = new JButton("검색");
		//btn_search.addActionListener(this);
		
		model = new DefaultTableModel(contents,header);
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(280,250));
	
		
		
		lb_title = new JLabel("주소찾기");
		lb_title.setFont(new Font("한컴 소망 B", Font.PLAIN, 20));
		lb_area = new JLabel("지역명");
		lb_example = new JLabel("거주하고 계신 동(읍/면) 이름을 입력해 주세요");
		lb_example2 = new JLabel("Ex) 청담1동, 한강로3가, 수지면 ");
		lb_search = new JLabel("지역명을  입력하신 후 검색해 주세요.");
		fd_area = new JTextField(15);

		pn_entire.add(lb_title);
		pn_north.add(lb_example);
		pn_north2.add(lb_example2);
		pn_center.add(lb_area);
		pn_center.add(fd_area);
		pn_center.add(btn_search);
		pn_south.add(lb_search);
		pn_south.add(sp);
		pn_entire.add(pn_north, "North");
		pn_entire.add(pn_north2);
		pn_entire.add(pn_center);
		pn_entire.add(pn_south);
	
		
		lb_title.setBounds(40, 10, 100, 50);
		pn_north.setBounds(25, 60, 300, 20);
		pn_north2.setBounds(37, 80, 200, 30);
		pn_center.setBounds(35, 120, 300, 50);
		pn_south.setBounds(35, 185, 300, 300);
		
		sp.setVisible(false);
		this.add(pn_entire);
		this.setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_search){
			lb_search.setVisible(false);
			sp.setVisible(true);
			String [] str = new String [4];
			str[0] = "부산광역시"; //나중에 수정하기 DB필요
			str[1] = "연제구";   //나중에 수정하기 DB필요
			str[2] = fd_area.getText();   
			str[3] = "16582"+"";   //나중에 수정하기 DB필요
			model.addRow(str);
			if( fd_area.getText().isEmpty()){
					lb_search.setVisible(true);
					sp.setVisible(false);
				}
			}
		}
}
*/
	//구매자 정보 입력 창
public class CusInfo extends JPanel{
	JPanel pn_info, pn_first, pn_first1, pn_first2, pn_second, pn_second1, pn_second2, pn_third, pn_third1, pn_third2,
			pn_fourth, pn_fourth1, pn_fourth2, pn_fifth, pn_fifth1, pn_fifth2, pn_sixth, pn_sixth1, pn_sixth2,
			pn_seventh, pn_seventh1, pn_seventh2;
	JLabel lb_Info, lb_select, lb_reciever, lb_address, lb_home, lb_phone, lb_email, lb_line, lb_at, lb_dash, lb_dash2,
			lb_dash3;
	static JTextField fd_reciever;
	static JTextField fd_address1;
	static JTextField fd_address2;
	static JTextField fd_address3;
	static JTextField fd_home;
	static JTextField fd_home2;
	static JTextField fd_phone;
	static JTextField fd_phone2;
	static JTextField fd_email1;
	static JTextField fd_email2;
	static JComboBox cb_email, cb_home;
	static JComboBox cb_phone;
	JButton btn;
	//Postcode pst = new Postcode();
	String[] str = { "직접입력", "naver.com", "gmail.com", "daum.net" };
	String[] str1 =  {"02","051","055","032","031"};
	String[] str2 = { "010  ", "011  ", "016  ", "017  "};

	public CusInfo() {
		this.setSize(600, 400);
		//this.setDefaultCloseOperation(3);
		this.setLayout(new BorderLayout());

		pn_info = new JPanel(null); // 전체패널
		pn_first1 = new JPanel();
		pn_first2 = new JPanel();
		pn_second1 = new JPanel();
		pn_second2 = new JPanel();
		pn_third1 = new JPanel();
		pn_third2 = new JPanel();
		pn_fourth1 = new JPanel();
		pn_fourth2 = new JPanel();
		pn_fifth1 = new JPanel();
		pn_fifth2 = new JPanel();

		//pn_info.setBorder(new TitledBorder(new LineBorder(Color.black, 1)));

		// -----------------------setbounds----------------------------

		pn_first1.setBounds(35, 100, 100, 50);
		pn_first2.setBounds(136, 100, 250, 50);
		pn_second1.setBounds(55, 160, 100, 50);
		pn_second2.setBounds(130, 155, 370, 100);
		pn_third1.setBounds(44, 263, 100, 50);
		pn_third2.setBounds(105, 260, 370, 50);
		pn_fourth1.setBounds(43, 335, 100, 50);
		pn_fourth2.setBounds(105, 330, 370, 50);
		pn_fifth1.setBounds(46, 412, 100, 50);
		pn_fifth2.setBounds(138, 415, 400, 50);

		// ------------------------labels-------------------------
		lb_Info = new JLabel("주문 정보");
		lb_reciever = new JLabel("주문하시는 분 *  ");
		lb_address = new JLabel("주소 *");
		lb_home = new JLabel("일반 전화");
		lb_phone = new JLabel("휴대 전화 *");
		lb_email = new JLabel("이메일 *");
		lb_line = new JLabel("-");
		lb_at = new JLabel("@");
		lb_dash = new JLabel("-");
		lb_dash2 = new JLabel("-");
		lb_dash3 = new JLabel("-");
		lb_Info.setBounds(60, 15, 100, 50);
		lb_Info.setFont(new Font("한컴 소망 B", Font.PLAIN, 20));

		fd_reciever = new JTextField(17);
		fd_address1 = new JTextField(17);
		fd_address2 = new JTextField(27);
		fd_address3 = new JTextField(27);
		fd_home = new JTextField(7);
		fd_home2 = new JTextField(7);
		fd_phone = new JTextField(7);
		fd_phone2 = new JTextField(7);
		fd_email1 = new JTextField(10);
		fd_email2 = new JTextField(10);

		btn = new JButton("  우편번호  ");
		btn.setPreferredSize(new Dimension(103, 30));
		//btn.addActionListener(this);

		cb_email = new JComboBox(str);
		//cb_email.addActionListener(this);
		cb_home = new JComboBox(str1);
		cb_phone = new JComboBox(str2);

		pn_first1.add(lb_reciever);
		pn_first2.add(fd_reciever);
		pn_second1.add(lb_address);
		//pn_second2.add(fd_address1);
		//pn_second2.add(btn);
		pn_second2.add(fd_address2);
		pn_second2.add(fd_address2);
		pn_second2.add(fd_address3);
		pn_third1.add(lb_home);
		pn_third2.add(cb_home);
		pn_third2.add(lb_dash);
		pn_third2.add(fd_home);
		pn_third2.add(lb_line);
		pn_third2.add(fd_home2);
		pn_fourth1.add(lb_phone);
		pn_fourth2.add(cb_phone);
		pn_fourth2.add(lb_dash2);
		pn_fourth2.add(fd_phone);
		pn_fourth2.add(lb_dash3);
		pn_fourth2.add(fd_phone2);
		pn_fifth1.add(lb_email);
		pn_fifth2.add(fd_email1);
		pn_fifth2.add(lb_at);
		pn_fifth2.add(fd_email2);
		pn_fifth2.add(cb_email);

		pn_info.add(lb_Info);
		pn_info.add(pn_first1);
		pn_info.add(pn_first2);
		pn_info.add(pn_second1);
		pn_info.add(pn_second2);
		pn_info.add(pn_third1);
		pn_info.add(pn_third2);
		pn_info.add(pn_fourth1);
		pn_info.add(pn_fourth2);
		//pn_info.add(pn_fifth1);
		//pn_info.add(pn_fifth2);

		this.add(pn_info);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(600,500);
		f.setDefaultCloseOperation(3);
		f.add(new CusInfo());
		f.setVisible(true);
		//new CusInfo();

	}
/*
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == cb_email) {
			String email = cb_email.getSelectedItem().toString();
			fd_email2.setText(email);
		} else if (e.getSource() == btn) {
			pst.setVisible(true);

		}

	}
	*/
}