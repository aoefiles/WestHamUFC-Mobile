# Responsi 1 Mobile - West Ham United FC App

Aplikasi Android sederhana yang menampilkan informasi tentang klub sepak bola West Ham United FC, menggunakan data dari football-data.org API.

## Identitas Mahasiswa
Nama: Firyal Aufa Fahrudin

NIM: H1D023018

Shift Baru: Shift F

Shift Asal: Shift B

## Demo Aplikasi
Berikut adalah demo dari West Ham United FC App

![Demo Aplikasi West Ham United FC](Demo-WestHamUFC.gif)

## Penjelasan Alur Data Aplikasi

Berikut adalah penjelasan rinci mengenai alur data dalam aplikasi ini, mulai dari pengambilan data dari API hingga ditampilkan ke pengguna:

1. Inisiasi ViewModel:

Ketika pengguna membuka salah satu halaman utama (MainActivity, CoachActivity, atau SquadActivity), instance dari TeamViewModel dibuat atau diambil (jika sudah ada) menggunakan by viewModels(). ViewModel ini bertanggung jawab untuk mengelola dan menyediakan data UI, serta bertahan dari perubahan konfigurasi (misalnya rotasi layar).

2. Pemanggilan API (fetchTeamData):

Saat TeamViewModel pertama kali dibuat (init block), fungsi fetchTeamData() dipanggil.

Fungsi ini memulai proses pengambilan data di background thread menggunakan viewModelScope.launch{} (Kotlin Coroutines), sehingga tidak memblokir UI thread. Status _isLoading diatur menjadi true.

Di dalam coroutine, RetrofitInstance.api.getTeamDetails() dipanggil. Ini adalah fungsi suspend yang didefinisikan dalam interface ApiService. Retrofit akan membuat request HTTP GET ke endpoint v4/teams/563 di BASE_URL (https://api.football-data.org/). Request ini secara otomatis menyertakan header X-Auth-Token yang telah dikonfigurasi di RetrofitInstance.

3. Penerimaan Respons & Parsing JSON:

Setelah server football-data.org merespons, Retrofit menerima data dalam format JSON.

GsonConverterFactory yang terpasang pada RetrofitInstance secara otomatis melakukan parsing (konversi) data JSON tersebut menjadi objek Kotlin. Struktur JSON respons dicocokkan dengan data class TeamResponse, yang di dalamnya terdapat objek Coach dan List<Player>.

4. Pembaruan LiveData:

Jika request API berhasil (response.isSuccessful) dan body respons tidak null, TeamViewModel mengekstrak data yang relevan dari objek TeamResponse (misalnya, responseBody.coach, responseBody.squad).

Data ini kemudian dikirimkan ke MutableLiveData yang sesuai (_coach.value = ..., _squad.value = ...). LiveData adalah data holder yang lifecycle-aware, artinya ia hanya akan memperbarui UI jika observer (Activity) dalam keadaan aktif. Status _isLoading diatur kembali menjadi false.

Jika terjadi error (respons tidak berhasil atau ada exception), pesan error dicatat ke Logcat.

5. Observasi LiveData oleh Activity:

Setiap Activity (MainActivity, CoachActivity, SquadActivity) yang membutuhkan data dari ViewModel akan melakukan observasi pada LiveData yang relevan menggunakan teamViewModel.coach.observe(this) atau teamViewModel.squad.observe(this).

Blok kode di dalam {} pada observer akan otomatis dijalankan setiap kali nilai LiveData yang diamati berubah (misalnya, setelah data dari API berhasil dimuat).

6. Penyajian Data di Layar (Pembaruan UI):
- MainActivity: Meskipun MainActivity mungkin meng-observe teamData (jika diperlukan untuk nama tim), deskripsi klub (tvTeamDescription) diatur menggunakan teks statis dari strings.xml untuk memastikan konsistensi. Listener klik pada card menu disiapkan untuk navigasi menggunakan Intent.

- CoachActivity: Saat LiveData<Coach> berubah, observer di CoachActivity menerima objek Coach. Nama (coach.name), tanggal lahir (coach.dateOfBirth), dan kebangsaan (coach.nationality) kemudian ditampilkan ke TextView yang sesuai menggunakan view binding.

- SquadActivity: Saat LiveData<List<Player>> berubah, observer di SquadActivity menerima daftar pemain (List<Player>). Daftar ini dikirim ke PlayerAdapter melalui fungsi adapter.setData(it).

- PlayerAdapter: Fungsi setData() memperbarui list internal dan memanggil notifyDataSetChanged(). RecyclerView kemudian meminta adapter untuk me-render ulang item yang terlihat. Fungsi onBindViewHolder dipanggil untuk setiap item, di mana data pemain (nama, kebangsaan) ditampilkan. Logika when digunakan untuk menentukan warna latar belakang CardView (item_player.xml) berdasarkan player.position. Listener klik pada setiap item disiapkan.

- PlayerDetailFragment: Ketika sebuah item pemain di SquadActivity diklik, interface OnPlayerClickListener memicu pemanggilan PlayerDetailFragment(...). Data pemain (Player) diteruskan melalui constructor. PlayerDetailFragment kemudian menampilkan detail pemain (nama, tanggal lahir, kebangsaan, posisi) di layout fragment_player_detail.xml.

7. Halaman Statis:

HistoryActivity tidak berinteraksi dengan ViewModel atau API. Saat dibuka melalui Intent dari MainActivity, onCreate-nya hanya mengatur layout (activity_history.xml) yang menampilkan gambar dan teks panjang yang diambil langsung dari strings.xml.
