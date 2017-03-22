/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.eelections.model;

import info.akritikos.eelections.contracts.IDBEntities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author akritikos
 */
@Entity
@Table(name = "TBL_CANDIDATE")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Candidate.findAll", query = "SELECT c FROM Candidate c")
	, @NamedQuery(name = "Candidate.findByPkCandidateId", query = "SELECT c FROM Candidate c WHERE c.pkCandidateId = :pkCandidateId")
	, @NamedQuery(name = "Candidate.findByFldSurname", query = "SELECT c FROM Candidate c WHERE c.fldSurname = :fldSurname")
	, @NamedQuery(name = "Candidate.findByFldName", query = "SELECT c FROM Candidate c WHERE c.fldName = :fldName")
})
public class Candidate implements Serializable, IDBEntities {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "PK_CANDIDATE_ID")
	private Long pkCandidateId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "FLD_SURNAME")
	private String fldSurname;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "FLD_NAME")
	private String fldName;
	@JoinColumn(name = "FK_ELECTORAL_PERIPHERY_ID", referencedColumnName = "PK_ELECTORAL_PERIPHERY_ID")
	@ManyToOne(optional = false)
	private ElectoralPeriphery fkElectoralPeripheryId;
	@JoinColumn(name = "FK_POLITICAL_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
	@ManyToOne(optional = false)
	private PoliticalParty fkPoliticalPartyId;
	@OneToMany(mappedBy = "fkCandidateId")
	private List<Vote> voteList;

	public Candidate() {
	}

	public Candidate(Long pkCandidateId) {
		this.pkCandidateId = pkCandidateId;
	}

	public Candidate(Long pkCandidateId, String fldSurname, String fldName) {
		this.pkCandidateId = pkCandidateId;
		this.fldSurname = fldSurname;
		this.fldName = fldName;
	}

	public Long getPkCandidateId() {
		return pkCandidateId;
	}

	public void setPkCandidateId(Long pkCandidateId) {
		this.pkCandidateId = pkCandidateId;
	}

	public String getFldSurname() {
		return fldSurname;
	}

	public void setFldSurname(String fldSurname) {
		this.fldSurname = fldSurname;
	}

	public String getFldName() {
		return fldName;
	}

	public void setFldName(String fldName) {
		this.fldName = fldName;
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
		hash += (pkCandidateId != null ? pkCandidateId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Candidate)) {
			return false;
		}
		Candidate other = (Candidate) object;
		if ((this.pkCandidateId == null && other.pkCandidateId != null) || (this.pkCandidateId != null && !this.pkCandidateId.equals(other.pkCandidateId))) {
			return false;
		}
		return true;
	}

	public Candidate(String fldSurname, String fldName, ElectoralPeriphery fkElectoralPeripheryId, PoliticalParty fkPoliticalPartyId) {
		this.fldSurname = fldSurname;
		this.fldName = fldName;
		this.fkElectoralPeripheryId = fkElectoralPeripheryId;
		this.fkPoliticalPartyId = fkPoliticalPartyId;
	}

	@Override
	public String toString() {
		return "info.akritikos.eelections.Candidate[ pkCandidateId=" + pkCandidateId + " ]";
	}

    @Override
    public Integer getID() {
        return pkCandidateId.intValue();
    }

}
