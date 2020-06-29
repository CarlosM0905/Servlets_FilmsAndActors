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
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");

        System.out.println("Usuario:" + usuario);
        System.out.println("Contraseña:" + contrasenia);

        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<body>");
        out.print("El parametro del usuario es: " + usuario);
        out.print("<br/>");
        out.print("El paràmetro de la contrasenia es: "
                + contrasenia);

        out.print("</body>");
        out.print("</html>");
        out.close();
    }
}
