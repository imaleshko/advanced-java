<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<!DOCTYPE html>
<html lang="uk">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Стек і проєкти — Іван Малешко</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <main class="container">
            <nav class="nav">
                <a href="index.jsp">Головна</a>
                <a href="education.jsp">Освіта</a>
                <a href="contact.jsp">Контакти</a>
            </nav>

            <h1>Стек технологій і проєкти</h1>

            <section class="card">
                <h2>Технології</h2>
                <%
                    List<String> stack = List.of(
                            "Java / Jakarta EE / Spring Boot",
                            "Python",
                            "Go",
                            "TypeScript / JavaScript",
                            "React / HTML / CSS",
                            "PostgreSQL / MySQL",
                            "Docker / Git / Linux"
                    );
                %>
                <ul class="clean-list">
                    <% for (String tech : stack) { %>
                    <li><%= tech %>
                    </li>
                    <% } %>
                </ul>
            </section>

            <section class="card">
                <h2>Проєкти (GitHub)</h2>
                <%
                    List<String[]> projects = List.of(
                            new String[]{"advanced-java", "Лабораторні роботи з Jakarta EE (Servlets, JSP, Tomcat)", "https://github.com/imaleshko/advanced-java"},
                            new String[]{"maleshko-blockchain", "Прототип блокчейну на Python із Proof of Work та REST API на Flask", "https://github.com/imaleshko/maleshko-blockchain"},
                            new String[]{"donats", "Вебплатформа для збору донатів (React + TypeScript + Spring Boot)", "https://github.com/imaleshko/donats"},
                            new String[]{"statistical-analysis", "Вебзастосунок для статистичного аналізу даних", "https://github.com/imaleshko/statistical-analysis"}
                    );
                %>
                <ul class="project-list">
                    <% for (String[] project : projects) { %>
                    <li>
                        <a href="<%= project[2] %>" target="_blank"><%= project[0] %>
                        </a>
                        <span class="desc"><%= project[1] %></span>
                    </li>
                    <% } %>
                </ul>
            </section>
        </main>
    </body>
</html>
