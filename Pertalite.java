package Responsi;

public class Pertalite extends bahanBakar implements Pembayaran {

    private double sisaKuotaSubsidi;

    public Pertalite(String kode, double harga) {
        super(kode, "Pertalite", harga);
        this.sisaKuotaSubsidi = 500.0;
    }

    public Pertalite(String kode, double harga, double stok, double kuota) {
        super(kode, "Pertalite", harga, stok);
        this.sisaKuotaSubsidi = kuota;
    }

    public double getSisaKuotaSubsidi() {
        return sisaKuotaSubsidi;
    }

    public void setSisaKuotaSubsidi(double sisaKuotaSubsidi) {
        this.sisaKuotaSubsidi = sisaKuotaSubsidi;
    }

    @Override
    public boolean prosesPengisian(double liter) {
        if (!isAktif()) {
            System.out.println("  [GAGAL] BahanBakar " + getKodeBBM() + " sedang nonaktif.");
            return false;
        } else if (liter <= 0 || liter > BATAS_LITER_PER_TRANSAKSI) {
            System.out.println("  [GAGAL] Jumlah liter tidak valid (maks " + BATAS_LITER_PER_TRANSAKSI + " L).");
            return false;
        } else if (liter > getStokLiter()) {
            System.out.println("  [GAGAL] Stok tidak cukup. Sisa stok: " + getStokLiter() + " L.");
            return false;
        } else if (liter > sisaKuotaSubsidi) {
            System.out.println("  [GAGAL] Kuota subsidi habis. Sisa kuota: " + sisaKuotaSubsidi + " L.");
            return false;
        } else {
            catatPenjualan(liter);
            sisaKuotaSubsidi -= liter;
            System.out.println("  [OK] " + liter + " L Pertalite berhasil diisi. Sisa kuota: " + sisaKuotaSubsidi + " L.");
            return true;
        }
    }

    // [Req 6 & 5] Override + pemanggilan super.namaMethod()
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   -> Sisa kuota subsidi: " + sisaKuotaSubsidi + " L");
    }

    // Implementasi interface Pembayaran
    @Override
    public double hitungTotalBayar(double liter) {
        return hitungHarga(liter); // tanpa diskon
    }

    @Override
    public String getMetodePembayaran() {
        return "Tunai";
    }
}