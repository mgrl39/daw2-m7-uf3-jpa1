package app;

import java.util.List;

import entidades.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class App {

	private static final String NAME_PERSISTENCE_UNIT = "UPEstudiantes";

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(NAME_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();

		Estudiante e1 = new Estudiante();
		e1.setNombre("Margarita Gonzalez");

		// insertar(em, e1);
		// actualizar(em);

		//listar(em);
		buscar(em, 1);
		buscarNombrada(em, 1);
		em.close();
		emf.close();

		System.out.println("Programa finalizado");
	}

	private static void buscarNombrada(EntityManager em, int codigo) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> qry = em.createNamedQuery("Estudiante.findById", Estudiante.class);
		qry.setParameter("id", codigo);
		Estudiante estudiante = qry.getSingleResult();
		if (estudiante != null) { // si el estudinte esta instanciado
			System.out.println(estudiante);
		} else
			System.out.println("Ningún estudiante encontrado para el código " + codigo);
	}

	private static void buscar(EntityManager em, int codigo) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> qry = em.createQuery("SELECT e FROM Estudiante e WHERE e.id = ?1", Estudiante.class);
		qry.setParameter(1, codigo);
		Estudiante estudiante = qry.getSingleResult();
		if (estudiante != null) { // si el estudinte esta instanciado
			System.out.println(estudiante);
		} else
			System.out.println("Ningún estudiante encontrado para el código " + codigo);
	}

	private static void listar(EntityManager em) {
		TypedQuery<Estudiante> qry = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
		List<Estudiante> resultados = qry.getResultList();
		if (!resultados.isEmpty()) {
			for (Estudiante e : resultados)
				System.out.println(e);
		} else {
			System.out.println("La búsqueda no ha dado resultados");
		}
	}

	private static void actualizar(EntityManager em) {
		Estudiante e1;
		/*
		 * Ahora usamos metodo find() para buscar por ID, Cuando lo recuperamos le
		 * cambiamos el nombre y grabamos con el flush(), ya que sincroniza cualquier
		 * cambio pendiente con la BD.
		 */
		try {
			em.getTransaction().begin();
			// Cogemos al estudiante
			e1 = em.find(Estudiante.class, 1);
			e1.setNombre("Margarita Sanchez");
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			// Si se produce algun error tiro para atras
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}

	private static void insertar(EntityManager em, Estudiante e1) {
		try {
			// Que pasa si queremos grabar este usuario Evaristo Perez? Hay que utilizar
			// EntityTransaction
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
	}

}
