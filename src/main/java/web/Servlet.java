/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos Rodriguez
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request);
        String title = request.getParameter("title");
        String language = request.getParameter("language");
        int rentalDuration = Integer.parseInt(request.getParameter("rental_duration"));
        double rentalRate = Double.parseDouble(request.getParameter("rental_rate"));
        double replacementCost = Double.parseDouble(request.getParameter("replacement_cost"));
        
        String actorFirstName1 = request.getParameter("actor_first_name_1");
        String actorFirstName2 = request.getParameter("actor_first_name_2");
        String actorFirstName3 = request.getParameter("actor_first_name_3");
        
        String actorLastName1 = request.getParameter("actor_last_name_1");
        String actorLastName2 = request.getParameter("actor_last_name_2");
        String actorLastName3 = request.getParameter("actor_last_name_3");

        System.out.println(title +" "+ language +" "+ rentalDuration +" "+ rentalRate +" "+ replacementCost);

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
