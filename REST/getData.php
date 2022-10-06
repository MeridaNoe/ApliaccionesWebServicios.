<?php
    try {
        $pdo = new PDO("mysql:host=localhost;dbname=staff", "root",""); 
    } catch (PDOException $e) {
        echo $e->getMessage();
    }

    $sql = "SELECT fname, Iname,position,sex,dob,salary FROM staff";
    $resultado = $pdo->query($sql);
    $datos = array();
    while ($row = $resultado->fetch(PDO::FETCH_ASSOC)) {
        $datos[] = $row;
    }
    echo json_encode($datos);
?>