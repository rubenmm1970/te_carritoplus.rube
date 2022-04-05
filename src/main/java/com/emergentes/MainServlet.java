package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op.equals("vaciar")) {
            // Vaciar carrito
            HttpSession ses = request.getSession();
            ses.invalidate();
            response.sendRedirect("index.jsp");
        }
        if (op.equals("eliminar")){
            int pos = -1;
            int buscado = -1;
            int id = Integer.parseInt(request.getParameter("id"));
            // Eliminar el producto
            HttpSession ses = request.getSession();
            ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("almacen");
            
            for(Producto p : lista){
                pos++;
                if (p.getId() == id){
                    buscado = pos;
                }                
            }
            lista.remove(buscado);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String producto = request.getParameter("producto");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precio = Double.parseDouble(request.getParameter("precio"));

        Producto prod = new Producto();

        prod.setId(id);
        prod.setProducto(producto);
        prod.setCantidad(cantidad);
        prod.setPrecio(precio);

        HttpSession ses = request.getSession();

        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("almacen");

        lista.add(prod);

        response.sendRedirect("index.jsp");

    }

}
