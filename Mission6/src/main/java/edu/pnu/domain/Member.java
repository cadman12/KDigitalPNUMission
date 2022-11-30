package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pass;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regidate;

    public Member() {
	}

	public Member(String pass, String name) {
		this.id = -1L;
		this.pass = pass;
		this.name = name;
		this.regidate = new Date();
	}
    
	public Member(Long id, String pass, String name, Date regidate) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.regidate = regidate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pass=" + pass + ", name=" + name + ", regidate=" + regidate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}    
}
