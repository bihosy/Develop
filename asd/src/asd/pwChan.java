package asd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.*;

public class pwChan extends JFrame implements ActionListener {

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

	JLabel lb_newPW; // 새로운 비밀번호(New Password) 라벨
	JLabel lb_newPWC; // 새로운 비밀번호 확인(New Password Check) 라벨

	// ----------------파일 리드라이트-----------//

	static int pwChanRow;

	JPasswordField pf_newPW; // 새 비밀번호
	JPasswordField pf_newPWC; // 새 비밀번호 확인
	JButton btn_newPW;

	BufferedImage bi;
	JPanel P;

	public pwChan() {

		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB 테이블 관련 --------//

		try {
			bi = ImageIO.read(new File("h.jfif"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg2 = bi.getScaledInstance(395, 212, Image.SCALE_SMOOTH);

		P = new JPanel(null)
		{
			public void paintComponent(Graphics g){
				g.drawImage(dimg2, 0, 0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		pf_newPW = new JPasswordField(10);
		pf_newPWC = new JPasswordField(10);
		btn_newPW = new JButton("변경");
		lb_newPW = new JLabel("새 비밀번호");
		lb_newPWC = new JLabel("새 비밀번호 확인");

		this.setTitle("비밀번호 변경");
		this.setSize(400, 240);

		lb_newPW.setBounds(50, 30, 100, 25);
		pf_newPW.setBounds(190, 30, 150, 25);
		lb_newPWC.setBounds(50, 80, 100, 25);
		pf_newPWC.setBounds(190, 80, 150, 25);
		btn_newPW.setBounds(50, 140, 290, 35);

		lb_newPW.setHorizontalAlignment(JLabel.RIGHT);
		lb_newPWC.setHorizontalAlignment(JLabel.RIGHT);

		lb_newPW.setForeground(Color.WHITE);
		lb_newPWC.setForeground(Color.WHITE);

		this.add(P);
		P.add(lb_newPW);
		P.add(pf_newPW);
		P.add(lb_newPWC);
		P.add(pf_newPWC);
		P.add(btn_newPW);

		btn_newPW.addActionListener(this);

		this.setLocation(750, 400);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

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

		if (pf_newPW.getText().equals(pf_newPWC.getText()) ) {
			Table_model.setValueAt(pf_newPWC.getText(), pwChanRow, 1);
			System.out.println(Table_model.getValueAt(pwChanRow, 1));

			try {
				fw = new FileWriter(gr);
				pw = new PrintWriter(fw);
				for (int i = 0; i < tb_Mem.getRowCount(); i++) {
					for (int j = 0; j < tb_Mem.getColumnCount(); j++) {
						if (j == 7) {
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
			this.setVisible(false);
			this.setDefaultCloseOperation(3);
			
			JOptionPane.showMessageDialog(this, "비밀번호가 성공적으로 변경되었습니다.", "비밀번호 찾기", JOptionPane.PLAIN_MESSAGE);
			
		}

		else{
			JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다. 다시 입력해주세요.", "비밀번호 변경 오류", JOptionPane.ERROR_MESSAGE);
			Table_model.setRowCount(0);
		}
	}

	public static void main(String[] args) {
		new pwChan();
	}
}