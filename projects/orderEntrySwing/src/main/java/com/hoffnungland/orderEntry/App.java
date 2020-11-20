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
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class App {

	private static final Logger logger = LogManager.getLogger(App.class);
	private JFrame frame;
	private Session session;
	private JComboBox comboBox;

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
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
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

		//this.initializeHibernate();

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel companyNameLabel = new JLabel("Company Name");
		Dimension companyNamePreferredSize = new Dimension(124, 30);
		companyNameLabel.setPreferredSize(companyNamePreferredSize);
		companyNameLabel.setMinimumSize(companyNamePreferredSize);
		companyNameLabel.setMaximumSize(companyNamePreferredSize);
		springLayout.putConstraint(SpringLayout.NORTH, companyNameLabel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, companyNameLabel, 5, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(companyNameLabel);
		
		comboBox = new JComboBox();
		comboBox.setMaximumSize(new Dimension(32767, 30));
		comboBox.setMinimumSize(new Dimension(350, 30));
		comboBox.setPreferredSize(new Dimension(350, 30));
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 0, SpringLayout.NORTH, companyNameLabel);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 5, SpringLayout.EAST, companyNameLabel);
		frame.getContentPane().add(comboBox);


		logger.traceExit();
	}

	private void initializeHibernate() {
		logger.traceEntry();
		
		BootstrapServiceRegistryBuilder bootstrapServiceRegistryBuilder = new BootstrapServiceRegistryBuilder();
		
		BootstrapServiceRegistry bootstrapRegistry = bootstrapServiceRegistryBuilder.build();

		StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder( bootstrapRegistry );
		
		String resourceName = "./src/main/resources/hibernate.cfg.xml";
		standardRegistryBuilder = standardRegistryBuilder.configure(new File(resourceName));
		//ServiceRegistry standardRegistry = standardRegistryBuilder.configure(new File("hibernate.cfg.xml")).build();
		ServiceRegistry standardRegistry = standardRegistryBuilder.build();

		MetadataSources sources = new MetadataSources(standardRegistry);

		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Agent.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Address.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Customer.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Item.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Order.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Product.class);
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.ProductCategory.class);
		
		Metadata metadata = sources.buildMetadata();

		SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

		SessionFactory sessionFactory = sessionFactoryBuilder.build();

		Session session = sessionFactory.openSession();
		
		logger.traceExit();
	}
	
	private void addAgent() {
		this.session.getTransaction().begin();
		
		com.hoffnungland.orderEntry.entity.Agent agent = new com.hoffnungland.orderEntry.entity.Agent();
		agent.setUserName("manuel.m.speranza");
		
		com.hoffnungland.orderEntry.entity.Customer customer = new com.hoffnungland.orderEntry.entity.Customer();
		customer.setReferent(agent);
		
		this.session.persist(agent);
		this.session.persist(customer);
		this.session.getTransaction().commit();
	}
}
