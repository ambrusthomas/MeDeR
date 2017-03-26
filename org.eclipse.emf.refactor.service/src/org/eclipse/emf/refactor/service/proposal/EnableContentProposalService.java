package org.eclipse.emf.refactor.service.proposal;

import com.google.inject.Singleton;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

@Singleton
public class EnableContentProposalService {
	private static final String LCL = "abcdefghijklmnopqrstuvwxyz";
	private static final String UCL = LCL.toUpperCase();
	private static final String NUMS = "0123456789";
	
	public void enableContentProposal(Control control) {
		SimpleContentProposalProvider proposalProvider = null;
		ContentProposalAdapter proposalAdapter = null;
		
		if (control instanceof Combo) {
			Combo combo = (Combo) control;
			proposalProvider = new SimpleContentProposalProvider(combo.getItems());
			proposalAdapter = new ContentProposalAdapter(combo, new ComboContentAdapter(), proposalProvider, getActivationKeystroke(), getAutoactivationChars());
		}
		
		proposalProvider.setFiltering(true);
		proposalAdapter.setPropagateKeys(true);
		proposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
	}

	static char[] getAutoactivationChars() {
		String delete = new String(new char[] { 8 });
		String allChars = LCL + UCL + NUMS + delete;
		
		return allChars.toCharArray();
	}

	static KeyStroke getActivationKeystroke() {
		return KeyStroke.getInstance(new Integer(SWT.CTRL).intValue(), new Integer(' ').intValue());
	}
}
