<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
            <script src="ajax.js" type="text/javascript"></script>
            <script src="inventory.js" type="text/javascript"></script>
            <script language="javascript" type="text/javascript">
                function pageAction()
                {
                    var x = document.getElementById("store").value;
                    if (ajax) {
                        ajax.open('GET', 'BookInventory?store=' + x);
                        ajax.send(null);
                    } else {
                        document.inventory.submit();
                    }
                }
            </script>
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
        <body>
            <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="text-center">&nbsp;Store Selection</h1>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <form action="BookInventory" method="post" name="inventory" id="inventory">
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <h4>${user}</h4>
                                    <hr>
                                </div>
                            </div>
                                
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <br>
                                <label for="store" class="control-label">Select Your Store</label>
                                <br>
                                <div class="">
                                    <select class="form-control" id="store" name="store">
                                        <c:forEach var="s" items="${stores}">
                                            <option id="storeName" name="storeName"  ${s.storeID == user.storeID? 'Selected' : ''}
                                                    value="${s.storeID}">${s.storeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-6">
                                    <br>
                                  <button type="button" class="btn btn-primary" name="showInventory" id="showInventory" 
                                          onclick="pageAction()">View/Edit Inventory</button>
                                </div>
                            </div>
                            <input type="hidden" name="store" id="store" value="">
                        </form>
                    </div> <!-- /col-sm-6 -->
                    <div class="col-sm-6">
                        <br>
                        <div class="alert alert-warning" role="alert">
                            ${msg}
                        </div>  
                        <h1 class="text-primary text-center"><i class="fa fa-id-card-o fa-5x"></i></h1>
                    </div <!-- col-sm-6 -->
                </div><!-- /row -->    
                
                <div id="resul">
                </div> <!-- /results -->
                           
                <div>
                    <br>
                    <h5>
                        <a href="/HenryBooks_Arcelo">&nbsp;&nbsp;&nbsp;<i class="fa fa-undo"></i>&nbsp;Back to the Login Screen</a>
                    </h5>
                </div>              
            </div> 
            </div><!-- /container -->
            <div>
                <div id="results">
                </div> <!-- /results -->  
            </div>
        </body>
    </c:if>  
</html>