/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.eelections.gui;

import info.akritikos.eelections.model.*;
import info.akritikos.eelections.utils.DataManager;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author akritikos
 */
public class frmElections extends javax.swing.JFrame {

	/**
	 * Creates new form frmElections
	 */
	public frmElections() {
		dm = new DataManager();
		initComponents();
		Runnable retrieval = () -> {
			peripheries.addAll(dm.retrieveAll(ElectoralPeriphery.class));
			candidates.addAll(dm.retrieveAll(Candidate.class));
			parties.addAll(dm.retrieveAll(PoliticalParty.class));
		};
		new Thread(retrieval).start();
		progress.setVisible(false);
		layout = (CardLayout)jContent.getLayout();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jContent = new javax.swing.JPanel();
		jButtons = new javax.swing.JPanel();
		btnManage = new javax.swing.JButton();
		btnSimulate = new javax.swing.JButton();
		btnExit = new javax.swing.JButton();
		btnReturn = new javax.swing.JButton();
		progress = new javax.swing.JProgressBar();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jContent.setLayout(new java.awt.CardLayout());
		jContent.add(new info.akritikos.eelections.gui.pElectionsSplash(), "splash");
		jContent.add(new pCandidateManagement(peripheries, parties, candidates), "manage");
		jContent.add(new pElectionsSimulation(this, peripheries, parties, candidates, progress), "simulate");

		btnManage.setText("Διαχείριση Υποψηφίων");
		btnManage.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnManageMouseClicked(evt);
			}
		});

		btnSimulate.setText("Προσομοιωτής Εκλογικής Διαδικασίας");
		btnSimulate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnSimulateMouseClicked(evt);
			}
		});

		btnExit.setText("Έξοδος");
		btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnExitMouseClicked(evt);
			}
		});

		btnReturn.setText("Επιστροφή");
		btnReturn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnReturnMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jButtonsLayout = new javax.swing.GroupLayout(jButtons);
		jButtons.setLayout(jButtonsLayout);
		jButtonsLayout.setHorizontalGroup(
			jButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jButtonsLayout.createSequentialGroup()
					  .addComponent(btnManage)
					  .addGap(18, 18, 18)
					  .addComponent(btnSimulate)
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					  .addComponent(btnReturn)
					  .addGap(18, 18, 18)
					  .addComponent(btnExit)
					  .addContainerGap())
		);
		jButtonsLayout.setVerticalGroup(
			jButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jButtonsLayout.createSequentialGroup()
					  .addContainerGap()
					  .addGroup(jButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnManage)
								.addComponent(btnSimulate)
								.addComponent(btnExit)
								.addComponent(btnReturn))
					  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
					  .addContainerGap()
					  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					  .addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					  .addContainerGap()
					  .addComponent(jContent, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					  .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					  .addComponent(jButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					  .addContainerGap())
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnManageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManageMouseClicked
		changeView("manage");
	}//GEN-LAST:event_btnManageMouseClicked

	private void btnSimulateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimulateMouseClicked
		changeView("simulate");
	}//GEN-LAST:event_btnSimulateMouseClicked

	private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
		System.exit(0);
	}//GEN-LAST:event_btnExitMouseClicked

	private void btnReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnMouseClicked
		changeView("splash");
	}//GEN-LAST:event_btnReturnMouseClicked

	private void changeView(String card) {
		boolean toHome = true;
		if (card.equals("splash")) {
			toHome = true;
		} else {
			toHome = false;
		}
		btnExit.setVisible(toHome);
		btnExit.setEnabled(toHome);
		btnReturn.setVisible(!toHome);
		btnReturn.setEnabled(!toHome);
		layout.show(jContent, card);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(frmElections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(frmElections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(frmElections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(frmElections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new frmElections().setVisible(true);
			}
		});
	}

	DataManager dm;
	List<ElectoralPeriphery> peripheries = ObservableCollections.observableList(new ArrayList<>());
	List<PoliticalParty> parties = ObservableCollections.observableList(new ArrayList<>());
	List<Candidate> candidates = ObservableCollections.observableList(new ArrayList<>());
	CardLayout layout;

	// <editor-fold defaultstate="collapsed" desc="Internal fields">
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnExit;
	private javax.swing.JButton btnManage;
	private javax.swing.JButton btnReturn;
	private javax.swing.JButton btnSimulate;
	private javax.swing.JPanel jButtons;
	private javax.swing.JPanel jContent;
	private javax.swing.JProgressBar progress;
	// End of variables declaration//GEN-END:variables
	// </editor-fold>
}
