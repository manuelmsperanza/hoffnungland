package com.hoffnungland.orderEntry;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;

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

public class App {

	private static final Logger logger = LogManager.getLogger(App.class);
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		logger.traceEntry();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
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

		this.initializeHibernate();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
		sources.addAnnotatedClass(com.hoffnungland.orderEntry.entity.Product.class);
		
		Metadata metadata = sources.buildMetadata();

		SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

		SessionFactory sessionFactory = sessionFactoryBuilder.build();

		Session session = sessionFactory.openSession();

		session.getTransaction().begin();
		
		com.hoffnungland.orderEntry.entity.Agent agent = new com.hoffnungland.orderEntry.entity.Agent();
		agent.setUserName("manuel.m.speranza");
		
		com.hoffnungland.orderEntry.entity.Customer customer = new com.hoffnungland.orderEntry.entity.Customer();
		customer.setReferent(agent);
		
		session.persist(agent);
		session.persist(customer);
		session.getTransaction().commit();

		logger.traceExit();
	}

}
