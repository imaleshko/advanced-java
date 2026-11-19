<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
    private String escapeHtml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
%>
<%
    request.setCharacterEncoding("UTF-8");
    boolean isPost = "POST".equalsIgnoreCase(request.getMethod());
    String sender = escapeHtml(request.getParameter("sender"));
    String email = escapeHtml(request.getParameter("email"));
    String message = escapeHtml(request.getParameter("message"));
%>
<!DOCTYPE html>
<html lang="uk">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Контакти — Іван Малешко</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <main class="container">
            <nav class="nav">
                <a href="index.jsp">Головна</a>
                <a href="education.jsp">Освіта</a>
                <a href="stack.jsp">Стек і проєкти</a>
            </nav>

            <h1>Контакти</h1>

            <section class="card">
                <p><strong>Локація:</strong> Ужгород, Україна</p>
                <p><strong>GitHub:</strong> <a href="https://github.com/imaleshko" target="_blank"
                >github.com/imaleshko</a></p>
            </section>

            <section class="card">
                <h2>Зворотний зв'язок</h2>
                <form id="contact-form" method="post" action="contact.jsp" novalidate>
                    <label>
                        Ім'я
                        <input type="text" name="sender" placeholder="Ваше ім'я">
                    </label>
                    <label>
                        Email
                        <input type="email" name="email" placeholder="name@example.com">
                    </label>
                    <label>
                        Повідомлення
                        <textarea name="message" rows="3" placeholder="Текст повідомлення"></textarea>
                    </label>
                    <button type="submit">Надіслати</button>
                    <p id="form-error" class="error" hidden></p>
                </form>

                <% if (isPost && !sender.isEmpty() && !message.isEmpty()) { %>
                <div class="result">
                    <p><strong>Повідомлення прийнято (метод: ${pageContext.request.method})</strong></p>
                    <p>Від: <%= sender %> (<%= email %>)</p>
                    <p>Текст: <%= message %>
                    </p>
                </div>
                <% } %>
            </section>
        </main>
        <script src="app.js"></script>
    </body>
</html>
