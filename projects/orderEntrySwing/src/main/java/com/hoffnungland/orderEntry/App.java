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
			
			JLabel companyNameLabel = new JLabel("Company Name");
			springLayout.putConstraint(SpringLayout.NORTH, companyNameLabel, 10, SpringLayout.NORTH, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, companyNameLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			companyNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			companyNameLabel.setMinimumSize(new Dimension(90, 22));
			companyNameLabel.setPreferredSize(new Dimension(90, 22));
			companyNameLabel.setMaximumSize(new Dimension(90, 22));
			frmOrderEntry.getContentPane().add(companyNameLabel);
			
			companyComboBox = new JComboBox<String>();
			springLayout.putConstraint(SpringLayout.NORTH, companyComboBox, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, companyComboBox, 5, SpringLayout.EAST, companyNameLabel);
			companyNameLabel.setLabelFor(companyComboBox);
			companyComboBox.setMinimumSize(new Dimension(150, 22));
			companyComboBox.setPreferredSize(new Dimension(200, 22));
			companyComboBox.setMaximumSize(new Dimension(32767, 22));
			frmOrderEntry.getContentPane().add(companyComboBox);
			
			JButton customerDetailButton = new JButton("+");
			springLayout.putConstraint(SpringLayout.NORTH, customerDetailButton, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, customerDetailButton, 5, SpringLayout.EAST, companyComboBox);
			customerDetailButton.setMinimumSize(new Dimension(41, 22));
			customerDetailButton.setPreferredSize(new Dimension(41, 22));
			customerDetailButton.setMaximumSize(new Dimension(41, 22));
			frmOrderEntry.getContentPane().add(customerDetailButton);
			
			JLabel agentNameLabel = new JLabel("Agent");
			springLayout.putConstraint(SpringLayout.NORTH, agentNameLabel, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, agentNameLabel, 5, SpringLayout.EAST, customerDetailButton);
			agentNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			agentNameLabel.setMinimumSize(new Dimension(90, 22));
			agentNameLabel.setPreferredSize(new Dimension(90, 22));
			agentNameLabel.setMaximumSize(new Dimension(90, 22));
			frmOrderEntry.getContentPane().add(agentNameLabel);
			
			agentComboBox = new JComboBox<String>();
			agentComboBox.setName("agentComboBox");
			agentComboBox.addActionListener(this);
			agentNameLabel.setLabelFor(agentComboBox);
			springLayout.putConstraint(SpringLayout.NORTH, agentComboBox, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, agentComboBox, 5, SpringLayout.EAST, agentNameLabel);
			agentComboBox.setMinimumSize(new Dimension(150, 22));
			agentComboBox.setPreferredSize(new Dimension(200, 22));
			agentComboBox.setMaximumSize(new Dimension(32767, 22));
			agentComboBox.addItem(null);
			this.retrieveAgents();
			
			frmOrderEntry.getContentPane().add(agentComboBox);
			
			JButton agentDetailButton = new JButton("+");
			agentDetailButton.setName("agentDetailButton");
			agentDetailButton.addActionListener(this);
			springLayout.putConstraint(SpringLayout.NORTH, agentDetailButton, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, agentDetailButton, 5, SpringLayout.EAST, agentComboBox);
			
			agentDetailButton.setMinimumSize(new Dimension(41, 22));
			agentDetailButton.setPreferredSize(new Dimension(41, 22));
			agentDetailButton.setMaximumSize(new Dimension(41, 22));
			frmOrderEntry.getContentPane().add(agentDetailButton);
			
			
			JLabel shopTypeLabel = new JLabel("Shop Type");
			springLayout.putConstraint(SpringLayout.NORTH, shopTypeLabel, 10, SpringLayout.SOUTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, shopTypeLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			shopTypeLabel.setMinimumSize(new Dimension(90, 22));
			shopTypeLabel.setPreferredSize(new Dimension(90, 22));
			shopTypeLabel.setMaximumSize(new Dimension(90, 22));
			shopTypeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			frmOrderEntry.getContentPane().add(shopTypeLabel);
			
			shopTypeTextField = new JTextField();
			shopTypeLabel.setLabelFor(shopTypeTextField);
			springLayout.putConstraint(SpringLayout.NORTH, shopTypeTextField, 0, SpringLayout.NORTH, shopTypeLabel);
			springLayout.putConstraint(SpringLayout.WEST, shopTypeTextField, 5, SpringLayout.EAST, shopTypeLabel);
			shopTypeTextField.setEditable(false);
			shopTypeTextField.setMinimumSize(new Dimension(150, 22));
			shopTypeTextField.setPreferredSize(new Dimension(200, 22));
			shopTypeTextField.setMaximumSize(new Dimension(2147483647, 22));
			frmOrderEntry.getContentPane().add(shopTypeTextField);
			
			JLabel vatCodeLabel = new JLabel("Vat Code");
			springLayout.putConstraint(SpringLayout.NORTH, vatCodeLabel, 0, SpringLayout.NORTH, shopTypeLabel);
			springLayout.putConstraint(SpringLayout.WEST, vatCodeLabel, 5, SpringLayout.EAST, shopTypeTextField);
			vatCodeLabel.setMinimumSize(new Dimension(90, 22));
			vatCodeLabel.setPreferredSize(new Dimension(90, 22));
			vatCodeLabel.setMaximumSize(new Dimension(90, 22));
			vatCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			frmOrderEntry.getContentPane().add(vatCodeLabel);
			
			vatCodeTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, vatCodeTextField, 0, SpringLayout.NORTH, shopTypeLabel);
			springLayout.putConstraint(SpringLayout.WEST, vatCodeTextField, 5, SpringLayout.EAST, vatCodeLabel);
			vatCodeLabel.setLabelFor(vatCodeTextField);
			vatCodeTextField.setEditable(false);
			vatCodeTextField.setMinimumSize(new Dimension(150, 22));
			vatCodeTextField.setPreferredSize(new Dimension(200, 22));
			vatCodeTextField.setMaximumSize(new Dimension(2147483647, 22));
			frmOrderEntry.getContentPane().add(vatCodeTextField);
			
			JLabel fiscalCodeLabel = new JLabel("Fiscal Code");
			springLayout.putConstraint(SpringLayout.NORTH, fiscalCodeLabel, 0, SpringLayout.NORTH, shopTypeLabel);
			springLayout.putConstraint(SpringLayout.WEST, fiscalCodeLabel, 5, SpringLayout.EAST, vatCodeTextField);
			fiscalCodeLabel.setPreferredSize(new Dimension(90, 22));
			fiscalCodeLabel.setMinimumSize(new Dimension(90, 22));
			fiscalCodeLabel.setMaximumSize(new Dimension(90, 22));
			fiscalCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			frmOrderEntry.getContentPane().add(fiscalCodeLabel);
			
			fiscalCodeTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, fiscalCodeTextField, 0, SpringLayout.NORTH, shopTypeLabel);
			springLayout.putConstraint(SpringLayout.WEST, fiscalCodeTextField, 5, SpringLayout.EAST, fiscalCodeLabel);
			fiscalCodeLabel.setLabelFor(fiscalCodeTextField);
			fiscalCodeTextField.setEditable(false);
			fiscalCodeTextField.setMinimumSize(new Dimension(150, 22));
			fiscalCodeTextField.setPreferredSize(new Dimension(200, 22));
			fiscalCodeTextField.setMaximumSize(new Dimension(2147483647, 22));
			frmOrderEntry.getContentPane().add(fiscalCodeTextField);
			
			JLabel addressLabel = new JLabel("Address");
			springLayout.putConstraint(SpringLayout.NORTH, addressLabel, 10, SpringLayout.SOUTH, shopTypeLabel);
			springLayout.putConstraint(SpringLayout.WEST, addressLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			addressLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			addressLabel.setMinimumSize(new Dimension(90, 22));
			addressLabel.setPreferredSize(new Dimension(90, 22));
			addressLabel.setMaximumSize(new Dimension(90, 22));
			frmOrderEntry.getContentPane().add(addressLabel);
			
			addressTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, addressTextField, 0, SpringLayout.NORTH, addressLabel);
			springLayout.putConstraint(SpringLayout.WEST, addressTextField, 5, SpringLayout.EAST, addressLabel);
			
			addressTextField.setEditable(false);
			addressTextField.setMinimumSize(new Dimension(150, 22));
			addressTextField.setPreferredSize(new Dimension(200, 22));
			addressTextField.setMaximumSize(new Dimension(2147483647, 22));
			frmOrderEntry.getContentPane().add(addressTextField);
			
			JLabel emailLabel = new JLabel("Email");
			springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 10, SpringLayout.SOUTH, addressLabel);
			springLayout.putConstraint(SpringLayout.WEST, emailLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			emailLabel.setMinimumSize(new Dimension(90, 22));
			emailLabel.setPreferredSize(new Dimension(90, 22));
			emailLabel.setMaximumSize(new Dimension(90, 22));
			frmOrderEntry.getContentPane().add(emailLabel);
			
			emailTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, emailTextField, 0, SpringLayout.NORTH, emailLabel);
			springLayout.putConstraint(SpringLayout.WEST, emailTextField, 5, SpringLayout.EAST, emailLabel);
			emailTextField.setMinimumSize(new Dimension(150, 22));
			emailTextField.setPreferredSize(new Dimension(200, 22));
			emailTextField.setMaximumSize(new Dimension(2147483647, 22));
			emailLabel.setLabelFor(emailTextField);
			frmOrderEntry.getContentPane().add(emailTextField);
			
			JLabel phoneLabel = new JLabel("Phone");
			springLayout.putConstraint(SpringLayout.NORTH, phoneLabel, 0, SpringLayout.NORTH, emailLabel);
			springLayout.putConstraint(SpringLayout.WEST, phoneLabel, 5, SpringLayout.EAST, emailTextField);
			phoneLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			phoneLabel.setMinimumSize(new Dimension(90, 22));
			phoneLabel.setPreferredSize(new Dimension(90, 22));
			phoneLabel.setMaximumSize(new Dimension(90, 22));
			frmOrderEntry.getContentPane().add(phoneLabel);
			
			phoneTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, phoneTextField, 0, SpringLayout.NORTH, emailLabel);
			springLayout.putConstraint(SpringLayout.WEST, phoneTextField, 5, SpringLayout.EAST, phoneLabel);
			phoneTextField.setMinimumSize(new Dimension(150, 22));
			phoneTextField.setPreferredSize(new Dimension(200, 22));
			phoneTextField.setMaximumSize(new Dimension(2147483647, 22));
			phoneLabel.setLabelFor(phoneTextField);
			frmOrderEntry.getContentPane().add(phoneTextField);
			
			JLabel mobileLabel = new JLabel("Mobile");
			springLayout.putConstraint(SpringLayout.NORTH, mobileLabel, 0, SpringLayout.NORTH, emailLabel);
			springLayout.putConstraint(SpringLayout.WEST, mobileLabel, 5, SpringLayout.EAST, phoneTextField);
			mobileLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			mobileLabel.setMinimumSize(new Dimension(90, 22));
			mobileLabel.setPreferredSize(new Dimension(90, 22));
			mobileLabel.setMaximumSize(new Dimension(90, 22));
			frmOrderEntry.getContentPane().add(mobileLabel);
			
			mobileTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, mobileTextField, 0, SpringLayout.NORTH, emailLabel);
			springLayout.putConstraint(SpringLayout.WEST, mobileTextField, 5, SpringLayout.EAST, mobileLabel);
			mobileTextField.setMinimumSize(new Dimension(150, 22));
			mobileTextField.setPreferredSize(new Dimension(200, 22));
			mobileTextField.setMaximumSize(new Dimension(2147483647, 22));
			mobileLabel.setLabelFor(mobileTextField);
			
			frmOrderEntry.getContentPane().add(mobileTextField);
			
			springLayout.putConstraint(SpringLayout.EAST, fiscalCodeTextField, 0, SpringLayout.EAST, agentDetailButton);
			springLayout.putConstraint(SpringLayout.EAST, addressTextField, 0, SpringLayout.EAST, fiscalCodeTextField);
			springLayout.putConstraint(SpringLayout.EAST, mobileTextField, 0, SpringLayout.EAST, addressTextField);
			springLayout.putConstraint(SpringLayout.EAST, frmOrderEntry.getContentPane(), 5, SpringLayout.EAST, mobileTextField);
			
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
