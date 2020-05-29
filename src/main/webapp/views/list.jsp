<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

There are ${requestScope.customers.size()} customer(s) in list.
<table style="border: solid black 1px">
<c:forEach var="c" items="${requestScope.customers}">
    <tr style="border: solid black 1px">
        <td>
            <c:out value="${c.id}"/>
        </td>
        <td>
            <a href="/customers/${c.id}">${c.name}</a>
        </td>
        <td>
            <c:out value="${c.email}"/>
        </td>
        <td>
            <c:out value="${c.address}"/>
        </td>
    </tr>
</c:forEach>
</table>