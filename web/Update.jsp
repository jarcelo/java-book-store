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
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-8">
                    <h1 class="text-warning text-center"><i class="fa fa-book fa-3x"></i></h1>
                    <h2 class="text-center">Update Inventory</h1>
                    <br>
                    <br>
                </div>
                <div class="col-sm-4">
                    <br>
                    <br>
                    <ul class="list-group">
                        <li class="list-group-item disabled">${user} </li>
                        <li class="list-group-item">Branch No: ${store.storeID} </li>
                        <li class="list-group-item">Branch Name: ${store.storeName}</li>
                        <li class="list-group-item">Address: ${store.storeAddress}</li>
                    </ul>
                </div>
            </div>
         
            <div class="row">
                <hr>
                <div class="col-sm-6 col-sm-offset-3">
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
                        
                            <label for="quantity">Inventory On Hand In Branch</label>
                            <input type="text" class="form-control" id="quantity" name="quantity"
                                       placeholder="Quantity" value="" >
                            <br>
                            <br>
                            <button type="submit" class="btn btn-info">&nbsp; Update Inventory &nbsp;</button>
                            <a href="/HenryBooks_Arcelo/StoreSelection.jsp" class="btn btn-info" role="button">&nbsp; Cancel &nbsp;</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
