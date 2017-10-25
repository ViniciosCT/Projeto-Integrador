package controller;

import util.Utilitarios;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ServletCliente extends HttpServlet  {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


/*
        String acao = request.getParameter("acao");
        Utilitarios ut = new Utilitarios();
        ArrayList<String> saida = new ArrayList<String>();
        System.out.println("Aqui:" +acao);

        if (acao.equals("salvar")) {

            String artigo = request.getParameter("artigo");
            ut.indexacao(artigo);

        }
        else{

            String pesquisa = request.getParameter("pesquisa");
            System.out.println(pesquisa);
            try {
                saida = ut.recuperaArtigo(pesquisa);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(saida);

        }

        request.setAttribute("saida", saida);
        RequestDispatcher despachante;
        despachante = request.getServletContext().getRequestDispatcher("/index.jsp");
        despachante.forward(request, response);
        */
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }


}
