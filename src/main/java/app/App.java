package app;

import entidades.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
	
	private static final String NAME_PERSISTENCE_UNIT = "UPEstudiantes";
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(NAME_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		
		Estudiante e1 = new Estudiante();
		e1.setNombre("Evaristo Perez");
		
		try {
		// Que pasa si queremos grabar este usuario Evaristo Perez? Hay que utilizar EntityTransaction
		// Como obtenemos un EntityTransaction? El EntityManager me da uno!!
		// Empezamos una transaccion
		em.getTransaction().begin();
		// Le digo que ersista
		em.persist(e1);
		// Pillamos la transaccion y la configuramos
		em.getTransaction().commit();

		} catch (Exception e) {
			// Si se produce algun error tiro para atras
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		
		/*
		 * Ahora usamos metodo find() para buscar por ID,
		 * Cuando lo recuperamos le cambiamos el nombre y 
		 * grabamos con el flush(), ya que sincroniza cualquier
		 * cambio pendiente con la BD.
		 */
		
		em.getTransaction().begin();
		// Cogemos al estudiante
		e1 = em.find(Estudiante.class, 1);
		e1.setNombre("Pepe Porras");
		em.flush();
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		System.out.println("Programa finalizado");
	}
	
}
