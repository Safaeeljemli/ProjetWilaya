/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.History;
import bean.User;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author safa
 */
@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "com.mycompany_ProjetWilaya_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }

    public void createHistoryElement(User loadedUser, int type) {
        History connexionHistory = new History();
        connexionHistory.setUser(loadedUser);
        if (type == 1) {
            connexionHistory.setType(1);
            connexionHistory.setInOutTimeStamp(LocalDateTime.now());
        }
        if (type == 2) {
            connexionHistory.setType(2);
            connexionHistory.setInOutTimeStamp(LocalDateTime.now());
        }
        create(connexionHistory);
    }

    public List<History> findByConditions(String userName, LocalDateTime dateMin, LocalDateTime dateMax, int action) {
        String sqlQuery = "SELECT h FROM History h WHERE 1=1 ";
        if (userName != null && !userName.isEmpty()) {
            sqlQuery += " AND h.user.login = :userName";
        }
        if (action != -1) {
            sqlQuery += " AND h.type = :actionType";
        }
        if (dateMin != null) {
            sqlQuery += " AND h.inOutTimeStamp >= :dateMin";
        }
        if (dateMax != null) {
            sqlQuery += " AND h.inOutTimeStamp <= :dateMax";
        }
        TypedQuery<History> query = getEntityManager().createQuery(sqlQuery, History.class);
        if (sqlQuery.contains("userName")) {
            query.setParameter("userName", userName);
        }

        if (sqlQuery.contains("actionType")) {
            query.setParameter("actionType", action);
        }

        if (sqlQuery.contains("dateMin")) {
            query.setParameter("dateMin", dateMin);
        }

        if (sqlQuery.contains("dateMax")) {
            query.setParameter("dateMax", dateMax);
        }

        return query.getResultList();
    }
}
