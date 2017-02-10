/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.eelections;

import info.akritikos.eelections.model.ElectoralPeriphery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author akritikos
 */
public class Debug {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eElectionsDB");
        EntityManager em = emf.createEntityManager();
        
        List<ElectoralPeriphery> peripheries;
        Query q = em.createNamedQuery("PoliticalParty.findAll",ElectoralPeriphery.class);
        peripheries=q.getResultList();
        System.out.println();
    }
}
