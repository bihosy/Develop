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

	// --------DB 테이블 관련 --------//
	String header[] = { "ID", "PW", "이름", "성별", "생년월일", "휴대폰", "주소", "권한" };
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
	
	BufferedImage bi;
	JPanel P;
	JPanel pnl_YMD;
	
	//-----------------------달력생성----------------------//
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	private String datepattern = "yyyy-MM-dd";
	private SimpleDateFormat dateformatter = new SimpleDateFormat(datepattern);
	//-----------------------달력생성----------------------//

	
	public idSearch() {

		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB 테이블 관련 --------//
		
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
		
		lb_Name = new JLabel("이름");
		lb_Phone = new JLabel("휴대전화");
		lb_YMD = new JLabel("생년월일");

		this.setTitle("아이디 찾기");
		this.setSize(350, 300);
		setLocationRelativeTo(null);

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
			if (Table_model.getValueAt(k, 2).equals(name) && Table_model.getValueAt(k, 5).equals(phone) && Table_model.getValueAt(k,  4).equals(YMD)) {
				
				this.setDefaultCloseOperation(3);
				this.setVisible(false);				
				
				JOptionPane.showMessageDialog(this, name + "님의 아이디는 " + Table_model.getValueAt(k, 0) + " 입니다.", "아이디 찾기", JOptionPane.PLAIN_MESSAGE);
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
