

import ejb.Courses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String profesor = request.getParameter("profesor");
        int credite = Integer.parseInt(request.getParameter("nrCredite"));

        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        // creare entitate JPA si populare cu datele primite din formular
        Courses course = new Courses();
        course.setNume(nume);
        course.setNrCredite(credite);
        course.setProfesorTitular(profesor);

        // adaugare entitate in baza de date (operatiune de persistenta)
        // se face intr-o tranzactie
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(course);
        transaction.commit();

        // inchidere EntityManager
        em.close();
        factory.close();

        // trimitere raspuns inapoi la client
        response.setContentType("text/html");
        response.getWriter().println("Datele au fost adaugate in baza de date." +
                "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
    }
}
