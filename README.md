# Responsi 1 Mobile - West Ham United FC App

Aplikasi Android sederhana yang menampilkan informasi tentang klub sepak bola West Ham United FC, menggunakan data dari football-data.org API.

## Identitas Mahasiswa
Nama: Firyal Aufa Fahrudin

NIM: H1D023018

Shift Baru: Shift F

Shift Asal: Shift B

## Demo Aplikasi
Berikut ada;ah demo dari West Ham United FC App
![Demo Aplikasi West Ham United FC](Demo-WestHamUFC.gif)

## Penjelasan Alur Data Aplikasi

1. Inisiasi ViewModel: Saat aplikasi dibuka (MainActivity, CoachActivity, SquadActivity), TeamViewModel diinisialisasi.

2. Pemanggilan API: TeamViewModel menggunakan viewModelScope (Coroutines) untuk memanggil RetrofitInstance.api.getTeamDetails() secara asynchronous. Request ini dikirim ke https://api.football-data.org/v4/teams/563 dengan header X-Auth-Token.

3. Penerimaan & Parsing Data: Retrofit menerima respons JSON dari API. Library Gson (melalui GsonConverterFactory) secara otomatis mem-parsing JSON ini menjadi objek Kotlin TeamResponse (beserta objek Coach dan List<Player> di dalamnya).

4. Update LiveData: TeamViewModel memperbarui MutableLiveData (_teamData, _coach, _squad) dengan data yang diterima dari API.

5. Observasi oleh Activity:
- MainActivity, CoachActivity, dan SquadActivity meng-observe LiveData yang relevan dari TeamViewModel.
- Ketika LiveData berubah (data dari API masuk), callback observe di masing-masing Activity akan terpanggil.

6. Penyajian di Layar (UI Update):
- MainActivity: Menampilkan nama klub dan deskripsi statis. Menyiapkan listener untuk navigasi ke halaman lain.
- CoachActivity: Menampilkan nama, tanggal lahir, dan kebangsaan pelatih yang didapat dari LiveData<Coach>.
- SquadActivity: Mengirim List<Player> ke PlayerAdapter. RecyclerView menampilkan daftar pemain. PlayerAdapter mengatur pewarnaan card berdasarkan posisi pemain. Listener disiapkan untuk menampilkan detail saat pemain diklik.
- PlayerDetailFragment: Ditampilkan sebagai bottom sheet saat item pemain di SquadActivity diklik, menerima data pemain melalui constructor-nya dan menampilkannya.

7. Halaman Statis: HistoryActivity menampilkan teks statis yang diambil dari strings.xml tanpa interaksi dengan ViewModel atau API.
