package com.hoffnungland.orderEntry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoffnungland.orderEntry.entity.Agent;

public class AgentDetailActionListener implements ActionListener {
	
	private static final Logger logger = LogManager.getLogger(AgentDetailActionListener.class);
	private App app;

	public AgentDetailActionListener(App app) {
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.traceEntry();
		
		AgentDetailDialog agentDetailDialog = new AgentDetailDialog(this.app.getFrame(), this.app.getAgent());
		agentDetailDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		agentDetailDialog.setVisible(true);
		if("OK".equals(agentDetailDialog.getResultAction())) {
			this.app.saveAgent(agentDetailDialog.getAgent());
		}
		
		logger.traceExit();
	}

}
