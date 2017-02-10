/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.evoting.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author akritikos
 */
@Entity
@Table(name = "TBL_POLITICAL_PARTY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoliticalParty.findAll", query = "SELECT p FROM PoliticalParty p")
    , @NamedQuery(name = "PoliticalParty.findByPkPartyId", query = "SELECT p FROM PoliticalParty p WHERE p.pkPartyId = :pkPartyId")
    , @NamedQuery(name = "PoliticalParty.findByFldTitle", query = "SELECT p FROM PoliticalParty p WHERE p.fldTitle = :fldTitle")
    , @NamedQuery(name = "PoliticalParty.findByFldLeaderfullname", query = "SELECT p FROM PoliticalParty p WHERE p.fldLeaderfullname = :fldLeaderfullname")})
public class PoliticalParty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_PARTY_ID")
    private Integer pkPartyId;
    @Basic(optional = false)
    @Column(name = "FLD_TITLE")
    private String fldTitle;
    @Basic(optional = false)
    @Column(name = "FLD_LEADERFULLNAME")
    private String fldLeaderfullname;
    @JoinColumn(name = "FK_ELECTORAL_PERIPHERY_ID", referencedColumnName = "PK_ELECTORAL_PERIPHERY_ID")
    @ManyToOne(optional = false)
    private ElectoralPeriphery fkElectoralPeripheryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPoliticalPartyId")
    private List<Candidate> candidateList;
    @OneToMany(mappedBy = "fkPoliticalPartyId")
    private List<Vote> voteList;

    public PoliticalParty() {
    }

    public PoliticalParty(Integer pkPartyId) {
        this.pkPartyId = pkPartyId;
    }

    public PoliticalParty(Integer pkPartyId, String fldTitle, String fldLeaderfullname) {
        this.pkPartyId = pkPartyId;
        this.fldTitle = fldTitle;
        this.fldLeaderfullname = fldLeaderfullname;
    }

    public Integer getPkPartyId() {
        return pkPartyId;
    }

    public void setPkPartyId(Integer pkPartyId) {
        this.pkPartyId = pkPartyId;
    }

    public String getFldTitle() {
        return fldTitle;
    }

    public void setFldTitle(String fldTitle) {
        this.fldTitle = fldTitle;
    }

    public String getFldLeaderfullname() {
        return fldLeaderfullname;
    }

    public void setFldLeaderfullname(String fldLeaderfullname) {
        this.fldLeaderfullname = fldLeaderfullname;
    }

    public ElectoralPeriphery getFkElectoralPeripheryId() {
        return fkElectoralPeripheryId;
    }

    public void setFkElectoralPeripheryId(ElectoralPeriphery fkElectoralPeripheryId) {
        this.fkElectoralPeripheryId = fkElectoralPeripheryId;
    }

    @XmlTransient
    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    @XmlTransient
    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPartyId != null ? pkPartyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoliticalParty)) {
            return false;
        }
        PoliticalParty other = (PoliticalParty) object;
        if ((this.pkPartyId == null && other.pkPartyId != null) || (this.pkPartyId != null && !this.pkPartyId.equals(other.pkPartyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.akritikos.evoting.model.PoliticalParty[ pkPartyId=" + pkPartyId + " ]";
    }

    public PoliticalParty(String fldTitle, String fldLeaderfullname, ElectoralPeriphery fkElectoralPeripheryId){
        this.fldTitle = fldTitle;
        this.fldLeaderfullname = fldLeaderfullname;
        this.fkElectoralPeripheryId = fkElectoralPeripheryId;
    }
}
