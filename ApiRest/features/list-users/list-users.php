<?php
require_once(dirname(__DIR__)."../../db/db_config.php");
$db = new DBConfig();
$dbConnection = $db->connect();
$query = "SELECT * FROM users";
$users = $dbConnection->query($query)->fetchAll(PDO::FETCH_ASSOC);
header('Content-Type: application/json');
echo json_encode($users);