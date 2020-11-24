package com.hoffnungland.orderEntry;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoffnungland.orderEntry.entity.Agent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgentDetailDialog extends JDialog implements ActionListener {
	
	private static final Logger logger = LogManager.getLogger(AgentDetailDialog.class);
	
	private final AgentDetailPanel agentDetailPanel;
	private JButton okButton;
	private String resultAction;
	private Agent agent;
	
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
	public AgentDetailDialog(Frame owner, Agent agent) {
		super(owner, true);
		this.agent = agent;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		this.agentDetailPanel = new AgentDetailPanel(this);
		if(this.agent != null) {
			this.agentDetailPanel.getNameTextField().setText(this.agent.getName());
			this.agentDetailPanel.getSurnameTextField().setText(this.agent.getSurname());
			this.agentDetailPanel.getUsernameTextField().setText(this.agent.getUserName());
			this.agentDetailPanel.getUsernameTextField().setEnabled(false);
			this.agentDetailPanel.getEmailTextField().setText(this.agent.getEmail());
		}
		
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
	}

	public void checkOkButton() {
		logger.traceEntry();
		
		
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
		if("OK".equals(e.getActionCommand())) {
			if(this.agent == null) {
				this.agent = new Agent();
				this.agent.setUserName(this.agentDetailPanel.getUsernameTextField().getText());
			}
			
			this.agent.setName(this.agentDetailPanel.getNameTextField().getText());
			this.agent.setSurname(this.agentDetailPanel.getSurnameTextField().getText());
			this.agent.setEmail(this.agentDetailPanel.getEmailTextField().getText());
		}
		
		this.dispose();
		logger.traceExit();

	}
	
}
