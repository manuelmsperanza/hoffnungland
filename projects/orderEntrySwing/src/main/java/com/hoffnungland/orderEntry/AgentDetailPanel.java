package com.hoffnungland.orderEntry;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class AgentDetailPanel extends JPanel {
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JTextField nameTextField;
	private JTextField surnameTextField;

	/**
	 * Create the panel.
	 */
	public AgentDetailPanel() {
		setMinimumSize(new Dimension(555, 85));
		setPreferredSize(new Dimension(555, 85));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel usernameLabel = new JLabel("username");
		usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		usernameLabel.setPreferredSize(new Dimension(47, 20));
		springLayout.putConstraint(SpringLayout.WEST, usernameLabel, 10, SpringLayout.WEST, this);
		add(usernameLabel);
		
		usernameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 0, SpringLayout.NORTH, usernameLabel);
		springLayout.putConstraint(SpringLayout.WEST, usernameTextField, 5, SpringLayout.EAST, usernameLabel);
		usernameLabel.setLabelFor(usernameTextField);
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
		add(emailTextField);
		emailTextField.setColumns(30);
		
		JLabel nameLabel = new JLabel("name");
		nameLabel.setPreferredSize(new Dimension(47, 20));
		nameLabel.setMinimumSize(new Dimension(47, 20));
		nameLabel.setMaximumSize(new Dimension(47, 20));
		nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 10, SpringLayout.SOUTH, nameLabel);
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, usernameLabel);
		add(nameLabel);
		
		nameTextField = new JTextField();
		nameLabel.setLabelFor(nameTextField);
		springLayout.putConstraint(SpringLayout.NORTH, nameTextField, 0, SpringLayout.NORTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, nameTextField, 0, SpringLayout.WEST, usernameTextField);
		add(nameTextField);
		nameTextField.setColumns(20);
		
		JLabel surnameLabel = new JLabel("surname");
		surnameLabel.setPreferredSize(new Dimension(47, 20));
		surnameLabel.setMinimumSize(new Dimension(47, 20));
		surnameLabel.setMaximumSize(new Dimension(47, 20));
		surnameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		springLayout.putConstraint(SpringLayout.NORTH, surnameLabel, 0, SpringLayout.NORTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, surnameLabel, 0, SpringLayout.WEST, emailLabel);
		add(surnameLabel);
		
		surnameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, surnameTextField, 0, SpringLayout.NORTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, surnameTextField, 0, SpringLayout.WEST, emailTextField);
		springLayout.putConstraint(SpringLayout.EAST, surnameTextField, -10, SpringLayout.EAST, this);
		add(surnameTextField);
		surnameTextField.setColumns(30);

	}
}
