/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.eelections.gui;

import info.akritikos.eelections.DataManager;
import info.akritikos.eelections.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author akritikos
 */
public class frmCandidateManagement extends javax.swing.JFrame {

    /**
     * Creates new form frmCandidateManagement
     */
    public frmCandidateManagement() {
        dm = new DataManager();
//        candidates.addAll(dm.retrieveAll(Candidate.class));
        allCandidates = dm.retrieveAll(Candidate.class);
        parties.addAll(dm.retrieveAll(PoliticalParty.class));
        peripheries.addAll(dm.retrieveAll(ElectoralPeriphery.class));
        initComponents();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCandidates = new javax.swing.JTable();
        btnDeleteOne = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        lblFilterPeriphery = new javax.swing.JLabel();
        lblFilterParty = new javax.swing.JLabel();
        cmbFilterPeriphery = new javax.swing.JComboBox<>();
        cmbFilterParty = new javax.swing.JComboBox<>();
        lblCandidates = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Διαχείριση Υποψηφίων");

        tblCandidates.setColumnSelectionAllowed(true);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${candidates}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblCandidates);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pkCandidateId}"));
        columnBinding.setColumnName("Α/Α");
        columnBinding.setColumnClass(Long.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fldSurname}"));
        columnBinding.setColumnName("Επώνυμο");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fldName}"));
        columnBinding.setColumnName("Όνομα");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tblCandidates);
        tblCandidates.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnDeleteOne.setText("Διαγραφή Επιλεγμένου");
        btnDeleteOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteOneMouseClicked(evt);
            }
        });

        btnDeleteAll.setText("Διαγραφή Όλων");
        btnDeleteAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteAllMouseClicked(evt);
            }
        });

        lblFilterPeriphery.setText("Εκλογική Περιφέρεια:");

        lblFilterParty.setText("Πολιτικό Κόμμα:");

        cmbFilterPeriphery.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${peripheries}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cmbFilterPeriphery);
        bindingGroup.addBinding(jComboBoxBinding);

        cmbFilterPeriphery.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFilterPeripheryItemStateChanged(evt);
            }
        });

        cmbFilterParty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${parties}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cmbFilterParty);
        bindingGroup.addBinding(jComboBoxBinding);

        cmbFilterParty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFilterPartyItemStateChanged(evt);
            }
        });

        lblCandidates.setText("Υποψήφιοι:");

        btnSave.setText("Αποθήκευση Αλλαγών");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });

        btnAdd.setText("Προσθήκη");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFilterPeriphery)
                            .addComponent(lblFilterParty))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbFilterParty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbFilterPeriphery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCandidates)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDeleteAll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteOne)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFilterPeriphery)
                    .addComponent(cmbFilterPeriphery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFilterParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFilterParty))
                .addGap(18, 18, 18)
                .addComponent(lblCandidates)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteOne)
                    .addComponent(btnDeleteAll)
                    .addComponent(btnSave)
                    .addComponent(btnAdd))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * On combo box selection changes, filters the corresponding records from 
     * allCandidates list and refreshes the list bound to tblCandidates
     * @param event 
     */
    private void filterChanged(java.awt.event.ItemEvent event){
        ElectoralPeriphery periphery = peripheries.get(cmbFilterPeriphery.getSelectedIndex());
        PoliticalParty party = parties.get(cmbFilterParty.getSelectedIndex());
        candidates.clear();
        Predicate<Candidate> filter = 
                c->c.getFkPoliticalPartyId() == party && c.getFkElectoralPeripheryId() == periphery;
        candidates.addAll(
                allCandidates.stream().filter(filter).collect(Collectors.toList())
        );
    }
    
    /**
     * Deletes the currently selected candidate
     * @param evt 
     */
    private void btnDeleteOneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteOneMouseClicked
        Candidate c = candidates.get(tblCandidates.getSelectedRow());
        candidates.remove(c);
        allCandidates.remove(c);
        dm.delete(c);
    }//GEN-LAST:event_btnDeleteOneMouseClicked

    /**
     * Null method, if the triggering event was an item selection, forward
     * the event to the proper handler
     * @param evt 
     */
    private void cmbFilterPeripheryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFilterPeripheryItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            filterChanged(evt);
        }
    }//GEN-LAST:event_cmbFilterPeripheryItemStateChanged

    /**
     * Null method, if the triggering event was an item selection, forward 
     * the event to the proper handler
     * @param evt 
     */
    private void cmbFilterPartyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFilterPartyItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            filterChanged(evt);
        }
    }//GEN-LAST:event_cmbFilterPartyItemStateChanged

    /**
     * Deletes all current candidates
     * @param evt 
     */
    private void btnDeleteAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllMouseClicked
        allCandidates.forEach(c->dm.delete(c));
        allCandidates.clear();
        candidates.clear();
    }//GEN-LAST:event_btnDeleteAllMouseClicked

    /**
     * Adds a new candidate to the list and passes focus to that entry on the 
     * table.
     * @param evt 
     */
    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        ElectoralPeriphery periphery = peripheries.get(cmbFilterPeriphery.getSelectedIndex());
        PoliticalParty party = parties.get(cmbFilterParty.getSelectedIndex());
        Candidate c = new Candidate("","",periphery,party);
        candidates.add(c);
        tblCandidates.requestFocus();
        tblCandidates.editCellAt(candidates.size()-1, 1);
    }//GEN-LAST:event_btnAddMouseClicked

    /**
     * Saves all changes added to the table, ignores entries with null 
     * name/surname since they haven't been edited after being added
     * @param evt 
     */
    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        candidates.forEach(c->{
            if (!c.getFldName().equals("") && !c.getFldSurname().equals(""))
                dm.saveChanges(c);
        });
    }//GEN-LAST:event_btnSaveMouseClicked

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
            java.util.logging.Logger.getLogger(frmCandidateManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCandidateManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCandidateManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCandidateManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCandidateManagement().setVisible(true);
            }
        });
    }
    
    private final DataManager dm;
    private final List<ElectoralPeriphery> peripheries = ObservableCollections.observableList(new ArrayList<>());;
    private final List<PoliticalParty> parties = ObservableCollections.observableList(new ArrayList<>());;
    private final List<Candidate> candidates = ObservableCollections.observableList(new ArrayList<>());
    private List<Candidate> allCandidates;
    
    // <editor-fold defaultstate="collapsed" desc="Binding Properties">
    public DataManager getDataManager(){
        return dm;
    }
    public List<ElectoralPeriphery> getPeripheries(){
        return peripheries;
    }
    public List<PoliticalParty> getParties(){
        return parties;
    }
    public List<Candidate> getCandidates(){
        return candidates;
    }
    // </editor-fold>
            
    // <editor-fold defaultstate="collapsed" desc="GUI generated variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JButton btnDeleteOne;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbFilterParty;
    private javax.swing.JComboBox<String> cmbFilterPeriphery;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCandidates;
    private javax.swing.JLabel lblFilterParty;
    private javax.swing.JLabel lblFilterPeriphery;
    private javax.swing.JTable tblCandidates;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}