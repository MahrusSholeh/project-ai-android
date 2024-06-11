<?php
require 'config.php';  // Asumsi bahwa konfigurasi database ada di file ini
header('Content-Type: application/json');

// Menerima data dari POST
$id_oleh_oleh = isset($_POST['id_oleh_oleh']) ? $_POST['id_oleh_oleh'] : '';
$id_kriteria = isset($_POST['id_kriteria']) ? $_POST['id_kriteria'] : '';
$nilai = isset($_POST['nilai']) ? $_POST['nilai'] : '';

// Periksa jika data yang diperlukan tidak kosong
if (empty($id_oleh_oleh) || empty($id_kriteria) || empty($nilai)) {
    echo json_encode(['success' => false, 'message' => 'Data tidak lengkap.']);
    exit;
}

// Membuat koneksi ke database
$conn = new mysqli($servername, $username, $password, $database);

// Mengecek koneksi
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Mempersiapkan dan menjalankan query
$query = "INSERT INTO `tb_matrik` (`id_oleh_oleh`, `id_kriteria`, `nilai`) VALUES (?, ?, ?)";
$stmt = $conn->prepare($query);

if ($stmt === false) {
    echo json_encode(['success' => false, 'message' => 'Gagal menyiapkan query.']);
    $conn->close();
    exit;
}

$stmt->bind_param("sss", $id_oleh_oleh, $id_kriteria, $nilai);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'message' => 'Data berhasil disimpan.']);
} else {
    echo json_encode(['success' => false, 'message' => 'Gagal menyimpan data.']);
}

$stmt->close();
$conn->close();
