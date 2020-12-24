package com.hoffnungland.orderEntry;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import com.hoffnungland.orderEntry.entity.ShopType;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.hoffnungland.orderEntry.entity.EuCountry;
import javax.swing.JButton;

public class CompanyDetailPanel extends JPanel {
	private JTextField companyNameTextField;
	private JComboBox<ShopType> shopTypeComboBox;
	private JTextField vatCodeTextField;
	private JTextField tinCodeTextField;
	private JComboBox vatCodeCountryComboBox;
	private JComboBox tinCodeCountryComboBox;

	/**
	 * Create the panel.
	 */
	public CompanyDetailPanel() {
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JPanel firstLinePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, firstLinePanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, firstLinePanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, firstLinePanel, 0, SpringLayout.EAST, this);
		add(firstLinePanel);
		SpringLayout firstLineSpringLayout = new SpringLayout();
		firstLinePanel.setLayout(firstLineSpringLayout);
		
		JLabel companyNameLabel = new JLabel("Company Name");
		companyNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		firstLineSpringLayout.putConstraint(SpringLayout.NORTH, companyNameLabel, 10, SpringLayout.NORTH, firstLinePanel);
		firstLineSpringLayout.putConstraint(SpringLayout.WEST, companyNameLabel, 5, SpringLayout.WEST, firstLinePanel);
		firstLineSpringLayout.putConstraint(SpringLayout.SOUTH, firstLinePanel, 0, SpringLayout.SOUTH, companyNameLabel);
		companyNameLabel.setPreferredSize(new Dimension(90, 22));
		companyNameLabel.setMinimumSize(new Dimension(90, 22));
		companyNameLabel.setMaximumSize(new Dimension(90, 22));
		firstLinePanel.add(companyNameLabel);
		
		companyNameTextField = new JTextField();
		firstLineSpringLayout.putConstraint(SpringLayout.NORTH, companyNameTextField, 1, SpringLayout.NORTH, companyNameLabel);
		companyNameTextField.setMinimumSize(new Dimension(150, 20));
		companyNameLabel.setLabelFor(companyNameTextField);
		companyNameTextField.setPreferredSize(new Dimension(200, 20));
		firstLineSpringLayout.putConstraint(SpringLayout.WEST, companyNameTextField, 5, SpringLayout.EAST, companyNameLabel);
		firstLinePanel.add(companyNameTextField);
		
		JLabel shopTypeLabel = new JLabel("Shop Type");
		shopTypeLabel.setMinimumSize(new Dimension(90, 22));
		shopTypeLabel.setPreferredSize(new Dimension(90, 22));
		shopTypeLabel.setMaximumSize(new Dimension(90, 22));
		shopTypeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		firstLineSpringLayout.putConstraint(SpringLayout.NORTH, shopTypeLabel, 0, SpringLayout.NORTH, companyNameLabel);
		firstLineSpringLayout.putConstraint(SpringLayout.WEST, shopTypeLabel, 5, SpringLayout.EAST, companyNameTextField);
		firstLinePanel.add(shopTypeLabel);
		
		shopTypeComboBox = new JComboBox<ShopType>();
		shopTypeComboBox.setPreferredSize(new Dimension(200, 22));
		shopTypeComboBox.setMinimumSize(new Dimension(150, 22));
		shopTypeComboBox.setModel(new DefaultComboBoxModel(ShopType.values()));
		shopTypeLabel.setLabelFor(shopTypeComboBox);
		firstLineSpringLayout.putConstraint(SpringLayout.NORTH, shopTypeComboBox, 0, SpringLayout.NORTH, companyNameLabel);
		firstLineSpringLayout.putConstraint(SpringLayout.WEST, shopTypeComboBox, 5, SpringLayout.EAST, shopTypeLabel);
		firstLineSpringLayout.putConstraint(SpringLayout.EAST, firstLinePanel, 5, SpringLayout.EAST, shopTypeComboBox);
		
		firstLinePanel.add(shopTypeComboBox);
		
		JPanel secondLinePanel = new JPanel();
		SpringLayout secondLineSpringLayout = new SpringLayout();
		secondLinePanel.setLayout(secondLineSpringLayout);
		springLayout.putConstraint(SpringLayout.NORTH, secondLinePanel, 10, SpringLayout.SOUTH, firstLinePanel);
		springLayout.putConstraint(SpringLayout.WEST, secondLinePanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, secondLinePanel, 0, SpringLayout.EAST, this);
		add(secondLinePanel);
		
		JLabel vatCodeLabel = new JLabel("Vat Code");
		vatCodeLabel.setMaximumSize(new Dimension(90, 22));
		vatCodeLabel.setMinimumSize(new Dimension(90, 22));
		vatCodeLabel.setPreferredSize(new Dimension(90, 22));
		secondLineSpringLayout.putConstraint(SpringLayout.NORTH, vatCodeLabel, 0, SpringLayout.NORTH, secondLinePanel);
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, vatCodeLabel, 0, SpringLayout.WEST, secondLinePanel);
		secondLineSpringLayout.putConstraint(SpringLayout.SOUTH, secondLinePanel, 0, SpringLayout.SOUTH, vatCodeLabel);
		vatCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		secondLinePanel.add(vatCodeLabel);
		
		vatCodeCountryComboBox = new JComboBox();
		vatCodeCountryComboBox.setMaximumSize(new Dimension(30, 32767));
		vatCodeCountryComboBox.setModel(new DefaultComboBoxModel(EuCountry.values()));
		secondLineSpringLayout.putConstraint(SpringLayout.NORTH, vatCodeCountryComboBox, 0, SpringLayout.NORTH, secondLinePanel);
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, vatCodeCountryComboBox, 5, SpringLayout.EAST, vatCodeLabel);
		secondLinePanel.add(vatCodeCountryComboBox);
		
		vatCodeTextField = new JTextField();
		secondLineSpringLayout.putConstraint(SpringLayout.NORTH, vatCodeTextField, 0, SpringLayout.NORTH, secondLinePanel);
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, vatCodeTextField, 5, SpringLayout.EAST, vatCodeCountryComboBox);
		vatCodeLabel.setLabelFor(vatCodeTextField);
		secondLinePanel.add(vatCodeTextField);
		
		JButton checkVatButton = new JButton(new String(Character.toChars(App.emojiQuestionMark)));
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, checkVatButton, 5, SpringLayout.EAST, vatCodeTextField);
		secondLinePanel.add(checkVatButton);
		
		JLabel tinCodeLabel = new JLabel("TIN Code");
		secondLineSpringLayout.putConstraint(SpringLayout.NORTH, tinCodeLabel, 0, SpringLayout.NORTH, secondLinePanel);
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, tinCodeLabel, 5, SpringLayout.EAST, checkVatButton);
		tinCodeLabel.setPreferredSize(new Dimension(90, 22));
		tinCodeLabel.setMinimumSize(new Dimension(90, 22));
		tinCodeLabel.setMaximumSize(new Dimension(90, 22));
		tinCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		secondLinePanel.add(tinCodeLabel);
		
		tinCodeCountryComboBox = new JComboBox();
		tinCodeCountryComboBox.setMaximumSize(new Dimension(30, 32767));
		tinCodeCountryComboBox.setModel(new DefaultComboBoxModel(EuCountry.values()));
		secondLineSpringLayout.putConstraint(SpringLayout.NORTH, tinCodeCountryComboBox, 0, SpringLayout.NORTH, secondLinePanel);
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, tinCodeCountryComboBox, 5, SpringLayout.EAST, tinCodeLabel);
		secondLinePanel.add(tinCodeCountryComboBox);
		
		tinCodeTextField = new JTextField();
		secondLineSpringLayout.putConstraint(SpringLayout.NORTH, tinCodeTextField, 0, SpringLayout.NORTH, secondLinePanel);
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, tinCodeTextField, 5, SpringLayout.EAST, tinCodeCountryComboBox);
		
		JButton checkTinButton = new JButton(new String(Character.toChars(App.emojiQuestionMark)));
		secondLineSpringLayout.putConstraint(SpringLayout.WEST, checkTinButton, 5, SpringLayout.EAST, tinCodeTextField);
		secondLinePanel.add(checkTinButton);
		
		secondLineSpringLayout.putConstraint(SpringLayout.EAST, secondLinePanel, 5, SpringLayout.EAST, checkTinButton);
		tinCodeLabel.setLabelFor(tinCodeTextField);
		secondLinePanel.add(tinCodeTextField);
		

		
		

	}
}
