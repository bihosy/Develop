package asd;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class pro_show extends JFrame {
	
	
	static JLabel lb_name,lb_price,lb_size,lb_img;
	
	
	public pro_show(){
		
		lb_name = new JLabel("��ǰ��");
		lb_price = new JLabel("��  ��");
		lb_size = new JLabel("������");
		lb_img = new JLabel();
		
		this.setLayout(null);
		
		lb_img.setBounds(50, 50, 150, 250);
		lb_img.setOpaque(true);
		lb_img.setBackground(Color.CYAN);
		lb_name.setBounds(230, 50, 100, 25);
		
		this.setSize(400, 500);
		this.add(lb_name);
		this.add(lb_price);
		this.add(lb_size);
		this.add(lb_img);
		
		
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pro_show();
	}

}
