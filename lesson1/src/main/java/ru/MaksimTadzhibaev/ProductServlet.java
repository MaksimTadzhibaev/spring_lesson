package ru.MaksimTadzhibaev;

import ru.MaksimTadzhibaev.persist.Product;
import ru.MaksimTadzhibaev.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productRepository.findAll();
        resp.getWriter().println("<table border=\"1\" width=\"600\" align=\"center\">");
        resp.getWriter().println("<colgroup>");
        resp.getWriter().println("<caption>Hello, products</caption>");
        resp.getWriter().println("<tr align=\"center\"><th>id product</th><th>name product</th><th>cost product</th></tr>"); //ряд с ячейками заголовков
        for (int i = 0; i < products.size(); i++) {
            resp.getWriter().println("<tr align=\"center\"><td>" + products.get(i).getId() + "</td><td>" + products.get(i).getName() + "</td><td>" + products.get(i).getCost() + "</td></tr>");
        } //ряд с ячейками тела таблицы
        resp.getWriter().println("<colgroup>");
        resp.getWriter().println("</table>");
    }
}
