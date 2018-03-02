package com.nalashaa.junitTest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = Employee.TABLE)
public class Employee {

	public static final String TABLE = "EMPLOYEE";
	public static final String EMPLOYEE = "employee";
	public static final String FK_EMPLOYEE = "fk_employee";

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COMPANY")
	private String company;

	@JoinColumn(name = FK_EMPLOYEE)
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Skill> skillList = new ArrayList<Skill>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}
}
