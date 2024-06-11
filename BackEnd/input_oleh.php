<?php
require 'config.php'; // Asumsi konfigurasi database ada di file ini
header('Content-Type: application/json');

// Menerima data dari request, misalnya menggunakan metode POST
$nama_tempat = isset($_POST['nama_tempat']) ? trim($_POST['nama_tempat']) : '';
$lokasi = isset($_POST['lokasi']) ? trim($_POST['lokasi']) : '';


// Koneksi ke database
$conn = new mysqli($servername, $username, $password, $database);

// Cek koneksi
if ($conn->connect_error) {
    die(json_encode(['error' => "Connection failed: " . $conn->connect_error]));
}

// Cek apakah nama tempat atau lokasi kosong
if (empty($nama_tempat) || empty($lokasi)) {
    echo json_encode(['success' => false, 'message' => 'Nama tempat dan lokasi tidak boleh kosong']);
} else {
    // Menyiapkan query
    $stmt = $conn->prepare("INSERT INTO `daftar_oleh_oleh`(`nama_tempat`, `lokasi`) VALUES (?, ?)");
    $stmt->bind_param("ss", $nama_tempat, $lokasi);

    // Eksekusi query
    if ($stmt->execute()) {
        echo json_encode(['success' => true, 'message' => 'Data berhasil disimpan']);
    } else {
        echo json_encode(['success' => false, 'message' => 'Data gagal disimpan']);
    }

    // Menutup statement
    $stmt->close();
}

// Menutup koneksi
$conn->close();
