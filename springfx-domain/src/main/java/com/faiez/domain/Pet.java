package com.faiez.domain;

import javax.persistence.*;

/**
 * Created by faiez.elleuch on 19/02/14.

 */
@Entity
@Table(name="pets")
public class Pet {


	public Integer getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(Integer ownerid) {
		this.ownerid = ownerid;
	}

	@Id
	@GeneratedValue

	private Integer id;


	@Transient
	private Integer ownerid;

	private String name;

	private String color;


	@ManyToOne
	private Owner owner;

	private String image;

	@Column(name="shortdesc")
	private String description;


	@ManyToOne
	private PetType petType;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
