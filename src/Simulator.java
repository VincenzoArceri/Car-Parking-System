import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulator {
	
	public static long initialTime = System.currentTimeMillis();
	public static Toolkit t = Toolkit.getDefaultToolkit();
	public static Dimension screenSize = t.getScreenSize();
	public static Detector detector = new Detector();
	public static Monitor monitor = new Monitor();
	public static ProcessingUnit processingUnit = new ProcessingUnit();
	
	public static JLabel FreePark; 
	public static JLabel Average;
	public static JLabel Cars;
	public static JLabel Calculate;
	
	public static void main(String args[]){
		
		detector.registerObserver(processingUnit);
		processingUnit.registerObserver(monitor);
		
		setDetector();
		setProcessingUnit(0);
		setDisplay(500, 0);
	}

	public static void setDetector() {
		JPanel dx = new JPanel();
		JPanel sx = new JPanel();
		JButton enter = new JButton("Enter");
		JButton exit = new JButton("Exit");

		JLabel Message = new JLabel();
		
		int width = (int) screenSize.getWidth();
		int heigth = (int) screenSize.getHeight();
		
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				detector.enteringCar();
			}	
			
		});
			
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				detector.exitingCar();
			}		
		});
		
		dx.add(exit);
		sx.add(enter);
		
		JFrame detector = new JFrame("Detector");
		Container cont = detector.getContentPane();
		cont.add(Message, BorderLayout.NORTH);
		cont.add(dx, BorderLayout.WEST);
		cont.add(sx, BorderLayout.EAST);
		
		dx.setBorder(new TitledBorder("Exit car"));
		sx.setBorder(new TitledBorder("Enter car"));
		
		detector.setSize(200, 60);
		detector.setLocation(width / 3 - 100, heigth / 3 - 30);
		detector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		detector.setVisible(true);
	}
	
	private static void setProcessingUnit(int cars) {
		int width = (int) screenSize.getWidth();
		int heigth = (int) screenSize.getHeight();
		
		Cars = new JLabel("" + cars);
		Calculate = new JLabel("");
		
		JFrame ProcUnit = new JFrame("Processing Unit");
		Container cont2 = ProcUnit.getContentPane();
		cont2.add(Cars, BorderLayout.NORTH);
		cont2.add(Calculate, BorderLayout.SOUTH);
		
		Cars.setBorder(new TitledBorder("Total cars entered"));
		Calculate.setBorder(new TitledBorder(""));
		
		ProcUnit.setSize(200, 150);
		ProcUnit.setLocation(width / 2 - 100, heigth / 2 - 60);
		ProcUnit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ProcUnit.setVisible(true);
	}
	
	private static void setDisplay(int freePark, double average) {
		int width = (int) screenSize.getWidth();
		int heigth = (int) screenSize.getHeight();
		
	  	FreePark = new JLabel("" + freePark);
	  	Average = new JLabel("" + average);
		JFrame Display = new JFrame("Monitor");
		Container cont3 = Display.getContentPane();
		cont3.add(Average, BorderLayout.NORTH);
		cont3.add(FreePark, BorderLayout.SOUTH);


		FreePark.setBorder(new TitledBorder("Free parking places"));
		Average.setBorder(new TitledBorder("Entering Cars/Hour"));
		
		Display.setSize(200, 90);
		Display.setLocation(width * 2 / 3 - 100, heigth / 3 - 40);
		Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display.setVisible(true);
	}
}