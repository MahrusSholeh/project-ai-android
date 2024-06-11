<?php
$servername = "localhost";  // server database, biasanya localhost
$username = "root";     // username untuk login ke database
$password = "";     // password untuk login ke database
$database = "rekomendasi"; // nama database

// Membuat koneksi
$conn = new mysqli($servername, $username, $password, $database);

// Mengecek koneksi
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// echo "Connected successfully";

// Selanjutnya, kamu bisa melakukan operasi database lainnya
