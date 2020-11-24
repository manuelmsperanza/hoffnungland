package com.hoffnungland.orderEntry;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgentDetailDocumentListener implements DocumentListener {
	
	private static final Logger logger = LogManager.getLogger(AgentDetailDocumentListener.class);
	
	private AgentDetailDialog agentDetailDialog;

	public AgentDetailDocumentListener(AgentDetailDialog agentDetailDialog) {
		this.agentDetailDialog = agentDetailDialog;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		logger.traceEntry();
		this.agentDetailDialog.checkOkButton();
		logger.traceExit();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		logger.traceEntry();
		this.agentDetailDialog.checkOkButton();
		logger.traceExit();

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		logger.traceEntry();
		logger.traceExit();
	}

}
