package xupt.se.ttms.view.tmpl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 有背景图片的Panel类
 * 
 * @author tntxia
 * 
 */
public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 1;

	private Image image = null;

	public BackgroundPanel(Image image) {
		this.image = image;
	}

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}