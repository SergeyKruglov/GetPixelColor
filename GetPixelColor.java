import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class myFrame extends JFrame {
	
	private Robot robot;
	 
	public myFrame() {
		
		JPanel windowContent= new JPanel();
		BorderLayout bl = new BorderLayout();
		windowContent.setLayout(bl);
		
		final JPanel colorPanel = new JPanel();
		colorPanel.setBackground(Color.white);
		windowContent.add("Center", colorPanel);
		
		setTitle("GPC");
		setContentPane(windowContent);
		pack();
		setSize(200,200);
		setResizable(true);
		setVisible(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			
			robot = new Robot();
			
		} catch (AWTException ex) {
			
			System.err.println(ex.getMessage() + " выбор цвета");
			System.exit(0);
			
		}
	 
		addMouseListener(new MouseListener(){
			
	            public void mouseClicked(MouseEvent event) {}
	            
				public void mouseEntered(MouseEvent event) {}
				
				public void mouseExited(MouseEvent event)  {}
				
				public void mousePressed(MouseEvent event) {}
				
				public void mouseReleased(MouseEvent event){	

					Point location = event.getLocationOnScreen();
					
					double x = location.getX();
					double y = location.getY();
					
					int xi = (int) x;
					int yi = (int) y;

					Color colors = robot.getPixelColor(xi,yi);
					
					colorPanel.setBackground(colors);
					
					String hex = Integer.toHexString(colors.getRGB());
					hex = "#" + hex.substring(2, hex.length()).toUpperCase();
					
					System.out.println(hex);
					
					StringSelection buffHex = new StringSelection(hex);
				    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(buffHex, null);
				    
				}			    	
		});		
	}
}
	 
public class GetPixelColor {
	
	public static void main(String[] args) {
		
		myFrame frame = new myFrame();
		
	}
}