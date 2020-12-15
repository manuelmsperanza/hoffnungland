package com.hoffnungland.orderEntry;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import com.hoffnungland.orderEntry.entity.ShopType;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CompanyDetailPanel extends JPanel {
	private JTextField companyNameTextField;
	private JComboBox<ShopType> shopTypeComboBox;
	private JTextField vatCodeTextField;
	private JTextField fiscalCodeTextField;

	/**
	 * Create the panel.
	 */
	public CompanyDetailPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel companyNameLabel = new JLabel("Company Name");
		companyNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		springLayout.putConstraint(SpringLayout.NORTH, companyNameLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, companyNameLabel, 10, SpringLayout.WEST, this);
		
		companyNameLabel.setPreferredSize(new Dimension(80, 22));
		companyNameLabel.setMinimumSize(new Dimension(80, 22));
		companyNameLabel.setMaximumSize(new Dimension(100, 22));
		add(companyNameLabel);
		
		companyNameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, companyNameTextField, 1, SpringLayout.NORTH, companyNameLabel);
		companyNameTextField.setMinimumSize(new Dimension(150, 20));
		companyNameLabel.setLabelFor(companyNameTextField);
		companyNameTextField.setPreferredSize(new Dimension(200, 20));
		springLayout.putConstraint(SpringLayout.WEST, companyNameTextField, 5, SpringLayout.EAST, companyNameLabel);
		add(companyNameTextField);
		
		JLabel shopTypeLabel = new JLabel("Shop Type");
		shopTypeLabel.setMinimumSize(new Dimension(70, 22));
		shopTypeLabel.setPreferredSize(new Dimension(60, 22));
		shopTypeLabel.setMaximumSize(new Dimension(70, 22));
		shopTypeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		springLayout.putConstraint(SpringLayout.NORTH, shopTypeLabel, 0, SpringLayout.NORTH, companyNameLabel);
		springLayout.putConstraint(SpringLayout.WEST, shopTypeLabel, 5, SpringLayout.EAST, companyNameTextField);
		add(shopTypeLabel);
		
		shopTypeComboBox = new JComboBox<ShopType>();
		shopTypeLabel.setLabelFor(shopTypeComboBox);
		springLayout.putConstraint(SpringLayout.NORTH, shopTypeComboBox, 0, SpringLayout.NORTH, companyNameLabel);
		springLayout.putConstraint(SpringLayout.WEST, shopTypeComboBox, 5, SpringLayout.EAST, shopTypeLabel);
		springLayout.putConstraint(SpringLayout.EAST, shopTypeComboBox, -10, SpringLayout.EAST, this);
		
		for(ShopType curShopType : ShopType.values()) {
			shopTypeComboBox.addItem(curShopType);
		}
		
		add(shopTypeComboBox);
		
		JLabel vatCodeLabel = new JLabel("Vat Code");
		springLayout.putConstraint(SpringLayout.EAST, vatCodeLabel, 0, SpringLayout.EAST, companyNameLabel);
		vatCodeLabel.setMaximumSize(new Dimension(44, 22));
		vatCodeLabel.setMinimumSize(new Dimension(44, 22));
		vatCodeLabel.setPreferredSize(new Dimension(44, 22));
		springLayout.putConstraint(SpringLayout.NORTH, vatCodeLabel, 10, SpringLayout.SOUTH, companyNameLabel);
		vatCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		springLayout.putConstraint(SpringLayout.WEST, vatCodeLabel, 10, SpringLayout.WEST, this);
		add(vatCodeLabel);
		
		vatCodeTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, vatCodeTextField, 12, SpringLayout.SOUTH, companyNameTextField);
		springLayout.putConstraint(SpringLayout.WEST, vatCodeTextField, 95, SpringLayout.WEST, this);
		vatCodeLabel.setLabelFor(vatCodeTextField);
		add(vatCodeTextField);
		vatCodeTextField.setColumns(20);
		
		JLabel fiscalCodeLabel = new JLabel("Fiscal Code");
		springLayout.putConstraint(SpringLayout.NORTH, fiscalCodeLabel, 10, SpringLayout.SOUTH, shopTypeLabel);
		springLayout.putConstraint(SpringLayout.WEST, fiscalCodeLabel, 0, SpringLayout.WEST, shopTypeLabel);
		springLayout.putConstraint(SpringLayout.EAST, fiscalCodeLabel, 0, SpringLayout.EAST, shopTypeLabel);
		fiscalCodeLabel.setPreferredSize(new Dimension(54, 22));
		fiscalCodeLabel.setMinimumSize(new Dimension(54, 22));
		fiscalCodeLabel.setMaximumSize(new Dimension(54, 22));
		fiscalCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		add(fiscalCodeLabel);
		
		fiscalCodeTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, fiscalCodeTextField, 11, SpringLayout.SOUTH, shopTypeComboBox);
		springLayout.putConstraint(SpringLayout.WEST, fiscalCodeTextField, 5, SpringLayout.EAST, fiscalCodeLabel);
		springLayout.putConstraint(SpringLayout.EAST, fiscalCodeTextField, -10, SpringLayout.EAST, this);
		fiscalCodeLabel.setLabelFor(fiscalCodeTextField);
		add(fiscalCodeTextField);

	}
}
