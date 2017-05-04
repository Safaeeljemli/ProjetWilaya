/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author PC
 */
@Entity
public class Stage implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int typeStage;//1=normal 2=PFE
    @ManyToOne
    private Departement dep;
    @ManyToOne
    private Employee responsable;
    
    @OneToMany(mappedBy = "stage")
    private List<Stagiaire> stagiaires;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTypeStage() {
        return typeStage;
    }

    public void setTypeStage(int typeStage) {
        this.typeStage = typeStage;
    }

    public Departement getDep() {
        return dep;
    }

    public void setDep(Departement dep) {
        this.dep = dep;
    }

    public Employee getResponsable() {
        return responsable;
    }

    public void setResponsable(Employee responsable) {
        this.responsable = responsable;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stage)) {
            return false;
        }
        Stage other = (Stage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Stage[ id=" + id + " ]";
    }
    
}
