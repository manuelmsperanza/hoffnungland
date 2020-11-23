package com.hoffnungland.orderEntry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgentDetailActionListener implements ActionListener {
	
	private static final Logger logger = LogManager.getLogger(AgentDetailActionListener.class);
	private JFrame frame;

	public AgentDetailActionListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.traceEntry();
		
		AgentDetailPanel newAgentPanel = new AgentDetailPanel();
		int option = JOptionPane.showOptionDialog(this.frame, newAgentPanel, "Agent Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if(option == JOptionPane.OK_OPTION) { // pressing OK button
			
		} else {
			logger.traceExit();
			return;
		}
		
		logger.traceExit();
	}

}
