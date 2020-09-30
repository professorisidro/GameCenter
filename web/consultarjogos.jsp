<jsp:useBean id="User" type="model.User" scope="session"/>
<jsp:useBean id="Jogos" type="java.util.ArrayList" scope="request"/>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>.: Game Center :. Meus Jogos</title>

        <meta name="description" content="Source code generated using layoutit.com">
        <meta name="author" content="LayoutIt!">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                </div>
                <div class="col-md-6">
                    <h3 class="text-center">
                        Game Center
                    </h3>
                </div>
                <div class="col-md-3">
                </div>
            </div>
            <!-- outra linha -->
            <div class="row">
                <div class="col-md-3">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="./consultarjogos">My Games</a>
                        </li>
                        <li class="nav-item">                                    
                            <a class="nav-link" href="./novojogo">Add new Game</a>
                        </li>			
                        <li class="nav-item">
                            <a class="nav-link" href="./manual">WebAPI Manual</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-9">
                    <c:forEach var="jogo" items="${Jogos}">
                        <p> Title:  <strong>${jogo.title}</strong> <br/>
                            Platform: ${jogo.platform} <br/>
                        <pre>Key:${jogo.key}</pre>
                        </p>
                    </c:forEach>
                </div>
            </div>

        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>