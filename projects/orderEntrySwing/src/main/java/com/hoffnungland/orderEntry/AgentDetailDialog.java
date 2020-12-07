package com.hoffnungland.orderEntry;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoffnungland.orderEntry.entity.Agent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgentDetailDialog extends JDialog implements ActionListener, DocumentListener {
	
	private static final long serialVersionUID = 8113495756257129233L;

	private static final Logger logger = LogManager.getLogger(AgentDetailDialog.class);
	
	private final AgentDetailPanel agentDetailPanel;
	private JButton okButton;
	private String resultAction;
	

	public String getResultAction() {
		return resultAction;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgentDetailDialog dialog = new AgentDetailDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgentDetailDialog(Frame owner, AgentDetailPanel agentDetailPanel) {
		super(owner, true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		this.agentDetailPanel = agentDetailPanel;	
		getContentPane().add(agentDetailPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				this.checkOkButton();
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.agentDetailPanel.addDialogListeners(this);
	}

	public void checkOkButton() {
		logger.traceEntry();
		if(this.okButton == null) {
			logger.traceExit();
			return;
		}
		
		boolean agentEmailIsEmpty = this.agentDetailPanel.getEmailTextField().getText().isEmpty();
		boolean agentNameIsEmpty = this.agentDetailPanel.getNameTextField().getText().isEmpty();
		boolean agentSurnameIsEmpty = this.agentDetailPanel.getSurnameTextField().getText().isEmpty();
		boolean agentUsernameIsEmpty = this.agentDetailPanel.getUsernameTextField().getText().isEmpty();
		if(agentEmailIsEmpty || agentNameIsEmpty ||agentSurnameIsEmpty ||agentUsernameIsEmpty) {
			this.okButton.setEnabled(false);
		} else {
			this.okButton.setEnabled(true);
		}
		
		logger.traceExit();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.traceEntry();
		this.resultAction = e.getActionCommand();
		logger.debug(this.resultAction);		
		this.dispose();
		logger.traceExit();

	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		logger.traceEntry();
		this.checkOkButton();
		logger.traceExit();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		logger.traceEntry();
		this.checkOkButton();
		logger.traceExit();

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		logger.traceEntry();
		logger.traceExit();
	}
	
}
