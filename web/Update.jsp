<%-- 
    Document   : Update
    Created on : Dec 4, 2016, 8:31:20 PM
    Author     : josepharcelo
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Inventory | Henry Books</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://use.fontawesome.com/ea6070bed7.js"></script>
    </head>
    <c:if test="${!user.authenticated}">
        <script type="text/javascript">
            window.location = "/HenryBooks_Arcelo";
        </script>
    </c:if>
    <c:if test="${user.authenticated}">
    <body>
        <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="text-center">&nbsp;Update Inventory</h3>
                </div>
                <div class="row">
                    <div class="col-sm-8">
                        <h1 class="text-warning text-center"><i class="fa fa-book fa-3x"></i></h1>
                        <h2 class="text-center">Update Inventory</h1>
                        <br>
                        <br>
                    </div>
                    <div class="col-sm-3">
                        <br>
                        <br>
                        <ul class="list-group">
                            <li class="list-group-item disabled">${user} </li>
                            <li class="list-group-item">${store}</li>
                        </ul>
                    </div>
                </div>
                        
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-2">
                        <hr>
                        <form action="UpdateBookOnHand" method="post" class="">
                            <div class="form-group col-sm-12">
                                <label for="bookID">Book ID</label>
                                <input type="text" class="form-control" id="bookID" name="bookID"
                                           readonly="true" value="${book.bookId}" >
                                <label for="title">Title</label>
                                <input type="text" class="form-control" id="title" name="title"
                                           readonly="true" value="${book.title}" >
                                <label for="author">Author</label>
                                <input type="text" class="form-control" id="author" name="author"
                                           readonly="true" value="${book.author}" >
                                <br>
                                <hr>
                                <p class="text-danger">${msg}</p>
                                <label for="quantity">Inventory On Hand In Branch</label>
                                <input type="number" class="form-control" id="quantity" name="quantity"
                                           placeholder="Quantity" value="${bookCount}" >
                                <br>
                                <br>
                                <button type="submit" class="btn btn-info">&nbsp; Update Inventory &nbsp;</button>
                                <a href="/HenryBooks_Arcelo/StoreSelection.jsp" class="btn btn-warning" role="button">&nbsp; Cancel &nbsp;</a>
                            </div>
                        </form>
                    </div>
                </div>
                <br>
            </div>
        </div>
    </body>
    </c:if>  
</html>
