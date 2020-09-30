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
        <script type="text/javascript">
            function cadastraJogo() {
                
                var txtkey = document.getElementById("key");
                var xmlhttp = new XMLHttpRequest();
                var txtTitle = document.getElementById("title").value;
                var txtPlatf = document.getElementById("platform").value;
                xmlhttp.onreadystatechange  = function () {                   
                    if (xmlhttp.readyState === 4) {                       
                        if (xmlhttp.status === 200) {                            
                            txtkey.value = xmlhttp.responseText;
                        }
                    } else {
                        txtkey.value = "PROCESSING...";
                    }
                }
                xmlhttp.open("GET", "http://localhost:8080/gamecenter/efetivarjogo?txtTitle=" + txtTitle + "&txtPlatform=" + txtPlatf);              
                xmlhttp.send();
            }
        </script>

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
                    
                        <div class="form-group">

                            <label for="title">
                                Game Title
                            </label>
                            <input type="text" class="form-control" id="title" name="txtTitle" />
                        </div>
                        <div class="form-group">

                            <label for="platform">
                                Platform (Mobile, Web, Console, PC)
                            </label>
                            <input type="text" class="form-control" id="platform" name="txtPlatform"/>
                        </div>


                        <button class="btn btn-primary" onclick="cadastraJogo();">
                            Submit
                        </button>
                    
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    &nbsp;
                </div>
                <div class="col-md-9">
                    <label for="key">
                        Generated Key
                    </label>
                    <input type="text" readonly class="form-control" id="key" name="txtKey"/>
                </div>
            </div>

        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>