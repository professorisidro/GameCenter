<jsp:useBean id="User" type="model.User" scope="session"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Bootstrap 4, from LayoutIt!</title>

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
                    <p class="lead">
                        Welcome ${User.name} <br/>
                        Using WebAPI<br/><br/>
                    </p>
                    <p>
                        Requesting Ranking <strong><pre>http://localhost:8080/gamecenter/services/ranking?key=YOURKEY</pre></strong>
                    </p>
                    <p>
                        Submit Ranking <strong><pre>http://localhost:8080/gamecenter/services/submit?key=YOURKEY&email=EMAIL&score=SCORE</pre></strong>
                    </p>
                </div>
            </div>

        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>