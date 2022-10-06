<?php
    ini_set('display_errors', 1);
    $nombre = $_REQUEST["nombre"];
    $apellido = $_REQUEST["apellido"];
    $fdn = $_REQUEST["fdn"];
    $salario = $_REQUEST["salario"];
    $posiscion = $_REQUEST["posicion"];
    $genero = $_REQUEST["genero"];

    if ($nombre != "" && $apellido != "" && $fdn != "" && $salario != "" 
        && $posiscion != "" && $genero != ""
    ) {
        try {
            $pdo = new PDO("mysql:host=localhost;dbname=staff", "root","");
            $resultado = $pdo->prepare('INSERT INTO staff(fname,Iname,
                                        position,sex,dob,salary)
                                        VALUES(:a,:b,:c,:d,:e,:f)');
            $resultado->bindParam(":a", $nombre, PDO::PARAM_STR);
            $resultado->bindParam(":a", $apellido, PDO::PARAM_STR);
            $resultado->bindParam(":a", $posiscion, PDO::PARAM_STR);
            $resultado->bindParam(":a", $genero, PDO::PARAM_STR);
            $resultado->bindParam(":a", $fdn, PDO::PARAM_STR);
            $resultado->bindParam(":a", $salario, PDO::PARAM_STR);
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
    }else{
        echo "-1";
    }
?>