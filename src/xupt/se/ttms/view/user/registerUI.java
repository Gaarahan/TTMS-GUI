package xupt.se.ttms.view.user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import xupt.se.ttms.model.User;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.admin.adminRegister;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.MD5Util;
import xupt.se.util.MouseListerDemo;

public class registerUI extends JFrame {

	/**
	 * 
	 */
	UserSrv userSrv = new UserSrv();
	User user = new User();
	protected Container ct = getContentPane();
	protected JTextField jt1 = new JTextField(10);
	protected JButton enter = new JButton("��¼");
	protected JButton userL = new JButton("�û���¼");
	protected JButton register = new JButton("ע��");
	protected JButton adminL = new JButton("����Ա��¼");
	protected JLabel userName = new JLabel("�û���:");
	protected JLabel passWord = new JLabel("��    �� :");
	protected JLabel hint = new JLabel("ע��:����Ա��¼�������Ͻǰ�ť!");
	protected JPasswordField jp = new JPasswordField(10);
	private static final long serialVersionUID = 1L;

	public registerUI() {

		this.setTitle("����Ӱ�ǵ�¼ϵͳ");
		setLayout(null);
		setResizable(false);
		setSize(450, 250);
		int windowWidth = this.getWidth(); // ��ô��ڿ�
		int windowHeight = this.getHeight(); // ��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// ���ô��ھ�����ʾ

		ct.setBackground(new Color(153, 50, 204));

		hint.setForeground(Color.YELLOW);
		hint.setBounds(150, 180, 250, 30);

		userName.setForeground(Color.WHITE);
		userName.setBounds(100, 50, 100, 30);

		passWord.setForeground(Color.WHITE);
		passWord.setBounds(100, 90, 100, 30);

		jt1.setBounds(150, 50, 200, 30);

		jp.setEchoChar('��'); // �����ַ�
		jp.setBounds(150, 90, 200, 30);

		adminL.setBounds(5, 5, 100, 30);
		adminL.setBorderPainted(false); // ȥ���߿�
		adminL.setFocusPainted(false); // ȥ������
		adminL.setBackground(new Color(0, 0, 0));
		adminL.setForeground(Color.YELLOW);
		MouseListerDemo.setMouseLister(adminL);
		adminL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new adminRegister().setVisible(true);
				setVisible(false);

			}
		});

		enter.setForeground(Color.WHITE);
		enter.setBackground(new Color(0, 0, 0));
		enter.setBorderPainted(false); // ȥ���߿�
		enter.setFocusPainted(false); // ȥ������
		enter.setBounds(255, 130, 95, 30);
		MouseListerDemo.setMouseLister(enter);
		enter.addActionListener(new ActionListener() { // ����¼�

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = jt1.getText();
				String passWord = String.valueOf(jp.getPassword()); // ͨ��MD5����
				if (userName.length() == 0 || passWord.length() == 0) {
					JOptionPane.showMessageDialog(null, "������������¼��Ϣ");
				} else {
					user.setUserName(userName);
					user.setPassWord(MD5Util.crypt(passWord));
					user.setUserMoney(userSrv.selectMoney(userName));
					if (userSrv.select(user)) {
						JOptionPane.showMessageDialog(null, "��½�ɹ�");
						LoginedUser.getInstance().setUserID(userSrv.selectID(userName));
						LoginedUser.getInstance().setEmpName(userName);
						LoginedUser.getInstance().setUserMoney(user.getUserMoney());
						LoginedUser.getInstance().setPassWord(user.getPassWord());
						// System.out.println("jsjsj");
						// System.out.println(LoginedUser.getInstance().getUserID());
						new MainUITmpl().setVisible(true); // ����MainUITmpl()
						setVisible(false); // ���ص�¼����
					} else {
						JOptionPane.showMessageDialog(null, "�˺Ż������������");
					}
				}
			}
		});

		register.setBackground(new Color(0, 0, 0));
		register.setForeground(Color.WHITE);
		register.setBorderPainted(false);
		register.setFocusPainted(false);

		register.setBounds(150, 130, 95, 30);
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new zhuce();
				setVisible(false);
				// JOptionPane.showMessageDialog(null, "ע��ɹ�,���¼");
			}
		});
		MouseListerDemo.setMouseLister(register);

		ImageIcon img = new ImageIcon("resource/image/BGpicture.jpg");
		// Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);
		// ������ͼ���ڱ�ǩ�
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		// ��������ǩ��ӵ�jfram��LayeredPane����
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// ���ñ�����ǩ��λ��
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		// �����������Ϊ͸������LayeredPane����еı�����ʾ������
		this.setUndecorated(false);
		ct.add(adminL);
		ct.add(hint);
		ct.add(userName);
		ct.add(jt1);
		ct.add(passWord);
		ct.add(jp);
		ct.add(enter);
		ct.add(register);
		this.setVisible(true);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new registerUI();
	}
}
