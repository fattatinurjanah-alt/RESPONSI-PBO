package Responsi;

public class Pertamax extends bahanBakar implements Pembayaran, Perawatan {

    private final double persenDiskonMember;
    private int jumlahTransaksi;
    private int batasTransaksiPerawatan;

    public Pertamax(String kode, double harga) {
        super(kode, "Pertamax", harga);
        this.persenDiskonMember = 0.05;
        this.jumlahTransaksi = 0;
        this.batasTransaksiPerawatan = 3;
    }

    public Pertamax(String kode, double harga, double stok, double persenDiskonMember, int batasTransaksiPerawatan) {
        super(kode, "Pertamax", harga, stok);
        this.persenDiskonMember = persenDiskonMember;
        this.jumlahTransaksi = 0;
        this.batasTransaksiPerawatan = batasTransaksiPerawatan;
    }

    public double getPersenDiskonMember() {
        return persenDiskonMember;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public int getBatasTransaksiPerawatan() {
        return batasTransaksiPerawatan;
    }

    public void setBatasTransaksiPerawatan(int batasTransaksiPerawatan) {
        this.batasTransaksiPerawatan = batasTransaksiPerawatan;
    }

    @Override
    public boolean prosesPengisian(double liter) {
        if (!isAktif()) {
            System.out.println("  [GAGAL] BahanBakar " + getKodeBBM() + " nonaktif (butuh perawatan?).");
            return false;
        } else if (perluPerawatan()) {
            setAktif(false);
            System.out.println("  [GAGAL] Batas transaksi tercapai, layanan dinonaktifkan untuk perawatan.");
            return false;
        } else if (liter <= 0 || liter > BATAS_LITER_PER_TRANSAKSI) {
            System.out.println("  [GAGAL] Jumlah liter tidak valid.");
            return false;
        } else if (liter > getStokLiter()) {
            System.out.println("  [GAGAL] Stok tidak cukup. Sisa stok: " + getStokLiter() + " L.");
            return false;
        } else {
            catatPenjualan(liter);
            jumlahTransaksi++;
            System.out.println("  [OK] " + liter + " L Pertamax berhasil diisi. Transaksi ke-" + jumlahTransaksi + ".");
            return true;
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   -> Transaksi sejak perawatan: " + jumlahTransaksi + "/" + batasTransaksiPerawatan);
    }

    @Override
    public double hitungTotalBayar(double liter) {
        return hitungHarga(liter) * (1 - persenDiskonMember); // diskon member
    }

    @Override
    public String getMetodePembayaran() {
        return "Non-Tunai (E-Wallet, diskon member " + (persenDiskonMember * 100) + "%)";
    }

    @Override
    public boolean perluPerawatan() {
        return jumlahTransaksi >= batasTransaksiPerawatan;
    }

    @Override
    public void lakukanPerawatan() {
        jumlahTransaksi = 0;
        setAktif(true);
        System.out.println("  [PERAWATAN] BahanBakar " + getKodeBBM() + " selesai dirawat & diaktifkan kembali.");
    }
}