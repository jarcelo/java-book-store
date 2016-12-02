<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
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
        <body class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="text-center">&nbsp;Inventory View/Update</h1>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        
                        
                        <form id="memupdate" action="MemberUpdate" method="post" class="">
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <h4>${user}</h4>
                                    <hr>
                                </div>
                            </div>
                                
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <br>
                                <label for="firstname" class="control-label">Select Your Store</label>
                                <br>
                                <div class="">
                                    <select class="form-control">
                                        <c:forEach var="s" items="${stores}">
                                            <option value="${s.storeID}">${s.storeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                </div>
                            </div>
                            

                         
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-6">
                                    <br>
                                  <button type="submit" class="btn btn-primary">View/Edit Inventory</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6">
                        <br>
                        <br>
                        <h1 class="text-primary text-center"><i class="fa fa-id-card-o fa-5x"></i></h1>
                    </div
                </div>
                           
                </div>
                <div>
                    <br>
                    <h5>
                        <a href="/HenryBpoks_Arcelo">&nbsp;&nbsp;&nbsp;<i class="fa fa-chevron-circle-left"></i>&nbsp;Back to the Login Screen</a>
                    </h5>
                </div>              
            </div>
            <div class="alert alert-info" role="alert">
                ${msg}
            </div>
        </body>
    </c:if>
</html>