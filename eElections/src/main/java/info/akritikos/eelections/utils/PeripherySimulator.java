package info.akritikos.eelections.utils;

import info.akritikos.eelections.model.*;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Simulates the election process on a single electoral periphery using
 * background threads
 * Created by akritikos on 13/03/2017.
 */
public class PeripherySimulator extends SwingWorker<Void, Vote> {
	// <editor-fold defaultstate="collapsed" desc="Internal fields">
	private int apoxi;
	private int leyka;
	private int akyra;
	private JProgressBar bar;
	private DataManager dm;
	private ElectoralPeriphery periphery;
	// </editor-fold>

	/**
	 * Prepares a new PeripherySimulator for running
	 * @param periphery ElectoralPeriphery to work on
	 * @param bar ProgressBar to keep track of progress
	 * @param apoxi Chance for citizens to abstain
	 */
	public PeripherySimulator(ElectoralPeriphery periphery, JProgressBar bar, int apoxi) {
		this.periphery = periphery;
		this.bar = bar;
		this.apoxi = apoxi;
		dm = new DataManager();
//        if ( bar != null) {
		// Update the progress bar on every tick
//            addPropertyChangeListener(new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent evt) {
//                    if ("progress".equals(evt.getPropertyName())) {
//                        bar.setValue(1+bar.getValue());
//                    }
//                }
//            });
//        }
	}

	/**
	 * Sets the chance for people voting to cast an invalid vote
	 * @param akyra Chance to cast an invalid vote [0-100]
	 */
	public void setAkyra(int akyra) {
		if (akyra < 0 || akyra > 100) {
			this.akyra = 0;
		} else {
			this.akyra = (int) ((float) akyra * (100 - apoxi) / 100);
		}
	}

	/**
	 * Sets the chance for people voting to cast a blank vote
	 * @param leyka Chance to cast a blank vote [0-100]
	 */
	public void setLeyka(int leyka) {
		if (leyka < 0 || leyka > 100) {
			this.leyka = 0;
		} else {
			this.leyka = (int) ((float) leyka * (100 - apoxi) / 100);
		}
	}

	private static int rand(int min, int max) {
		return new Random().nextInt(max - min + 1);
	}

	@Override
	protected Void doInBackground() throws Exception {
		List<Vote> votes = new ArrayList<>();
		int citizens = periphery.getFldRegisteredCitizensCount() / 1000;
		for (int i = 0; i < citizens; i++) {
			Vote v = null;
			int cast = rand(0, 100);
			if (cast <= apoxi) {

			} else if (cast <= apoxi + leyka) {
				v = new Vote(true, false, periphery);
			} else if (cast <= apoxi + leyka + akyra) {
				v = new Vote(false, true, periphery);
			} else {
				int roll = rand(0, periphery.getCandidateList().size() - 1);
				Candidate c = periphery.getCandidateList().get(roll);
				PoliticalParty p = c.getFkPoliticalPartyId();
				v = new Vote(c, p, periphery);
			}
			if (v != null) {
//                publish(v);
				votes.add(v);
			}
//            setProgress(100*i/citizens);
		}
		try {
			dm.massSave(votes);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
}
