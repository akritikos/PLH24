/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.eelections.gui;

import info.akritikos.eelections.contracts.IDBEntities;
import info.akritikos.eelections.utils.DataManager;
import info.akritikos.eelections.utils.PeripherySimulator;
import info.akritikos.eelections.model.*;
import info.akritikos.eelections.utils.XMLElectionResults;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;

/**
 *
 * @author akritikos
 */
public class pElectionsSimulation extends javax.swing.JPanel {

	/**
	 * Creates new form pElectionsSimulation
	 */
	public pElectionsSimulation() {
		initComponents();
		dm = new DataManager();
		countCitizens();
		if (bar != null)
			bar.setVisible(false);
	}

	/**
	 * Gets populated lists from parent frame to speed up loading
	 * @param peripheries
	 * @param parties
	 * @param candidates
	 * @param bar
	 */
	public pElectionsSimulation(frmElections parent, List<ElectoralPeriphery> peripheries, List<PoliticalParty> parties,
								List<Candidate> candidates, JProgressBar bar) {
		dm = new DataManager();
		this.parent = parent;
		this.peripheries = peripheries;
		this.parties = parties;
		this.candidates = candidates;
		this.bar = bar;
		initComponents();
		countCitizens();
	}

	public final int countCitizens() {
		citizens = 0;
		peripheries.forEach((p) -> {
			citizens = citizens + p.getFldRegisteredCitizensCount();
		});
		citizens /= 1000;
		return citizens;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		sldApoxi = new javax.swing.JSlider();
		sldLeyka = new javax.swing.JSlider();
		sldAkyra = new javax.swing.JSlider();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		btnSimulate = new javax.swing.JButton();
		btnStats = new javax.swing.JButton();
		btnToXML = new javax.swing.JButton();

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Παράμετροι Εκλογικής Διαδικασίας");

		sldApoxi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
		sldApoxi.setMajorTickSpacing(10);
		sldApoxi.setMinorTickSpacing(5);
		sldApoxi.setPaintLabels(true);
		sldApoxi.setPaintTicks(true);
		sldApoxi.setToolTipText("");
		sldApoxi.setValue(0);

		sldLeyka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
		sldLeyka.setMajorTickSpacing(10);
		sldLeyka.setMinorTickSpacing(5);
		sldLeyka.setPaintLabels(true);
		sldLeyka.setPaintTicks(true);
		sldLeyka.setToolTipText("");
		sldLeyka.setValue(0);
		sldLeyka.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				sldLeykaMouseReleased(evt);
			}
		});

		sldAkyra.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
		sldAkyra.setMajorTickSpacing(10);
		sldAkyra.setMinorTickSpacing(5);
		sldAkyra.setPaintLabels(true);
		sldAkyra.setPaintTicks(true);
		sldAkyra.setToolTipText("");
		sldAkyra.setValue(0);
		sldAkyra.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				sldAkyraMouseReleased(evt);
			}
		});

		jLabel2.setText("Πιθανότητα Αποχής");

		jLabel3.setText("Πιθανότητα Λευκών");

		jLabel4.setText("Πιθανότητα Άκυρων");

		jLabel5.setText("%");
		jLabel5.setToolTipText("");

		jLabel6.setText("%");

		jLabel7.setText("%");

		org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sldApoxi, org.jdesktop.beansbinding.ELProperty.create("${value}"), jLabel8, org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);

		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sldLeyka, org.jdesktop.beansbinding.ELProperty.create("${value}"), jLabel9, org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);

		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sldAkyra, org.jdesktop.beansbinding.ELProperty.create("${value}"), jLabel10, org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup()
					  .addContainerGap()
					  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
										  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												  .addGroup(jPanel1Layout.createSequentialGroup()
														  .addComponent(jLabel4)
														  .addGap(0, 0, Short.MAX_VALUE))
												  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
														  .addGap(0, 0, Short.MAX_VALUE)
														  .addComponent(jLabel10)
														  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														  .addComponent(jLabel6)))
										  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										  .addComponent(sldAkyra, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
										  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												  .addGroup(jPanel1Layout.createSequentialGroup()
														  .addComponent(jLabel2)
														  .addGap(0, 0, Short.MAX_VALUE))
												  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
														  .addGap(0, 0, Short.MAX_VALUE)
														  .addComponent(jLabel8)
														  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														  .addComponent(jLabel5)))
										  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										  .addComponent(sldApoxi, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
										  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												  .addGroup(jPanel1Layout.createSequentialGroup()
														  .addComponent(jLabel3)
														  .addGap(0, 0, Short.MAX_VALUE))
												  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
														  .addGap(0, 0, Short.MAX_VALUE)
														  .addComponent(jLabel9)
														  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														  .addComponent(jLabel7)))
										  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										  .addComponent(sldLeyka, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
					  .addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup()
					  .addContainerGap()
					  .addComponent(jLabel1)
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										  .addComponent(sldApoxi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										  .addComponent(jLabel5)
										  .addComponent(jLabel8))
								.addComponent(jLabel2))
					  .addGap(18, 18, 18)
					  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										  .addComponent(sldLeyka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										  .addComponent(jLabel7)
										  .addComponent(jLabel9))
								.addComponent(jLabel3))
					  .addGap(18, 18, 18)
					  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										  .addComponent(sldAkyra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										  .addComponent(jLabel6)
										  .addComponent(jLabel10))
								.addComponent(jLabel4))
					  .addContainerGap(103, Short.MAX_VALUE))
		);

		btnSimulate.setText("Έναρξη Προσομοίωσης");
		btnSimulate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnSimulateMouseClicked(evt);
			}
		});

		btnStats.setText("Εμφάνιση Στατιστικών");
		btnStats.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnStatsMouseClicked(evt);
			}
		});

		btnToXML.setText("Εξαγωγή Αποτελεσμάτων");
		btnToXML.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnToXMLMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel2Layout.createSequentialGroup()
					  .addContainerGap()
					  .addComponent(btnSimulate)
					  .addGap(18, 18, 18)
					  .addComponent(btnStats)
					  .addGap(18, 18, 18)
					  .addComponent(btnToXML)
					  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel2Layout.createSequentialGroup()
					  .addContainerGap()
					  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnSimulate)
								.addComponent(btnStats)
								.addComponent(btnToXML))
					  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
					  .addContainerGap()
					  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					  .addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
					  .addContainerGap()
					  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					  .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);

		bindingGroup.bind();
	}// </editor-fold>//GEN-END:initComponents

	private void sldLeykaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sldLeykaMouseReleased
		sldAkyra.setMaximum(100 - sldLeyka.getValue());
	}//GEN-LAST:event_sldLeykaMouseReleased

	private void sldAkyraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sldAkyraMouseReleased
		// TODO add your handling code here:
		sldLeyka.setMaximum(100 - sldAkyra.getValue());
	}//GEN-LAST:event_sldAkyraMouseReleased

	private void btnSimulateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimulateMouseClicked
		// Delete current votes
		List<Class<? extends IDBEntities>> types = new ArrayList<>();
		types.add(Vote.class);
		dm.clearEntities(types);
		if (bar != null) {
			bar.setMaximum(citizens);
			bar.setVisible(true);
		}
		List<PeripherySimulator> threads = new ArrayList<>();
		peripheries.forEach(p-> {
			PeripherySimulator t = new PeripherySimulator(p, bar, sldApoxi.getValue());
			t.setAkyra(sldAkyra.getValue());
			t.setLeyka(sldLeyka.getValue());
			threads.add(t);
		});
		threads.forEach(t-> {
			new Thread(t).start();
		});
	}//GEN-LAST:event_btnSimulateMouseClicked

	private void btnStatsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStatsMouseClicked
		// TODO add your handling code here:
		frmStatistic statistics = new frmStatistic(parent);
		statistics.setVisible(true);
		parent.setEnabled(false);
		parent.setVisible(false);
	}//GEN-LAST:event_btnStatsMouseClicked

	private void btnToXMLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToXMLMouseClicked
		XMLElectionResults districtResults = new XMLElectionResults();
		for (ElectoralPeriphery per : peripheries) {
			List<Candidate> perCandidates = new ArrayList<>(per.getCandidateList());
			XMLElectionResults partiesResults = new XMLElectionResults();
			parties = dm.retrieveAll(PoliticalParty.class);
			parties.sort((p1, p2)-> {
				return dm.countVotes(null, p2, per, PoliticalParty.class) -
				dm.countVotes(null, p1, per, PoliticalParty.class);
			});
			for (PoliticalParty par : parties) {
				int partyVotes = dm.countVotes(null, par, per, PoliticalParty.class);
				XMLElectionResults partyResults = new XMLElectionResults();
				List<Candidate> partyCandidates = new ArrayList<>(
					perCandidates.stream()
					.filter(c->c.getFkPoliticalPartyId().equals(par))
					.collect(Collectors.toList())
				);
				partyCandidates.sort((c1, c2)-> {
					return c2.getVoteList().size() - c1.getVoteList().size();
				});
				for (Candidate c : partyCandidates) {
					int votes = c.getVoteList().size();
					int percentage = 0;
					if (partyVotes != 0)
						percentage = (int) (float)votes * 100 / partyVotes;
					partyResults.put(c.getFldSurname(), votes + ", " + percentage + "%");
				}
				partiesResults.put(par.getFldTitle().replaceAll(" ", "_"), partyResults);
			}
			districtResults.put(per.getFldName().replaceAll(" ", "_"), partiesResults);
		}
		String content = districtResults.toXML();
		JFileChooser c = new JFileChooser();
		int rVal = c.showSaveDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(c.getSelectedFile().getAbsolutePath()))) {
				bw.write(content);
			} catch (IOException ex) {
				Logger.getLogger(pElectionsSimulation.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}//GEN-LAST:event_btnToXMLMouseClicked

	// <editor-fold defaultstate="collapsed" desc="Fields">
	frmElections parent;
	List<ElectoralPeriphery> peripheries = new ArrayList<>();
	List<PoliticalParty> parties = new ArrayList<>();
	List<Candidate> candidates = new ArrayList<>();
	int citizens;
	DataManager dm;
	JProgressBar bar;
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Internal fields">
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnSimulate;
	private javax.swing.JButton btnStats;
	private javax.swing.JButton btnToXML;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JSlider sldAkyra;
	private javax.swing.JSlider sldApoxi;
	private javax.swing.JSlider sldLeyka;
	private org.jdesktop.beansbinding.BindingGroup bindingGroup;
	// End of variables declaration//GEN-END:variables
	// </editor-fold>
}
