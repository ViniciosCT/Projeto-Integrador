package controller;

import dao.ClienteDAO;
import model.Cliente;
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

        String acao = request.getParameter("acao");
        System.out.println("Ação: " + acao);
        ClienteDAO clidao = new ClienteDAO();
        String pagina = "/index.jsp";

        if(acao.equals("listar")){

            ArrayList<Cliente> clientes = clidao.getClientes();

            pagina = "/WEB-INF/clientes.jsp";
            request.setAttribute("clientes", clientes);

        }
        else if(acao.equals("editarRedirect")){
            Cliente cli = clidao.getCliente(Integer.parseInt(request.getParameter("codigo")));
            pagina = "/WEB-INF/alterarCliente.jsp";
            request.setAttribute("cliente", cli);
        }
        else if(acao.equals("editar")){

            Cliente cli = new Cliente();
            cli.setNome(request.getParameter("nome"));
            cli.setEmail(request.getParameter("email"));
            cli.setTelefone(request.getParameter("telefone"));
            cli.setCodigo(Integer.parseInt(request.getParameter("codigo")));

            clidao.atualizaCliente(cli);

            ArrayList<Cliente> clientes = clidao.getClientes();
            pagina = "/WEB-INF/clientes.jsp";
            request.setAttribute("clientes", clientes);

        }
        else if(acao.equals("novoRedirect")){
            pagina = "/WEB-INF/novoCliente.jsp";
        }
        else if(acao.equals("novo")){

            Cliente cli = new Cliente();
            cli.setNome(request.getParameter("nome"));
            cli.setEmail(request.getParameter("email"));
            cli.setTelefone(request.getParameter("telefone"));

            clidao.novoCliente(cli);
            ArrayList<Cliente> clientes = clidao.getClientes();

            pagina = "/WEB-INF/clientes.jsp";
            request.setAttribute("clientes", clientes);

        }
        else if(acao.equals("deletar")){
            clidao.deleteCliente( Integer.parseInt( request.getParameter("codigo") ) );
            ArrayList<Cliente> clientes = clidao.getClientes();

            pagina = "/WEB-INF/clientes.jsp";
            request.setAttribute("clientes", clientes);
        }

        RequestDispatcher despachante;
        despachante = request.getServletContext().getRequestDispatcher(pagina);
        despachante.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }


}
