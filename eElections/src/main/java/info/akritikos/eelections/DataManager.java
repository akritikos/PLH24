/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.eelections;
import info.akritikos.eelections.model.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author akritikos
 */ 
public class DataManager {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("eElectionsDB");
    private EntityManager em;
    
    public DataManager(){
        em = emf.createEntityManager();
    }

    public <T> List<T> retrieveAll(Class<T> type){
		Query retrieve = em.createNamedQuery(type.getName()+".findall",type);
		return retrieve.getResultList();
    }

    public <T> void insert(T element){
    	em.persist(element);
    }

    public <T> T findBy(Class<T> type,Enum implements IQuerySearch t){

    }

    public <T> void delete(T element){

    }


    public void Dispose(){
    	em.close();
    	emf.close();
    }
}
