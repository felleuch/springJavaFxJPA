package com.faiez.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by faiez.elleuch on 21/02/14.
 */

@Entity
@Table(name="owners")
public class Owner {


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Id
	@GeneratedValue
	private Integer id;


	private String firstname;

	private String lastname;

	private int  tel;

	@Column(name="shortdesc")
	private String description;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="owner")
	private List<Pet> pets ;



}
