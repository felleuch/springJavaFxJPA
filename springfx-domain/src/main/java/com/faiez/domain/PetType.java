package com.faiez.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by faiez.elleuch on 19/02/14.
 */
@Entity
@Table(name="pet_type")
public class PetType {


	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	private Integer id;


	private String name;


	@OneToMany(fetch = FetchType.EAGER,mappedBy="petType")
	private List<Pet> pets ;


}
