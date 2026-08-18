package app.persistence.daos;

import app.persistence.HibernateConfig;
import app.persistence.entities.Point;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class PointDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();


    public Point create(Point p){
       try(EntityManager em = emf.createEntityManager()) {
           em.getTransaction().begin();
           em.persist(p);
           em.getTransaction().commit();
           return p;
       }  }
       public static void main(String[] args) {
           PointDAO pointDAO = new PointDAO();
           Point returnPoint = pointDAO.create(new Point(10,30));
            System.out.println(returnPoint);
            returnPoint.setX(1000);
           Point updatedPoint = pointDAO.update(returnPoint);
           System.out.println(updatedPoint);

           int deletedID = pointDAO.delete(updatedPoint.getId());
           System.out.println("deleted id "+deletedID);

           for (int i = 0; i < 1000; i++) {
               Point p = new Point(i, i);
              pointDAO.create(p);
           }


           pointDAO.readAll().forEach(p -> System.out.println(p));
           Point foundPoint = pointDAO.readById(999);
           System.out.println(foundPoint);

        }

        public Point update(Point p) {
            try (EntityManager em = emf.createEntityManager()) {
                Point foundPoint = em.find(Point.class, p.getId());
                if (foundPoint == null) {
                    throw new EntityNotFoundException("Entity with id" + p.getId() + "was not found in the database");
                }
                em.getTransaction().begin();
               p = em.merge(p);
                em.getTransaction().commit();

            }
            return p;
        }
        public int delete(int id){
        try(EntityManager em = emf.createEntityManager()) {
            Point foundPoint = em.find(Point.class, id);
            if (foundPoint == null) {
                throw new EntityNotFoundException("Entity with id" + id + "was not found in the database");
            }
            em.getTransaction().begin();
            em.remove(foundPoint);
            em.getTransaction().commit();

        }  return id;


}
    public List<Point> readAll(){
        try(EntityManager em = emf.createEntityManager()){
           TypedQuery<Point> query = em.createQuery("Select p FROM Point p", Point.class);
           return query.getResultList();

        }

}
    public Point readById(int id){
        try(EntityManager em = emf.createEntityManager()) {
            Point found = em.find(Point.class,id);
            em.close();
            return found;

        }

    }


}


  /*  public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
       /* em.getTransaction().begin();
        // em.persist(new Point(9, 3));


        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);


            // Close the database connection:
            em.close();
            emf.close();
        }
        */





