<%-- 
    Document   : Inventory
    Created on : Dec 1, 2016, 11:33:27 PM
    Author     : josepharcelo
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
                integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <title>Inventory</title>
    </head>
    <c:if test="${!user.authenticated}">
        <script type="text/javascript">
            window.location = "/HenryBooks_Arcelo";
        </script>
    </c:if>
    <c:if test="${user.authenticated}">
    <body>
        <div class="container">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="text-center">&nbsp;Inventory View/Update</h3>
                </div>
                <div class="row">
                    <div class="col-sm-5 col-sm-offset-1">
                        <br>
                        <h5>${user}</h5>
                        <h5>${store}</h5>
                    </div>
                    <div class="col-sm-6">
                         <c:if test="${user.adminLevel == 'Admn'}">
                            <div>
                                <br>
                                <br>
                                <br>
                                <form action="UpdateInventory" method="post" class="form-inline col-sm-10 col-sm-offset-1">
                                    <div class="form-group">
                                        <label for="bookCode">Book Code</label>
                                        <input type="text" class="form-control" id="bookCode" name="bookCode"
                                                   placeholder="Book Code" value="" >
                                        <input class="hidden form-control" id="storeId" name="storeId" value="${store.storeID}"> 
                                    </div>
                                    <button type="submit" class="btn btn-warning">&nbsp; Edit Record &nbsp;</button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div> 
                    <br>
                    <br>
                    <table class="table table-striped">
                        <tr>
                            <th class="text-left">Store</th>
                            <th class="text-left">Book Code</th>
                            <th class="text-left">Title</th>
                            <th class="text-left">Retail Price</th>
                            <th class="text-left">Quantity</th>
                        </tr>
                        <c:forEach var="i" items="${invs}">
                        <tr>
                            <td class="text-left">${i[0]}</td>
                            <td class="text-left">${i[1]}</td>
                            <td class="text-left">${i[2]}</td>
                            <td class="text-left">${i[3]}</td>
                            <td class="text-left">${i[4]}</td>
                        </tr>
                       </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <br>
    </body>
    </c:if> 
</html>
