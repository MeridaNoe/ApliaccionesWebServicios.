<?php
header('Content-Type: application/JSON');
$httpMethod = $_SERVER['REQUEST_METHOD'];
switch ($metodo) {
    case 'GET': // CONSULTA
        try {
            $client = new PDO("mysql:host=localhost;dbname=utez","root","");
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
     break;
     case 'PUT': // Actualizacion
        break;
        case 'DELETE': // Eliminacion
            break;
    default:
    echo 'Método no soportado';
        break;
}
?>