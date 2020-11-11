package com.hoffnungland;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.MetadataSources;
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
		ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().build();

		MetadataSources sources = new MetadataSources(standardRegistry);
		
		
		logger.traceExit();
	}

}
