package com.hoffnungland.orderEntry;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;

import com.hoffnungland.orderEntry.entity.Agent;
import com.hoffnungland.orderEntry.entity.Customer;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class App extends WindowAdapter implements ActionListener {

	private static final Logger logger = LogManager.getLogger(App.class);
	
	private EntityManager entityManager;
	private JFrame frmOrderEntry;
	private JComboBox<String> companyComboBox;
	private JComboBox<String> agentComboBox;
	private Agent agent;
	private Customer customer;

	private EntityManagerFactory entityManagerFactory;
	private JTextField addressTextField;
	private JTextField emailTextField;
	private JTextField phoneTextField;
	private JTextField mobileTextField;

	private JTextField shopTypeTextField;

	private JTextField vatCodeTextField;

	private JTextField fiscalCodeTextField;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public JFrame getFrame() {
		return frmOrderEntry;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		logger.traceEntry();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			logger.error(e);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmOrderEntry.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		});
		logger.traceExit();
	}

	/**
	 * Create the application.
	 */
	public App() {
		logger.traceEntry();
		initialize();
		logger.traceExit();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger.traceEntry();
		try {

			this.initializeJPA();
			
			frmOrderEntry = new JFrame();
			frmOrderEntry.setMinimumSize(new Dimension(800, 600));
			frmOrderEntry.addWindowListener(this);
			frmOrderEntry.setName("orderEntryFrame");
			frmOrderEntry.setTitle("Order Entry");
			frmOrderEntry.setBounds(100, 100, 800, 600);
			frmOrderEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			SpringLayout springLayout = new SpringLayout();
			frmOrderEntry.getContentPane().setLayout(springLayout);
			
			JPanel firstLinePanel = new JPanel();
			SpringLayout firstLineSpringLayout = new SpringLayout();
			firstLinePanel.setLayout(firstLineSpringLayout);
			springLayout.putConstraint(SpringLayout.NORTH, firstLinePanel, 10, SpringLayout.NORTH, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, firstLinePanel, 0, SpringLayout.WEST, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, firstLinePanel, 0, SpringLayout.EAST, frmOrderEntry.getContentPane());
			frmOrderEntry.getContentPane().add(firstLinePanel);
			
			JLabel companyNameLabel = new JLabel("Company Name");
			firstLineSpringLayout.putConstraint(SpringLayout.NORTH, companyNameLabel, 0, SpringLayout.NORTH, firstLinePanel);
			firstLineSpringLayout.putConstraint(SpringLayout.WEST, companyNameLabel, 5, SpringLayout.WEST, firstLinePanel);
			firstLineSpringLayout.putConstraint(SpringLayout.SOUTH, firstLinePanel, 0, SpringLayout.SOUTH, companyNameLabel);
			companyNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			companyNameLabel.setMinimumSize(new Dimension(90, 22));
			companyNameLabel.setPreferredSize(new Dimension(90, 22));
			companyNameLabel.setMaximumSize(new Dimension(90, 22));
			firstLinePanel.add(companyNameLabel);
			
			companyComboBox = new JComboBox<String>();
			firstLineSpringLayout.putConstraint(SpringLayout.NORTH, companyComboBox, 0, SpringLayout.NORTH, firstLinePanel);
			firstLineSpringLayout.putConstraint(SpringLayout.WEST, companyComboBox, 5, SpringLayout.EAST, companyNameLabel);
			companyNameLabel.setLabelFor(companyComboBox);
			companyComboBox.setMinimumSize(new Dimension(150, 22));
			companyComboBox.setPreferredSize(new Dimension(200, 22));
			companyComboBox.setMaximumSize(new Dimension(32767, 22));
			firstLinePanel.add(companyComboBox);
			
			JButton customerDetailButton = new JButton("+");
			firstLineSpringLayout.putConstraint(SpringLayout.NORTH, customerDetailButton, 0, SpringLayout.NORTH, firstLinePanel);
			firstLineSpringLayout.putConstraint(SpringLayout.WEST, customerDetailButton, 5, SpringLayout.EAST, companyComboBox);
			customerDetailButton.setMinimumSize(new Dimension(41, 22));
			customerDetailButton.setPreferredSize(new Dimension(41, 22));
			customerDetailButton.setMaximumSize(new Dimension(41, 22));
			firstLinePanel.add(customerDetailButton);
			
			JLabel agentNameLabel = new JLabel("Agent");
			firstLineSpringLayout.putConstraint(SpringLayout.NORTH, agentNameLabel, 0, SpringLayout.NORTH, firstLinePanel);
			firstLineSpringLayout.putConstraint(SpringLayout.WEST, agentNameLabel, 5, SpringLayout.EAST, customerDetailButton);
			agentNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			agentNameLabel.setMinimumSize(new Dimension(90, 22));
			agentNameLabel.setPreferredSize(new Dimension(90, 22));
			agentNameLabel.setMaximumSize(new Dimension(90, 22));
			firstLinePanel.add(agentNameLabel);
			
			agentComboBox = new JComboBox<String>();
			firstLineSpringLayout.putConstraint(SpringLayout.NORTH, agentComboBox, 0, SpringLayout.NORTH, firstLinePanel);
			firstLineSpringLayout.putConstraint(SpringLayout.WEST, agentComboBox, 5, SpringLayout.EAST, agentNameLabel);
			agentComboBox.setName("agentComboBox");
			agentComboBox.addActionListener(this);
			agentNameLabel.setLabelFor(agentComboBox);
			agentComboBox.setMinimumSize(new Dimension(150, 22));
			agentComboBox.setPreferredSize(new Dimension(200, 22));
			agentComboBox.setMaximumSize(new Dimension(32767, 22));
			agentComboBox.addItem(null);
			
			this.retrieveAgents();
			
			firstLinePanel.add(agentComboBox);
			
			JButton agentDetailButton = new JButton("+");
			agentDetailButton.setName("agentDetailButton");
			agentDetailButton.addActionListener(this);
			firstLineSpringLayout.putConstraint(SpringLayout.NORTH, agentDetailButton, 0, SpringLayout.NORTH, firstLinePanel);
			firstLineSpringLayout.putConstraint(SpringLayout.WEST, agentDetailButton, 5, SpringLayout.EAST, agentComboBox);
			agentDetailButton.setMinimumSize(new Dimension(41, 22));
			agentDetailButton.setPreferredSize(new Dimension(41, 22));
			agentDetailButton.setMaximumSize(new Dimension(41, 22));
			firstLinePanel.add(agentDetailButton);
			
			firstLineSpringLayout.putConstraint(SpringLayout.EAST, firstLinePanel, 5, SpringLayout.EAST, agentDetailButton);
			
			JPanel secondLinePanel = new JPanel();
			SpringLayout secondLineSpringLayout = new SpringLayout();
			secondLinePanel.setLayout(secondLineSpringLayout);
			springLayout.putConstraint(SpringLayout.NORTH, secondLinePanel, 10, SpringLayout.SOUTH, firstLinePanel);
			springLayout.putConstraint(SpringLayout.WEST, secondLinePanel, 0, SpringLayout.WEST, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, secondLinePanel, 0, SpringLayout.EAST, frmOrderEntry.getContentPane());
			frmOrderEntry.getContentPane().add(secondLinePanel);
			
			JLabel shopTypeLabel = new JLabel("Shop Type");
			secondLineSpringLayout.putConstraint(SpringLayout.NORTH, shopTypeLabel, 0, SpringLayout.NORTH, secondLinePanel);
			secondLineSpringLayout.putConstraint(SpringLayout.WEST, shopTypeLabel, 5, SpringLayout.WEST, secondLinePanel);
			secondLineSpringLayout.putConstraint(SpringLayout.SOUTH, secondLinePanel, 0, SpringLayout.SOUTH, shopTypeLabel);
			shopTypeLabel.setMinimumSize(new Dimension(90, 22));
			shopTypeLabel.setPreferredSize(new Dimension(90, 22));
			shopTypeLabel.setMaximumSize(new Dimension(90, 22));
			shopTypeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			secondLinePanel.add(shopTypeLabel);
			
			shopTypeTextField = new JTextField();
			shopTypeLabel.setLabelFor(shopTypeTextField);
			secondLineSpringLayout.putConstraint(SpringLayout.NORTH, shopTypeTextField, 0, SpringLayout.NORTH, secondLinePanel);
			secondLineSpringLayout.putConstraint(SpringLayout.WEST, shopTypeTextField, 5, SpringLayout.EAST, shopTypeLabel);
			shopTypeTextField.setEditable(false);
			shopTypeTextField.setMinimumSize(new Dimension(150, 22));
			shopTypeTextField.setPreferredSize(new Dimension(200, 22));
			shopTypeTextField.setMaximumSize(new Dimension(2147483647, 22));
			secondLinePanel.add(shopTypeTextField);
			
			JLabel vatCodeLabel = new JLabel("Vat Code");
			secondLineSpringLayout.putConstraint(SpringLayout.NORTH, vatCodeLabel, 0, SpringLayout.NORTH, secondLinePanel);
			secondLineSpringLayout.putConstraint(SpringLayout.WEST, vatCodeLabel, 5, SpringLayout.EAST, shopTypeTextField);
			vatCodeLabel.setMinimumSize(new Dimension(90, 22));
			vatCodeLabel.setPreferredSize(new Dimension(90, 22));
			vatCodeLabel.setMaximumSize(new Dimension(90, 22));
			vatCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			secondLinePanel.add(vatCodeLabel);
			
			vatCodeTextField = new JTextField();
			secondLineSpringLayout.putConstraint(SpringLayout.NORTH, vatCodeTextField, 0, SpringLayout.NORTH, secondLinePanel);
			secondLineSpringLayout.putConstraint(SpringLayout.WEST, vatCodeTextField, 5, SpringLayout.EAST, vatCodeLabel);
			vatCodeLabel.setLabelFor(vatCodeTextField);
			vatCodeTextField.setEditable(false);
			vatCodeTextField.setMinimumSize(new Dimension(150, 22));
			vatCodeTextField.setPreferredSize(new Dimension(200, 22));
			vatCodeTextField.setMaximumSize(new Dimension(2147483647, 22));
			secondLinePanel.add(vatCodeTextField);
			
			JLabel fiscalCodeLabel = new JLabel("Fiscal Code");
			secondLineSpringLayout.putConstraint(SpringLayout.NORTH, fiscalCodeLabel, 0, SpringLayout.NORTH, secondLinePanel);
			secondLineSpringLayout.putConstraint(SpringLayout.WEST, fiscalCodeLabel, 5, SpringLayout.EAST, vatCodeTextField);
			fiscalCodeLabel.setMinimumSize(new Dimension(90, 22));
			fiscalCodeLabel.setPreferredSize(new Dimension(90, 22));
			fiscalCodeLabel.setMaximumSize(new Dimension(90, 22));
			fiscalCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			secondLinePanel.add(fiscalCodeLabel);
			
			fiscalCodeTextField = new JTextField();
			secondLineSpringLayout.putConstraint(SpringLayout.NORTH, fiscalCodeTextField, 0, SpringLayout.NORTH, secondLinePanel);
			secondLineSpringLayout.putConstraint(SpringLayout.WEST, fiscalCodeTextField, 5, SpringLayout.EAST, fiscalCodeLabel);
			fiscalCodeLabel.setLabelFor(fiscalCodeTextField);
			fiscalCodeTextField.setEditable(false);
			fiscalCodeTextField.setMinimumSize(new Dimension(150, 22));
			fiscalCodeTextField.setPreferredSize(new Dimension(200, 22));
			fiscalCodeTextField.setMaximumSize(new Dimension(2147483647, 22));
			secondLinePanel.add(fiscalCodeTextField);
			
			secondLineSpringLayout.putConstraint(SpringLayout.EAST, secondLinePanel, 5, SpringLayout.EAST, fiscalCodeTextField);
			
			JPanel thirdLinePanel = new JPanel();
			SpringLayout thirdLineSpringLayout = new SpringLayout();
			thirdLinePanel.setLayout(thirdLineSpringLayout);
			springLayout.putConstraint(SpringLayout.NORTH, thirdLinePanel, 10, SpringLayout.SOUTH, secondLinePanel);
			springLayout.putConstraint(SpringLayout.WEST, thirdLinePanel, 0, SpringLayout.WEST, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, thirdLinePanel, 0, SpringLayout.EAST, frmOrderEntry.getContentPane());
			frmOrderEntry.getContentPane().add(thirdLinePanel);
			
			JLabel addressLabel = new JLabel("Address");
			thirdLineSpringLayout.putConstraint(SpringLayout.NORTH, addressLabel, 0, SpringLayout.NORTH, thirdLinePanel);
			thirdLineSpringLayout.putConstraint(SpringLayout.WEST, addressLabel, 5, SpringLayout.WEST, thirdLinePanel);
			thirdLineSpringLayout.putConstraint(SpringLayout.SOUTH, thirdLinePanel, 0, SpringLayout.SOUTH, addressLabel);
			addressLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			addressLabel.setMinimumSize(new Dimension(90, 22));
			addressLabel.setPreferredSize(new Dimension(90, 22));
			addressLabel.setMaximumSize(new Dimension(90, 22));
			thirdLinePanel.add(addressLabel);
			
			addressTextField = new JTextField();
			thirdLineSpringLayout.putConstraint(SpringLayout.NORTH, addressTextField, 0, SpringLayout.NORTH, thirdLinePanel);
			thirdLineSpringLayout.putConstraint(SpringLayout.WEST, addressTextField, 5, SpringLayout.EAST, addressLabel);
			
			addressTextField.setEditable(false);
			addressTextField.setMinimumSize(new Dimension(150, 22));
			addressTextField.setPreferredSize(new Dimension(200, 22));
			addressTextField.setMaximumSize(new Dimension(2147483647, 22));
			thirdLinePanel.add(addressTextField);
			
			thirdLineSpringLayout.putConstraint(SpringLayout.EAST, thirdLinePanel, 5, SpringLayout.EAST, addressTextField);
			
			JPanel forthLinePanel = new JPanel();
			SpringLayout forthLineSpringLayout = new SpringLayout();
			forthLinePanel.setLayout(forthLineSpringLayout);
			springLayout.putConstraint(SpringLayout.NORTH, forthLinePanel, 10, SpringLayout.SOUTH, thirdLinePanel);
			springLayout.putConstraint(SpringLayout.WEST, forthLinePanel, 0, SpringLayout.WEST, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, forthLinePanel, 0, SpringLayout.EAST, frmOrderEntry.getContentPane());
			frmOrderEntry.getContentPane().add(forthLinePanel);
			
			JLabel emailLabel = new JLabel("Email");
			forthLineSpringLayout.putConstraint(SpringLayout.NORTH, emailLabel, 0, SpringLayout.NORTH, forthLinePanel);
			forthLineSpringLayout.putConstraint(SpringLayout.WEST, emailLabel, 5, SpringLayout.WEST, forthLinePanel);
			forthLineSpringLayout.putConstraint(SpringLayout.SOUTH, forthLinePanel, 0, SpringLayout.SOUTH, emailLabel);
			emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			emailLabel.setMinimumSize(new Dimension(90, 22));
			emailLabel.setPreferredSize(new Dimension(90, 22));
			emailLabel.setMaximumSize(new Dimension(90, 22));
			forthLinePanel.add(emailLabel);
			
			emailTextField = new JTextField();
			forthLineSpringLayout.putConstraint(SpringLayout.NORTH, emailTextField, 0, SpringLayout.NORTH, forthLinePanel);
			forthLineSpringLayout.putConstraint(SpringLayout.WEST, emailTextField, 5, SpringLayout.EAST, emailLabel);
			emailTextField.setMinimumSize(new Dimension(150, 22));
			emailTextField.setPreferredSize(new Dimension(200, 22));
			emailTextField.setMaximumSize(new Dimension(2147483647, 22));
			emailLabel.setLabelFor(emailTextField);
			forthLinePanel.add(emailTextField);
			
			JLabel phoneLabel = new JLabel("Phone");
			forthLineSpringLayout.putConstraint(SpringLayout.NORTH, phoneLabel, 0, SpringLayout.NORTH, forthLinePanel);
			forthLineSpringLayout.putConstraint(SpringLayout.WEST, phoneLabel, 5, SpringLayout.EAST, emailTextField);
			phoneLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			phoneLabel.setMinimumSize(new Dimension(90, 22));
			phoneLabel.setPreferredSize(new Dimension(90, 22));
			phoneLabel.setMaximumSize(new Dimension(90, 22));
			forthLinePanel.add(phoneLabel);
			
			phoneTextField = new JTextField();
			forthLineSpringLayout.putConstraint(SpringLayout.NORTH, phoneTextField, 0, SpringLayout.NORTH, forthLinePanel);
			forthLineSpringLayout.putConstraint(SpringLayout.WEST, phoneTextField, 5, SpringLayout.EAST, phoneLabel);
			phoneTextField.setMinimumSize(new Dimension(150, 22));
			phoneTextField.setPreferredSize(new Dimension(200, 22));
			phoneTextField.setMaximumSize(new Dimension(2147483647, 22));
			phoneLabel.setLabelFor(phoneTextField);
			forthLinePanel.add(phoneTextField);
			
			JLabel mobileLabel = new JLabel("Mobile");
			forthLineSpringLayout.putConstraint(SpringLayout.NORTH, mobileLabel, 0, SpringLayout.NORTH, forthLinePanel);
			forthLineSpringLayout.putConstraint(SpringLayout.WEST, mobileLabel, 5, SpringLayout.EAST, phoneTextField);
			mobileLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			mobileLabel.setMinimumSize(new Dimension(90, 22));
			mobileLabel.setPreferredSize(new Dimension(90, 22));
			mobileLabel.setMaximumSize(new Dimension(90, 22));
			forthLinePanel.add(mobileLabel);
			
			mobileTextField = new JTextField();
			forthLineSpringLayout.putConstraint(SpringLayout.NORTH, mobileTextField, 0, SpringLayout.NORTH, forthLinePanel);
			forthLineSpringLayout.putConstraint(SpringLayout.WEST, mobileTextField, 5, SpringLayout.EAST, mobileLabel);
			mobileTextField.setMinimumSize(new Dimension(150, 22));
			mobileTextField.setPreferredSize(new Dimension(200, 22));
			mobileTextField.setMaximumSize(new Dimension(2147483647, 22));
			mobileLabel.setLabelFor(mobileTextField);
			
			forthLinePanel.add(mobileTextField);
			
			forthLineSpringLayout.putConstraint(SpringLayout.EAST, forthLinePanel, 5, SpringLayout.EAST, mobileTextField);
			
		} catch (Exception e) {
			logger.error(e);
			JOptionPane.showMessageDialog(this.frmOrderEntry, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
		
		logger.traceExit();
	}

	private void retrieveAgents() {
		logger.traceEntry();
		
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Agent> q = cb.createQuery(Agent.class);
		Root<Agent> c = q.from(Agent.class);
		q.select(c);
		
		TypedQuery<Agent> agentListQuery = this.entityManager.createQuery(q);
		//TypedQuery<Agent> agentListQuery = this.entityManager.createQuery("select a from Agent a", Agent.class);
		
		for (Agent curAgent: agentListQuery.getResultList()) {
			this.agentComboBox.addItem(curAgent.getUserName());
		}
		
		logger.traceExit();
	}
	
	public Agent retrieveAgent(String agentName) {
		logger.traceEntry();
		
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Agent> q = cb.createQuery(Agent.class);
		Root<Agent> c = q.from(Agent.class);
		q.select(c);
		ParameterExpression<String> p = cb.parameter(String.class);
		q.where(cb.equal(c.get("userName"), p));
		
		TypedQuery<Agent> agentListQuery = this.entityManager.createQuery(q);
		agentListQuery.setParameter(p, agentName);

		return logger.traceExit(agentListQuery.getSingleResult());
	}
	
	private void initializeJPA() {
		logger.traceEntry();
		this.entityManagerFactory = Persistence.createEntityManagerFactory("orderEntryDb");
		this.entityManager = entityManagerFactory.createEntityManager();
		logger.traceExit();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		logger.traceEntry();
		this.entityManager.close();
		this.entityManagerFactory.close();
		logger.traceExit();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		logger.traceEntry();
		
		Container sourceContainer = (Container) event.getSource();
		
		logger.debug(sourceContainer.getName());
		switch(sourceContainer.getName()) {
		case "agentComboBox":
			this.chooseAgent(event);
			break;
		case "agentDetailButton":
			this.showAgentDetailDialog();
			break;
		}
		
		logger.traceExit();
	}
	
	private void chooseAgent(ActionEvent event) {
		logger.traceEntry();
        String agentName = (String) this.agentComboBox.getSelectedItem();
        if(StringUtils.isNullOrEmpty(agentName)) {
        	this.agent = null;
        } else {
        	this.agent = this.retrieveAgent(agentName);
        	
        }
		logger.traceExit();
	}

	private void saveAgent(Agent agent) {
		logger.traceEntry();
		this.entityManager.getTransaction().begin();
		logger.info("Persist agent");
		this.entityManager.persist(agent);
		this.entityManager.getTransaction().commit();
		
		logger.traceExit();
		
	}

	private void showAgentDetailDialog() {
		logger.traceEntry();
		
		try {
			AgentDetailPanel agentDetailPanel = new AgentDetailPanel();
			
			boolean newAgent = (this.agent == null);
			
			if(!newAgent) {
				agentDetailPanel.getNameTextField().setText(this.agent.getName());
				agentDetailPanel.getSurnameTextField().setText(this.agent.getSurname());
				agentDetailPanel.getUsernameTextField().setText(this.agent.getUserName());
				agentDetailPanel.getUsernameTextField().setEnabled(false);
				agentDetailPanel.getEmailTextField().setText(this.agent.getEmail());
			}
			
			AgentDetailDialog agentDetailDialog = new AgentDetailDialog(this.frmOrderEntry, agentDetailPanel);
			agentDetailDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			agentDetailDialog.setVisible(true);
			
			if("OK".equals(agentDetailDialog.getResultAction())) {
				
				if(newAgent) {
					this.agent = new Agent();
					this.agent.setUserName(agentDetailPanel.getUsernameTextField().getText());
				}
				
				this.agent.setName(agentDetailPanel.getNameTextField().getText());
				this.agent.setSurname(agentDetailPanel.getSurnameTextField().getText());
				this.agent.setEmail(agentDetailPanel.getEmailTextField().getText());
				
				this.saveAgent(this.agent);
				
				if(newAgent) {	
					this.agentComboBox.addItem(this.agent.getUserName());		
					this.agentComboBox.setSelectedItem(this.agent.getUserName());
				}
			}
		} catch (Exception e) {
			logger.error(e);
			JOptionPane.showMessageDialog(this.frmOrderEntry, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
		
		logger.traceExit();
	}
}
