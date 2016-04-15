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
	static JLabel Departure1 = new JLabel("");
	JLabel Departure2 = new JLabel("");
	JLabel TimeLeft1 = new JLabel("");
	JLabel TimeLeft2 = new JLabel("");
	public static JLabel GetStation = new JLabel("");
	HashMap <Integer, JLabel> boxt= new HashMap <Integer, JLabel>();
	
	HashMap <Integer, JLabel> boxd= new HashMap <Integer, JLabel>();

	
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
		tabbedPane.setBounds(10, 89, 525, 231);
		contentPane.add(tabbedPane);
		
		JPanel MetroPanel = new JPanel();
		tabbedPane.addTab("Metro", null, MetroPanel, null);
		MetroPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
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
		
		
		Departure1.setBounds(10, 11, 144, 14);
		MetroPanel.add(Departure1);
		
		
		Departure2.setBounds(10, 74, 144, 14);
		MetroPanel.add(Departure2);
		
		TimeLeft1.setBounds(179, 11, 245, 14);
		MetroPanel.add(TimeLeft1);
		
		TimeLeft2.setBounds(179, 74, 245, 14);
		MetroPanel.add(TimeLeft2);
		
		JLabel lblStation = new JLabel("Showing departures from: ");
		lblStation.setBounds(10, 64, 159, 14);
		contentPane.add(lblStation);
		
		
		GetStation.setBounds(179, 64, 245, 14);
		contentPane.add(GetStation);
		
		btnGetrefresh.addActionListener(this);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		
		boxt.put(1, TimeLeft1);
		boxt.put(2, TimeLeft2);
		boxd.put(1, Departure1);
		boxd.put(2, Departure2);
		
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
			boxt.get(n).setText(ReadXML.timeleft(station));
			n++;
			boxd.get(m).setText((ReadXML.departure(station)));
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
