package org.maleshko.lab5;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.math3.linear.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/solve")
public class SolverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("index.html");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='uk'><head><meta charset='UTF-8'><link rel='stylesheet' href='style.css'></head><body><h2>Результати</h2>");
        String[] rs = req.getParameterValues("r");
        String[] cs = req.getParameterValues("c");
        String[] cells = req.getParameterValues("cell");
        int position = 0;

        if (rs != null && cs != null && cells != null) {
            for (int k = 0; k < rs.length; k++) {
                out.print("<p>Система №" + (k + 1) + ": ");
                int r = Integer.parseInt(rs[k]);
                int c = Integer.parseInt(cs[k]);
                int start = position;
                position += r * c;

                if (c != r + 1) {
                    out.println("некоректна розмірність " + r + "x" + c + "</p>");
                    continue;
                }

                try {
                    double[][] a = new double[r][r];
                    double[] b = new double[r];
                    for (int i = 0; i < r; i++) {
                        for (int j = 0; j < r; j++) {
                            a[i][j] = Double.parseDouble(cells[start++]);
                        }
                        b[i] = Double.parseDouble(cells[start++]);
                    }
                    RealMatrix coefficients = MatrixUtils.createRealMatrix(a);
                    RealVector constants = new ArrayRealVector(b);
                    double[] solution = new LUDecomposition(coefficients).getSolver().solve(constants).toArray();
                    for (int i = 0; i < solution.length; i++) {
                        out.printf("x%d = %.2f; ", i + 1, solution[i]);
                    }
                    out.println("</p>");
                } catch (Exception e) {
                    out.println("помилка під час обчислення</p>");
                }
            }
        }
        out.println("<a href='index.html'>Назад</a></body></html>");
    }
}
