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

public class visualApi extends JFrame implements ActionListener {
	JButton btnGetrefresh = new JButton("Get/Refresh");
	JLabel lblDepartures = new JLabel("Departures");
	JLabel lblTimeLeft = new JLabel("");
	JLabel Departure1 = new JLabel("");
	JLabel Departure2 = new JLabel("");
	JLabel TimeLeft1 = new JLabel("");
	JLabel TimeLeft2 = new JLabel("");
	
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		btnGetrefresh.setBounds(179, 11, 245, 42);
		contentPane.add(btnGetrefresh);
		
		txtStation = new JTextField();
		txtStation.setText("Station");
		txtStation.setBounds(10, 11, 159, 42);
		contentPane.add(txtStation);
		txtStation.setColumns(10);
		
		
		lblDepartures.setBounds(10, 81, 159, 14);
		contentPane.add(lblDepartures);
		
	
		lblTimeLeft.setBounds(179, 81, 46, 14);
		contentPane.add(lblTimeLeft);
		
		
		Departure1.setBounds(10, 132, 144, 14);
		contentPane.add(Departure1);
		
		
		Departure2.setBounds(10, 195, 144, 14);
		contentPane.add(Departure2);
		
		TimeLeft1.setBounds(179, 132, 116, 14);
		contentPane.add(TimeLeft1);
		
		TimeLeft2.setBounds(179, 195, 116, 14);
		contentPane.add(TimeLeft2);
		
		btnGetrefresh.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String station = txtStation.getText();
		
		try {
			TimeLeft1.setText(ReadXML.timeleft(station));
			Departure1.setText((ReadXML.departure(station)));
		
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