<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Product</title>
</head>
<body>
<div>
    <form method="post">
        <label>ProductName:
            <input type="text" name="productName"><br/>
        </label>
        <button type="submit">Submit</button>
    </form>
</div>
<div>
    <%
        List<String> names = (List<String>) request.getAttribute("productNames");

        if (names != null && !names.isEmpty()) {
            out.println("<ui>");
            for (String s : names) {
                out.println("<li>" + s + "</li>");
            }
            System.out.println("</ui>");
        } else out.println("<p>There are no products yet!</p>");
    %>
</div>
</body>
</html>
