package web;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.apache.jasper.compiler.NewlineReductionServletWriter;

import dao.IProduitDao;
import dao.ProduitDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Produit;
@WebServlet(name = "produitservlet", urlPatterns = "*.ca")
public class ControleurServlet extends HttpServlet {
	private IProduitDao metier;
	@Override
	public void init() throws ServletException {
		metier=new ProduitDaoImpl();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if (path.equals("/index.ca")) {
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		else if (path.equals("/chercher.ca")) {
			String mc=request.getParameter("motCle");
			ProduitModel model=new ProduitModel();
			model.setMotCle(mc);
			List<Produit> produits=metier.produitParMC("%"+mc+"%");
			model.setProduits(produits);
			request.setAttribute("model", model);
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		else if (path.equals("/saisie.ca")) {
			request.setAttribute("produit", new Produit());
			request.getRequestDispatcher("saisieProduit.jsp").forward(request, response);
			
		}
		else if ((path.equals("/SaveProduit.ca"))  &&  (request.getMethod().equals("POST"))) {
			 String des=request.getParameter("designation");
			 double prix=Double.parseDouble(request.getParameter("prix"));
			 int quantite= Integer.parseInt( request.getParameter("quantite"));
			 Produit p=metier.save(new Produit(des,prix,quantite));
			 request.setAttribute("produit", p);
			 request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
			
		} 
		else if (path.equals("/Supprimer.ca")) {
			Long id=Long.parseLong(request.getParameter("id"));
			metier.deleteProduit(id);
			//request.getRequestDispatcher("produits.jsp").forward(request, response);
			response.sendRedirect("chercher.ca?motCle=");
			
		}
		else if (path.equals("/Editer.ca")) {
			Long id=Long.parseLong(request.getParameter("id"));
			Produit p=metier.getProduit(id);
			request.setAttribute("produit", p);
			request.getRequestDispatcher("EditProduit.jsp").forward(request, response);
		}
		else if ((path.equals("/UpdateProduit.ca"))  &&  (request.getMethod().equals("POST"))) {
			Long id=Long.parseLong(request.getParameter("id"));
			String des=request.getParameter("designation");
			 double prix=Double.parseDouble(request.getParameter("prix"));
			 int quantite= Integer.parseInt( request.getParameter("quantite"));
			 Produit p=new Produit(des,prix,quantite);
			 p.setId(id);
			 metier.update(p);
			 request.setAttribute("produit", p);
			 request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
		}
		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);	}

}
