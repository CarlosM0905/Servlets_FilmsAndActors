/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import database.RegisterInDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Actor;
import model.Film;

/**
 *
 * @author Carlos Rodriguez
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    RegisterInDB registerDB = new RegisterInDB();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        

        String title = request.getParameter("title");
        String language = request.getParameter("language");
        int rentalDuration = Integer.parseInt(request.getParameter("rental_duration"));
        double rentalRate = Double.parseDouble(request.getParameter("rental_rate"));
        double replacementCost = Double.parseDouble(request.getParameter("replacement_cost"));
        
        Film film = new Film(title, language, rentalDuration, rentalRate, replacementCost);
        
        String actorFirstName1 = request.getParameter("actor_first_name_1");
        String actorFirstName2 = request.getParameter("actor_first_name_2");
        String actorFirstName3 = request.getParameter("actor_first_name_3");
        
        String actorLastName1 = request.getParameter("actor_last_name_1");
        String actorLastName2 = request.getParameter("actor_last_name_2");
        String actorLastName3 = request.getParameter("actor_last_name_3");

        Actor actors[] = {new Actor(actorFirstName1, actorLastName1),
                          new Actor(actorFirstName2, actorLastName2),
                          new Actor(actorFirstName3, actorLastName3)
                         };
        
        
//        registerDB.registerFilm(film, actors);
        response.sendRedirect(request.getContextPath() + "/FilmAdded.jsp");

        
        
        System.out.println(title +" "+ language +" "+ rentalDuration +" "+ rentalRate +" "+ replacementCost);
        System.out.println(actorFirstName1 + " " + actorLastName1);
        System.out.println(actorFirstName2 + " " + actorLastName2);
        System.out.println(actorFirstName3 + " " + actorLastName3);
        
//        String usuario = request.getParameter("usuario");
//        String contrasenia = request.getParameter("contrasenia");
//
//        System.out.println("Usuario:" + usuario);
//        System.out.println("Contraseña:" + contrasenia);
//
//        PrintWriter out = response.getWriter();
//        out.print("<html>");
//        out.print("<body>");
//        out.print("El parametro del usuario es: " + usuario);
//        out.print("<br/>");
//        out.print("El paràmetro de la contrasenia es: "
//                + contrasenia);
//
//        out.print("</body>");
//        out.print("</html>");
//        out.close();
    }
}
