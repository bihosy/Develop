package asd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Write extends JFrame implements ActionListener, MouseListener, ItemListener {

	// --------DB 테이블 관련 --------//
	String header[] = { "상품번호", "사진경로", "상품명", "가격", "사이즈","내용" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Pro;
	// --------DB 테이블 관련 --------//

	// ----------------파일 리드라이트-----------//
	BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

	FileReader fr = null;// 파일객체 읽어오기
	FileWriter fw = null;// 파일객체 쓰기

	String gr = "product.txt";// 경로저장
	String img_gr = "noimage.png";
	String size = "";

	String l;// 파일 읽어서 문자열 저장
	int idx = 0;
	// ----------------파일 리드라이트-----------//

	String[] menu = { "Outer", "Top", "Bottom", "Acc", "Shoes" };
	JTextField fd_title, fd_price, fd_size;
	JTextArea ta_contents;
	JPanel pnl_north, pnl_north1, pnl_north2, pnl_middle, pnl_south;
	JComboBox com_menu;
	JLabel lb_title, lb_upload1, lb_upload2, lb_upload3, lb_image, lb_price, lb_size, lb_blank, lb_blank2;
	JButton btn_reload, btn_preview;
	static JButton btn_finish;
	ImageIcon img;
	JCheckBox cb_sizeS, cb_sizeM, cb_sizeL;

	@SuppressWarnings("rawtypes")

	public Write() {

		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Pro = new JTable(Table_model);
		// --------DB 테이블 관련 --------//

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// 읽어온 파일 버퍼에 객체 담기

			while ((l = br.readLine()) != null) {

				String[] str = l.split("/");

				System.out.print(str[0] + "/");
				System.out.print(str[1] + "/");
				System.out.print(str[2] + "/");
				System.out.print(str[3] + "/");
				System.out.print(str[4] + "/");
				System.out.println(str[5]);

				Table_model.addRow(str);
				idx = Integer.parseInt(str[0]) + 1;

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

		this.setSize(1000, 800);

		fd_title = new JTextField(25);
		fd_price = new JTextField(20);
		// fd_size = new JTextField(28);

		// 텍스트에리아 라인두께설정,공백두께설정
		Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
		Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		ta_contents = new JTextArea(20, 60);
		ta_contents.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

		cb_sizeS = new JCheckBox("S");
		cb_sizeM = new JCheckBox("M");
		cb_sizeL = new JCheckBox("L");

		pnl_north = new JPanel();
		pnl_north1 = new JPanel();
		pnl_north2 = new JPanel(new FlowLayout());
		pnl_north2.setPreferredSize(new Dimension(350, 130));
		pnl_middle = new JPanel();
		pnl_south = new JPanel();
		pnl_south.setPreferredSize(new Dimension(60, 60));

		lb_blank = new JLabel("                ");
		lb_price = new JLabel("가격 :");
		lb_size = new JLabel("사이즈 :");
		lb_blank2 = new JLabel("                                                         ");
		lb_title = new JLabel("제목 :");
		img = new ImageIcon(img_gr);
		lb_image = new JLabel(img);

		Color color = new Color(176, 196, 222);
		
		btn_reload = new JButton("불러 오기");
		
		
		btn_finish = new JButton("등록");
		btn_finish.setPreferredSize(new Dimension(90, 30));
		btn_finish.setBackground(color);
		btn_preview = new JButton("미리 보기");

		com_menu = new JComboBox(menu);

		pnl_north1.add(lb_image);

		pnl_north2.add(lb_title);
		pnl_north2.add(fd_title);
		pnl_north2.add(lb_price);
		pnl_north2.add(fd_price);
		pnl_north2.add(lb_blank);
		pnl_north2.add(lb_size);
		pnl_north2.add(cb_sizeS);
		pnl_north2.add(cb_sizeM);
		pnl_north2.add(cb_sizeL);
		pnl_north2.add(lb_blank2);
		

		lb_image.addMouseListener(this);
		btn_finish.addActionListener(this);
		cb_sizeS.addItemListener(this);
		cb_sizeM.addItemListener(this);
		cb_sizeL.addItemListener(this);

		pnl_north.add(pnl_north1);
		pnl_north.add(pnl_north2);
		pnl_middle.add(ta_contents);
		//pnl_middle.add(tb_Pro);
		
		pnl_south.add(btn_preview);
		pnl_south.add(btn_finish);

		this.add(pnl_north, "North");
		this.add(pnl_middle, "Center");
		this.add(pnl_south, "South");

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Write();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] Pro_str = new String[6];
		String ta_chi = ta_contents.getText();
		ta_chi = ta_chi.replace("\n","<br>");
		

		if (e.getSource() == btn_finish) {
			Pro_str[0] = idx + "";
			Pro_str[1] = img_gr;
			Pro_str[2] = fd_title.getText();
			Pro_str[3] = fd_price.getText();
			
			Pro_str[4] = size;
			Pro_str[5] = ta_chi;

			Table_model.addRow(Pro_str);

			try {
				fw = new FileWriter(gr);
				pw = new PrintWriter(fw);

				for (int i = 0; i < tb_Pro.getRowCount(); i++) {
					for (int j = 0; j < tb_Pro.getColumnCount(); j++) {

						if (j == tb_Pro.getColumnCount()-1) {
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
			JOptionPane.showMessageDialog(null, "상품등록이 완료되었습니다.", "상품등록 완료", JOptionPane.PLAIN_MESSAGE);
			this.setDefaultCloseOperation(3);
			setVisible(false);
			Table_model.setRowCount(0);
			new Shop_admin();

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일(png,jpg)", "png", "jpg");
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);
		fc.showOpenDialog(this);
		if (fc.getSelectedFile() != null) {
			img_gr = fc.getSelectedFile().getPath();
			System.out.println(img_gr);
			Image image = null;
			Image resizeImage = null;
			try {
				image = ImageIO.read(new File(img_gr));
				resizeImage = image.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			lb_image.setIcon(new ImageIcon(resizeImage));

			repaint();
			revalidate();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();

		if (source == cb_sizeS) {
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				size = size.replace("s,", "");
				System.out.println(size);

			} else {
				size = size + "s,";
				System.out.println(size);
			}
		}
		if (source == cb_sizeM) {
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				size = size.replaceAll("m,", "");
				System.out.println(size);
			} else {
				size = size + "m,";
				System.out.println(size);
			}
		}
		if (source == cb_sizeL) {
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				size = size.replace("l,", "");
				System.out.println(size);
			} else {
				size = size + "l,";
				System.out.println(size);
			}
		}

	}

}
