package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
@NamedQueries({
	@NamedQuery(
		name = "Estudiante.findById",
		query = "SELECT e FROM Estudiante e WHERE e.id = :id"),
	@NamedQuery(
		name = "Estudiante.findByNombre",
		query = "SELECT e FROM Estudiante e WHERE e.nombre = :nombre")
})
public class Estudiante {
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + id + " - Nombre: " + nombre;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NOMBRE", length = 100, nullable = false)
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}



}
