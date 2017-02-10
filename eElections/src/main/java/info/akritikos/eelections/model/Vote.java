/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.eelections.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author akritikos
 */
@Entity
@Table(name = "TBL_VOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vote.findAll", query = "SELECT v FROM Vote v")
    , @NamedQuery(name = "Vote.findByPkVoteId", query = "SELECT v FROM Vote v WHERE v.pkVoteId = :pkVoteId")
    , @NamedQuery(name = "Vote.findByFldIsInvalid", query = "SELECT v FROM Vote v WHERE v.fldIsInvalid = :fldIsInvalid")
    , @NamedQuery(name = "Vote.findByFldIsBlank", query = "SELECT v FROM Vote v WHERE v.fldIsBlank = :fldIsBlank")})
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_VOTE_ID")
    private Long pkVoteId;
    @Basic(optional = false)
    @Column(name = "FLD_IS_INVALID")
    private Boolean fldIsInvalid;
    @Basic(optional = false)
    @Column(name = "FLD_IS_BLANK")
    private Boolean fldIsBlank;
    @JoinColumn(name = "FK_CANDIDATE_ID", referencedColumnName = "PK_CANDIDATE_ID")
    @ManyToOne
    private Candidate fkCandidateId;
    @JoinColumn(name = "FK_ELECTORAL_PERIPHERY_ID", referencedColumnName = "PK_ELECTORAL_PERIPHERY_ID")
    @ManyToOne(optional = false)
    private ElectoralPeriphery fkElectoralPeripheryId;
    @JoinColumn(name = "FK_POLITICAL_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private PoliticalParty fkPoliticalPartyId;

    public Vote() {
    }

    public Vote(Long pkVoteId) {
        this.pkVoteId = pkVoteId;
    }

    public Vote(Long pkVoteId, Boolean fldIsInvalid, Boolean fldIsBlank) {
        this.pkVoteId = pkVoteId;
        this.fldIsInvalid = fldIsInvalid;
        this.fldIsBlank = fldIsBlank;
    }

    public Long getPkVoteId() {
        return pkVoteId;
    }

    public void setPkVoteId(Long pkVoteId) {
        this.pkVoteId = pkVoteId;
    }

    public Boolean getFldIsInvalid() {
        return fldIsInvalid;
    }

    public void setFldIsInvalid(Boolean fldIsInvalid) {
        this.fldIsInvalid = fldIsInvalid;
    }

    public Boolean getFldIsBlank() {
        return fldIsBlank;
    }

    public void setFldIsBlank(Boolean fldIsBlank) {
        this.fldIsBlank = fldIsBlank;
    }

    public Candidate getFkCandidateId() {
        return fkCandidateId;
    }

    public void setFkCandidateId(Candidate fkCandidateId) {
        this.fkCandidateId = fkCandidateId;
    }

    public ElectoralPeriphery getFkElectoralPeripheryId() {
        return fkElectoralPeripheryId;
    }

    public void setFkElectoralPeripheryId(ElectoralPeriphery fkElectoralPeripheryId) {
        this.fkElectoralPeripheryId = fkElectoralPeripheryId;
    }

    public PoliticalParty getFkPoliticalPartyId() {
        return fkPoliticalPartyId;
    }

    public void setFkPoliticalPartyId(PoliticalParty fkPoliticalPartyId) {
        this.fkPoliticalPartyId = fkPoliticalPartyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVoteId != null ? pkVoteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.pkVoteId == null && other.pkVoteId != null) || (this.pkVoteId != null && !this.pkVoteId.equals(other.pkVoteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.akritikos.eelections.model.Vote[ pkVoteId=" + pkVoteId + " ]";
    }
    
}
