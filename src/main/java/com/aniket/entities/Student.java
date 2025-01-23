package com.aniket.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@Table
@FilterDefs({
	@FilterDef(name="cityFilter",parameters=@ParamDef(name="city",type="string")),
	@FilterDef(name="perFilter",parameters=@ParamDef(name="sper",type="double")),
	@FilterDef(name="perCityFilter",parameters= {@ParamDef(name="scity",type="string"),@ParamDef(name="sper",type="double")})
})
@Filters({
	@Filter(name="cityFilter",condition="addr= :city"),
	@Filter(name="perFilter",condition="per>= :sper"),
	@Filter(name="perCityFilter",condition="addr= :scity and per>= :sper")
})
public class Student 
{
	@Id
	private Integer rno;
	@Column
	private String name;
	@Column
	private Double per;
	@Column
	private String addr;
	@Column
	private String bday;
	public Integer getRno() {
		return rno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPer() {
		return per;
	}
	public void setPer(Double per) {
		this.per = per;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
	
	
	
}
