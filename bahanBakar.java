package Responsi;

public class bahanBakar {
    public static final String NAMA_SPBU = "SPBU Pertamina 44.532.02";
    public static final double BATAS_LITER_PER_TRANSAKSI = 100.0;

    private final String kodeBBM;

    private String jenisbahanBakar;
    private double hargaPerLiter;
    private double stokLiter;
    private double totalTerjualLiter;
    private boolean aktif;

    public bahanBakar(String kodeBBM, String jenisbahanBakar, double hargaPerLiter) {
        this(kodeBBM, jenisbahanBakar, hargaPerLiter, 1000.0);
    }

    public bahanBakar(String kodeBBM, String jenisbahanBakar, double hargaPerLiter, double stokLiter) {
        this.kodeBBM = kodeBBM;
        this.jenisbahanBakar = jenisbahanBakar;
        this.hargaPerLiter = hargaPerLiter;
        this.stokLiter = stokLiter;
        this.totalTerjualLiter = 0;
        this.aktif = true;
    }

    public String getKodeBBM() {
        return kodeBBM;
    }

    public String getjenisbahanBakar() {
        return jenisbahanBakar;
    }

    public void setjenisbahanBakar(String jenisbahanBakar) {
        this.jenisbahanBakar = jenisbahanBakar;
    }

    public double getHargaPerLiter() {
        return hargaPerLiter;
    }

    public void setHargaPerLiter(double hargaPerLiter) {
        if (hargaPerLiter > 0) {
            this.hargaPerLiter = hargaPerLiter;
        }
    }

    public double getStokLiter() {
        return stokLiter;
    }

    public void setStokLiter(double stokLiter) {
        if (stokLiter >= 0) {
            this.stokLiter = stokLiter;
        }
    }

    public double getTotalTerjualLiter() {
        return totalTerjualLiter;
    }

    public void setTotalTerjualLiter(double totalTerjualLiter) {
        this.totalTerjualLiter = totalTerjualLiter;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public void tampilkanInfo() {
        System.out.println("[" + NAMA_SPBU + "] Kode " + kodeBBM
                + " | " + jenisbahanBakar
                + " | Rp" + hargaPerLiter + "/L"
                + " | Stok: " + stokLiter + " L"
                + " | Terjual: " + totalTerjualLiter + " L"
                + " | Status: " + (aktif ? "AKTIF" : "NONAKTIF"));
    }

    public void tambahStok(double liter) {
        if (liter > 0) {
            this.stokLiter += liter;
            System.out.println("Stok " + kodeBBM + " bertambah " + liter + " L. Stok sekarang: " + stokLiter + " L");
        }
    }

    public double hitungHarga(double liter) {
        return liter * hargaPerLiter;
    }

    protected void catatPenjualan(double liter) {
        this.stokLiter -= liter;
        this.totalTerjualLiter += liter;
    }

    public boolean prosesPengisian(double liter) {
        return false;
    }

}