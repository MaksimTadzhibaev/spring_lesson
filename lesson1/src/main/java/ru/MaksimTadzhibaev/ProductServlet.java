package ru.MaksimTadzhibaev;

import ru.MaksimTadzhibaev.persist.Product;
import ru.MaksimTadzhibaev.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productRepository.findAll();
        PrintWriter wr = resp.getWriter();
        if (req.getPathInfo() == null) {
            //вывод информации обо всех продуктах
            wr.println("<table border=\"1\" width=\"600\" align=\"center\">");
            wr.println("<colgroup>");
            wr.println("<caption>Hello, products</caption>");
            wr.println("<tr align=\"center\"><th>id product</th><th>name product</th><th>cost product</th></tr>"); //ряд с ячейками заголовков
            for (int i = 0; i < products.size(); i++) {
                wr.println("<tr align=\"center\"><td>" + products.get(i).getId() + "</td><td>"
                        + "<a href=\"http://194.67.93.77:8080/servlet-app/products/"
                        + products.get(i).getId() + "\">" + products.get(i).getName()
                        + "</a>" + "</td><td>" + products.get(i).getCost() + "</td></tr>");
            } //ряд с ячейками тела таблицы
            wr.println("<colgroup>");
            wr.println("</table>");
        } else {
            //вывод информации о конкретном продукте (переход по ссылке);
            String getProduct = req.getPathInfo().replace("/", "");
            Long getNumProduct = Long.valueOf(getProduct) - 1L;
            wr.println("<table border=\"1\" width=\"600\" align=\"center\">");
            wr.println("<colgroup>");
            wr.println("<caption>Hello, product info</caption>");
            wr.println("<tr align=\"center\"><th>name</th><th>cost</th></tr>"); //ряд с ячейками заголовков
            wr.println("<tr align=\"center\"><td>" + products.get((Math.toIntExact(getNumProduct))).getName()
                    + "</td><td>" + products.get(Math.toIntExact(getNumProduct)).getCost()
                    + "</td></tr>"); //ряд с ячейками тела таблицы
            wr.println("<colgroup>");
            wr.println("</table>");

        }
    }
}
