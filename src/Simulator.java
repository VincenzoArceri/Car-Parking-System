import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulator {
	
	public static long initialTime = System.currentTimeMillis();
	
	public static Toolkit t = Toolkit.getDefaultToolkit();
	public static Dimension screenSize = t.getScreenSize();
	
	public static Detector detector = new Detector("Detector");
	public static Monitor monitor = new Monitor("Monitor");
	public static ProcessingUnit processingUnit = new ProcessingUnit("Processing Unit");
	
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
		
		cont.setLayout(new GridLayout(1, 2, 10, 10));
		
		cont.add(dx);
		cont.add(sx);
		
		dx.setBorder(new TitledBorder("Exit car"));
		sx.setBorder(new TitledBorder("Enter car"));
		
		// -> posizione finestra "Detector"
		
		detector.setSize(200, 100);
		detector.setLocation(width / 4 - 100, heigth / 3 - 45);
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
		
		cont2.setLayout(new GridLayout(2, 1, 10, 10));	
		cont2.add(Cars);
		cont2.add(Calculate);
		
		Cars.setBorder(new TitledBorder("Total cars entered"));
		
		

		// -> posizione finestra "Processing Unit"
		
		ProcUnit.setSize(200, 150);
		ProcUnit.setLocation(width / 2 - 100, heigth / 3 - 45);
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
		cont3.setLayout(new GridLayout(2, 1, 10, 10));
		cont3.add(Average);
		cont3.add(FreePark);

		FreePark.setBorder(new TitledBorder("Free parking places"));
		Average.setBorder(new TitledBorder("Entering Cars/Hour"));
		
		// -> posizione finestra "Monitor"
		
		Display.setSize(200, 120);
		Display.setLocation(width * 3 / 4 - 100, heigth / 3 - 45);
		Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display.setVisible(true);
	}
}