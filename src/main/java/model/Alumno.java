package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="alumno")
@Data
public class Alumno {
	@Id
	private String cod_alumno;
	private String nombre_alumno;
	private String apellido_alumno;
	private String dni_alumno;
	private String tlf_alumno;
	private int edad_alumno;
	private int cod_carrera;
	
	
	
	@ManyToOne
	@JoinColumn(name="cod_carrera", insertable=false, updatable = false)
	private Carrera objCarrera;
}

