<?php
require_once(dirname(__DIR__)."../../db/db_config.php");
$db = new DBConfig();
$dbConnection = $db->connect();
$users = "";
if(isset($_GET['search'])){
    $search = $_GET['search'];
    $query = "SELECT * FROM users WHERE identification='$search'";
    $users = $dbConnection->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
}
else{
    $query = "SELECT * FROM users";
    $users = $dbConnection->query($query)->fetchAll(PDO::FETCH_ASSOC);
}
header('Content-Type: application/json');
echo json_encode($users);