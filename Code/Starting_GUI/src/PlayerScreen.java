
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.rmi.RemoteException;
import javax.swing.JButton;



public class PlayerScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton manageFilesJButton = null;
	private JLabel welcomeJLabel = null;
	
	private Player player;
	private JButton playClassicJButton = null;
	private JButton playTournamentJButton = null;
	private JButton changeNameJButton = null;
	/**
	 * This is the default constructor
	 */
	public PlayerScreen(Player player) {
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
			
			/* enterNameJLabel */
			
			/* errorMessageJLabel */
			welcomeJLabel = new JLabel();
			welcomeJLabel.setBounds(new Rectangle(169, 42, 249, 36));
			welcomeJLabel.setText("Welcome !");
			welcomeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			/* add components */
			jContentPane.add(getManageFilesJButton(), null);
			jContentPane.add(welcomeJLabel, null);
			jContentPane.add(getPlayClassicJButton(), null);
			jContentPane.add(getPlayTournamentJButton(), null);
			jContentPane.add(getChangeNameJButton(), null);
			
			/* register event handlers */
		}
		return jContentPane;
	}

	/**
	 * This method initializes manageFilesJButton
	 * @return javax.swing.JButton	
	 */
	private JButton getManageFilesJButton() {
		if (manageFilesJButton == null) {
			manageFilesJButton = new JButton();
			manageFilesJButton.setBounds(new Rectangle(210, 100, 150, 30));
			manageFilesJButton.setText("Manage files");
		}
		return manageFilesJButton;
	}

	/**
	 * This method initializes playClassicJButton
	 * @return javax.swing.JButton	
	 */
	private JButton getPlayClassicJButton() {
		if (playClassicJButton == null) {
			playClassicJButton = new JButton();
			playClassicJButton.setBounds(new Rectangle(210, 150, 150, 30));
			playClassicJButton.setText("Play classic game");
		}
		return playClassicJButton;
	}

	/**
	 * This method initializes playTournamentJButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPlayTournamentJButton() {
		if (playTournamentJButton == null) {
			playTournamentJButton = new JButton();
			playTournamentJButton.setBounds(new Rectangle(210, 200, 150, 30));
			playTournamentJButton.setText("Play tournament");
		}
		return playTournamentJButton;
	}

	/**
	 * This method initializes changeNameJButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getChangeNameJButton() {
		if (changeNameJButton == null) {
			changeNameJButton = new JButton();
			changeNameJButton.setBounds(new Rectangle(210, 250, 150, 30));
			changeNameJButton.setText("Play classic game");
		}
		return changeNameJButton;
	}
	

}  //  @jve:decl-index=0:visual-constraint="36,15"
