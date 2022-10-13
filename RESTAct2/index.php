<?php
header('Content-Type: application/JSON');
$metodo = $_SERVER['REQUEST_METHOD'];
switch ($metodo) {
    case 'GET': // CONSULTA
        try {
            $client = new PDO("mysql:host=localhost;dbname=utez","root","root");
            $result = $client->query("SELECT * FROM personal");
            $personal = array();
            while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
                $personal[] = $row;
            }
            echo json_encode($personal);
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
    break;
    case 'POST': // INSERCION
        if ($_GET ['accion'] == 'personal') {
            $jsonData = json_decode(file_get_contents("php://input"));
            try {
                $conn = new PDO("mysql:host=localhost;dbnamne=utez;cahrset=utf8","root","root");
            } catch (PDOException $e) {
                echo $e->getMessage();
            }   
            $query = $conn->prepare("INSERT INTO personal(name,surname, 
            lastname,salary,birthday,position_id) VALUES (:name,:surname,:lastname,:salary,:birthday,:position_id)");
            $query->bindParam(":name", $jsonData->name);
            $query->bindParam(":surname", $jsonData->surname);
            $query->bindParam(":lastname", $jsonData->lastname);
            $query->bindParam(":salary", $jsonData->salary);
            $query->bindParam(":birthday", $jsonData->birthday);
            $query->bindParam(":position_id", $jsonData->position_id);
            $result = $query->execute();
            if ($result) {
                echo("Personal registrada correctamente. Code $result");
            }else{
                echo("Error al registrar. Code $result");
            }
        }
     break;
     case 'PUT': // Actualizacion
        if ($_GET ['accion'] == 'personal') {
            $jsonData = json_decode(file_get_contents("php://input"));
            try {
                $conn = new PDO("mysql:host=localhost;dbnamne=utez;cahrset=utf8","root","root");
            } catch (PDOException $e) {
                echo $e->getMessage();
            }   
            $query = $conn->prepare("UPDATE 'personal' SET 'name' = :name, 'surname' = :surname, 
            'lastname' = :lastname, 'salary' = :salary, 'birthday' = :birthday, 'position_id' = :position WHERE 'id' = :id;");
            $query->bindParam(":name", $jsonData->name);
            $query->bindParam(":surname", $jsonData->surname);
            $query->bindParam(":lastname", $jsonData->lastname);
            $query->bindParam(":salary", $jsonData->salary);
            $query->bindParam(":birthday", $jsonData->birthday);
            $query->bindParam(":position", $jsonData->position);
            $query->bindParam(":id", $jsonData->id);
            $result = $query->execute();
            if ($result) {
                echo("Personal registrada correctamente. Code $result");
            }else{
                echo("Error al registrar. Code $result");
            }
        }
        break;
        case 'DELETE': // Eliminacion
            if ($_GET['accion'] == 'personal') {
                try {
                    $conn = new PDO("mysql:host=localhost;dbnamne=utez;cahrset=utf8","root","root");

                } catch (PDOException $e) {
                    echo $e ->getMessage();
                }
                $query = "DELETE FROM personal WHERE id = :id";
                $pstm = $conn -> prepare($query);
                $rs = $pstm->execute();
                if ($rs) {
                    echo("Personal elimanda correctamente. Code $rs");
                } else {
                    echo("Error al eliminar a este personal. Code $rs");
                }
            }
            break;
    default:
    echo 'Método no soportado';
        break;
}
?>