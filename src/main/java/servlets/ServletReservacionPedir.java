package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DestinoController;




   @WebServlet("/ServletReservacionPedir")
public class ServletReservacionPedir extends HttpServlet {

    private static final long serialVersionUID = 1L;

      public ServletReservacionPedir() {
        super();
   
}
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        DestinoController destino = new DestinoController();

        int id_destino = Integer.parseInt(request.getParameter("id_destino"));
        String id_usuario = request.getParameter("id_usuario");

        String destinoStr = destino.reservar(id_destino, id_usuario); //alquilar()

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(destinoStr);
        out.flush();
        out.close();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

   }