
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.rmi.RemoteException;



public class WelcomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel logoLabel = null;
	private JLabel enterNameJLabel = null;
	private JTextField enterNameJTextField = null;
	private JButton okJButton = null;
	private JButton newUserJButton = null;
	private JLabel errorMessageJLabel = null;
	
	private Player player;

	/**
	 * This is the default constructor
	 */
	public WelcomeScreen(Player player) {
		super();
		this.player = player;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLocation(100, 100);
		this.setSize(600, 400);
		this.setContentPane(getJContentPane());
		this.setTitle("Welcome screen");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);	
			
			/* logoLabel */
			Icon logo = new ImageIcon( getClass().getResource( "picture.jpg" ));
			logoLabel = new JLabel();
			logoLabel.setText( "This is the logo :" );
			logoLabel.setSize(new Dimension(481, 173));
			logoLabel.setLocation(new Point(45, 15));
			logoLabel.setIcon(logo);
			logoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			logoLabel.setVerticalTextPosition(SwingConstants.TOP);
			
			/* enterNameJLabel */
			enterNameJLabel = new JLabel();
			enterNameJLabel.setText("Enter name : ");
			enterNameJLabel.setBounds(new Rectangle(78, 216, 76, 31));

			
			/* errorMessageJLabel */
			errorMessageJLabel = new JLabel();
			errorMessageJLabel.setBounds(new Rectangle(167, 245, 249, 36));
			errorMessageJLabel.setText("No player registered under this name.");
			errorMessageJLabel.setVisible(false);
			
			/* add components */
			jContentPane.add(logoLabel, null);
			jContentPane.add(enterNameJLabel, null);
			jContentPane.add(getEnterNameJTextField(), null);
			jContentPane.add(getOkJButton(), null);
			jContentPane.add(getNewUserJButton(), null);
			jContentPane.add(errorMessageJLabel, null);
			
			/* register event handlers */
			OkButtonHandler okButtonHandler = new OkButtonHandler(); 
			okJButton.addActionListener( okButtonHandler );
			
			NewUserButtonHandler newUserButtonHandler = new NewUserButtonHandler(); 
			newUserJButton.addActionListener( newUserButtonHandler );
		}
		return jContentPane;
	}

	/**
	 * This method initializes enterNameJTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEnterNameJTextField() {
		if (enterNameJTextField == null) {
			enterNameJTextField = new JTextField();
			enterNameJTextField.setBounds(new Rectangle(167, 221, 252, 25));
		}
		return enterNameJTextField;
	}

	/**
	 * This method initializes okJButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkJButton() {
		if (okJButton == null) {
			okJButton = new JButton();
			okJButton.setBounds(new Rectangle(447, 218, 57, 30));
			okJButton.setText("OK");
		}
		return okJButton;
	}

	/**
	 * This method initializes newUserJButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNewUserJButton() {
		if (newUserJButton == null) {
			newUserJButton = new JButton();
			newUserJButton.setBounds(new Rectangle(210, 285, 150, 32));
			newUserJButton.setText("New player ?");
		}
		return newUserJButton;
	}
	
	private class OkButtonHandler implements ActionListener {
	
		public void actionPerformed( ActionEvent event ) {
			String name = enterNameJTextField.getText();
			try {
				if((player.myc).isRegistered(name)) // test whether existing
					JOptionPane.showMessageDialog(jContentPane, "Player "+name+" is registered :)");
				else
					errorMessageJLabel.setVisible(true);
			} catch (RemoteException e) {}
		}
	}
	
	private class NewUserButtonHandler implements ActionListener {
		
		public void actionPerformed( ActionEvent event ) {
			Boolean validName = false;
			String name;
			String message = "Type your name : ";
			try {
				do {
					name = JOptionPane.showInputDialog(jContentPane, message, "New player", JOptionPane.PLAIN_MESSAGE);
					if( name != null && !name.matches("[a-zA-Z]\\w*"))
						message = "Invalid name ! Type your name : ";
					else if ((player.myc).isRegistered(name))
						message = "Already registered ! Type your name : ";
					else {
						/* Registration of a new player */
						player.setName(name);
						message = "Player "+name+" is now registered :)";
						validName = true;
					}
				}while( name != null && !validName );
			} catch (RemoteException e) {}
		}
	}

}  //  @jve:decl-index=0:visual-constraint="36,15"
