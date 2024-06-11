<?php
require 'config.php';
header('Content-Type: application/json');

function normalize($matrix)
{
    $norm = [];
    foreach ($matrix[0] as $k => $v) {
        $sum_sq = 0;
        foreach ($matrix as $row) {
            $sum_sq += $row[$k] ** 2;
        }
        foreach ($matrix as $i => $row) {
            $norm[$i][$k] = $row[$k] / sqrt($sum_sq);
        }
    }
    return $norm;
}

function weightedNormalized($norm, $weights)
{
    foreach ($norm as $i => $row) {
        foreach ($row as $k => $v) {
            $norm[$i][$k] = $v * $weights[$k];
        }
    }
    return $norm;
}

function calculateIdealSolutions($weightedNorm)
{
    $idealPos = array_fill(0, count($weightedNorm[0]), PHP_FLOAT_MIN);
    $idealNeg = array_fill(0, count($weightedNorm[0]), PHP_FLOAT_MAX);

    foreach ($weightedNorm as $row) {
        foreach ($row as $k => $v) {
            if ($v > $idealPos[$k]) $idealPos[$k] = $v;
            if ($v < $idealNeg[$k]) $idealNeg[$k] = $v;
        }
    }
    return [$idealPos, $idealNeg];
}

function calculateDistances($weightedNorm, $idealPos, $idealNeg)
{
    $distances = [];
    foreach ($weightedNorm as $row) {
        $dPos = $dNeg = 0;
        foreach ($row as $k => $v) {
            $dPos += ($v - $idealPos[$k]) ** 2;
            $dNeg += ($v - $idealNeg[$k]) ** 2;
        }
        $distances[] = [sqrt($dPos), sqrt($dNeg)];
    }
    return $distances;
}

function calculatePreference($distances, $idList)
{
    $preferences = [];
    foreach ($distances as $index => $distance) {
        $preferences[] = [
            'id_oleh_oleh' => $idList[$index],  // Menyimpan ID
            'preference_score' => $distance[1] / ($distance[0] + $distance[1])
        ];
    }
    return $preferences;
}

// // Contoh data dan bobot
// $matrix = [[4, 70, 3], [5, 60, 2], [3, 80, 4]]; // Misalnya [harga, kualitas, lokasi]
/// Mengambil jumlah total kriteria
$query_count_kriteria = "SELECT COUNT(*) as total_kriteria FROM `daftar_kriteria`";
$result_count_kriteria = $conn->query($query_count_kriteria);
$row_count = $result_count_kriteria->fetch_assoc();
$total_kriteria = $row_count['total_kriteria'];

// Inisialisasi matriks dengan nilai 0
$matrix = [];
$query_oleh_oleh = "SELECT `id_oleholeh` FROM `daftar_oleh_oleh`";
$result_oleh_oleh = $conn->query($query_oleh_oleh);
while ($row_oleh_oleh = $result_oleh_oleh->fetch_assoc()) {
    $id_oleh_oleh = $row_oleh_oleh['id_oleholeh'];
    $matrix[$id_oleh_oleh] = array_fill(1, $total_kriteria, 0);
}

// Query untuk mengambil matriks nilai
$query_select_matrix = "SELECT t.id_oleh_oleh, t.id_kriteria, t.nilai FROM tb_matrik t JOIN daftar_oleh_oleh o ON t.id_oleh_oleh = o.id_oleholeh JOIN daftar_kriteria k ON t.id_kriteria = k.id_kriteria ORDER BY t.id_oleh_oleh, t.id_kriteria";
$result_select_matrix = $conn->query($query_select_matrix);

if ($result_select_matrix->num_rows > 0) {
    while ($row = $result_select_matrix->fetch_assoc()) {
        $id_oleh_oleh = $row['id_oleh_oleh'];
        $id_kriteria = $row['id_kriteria'];
        $nilai = $row['nilai'];
        $matrix[$id_oleh_oleh][$id_kriteria] = $nilai;
    }
    // Konversi matriks ke format yang diinginkan
    foreach ($matrix as $oleh_oleh => $criteria_values) {
        $final_matrix[] = array_values($criteria_values);
    }
} else {
    echo json_encode(['error' => 'No matrix data found']);
    exit;
}

// Query untuk mengambil data kriteria
$query_select_weight = "SELECT `id_kriteria`, `nama_kriteria`, `bobot_kriteria` FROM `daftar_kriteria` ORDER BY `id_kriteria` ASC";
$result_select_weight = $conn->query($query_select_weight);

// Array untuk menyimpan hasil
$weights = [];

// Mengambil data dari database
if ($result_select_weight->num_rows > 0) {
    while ($row = $result_select_weight->fetch_assoc()) {
        $weights[] = (float) $row['bobot_kriteria'];  // Menambahkan bobot ke array dan mengonversi ke float
    }
} else {
    echo json_encode(['error' => 'No data found']);
    exit;
}
// $weights = [0.3, 0.5, 0.2]; // Bobot untuk setiap kriteria

$normalized = normalize($final_matrix);
$weightedNormalized = weightedNormalized($normalized, $weights);
list($idealPos, $idealNeg) = calculateIdealSolutions($weightedNormalized);
$distances = calculateDistances($weightedNormalized, $idealPos, $idealNeg);
$preferences = calculatePreference($distances, array_keys($matrix));

$finalResponse = [];

foreach ($preferences as $preference) {
    $query_detail = "SELECT * FROM `daftar_oleh_oleh` WHERE `id_oleholeh` = " . $preference['id_oleh_oleh'];
    $result_detail = $conn->query($query_detail);

    if ($result_detail->num_rows > 0) {
        $detail = $result_detail->fetch_assoc();
        $finalResponse[] = array_merge($detail, ['preference_score' => $preference['preference_score']]);
    } else {
        $finalResponse[] = array_merge(['id_oleholeh' => $preference['id_oleh_oleh'], 'error' => 'Details not found'], ['preference_score' => $preference['preference_score']]);
    }
}

// Mengurutkan berdasarkan preference_score
usort($finalResponse, function ($item1, $item2) {
    return $item2['preference_score'] <=> $item1['preference_score'];
});

// Mengambil 3 teratas
$topThree = array_slice($finalResponse, 0, 3);

echo json_encode(['Data' => $topThree]);
