package com.hoffnungland.orderEntry;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.hoffnungland.orderEntry.entity.Agent;
import com.hoffnungland.orderEntry.entity.Customer;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class App implements ActionListener {

	private static final Logger logger = LogManager.getLogger(App.class);
	private Session session;
	private EntityManager entityManager;
	private JFrame frmOrderEntry;
	private JComboBox<String> companyComboBox;
	private JComboBox<String> agentComboBox;
	private Agent agent;
	
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
			//this.initializeHibernate();
			this.initializeJPA();
			
			frmOrderEntry = new JFrame();
			frmOrderEntry.setName("orderEntryFrame");
			frmOrderEntry.setTitle("Order Entry");
			frmOrderEntry.setBounds(100, 100, 800, 600);
			frmOrderEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			SpringLayout springLayout = new SpringLayout();
			frmOrderEntry.getContentPane().setLayout(springLayout);
			
			JLabel companyNameLabel = new JLabel("Company Name");
			companyNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			
			companyNameLabel.setPreferredSize(new Dimension(90, 25));
			companyNameLabel.setMinimumSize(new Dimension(80, 25));
			companyNameLabel.setMaximumSize(new Dimension(100, 25));
			springLayout.putConstraint(SpringLayout.NORTH, companyNameLabel, 10, SpringLayout.NORTH, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, companyNameLabel, 5, SpringLayout.WEST, frmOrderEntry.getContentPane());
			frmOrderEntry.getContentPane().add(companyNameLabel);
			
			companyComboBox = new JComboBox<String>();
			springLayout.putConstraint(SpringLayout.WEST, companyComboBox, 5, SpringLayout.EAST, companyNameLabel);
			companyNameLabel.setLabelFor(companyComboBox);
			companyComboBox.setBorder(UIManager.getBorder("ComboBox.border"));
			companyComboBox.setPreferredSize(new Dimension(200, 25));
			companyComboBox.setMaximumSize(new Dimension(32767, 25));
			companyComboBox.setMinimumSize(new Dimension(150, 25));
			springLayout.putConstraint(SpringLayout.NORTH, companyComboBox, 0, SpringLayout.NORTH, companyNameLabel);
			frmOrderEntry.getContentPane().add(companyComboBox);
			
			JButton newCustomerButton = new JButton("+");
			springLayout.putConstraint(SpringLayout.EAST, companyComboBox, -5, SpringLayout.WEST, newCustomerButton);
			springLayout.putConstraint(SpringLayout.NORTH, newCustomerButton, 0, SpringLayout.NORTH, companyNameLabel);
			newCustomerButton.setPreferredSize(new Dimension(41, 25));
			newCustomerButton.setMinimumSize(new Dimension(41, 25));
			newCustomerButton.setMaximumSize(new Dimension(41, 25));
			frmOrderEntry.getContentPane().add(newCustomerButton);
			
			JLabel agentNameLabel = new JLabel("Agent");
			springLayout.putConstraint(SpringLayout.WEST, agentNameLabel, 400, SpringLayout.WEST, frmOrderEntry.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, newCustomerButton, -5, SpringLayout.WEST, agentNameLabel);
			springLayout.putConstraint(SpringLayout.WEST, agentNameLabel, 5, SpringLayout.HORIZONTAL_CENTER, frmOrderEntry.getContentPane());
			agentNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			agentNameLabel.setPreferredSize(new Dimension(90, 25));
			agentNameLabel.setMinimumSize(new Dimension(80, 25));
			agentNameLabel.setMaximumSize(new Dimension(100, 25));
			springLayout.putConstraint(SpringLayout.NORTH, agentNameLabel, 0, SpringLayout.NORTH, companyNameLabel);
			frmOrderEntry.getContentPane().add(agentNameLabel);
			
			agentComboBox = new JComboBox<String>();
			agentComboBox.setName("agentComboBox");
			agentComboBox.addActionListener(this);
			
			agentComboBox.setEditable(true);
			agentNameLabel.setLabelFor(agentComboBox);
			springLayout.putConstraint(SpringLayout.WEST, agentComboBox, 5, SpringLayout.EAST, agentNameLabel);
			agentComboBox.setPreferredSize(new Dimension(200, 25));
			agentComboBox.setMinimumSize(new Dimension(150, 25));
			agentComboBox.setBorder(UIManager.getBorder("ComboBox.border"));
			agentComboBox.addItem(null);
			this.retrieveAgents();
			
			springLayout.putConstraint(SpringLayout.NORTH, agentComboBox, 0, SpringLayout.NORTH, companyNameLabel);
			frmOrderEntry.getContentPane().add(agentComboBox);
			
			JButton newAgentButton = new JButton("+");
			newAgentButton.setName("agentDetailButton");
			newAgentButton.addActionListener(this);
			springLayout.putConstraint(SpringLayout.EAST, agentComboBox, -5, SpringLayout.WEST, newAgentButton);
			springLayout.putConstraint(SpringLayout.EAST, newAgentButton, -5, SpringLayout.EAST, frmOrderEntry.getContentPane());
			newAgentButton.setPreferredSize(new Dimension(41, 25));
			newAgentButton.setMinimumSize(new Dimension(41, 25));
			newAgentButton.setMaximumSize(new Dimension(41, 25));
			springLayout.putConstraint(SpringLayout.NORTH, newAgentButton, 0, SpringLayout.NORTH, companyNameLabel);
			frmOrderEntry.getContentPane().add(newAgentButton);
			
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
	
	public void retrieveAgent(String agentName) {
		logger.traceEntry();
		
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Agent> q = cb.createQuery(Agent.class);
		Root<Agent> c = q.from(Agent.class);
		q.select(c);
		ParameterExpression<String> p = cb.parameter(String.class);
		q.where(cb.equal(c.get("userName"), p));
		
		TypedQuery<Agent> agentListQuery = this.entityManager.createQuery(q);
		agentListQuery.setParameter(p, agentName);
		
		this.agent = agentListQuery.getSingleResult();
		
		logger.debug(this.agent);
		
		logger.traceExit();
	}

	private void initializeHibernate() {
		logger.traceEntry();
		
		BootstrapServiceRegistryBuilder bootstrapServiceRegistryBuilder = new BootstrapServiceRegistryBuilder();
		
		BootstrapServiceRegistry bootstrapRegistry = bootstrapServiceRegistryBuilder.build();

		StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder( bootstrapRegistry );
		
		String resourceName = "hibernate.cfg.xml";
		standardRegistryBuilder = standardRegistryBuilder.configure(new File(resourceName));
		//ServiceRegistry standardRegistry = standardRegistryBuilder.configure(new File("hibernate.cfg.xml")).build();
		ServiceRegistry standardRegistry = standardRegistryBuilder.build();

		MetadataSources sources = new MetadataSources(standardRegistry);

		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Agent.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Customer.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Address.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Order.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Item.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Product.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.ProductCategory.class);
		
		Metadata metadata = sources.buildMetadata();

		SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

		SessionFactory sessionFactory = sessionFactoryBuilder.build();

		session = sessionFactory.openSession();
				
		logger.traceExit();
	}
	
	private void initializeJPA() {
		logger.traceEntry();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orderEntryDb");		
		this.entityManager = entityManagerFactory.createEntityManager();
		logger.traceExit();
	}
	
	private void addAgentHbn() {
		logger.traceEntry();
		this.session.getTransaction().begin();
		
		com.hoffnungland.orderEntry.entity.Agent agent = new com.hoffnungland.orderEntry.entity.Agent();
		agent.setUserName("valentina.desantis");
		
		com.hoffnungland.orderEntry.entity.Customer customer = new com.hoffnungland.orderEntry.entity.Customer();
		customer.setReferent(agent);
		
		this.session.persist(agent);
		this.session.persist(customer);
		this.session.getTransaction().commit();
		logger.traceExit();
	}
	
	private void addAgentJpa(String name, String surname, String email, String userName) {
		logger.traceEntry();
		this.entityManager.getTransaction().begin();
		this.agent = new com.hoffnungland.orderEntry.entity.Agent();
		this.agent.setEmail(email);
		this.agent.setName(name);
		this.agent.setSurname(surname);
		this.agent.setUserName(userName);
	
		this.entityManager.persist(this.agent);
		this.entityManager.getTransaction().commit();
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
        this.retrieveAgent(agentName);
		logger.traceExit();
	}

	private void saveAgent(Agent agent) {
		logger.traceEntry();
		this.agent = agent;
		this.entityManager.getTransaction().begin();
		logger.info("Persist agent");
		this.entityManager.persist(this.agent);
		this.entityManager.getTransaction().commit();
		this.agentComboBox.addItem(this.agent.getUserName());
		logger.traceExit();
		
	}

	private void showAgentDetailDialog() {
		logger.traceEntry();
		
		try {
			AgentDetailDialog agentDetailDialog = new AgentDetailDialog(this.frmOrderEntry, this.agent);
			agentDetailDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			agentDetailDialog.setVisible(true);
			if("OK".equals(agentDetailDialog.getResultAction())) {
				this.saveAgent(agentDetailDialog.getAgent());
			}
		} catch (Exception e) {
			logger.error(e);
			JOptionPane.showMessageDialog(this.frmOrderEntry, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
		
		logger.traceExit();
	}
}
