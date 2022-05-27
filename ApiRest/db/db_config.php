<?php
class DBConfig{
    private $user = "admin";
    private $password = "cesdemoviles2jueves";
    private $dbName = "delivery_app";
    private $host = "cesde-moviles2-jueves.cilulcrjbtir.us-east-1.rds.amazonaws.com";
    private $port = "3306";

    public function connect(){
        try{
            $dsn = "mysql:host=$this->host;port=$this->port;dbname=$this->dbName";
            $connection = new PDO($dsn,$this->user, $this->password);
            $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            echo "Conexión exitosa";
            return $connection;

        }catch(PDOException $exception){
            echo "Error en la db". $exception->getMessage();
        }
    }
}