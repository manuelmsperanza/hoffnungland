package com.hoffnungland.orderEntry;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class AgentDetailPanel extends JPanel {
	
	private static final Logger logger = LogManager.getLogger(AgentDetailPanel.class);
	
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	
	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JTextField getEmailTextField() {
		return emailTextField;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JTextField getSurnameTextField() {
		return surnameTextField;
	}

	/**
	 * Create the panel.
	 * @param agentDetailDialog 
	 */
	public AgentDetailPanel(AgentDetailDialog agentDetailDialog) {
		setMinimumSize(new Dimension(555, 85));
		setPreferredSize(new Dimension(555, 85));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel nameLabel = new JLabel("name");
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, this);
		nameLabel.setPreferredSize(new Dimension(47, 20));
		nameLabel.setMinimumSize(new Dimension(47, 20));
		nameLabel.setMaximumSize(new Dimension(47, 20));
		nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, this);
		add(nameLabel);
		
		nameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, nameTextField, 5, SpringLayout.EAST, nameLabel);

		nameLabel.setLabelFor(nameTextField);
		springLayout.putConstraint(SpringLayout.NORTH, nameTextField, 0, SpringLayout.NORTH, nameLabel);
		nameTextField.getDocument().addDocumentListener(new AgentDetailDocumentListener(agentDetailDialog));
		add(nameTextField);
		nameTextField.setColumns(20);
		
		JLabel surnameLabel = new JLabel("surname");
		springLayout.putConstraint(SpringLayout.WEST, surnameLabel, 5, SpringLayout.EAST, nameTextField);
		surnameLabel.setPreferredSize(new Dimension(47, 20));
		surnameLabel.setMinimumSize(new Dimension(47, 20));
		surnameLabel.setMaximumSize(new Dimension(47, 20));
		surnameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		springLayout.putConstraint(SpringLayout.NORTH, surnameLabel, 0, SpringLayout.NORTH, nameLabel);
		add(surnameLabel);
		
		surnameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, surnameTextField, 0, SpringLayout.NORTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, surnameTextField, 5, SpringLayout.EAST, surnameLabel);
		springLayout.putConstraint(SpringLayout.EAST, surnameTextField, -10, SpringLayout.EAST, this);
		surnameTextField.getDocument().addDocumentListener(new AgentDetailDocumentListener(agentDetailDialog));
		add(surnameTextField);
		surnameTextField.setColumns(30);
		
		JLabel usernameLabel = new JLabel("username");
		usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		usernameLabel.setPreferredSize(new Dimension(47, 20));
		springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 10, SpringLayout.SOUTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, usernameLabel, 10, SpringLayout.WEST, this);
		add(usernameLabel);
		
		usernameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 0, SpringLayout.NORTH, usernameLabel);
		springLayout.putConstraint(SpringLayout.WEST, usernameTextField, 5, SpringLayout.EAST, usernameLabel);
		usernameLabel.setLabelFor(usernameTextField);
		usernameTextField.getDocument().addDocumentListener(new AgentDetailDocumentListener(agentDetailDialog));
		add(usernameTextField);
		usernameTextField.setColumns(20);
		
		JLabel emailLabel = new JLabel("email");
		emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		emailLabel.setPreferredSize(new Dimension(47, 20));
		emailLabel.setMinimumSize(new Dimension(47, 20));
		emailLabel.setMaximumSize(new Dimension(47, 20));
		springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 0, SpringLayout.NORTH, usernameLabel);
		springLayout.putConstraint(SpringLayout.WEST, emailLabel, 5, SpringLayout.EAST, usernameTextField);
		add(emailLabel);
		
		emailTextField = new JTextField();
		emailLabel.setLabelFor(emailTextField);
		springLayout.putConstraint(SpringLayout.NORTH, emailTextField, 0, SpringLayout.NORTH, usernameLabel);
		springLayout.putConstraint(SpringLayout.WEST, emailTextField, 5, SpringLayout.EAST, emailLabel);
		springLayout.putConstraint(SpringLayout.EAST, emailTextField, -10, SpringLayout.EAST, this);
		emailTextField.getDocument().addDocumentListener(new AgentDetailDocumentListener(agentDetailDialog));
		add(emailTextField);
		emailTextField.setColumns(30);
		
	}
}
