<?php 
ini_set('display_errors', 1);
$nombre = $_REQUEST["nombre"];
$apellido = $_REQUEST["apellido"];
$fdn = $_REQUEST["fdn"];
$salario = $_REQUEST["salario"];
$posicion = $_REQUEST["posicion"];
$genero = $_REQUEST["genero"];

if($nombre != "" && $apellido != "" && $fdn != "" && $salario != "" && $posicion != "" && $genero != ""){
    try{
        $pdo = new PDO("mysql:host=localhost;dbname=staff", "root", "");
        $resultado = $pdo->prepare('INSERT INTO staff(fname, Iname,position, sex, dob, salary) VALUES (:a, :b, :c, :d, :e, :f)');
        $resultado->bindParam(":a", $nombre, PDO::PARAM_STR);
        $resultado->bindParam(":b", $apellido, PDO::PARAM_STR);
        $resultado->bindParam(":c", $posicion, PDO::PARAM_STR);
        $resultado->bindParam(":d", $genero, PDO::PARAM_STR);
        $resultado->bindParam(":e", $fdn, PDO::PARAM_STR);
        $resultado->bindParam(":f", $salario, PDO::PARAM_INT);
        $resultado->execute();
        printf("aaaaaaaaaaa");
    } catch (PDOException $e) {
        var_dump($e);
    }    
} else {
    echo "-1";
}   
?>