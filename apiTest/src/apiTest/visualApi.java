package apiTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JTabbedPane;

public class visualApi extends JFrame implements ActionListener {
	JButton btnGetrefresh = new JButton("Get departures");
	JLabel lblTimeLeft = new JLabel("");
	static JLabel Departure1M = new JLabel("");
	JLabel Departure2M = new JLabel("");
	JLabel TimeLeft1M = new JLabel("");
	JLabel TimeLeft2M = new JLabel("");
	
	JLabel Departure1B = new JLabel("");
	JLabel Departure2B = new JLabel("");
	JLabel TimeLeft1B = new JLabel("");
	JLabel TimeLeft2B = new JLabel("");
	
	public static JLabel GetStation = new JLabel("");
	HashMap <Integer, JLabel> boxt= new HashMap <Integer, JLabel>();
	
	HashMap <Integer, JLabel> boxd= new HashMap <Integer, JLabel>();
	
	HashMap <Integer, JLabel> boxb= new HashMap <Integer, JLabel>();
	
	HashMap <Integer, JLabel> boxbt= new HashMap <Integer, JLabel>();
	
	

	
	private JPanel contentPane;
	private JTextField txtStation;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visualApi frame = new visualApi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public visualApi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 89, 535, 231);
		contentPane.add(tabbedPane);
		
		JPanel MetroPanel = new JPanel();
		tabbedPane.addTab("Metro", null, MetroPanel, null);
		MetroPanel.setLayout(null);
		
		JPanel BusPanel = new JPanel();
		tabbedPane.addTab("Bus  ", null, BusPanel, null);
		BusPanel.setLayout(null);
		
		
		Departure1B.setBounds(10, 58, 144, 14);
		BusPanel.add(Departure1B);
		
		
		Departure2B.setBounds(10, 121, 144, 14);
		BusPanel.add(Departure2B);
		
		
		TimeLeft1B.setBounds(179, 58, 341, 14);
		BusPanel.add(TimeLeft1B);
		
		
		TimeLeft2B.setBounds(179, 121, 341, 14);
		BusPanel.add(TimeLeft2B);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Train", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);
		
	
		btnGetrefresh.setBounds(280, 11, 245, 42);
		contentPane.add(btnGetrefresh);
		
		txtStation = new JTextField();
		txtStation.setText("Station");
		txtStation.setBounds(10, 11, 260, 42);
		contentPane.add(txtStation);
		txtStation.setColumns(10);
		
	
		lblTimeLeft.setBounds(179, 106, 46, 14);
		contentPane.add(lblTimeLeft);
		
		
		Departure1M.setBounds(10, 58, 144, 14);
		MetroPanel.add(Departure1M);
		
		
		Departure2M.setBounds(10, 121, 144, 14);
		MetroPanel.add(Departure2M);
		
		TimeLeft1M.setBounds(179, 58, 341, 14);
		MetroPanel.add(TimeLeft1M);
		
		TimeLeft2M.setBounds(179, 121, 341, 14);
		MetroPanel.add(TimeLeft2M);
		
		JLabel lblStation = new JLabel("Showing departures from: ");
		lblStation.setBounds(10, 64, 159, 14);
		contentPane.add(lblStation);
		
		
		GetStation.setBounds(179, 64, 245, 14);
		contentPane.add(GetStation);
		
		btnGetrefresh.addActionListener(this);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		ReadXML.journeydM = "2";
		ReadXML.journeydB = "2";
		
		boxt.put(1, TimeLeft1M);
		boxt.put(2, TimeLeft2M);
		boxd.put(1, Departure1M);
		boxd.put(2, Departure2M);
		
		boxbt.put(1, TimeLeft1B);
		boxbt.put(2, TimeLeft2B);
		boxb.put(1, Departure1B);
		boxb.put(2, Departure2B);
		
		String station = txtStation.getText();
		try {
			ReadXML.XMLGen(station);
		} catch (ParserConfigurationException | SAXException | IOException e2) {
			// TODO Auto-generated scatch block
			e2.printStackTrace();
		}
		int n = 1;
		int m = 1;
		try {
			while(n<=2){
			ReadXML.XMLPos=0;
			boxt.get(n).setText(ReadXML.timeleft(station));
			boxbt.get(n).setText(ReadXML.timeleftBus(station));
			n++;
			boxd.get(m).setText(ReadXML.departure(station));
			boxb.get(m).setText(ReadXML.departureBus(station));
			m++;
			}	
	
		URLParser.initialscan=0;
		
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
