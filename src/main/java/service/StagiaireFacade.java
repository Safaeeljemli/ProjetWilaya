/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Ecole;
import bean.Employee;
import bean.Stagiaire;
import controller.util.SearchUtil;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author safa
 */
@Stateless
public class StagiaireFacade extends AbstractFacade<Stagiaire> {

    @PersistenceContext(unitName = "com.mycompany_ProjetWilaya_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StagiaireFacade() {
        super(Stagiaire.class);
    }
     public List<Stagiaire> findStagiaire(int typeStage,Ecole ecole,LocalDateTime dateDebut,LocalDateTime dateFin,Employee encadrant){
         System.out.println("recherche Facade");
        String query="Select s FROM Stagiaire s WHERE 1=1";
        if(typeStage!=0){
            query+=SearchUtil.addConstraint("s", "stage.typeStage", "=", typeStage);
        }
        if(ecole!=null){
            query+=SearchUtil.addConstraint("s", "ecoleStagiaire", "=", ecole);
        }
        if(dateDebut!=null){
            query+=" AND s.dateDebut >= :dateDebut";
        }
         if(dateFin!=null){
            query+=" AND s.dateFint <= :dateFin";
        }
        
        if(encadrant!=null){
                    query+=SearchUtil.addConstraint("s", "encadrant", "=", encadrant);
    }
        return em.createQuery(query).getResultList();
        
    }
}
