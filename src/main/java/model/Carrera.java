package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="carrera")
@Data
public class Carrera {
	@Id
	private int cod_carrera;
	private String nombre_carrera;
	private double precio;
}
