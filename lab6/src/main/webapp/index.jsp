<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="uk">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Іван Малешко</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <%!
            private int pageViews = 0;
        %>
        <main class="container">
            <h1>Іван Малешко</h1>
            <p class="subtitle">Студент спеціальності «Інженерія програмного забезпечення»</p>

            <ul class="hub-links">
                <li><a href="education.jsp">Освіта</a></li>
                <li><a href="stack.jsp">Стек технологій і проєкти</a></li>
                <li><a href="contact.jsp">Контакти</a></li>
            </ul>

            <p class="meta">
                Час сервера: <%= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) %>
                <br/>
                Цю сторінку відкрито: <strong><%= ++pageViews %>
            </strong> разів
            </p>
        </main>
    </body>
</html>
