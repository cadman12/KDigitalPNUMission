package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RequestLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Enumerated(EnumType.STRING)
    private MethodType method;
    private String sqlstring;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regidate;
    private boolean success;

    public RequestLog() {
	}

    public RequestLog(MethodType method, String sqlstring, boolean success) {
		this.id = -1L;
		this.method = method;
		this.sqlstring = sqlstring;
		this.regidate = new Date();
		this.success = success;
	}
    
	public RequestLog(Long id, MethodType method, String sqlstring, Date regidate, boolean success) {
		this.id = id;
		this.method = method;
		this.sqlstring = sqlstring;
		this.regidate = regidate;
		this.success = success;
	}

	@Override
	public String toString() {
		return "RequestLog [id=" + id + ", method=" + method + ", sqlstring=" + sqlstring + ", regidate=" + regidate
				+ ", success=" + success + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MethodType getMethod() {
		return method;
	}

	public void setMethod(MethodType method) {
		this.method = method;
	}

	public String getSqlstring() {
		return sqlstring;
	}

	public void setSqlstring(String sqlstring) {
		this.sqlstring = sqlstring;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}    
}
