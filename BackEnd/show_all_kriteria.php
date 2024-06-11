<?php
require 'config.php'; // Pastikan file config.php berisi informasi koneksi database
header('Content-Type: application/json');

// Membuat koneksi
$conn = new mysqli($servername, $username, $password, $database);

// Mengecek koneksi
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Mempersiapkan dan menjalankan query
$query = "SELECT * FROM `daftar_kriteria`";
$result = $conn->query($query);

// Mengecek hasil dan membangun array respons
if ($result->num_rows > 0) {
    $response = [];
    while ($row = $result->fetch_assoc()) {
        $response[] = $row;
    }
    echo json_encode(['Data' => $response]);
} else {
    echo json_encode(['message' => 'No records found']);
}

// Menutup koneksi
$conn->close();
