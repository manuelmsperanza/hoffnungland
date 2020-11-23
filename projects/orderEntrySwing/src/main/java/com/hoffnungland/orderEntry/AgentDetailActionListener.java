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
	private JFrame frame;
	private Agent agent;

	public AgentDetailActionListener(JFrame frame, Agent agent) {
		this.frame = frame;
		this.agent = agent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.traceEntry();
		
		AgentDetailDialog agentDetailDialog = new AgentDetailDialog(frame, agent);
		agentDetailDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		agentDetailDialog.setVisible(true);
		
		logger.traceExit();
	}

}
