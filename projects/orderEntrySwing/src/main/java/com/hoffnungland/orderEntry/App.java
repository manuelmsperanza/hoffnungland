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

import org.apache.log4j.xml.Log4jEntityResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;

import com.hoffnungland.orderEntry.entity.Agent;
import com.hoffnungland.orderEntry.entity.Customer;
import javax.swing.JTextField;
import javax.swing.Spring;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
	private JLabel phoneLabel;
	private JLabel mobileLabel;
	
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
			companyNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			companyNameLabel.setMinimumSize(new Dimension(80, 22));
			companyNameLabel.setPreferredSize(new Dimension(90, 22));
			companyNameLabel.setMaximumSize(new Dimension(100, 22));
			springLayout.putConstraint(SpringLayout.WEST, companyNameLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.NORTH, companyNameLabel, 10, SpringLayout.NORTH, frmOrderEntry.getContentPane());
			frmOrderEntry.getContentPane().add(companyNameLabel);
			
			companyComboBox = new JComboBox<String>();
			springLayout.putConstraint(SpringLayout.NORTH, companyComboBox, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, companyComboBox, 5, SpringLayout.EAST, companyNameLabel);
			companyNameLabel.setLabelFor(companyComboBox);
			companyComboBox.setBorder(UIManager.getBorder("ComboBox.border"));
			companyComboBox.setMinimumSize(new Dimension(150, 22));
			companyComboBox.setPreferredSize(new Dimension(200, 22));
			companyComboBox.setMaximumSize(new Dimension(32767, 22));
			frmOrderEntry.getContentPane().add(companyComboBox);
			
			JButton customerDetailButton = new JButton("+");
			springLayout.putConstraint(SpringLayout.NORTH, customerDetailButton, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.EAST, companyComboBox, -5, SpringLayout.WEST, customerDetailButton);
			customerDetailButton.setMinimumSize(new Dimension(41, 22));
			customerDetailButton.setPreferredSize(new Dimension(41, 22));
			customerDetailButton.setMaximumSize(new Dimension(41, 22));
			frmOrderEntry.getContentPane().add(customerDetailButton);
			
			JLabel agentNameLabel = new JLabel("Agent");
			springLayout.putConstraint(SpringLayout.NORTH, agentNameLabel, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, agentNameLabel, 400, SpringLayout.WEST, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, customerDetailButton, -5, SpringLayout.WEST, agentNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, agentNameLabel, 5, SpringLayout.HORIZONTAL_CENTER, frmOrderEntry.getContentPane());
			agentNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			agentNameLabel.setMinimumSize(new Dimension(80, 22));
			agentNameLabel.setPreferredSize(new Dimension(90, 22));
			agentNameLabel.setMaximumSize(new Dimension(100, 22));
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
			agentComboBox.setBorder(UIManager.getBorder("ComboBox.border"));
			agentComboBox.addItem(null);
			this.retrieveAgents();
			
			frmOrderEntry.getContentPane().add(agentComboBox);
			
			JButton agentDetailButton = new JButton("+");
			agentDetailButton.setName("agentDetailButton");
			agentDetailButton.addActionListener(this);
			springLayout.putConstraint(SpringLayout.NORTH, agentDetailButton, 0, SpringLayout.NORTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.EAST, agentComboBox, -5, SpringLayout.WEST, agentDetailButton);
			springLayout.putConstraint(SpringLayout.EAST, agentDetailButton, -5, SpringLayout.EAST, frmOrderEntry.getContentPane());
			agentDetailButton.setPreferredSize(new Dimension(41, 22));
			agentDetailButton.setMinimumSize(new Dimension(41, 22));
			agentDetailButton.setMaximumSize(new Dimension(41, 22));
			frmOrderEntry.getContentPane().add(agentDetailButton);
			
			JLabel addressLabel = new JLabel("Address");
			springLayout.putConstraint(SpringLayout.NORTH, addressLabel, 10, SpringLayout.SOUTH, companyNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, addressLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			addressLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			addressLabel.setMaximumSize(new Dimension(100, 22));
			addressLabel.setPreferredSize(new Dimension(90, 22));
			addressLabel.setMinimumSize(new Dimension(80, 22));
			frmOrderEntry.getContentPane().add(addressLabel);
			
			addressTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, addressTextField, 0, SpringLayout.NORTH, addressLabel);
			springLayout.putConstraint(SpringLayout.WEST, addressTextField, 5, SpringLayout.EAST, addressLabel);
			springLayout.putConstraint(SpringLayout.EAST, addressTextField, -5, SpringLayout.EAST, frmOrderEntry.getContentPane());
			addressTextField.setEditable(false);
			frmOrderEntry.getContentPane().add(addressTextField);
			
			JLabel emailLabel = new JLabel("Email");
			springLayout.putConstraint(SpringLayout.WEST, emailLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 10, SpringLayout.SOUTH, addressLabel);
			emailLabel.setMaximumSize(new Dimension(100, 22));
			emailLabel.setPreferredSize(new Dimension(90, 22));
			emailLabel.setMinimumSize(new Dimension(80, 22));
			frmOrderEntry.getContentPane().add(emailLabel);
			
			emailTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, emailTextField, 10, SpringLayout.SOUTH, addressLabel);
			springLayout.putConstraint(SpringLayout.WEST, emailTextField, 5, SpringLayout.EAST, emailLabel);
			emailTextField.setMaximumSize(new Dimension(2147483647, 20));
			emailTextField.setMinimumSize(new Dimension(170, 20));
			emailTextField.setPreferredSize(new Dimension(170, 20));
			emailLabel.setLabelFor(emailTextField);
			frmOrderEntry.getContentPane().add(emailTextField);
			
			
			phoneLabel = new JLabel("Phone");
			springLayout.putConstraint(SpringLayout.WEST, phoneLabel, 5, SpringLayout.EAST, emailTextField);
			springLayout.putConstraint(SpringLayout.NORTH, phoneLabel, 12, SpringLayout.SOUTH, addressTextField);
			phoneLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			phoneLabel.setMaximumSize(new Dimension(100, 22));
			phoneLabel.setPreferredSize(new Dimension(90, 22));
			phoneLabel.setMinimumSize(new Dimension(80, 22));
			frmOrderEntry.getContentPane().add(phoneLabel);
			
			phoneTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, phoneTextField, 12, SpringLayout.SOUTH, addressTextField);
			springLayout.putConstraint(SpringLayout.WEST, phoneTextField, 5, SpringLayout.EAST, phoneLabel);
			phoneTextField.setMinimumSize(new Dimension(170, 20));
			phoneTextField.setMaximumSize(new Dimension(2147483647, 20));
			phoneTextField.setPreferredSize(new Dimension(170, 20));
			phoneLabel.setLabelFor(phoneTextField);
			frmOrderEntry.getContentPane().add(phoneTextField);
			
			mobileLabel = new JLabel("Mobile");
			springLayout.putConstraint(SpringLayout.WEST, mobileLabel, 5, SpringLayout.EAST, phoneTextField);
			springLayout.putConstraint(SpringLayout.NORTH, mobileLabel, 12, SpringLayout.SOUTH, addressTextField);
			mobileLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			
			mobileLabel.setMaximumSize(new Dimension(100, 22));
			mobileLabel.setPreferredSize(new Dimension(90, 22));
			mobileLabel.setMinimumSize(new Dimension(80, 22));
			frmOrderEntry.getContentPane().add(mobileLabel);
			
			mobileTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, mobileTextField, 12, SpringLayout.SOUTH, addressTextField);
			springLayout.putConstraint(SpringLayout.WEST, mobileTextField, 5, SpringLayout.EAST, mobileLabel);
			springLayout.putConstraint(SpringLayout.EAST, frmOrderEntry.getContentPane(), 5, SpringLayout.EAST, mobileTextField);
			mobileTextField.setMinimumSize(new Dimension(170, 20));
			mobileTextField.setMaximumSize(new Dimension(2147483647, 20));
			mobileTextField.setPreferredSize(new Dimension(170, 20));
			mobileLabel.setLabelFor(mobileTextField);
			
			frmOrderEntry.getContentPane().add(mobileTextField);
	
			logger.debug(frmOrderEntry.getContentPane().getBounds());
			
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
