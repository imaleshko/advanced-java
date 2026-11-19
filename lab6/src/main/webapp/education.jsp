<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<!DOCTYPE html>
<html lang="uk">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Освіта</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <main class="container">
            <nav class="nav">
                <a href="index.jsp">Головна</a>
                <a href="stack.jsp">Стек і проєкти</a>
                <a href="contact.jsp">Контакти</a>
            </nav>

            <h1>Освіта</h1>

            <section class="card">
                <h2>Ужгородський університет</h2>
                <p><strong>Факультет:</strong> інформаційних технологій</p>
                <p><strong>Спеціальність:</strong> 121 — Інженерія програмного забезпечення</p>
                <p><strong>Курс:</strong> 3 курс (бакалаврат)</p>
            </section>

            <section class="card">
                <h2>Актуальні профільні дисципліни</h2>
                <%
                    List<String> subjects = List.of(
                            "Лінійне програмування",
                            "Типізовані мови програмування",
                            "Поглиблене вивчення Java",
                            "Проєктування баз даних та експертних систем",
                            "Розробка Інтернет клієнт-серверних систем",
                            "Системне програмування та ОС",
                            "Технологія блокчейн та машинне навчання"
                    );
                %>
                <ul class="clean-list">
                    <% for (String subject : subjects) { %>
                    <li><%= subject %>
                    </li>
                    <% } %>
                </ul>
            </section>
        </main>
    </body>
</html>
