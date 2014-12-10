import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ClientGui implements Runnable {
	private EventLoop loop;
	private JFrame mainFrame = new JFrame();
	private JPanel loginPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private String user = null;
	
	public ClientGui(EventLoop loop) {
		this.loop = loop;
		init();
	}
	
	public void init() {
		mainFrame.setSize(new Dimension(500, 500));
		mainFrame.setTitle("Login");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	public void showLoginPanel() {
		final JTextField loginBox = new JTextField();
		JButton loginButton = new JButton();
		loginButton.setText("Login");
		loginButton.setMargin(new Insets(10,10,10,10));
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				user = loginBox.getText();
				showSearchPanel(null);
			}
		});
		
		loginPanel.setName("Login Screen");
		loginPanel.setLayout(new BorderLayout());
		loginPanel.add(loginButton, BorderLayout.SOUTH);
		loginPanel.add(loginBox);
		mainFrame.add(loginPanel);
		
		mainFrame.setVisible(true);
	}
	
	public void showSearchPanel(Auction[] auctions) {
		mainFrame.setTitle("Search - UserName: " + user);
		JPanel topPanel = new JPanel();
		JPanel resultsPanel = new JPanel(new BorderLayout());
		
		//buttons
		JButton searchButton = new JButton();
		searchButton.setText("Search");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loop.searchState();
			}
			
		});
		
		JButton bidButton = new JButton();
		bidButton.setText("Bid");
		bidButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loop.bidState();
			}
			
		});
		
		JButton editButton = new JButton();
		editButton.setText("Edit");
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loop.editState();
			}
			
		});
		
		JButton createButton = new JButton();
		createButton.setText("Create");
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loop.createState();
			}
			
		});
		
		JButton deleteButton = new JButton();
		deleteButton.setText("Delete");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loop.deleteState();
			}
			
		});
		
		topPanel.setLayout(new FlowLayout());
		topPanel.add(searchButton);
		topPanel.add(bidButton);
		topPanel.add(editButton);
		topPanel.add(createButton);
		topPanel.add(deleteButton);
		
		//search Panel settup
		JList<Auction> auctionList = populateList(auctions);
		resultsPanel.add(auctionList);
		
		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(topPanel, BorderLayout.NORTH);
		searchPanel.add(resultsPanel, BorderLayout.CENTER);
		
		mainFrame.add(searchPanel);
		loginPanel.setVisible(false);
		searchPanel.setVisible(true);
		mainFrame.setVisible(true);
	}
	
	public JList<Auction> populateList(Auction[] auctions) {
		DefaultListModel<Auction> listModel = new DefaultListModel<Auction>();
		
		if(auctions != null) {
			for(Auction a : auctions) {
				listModel.addElement(a);
			}
		}
		
		JList<Auction> auctionList = new JList<Auction>(listModel);
		return auctionList;
	}

	@Override
	public void run() {
		showLoginPanel();
	}
}
