package Responsi;

public class Solar extends bahanBakar implements Pembayaran {

    private double kuotaHarian;
    private double terpakaiHariIni;

    public Solar(String kode, double harga) {
        super(kode, "Solar", harga);
        this.kuotaHarian = 300.0;
        this.terpakaiHariIni = 0;
    }

    public Solar(String kode, double harga, double stok, double kuotaHarian) {
        super(kode, "Solar", harga, stok);
        this.kuotaHarian = kuotaHarian;
        this.terpakaiHariIni = 0;
    }

    public double getKuotaHarian() {
        return kuotaHarian;
    }

    public void setKuotaHarian(double kuotaHarian) {
        this.kuotaHarian = kuotaHarian;
    }

    public double getTerpakaiHariIni() {
        return terpakaiHariIni;
    }

    public void setTerpakaiHariIni(double terpakaiHariIni) {
        this.terpakaiHariIni = terpakaiHariIni;
    }

    @Override
    public boolean prosesPengisian(double liter) {
        double sisaKuota = kuotaHarian - terpakaiHariIni;
        if (!isAktif()) {
            System.out.println("  [GAGAL] BahanBakar " + getKodeBBM() + " nonaktif.");
            return false;
        } else if (liter <= 0 || liter > BATAS_LITER_PER_TRANSAKSI) {
            System.out.println("  [GAGAL] Jumlah liter tidak valid.");
            return false;
        } else if (liter > getStokLiter()) {
            System.out.println("  [GAGAL] Stok Solar tidak cukup.");
            return false;
        } else if (liter > sisaKuota) {
            System.out.println("  [GAGAL] Kuota harian Solar terlampaui. Sisa kuota: " + sisaKuota + " L.");
            return false;
        } else {
            catatPenjualan(liter);
            terpakaiHariIni += liter;
            System.out.println("  [OK] " + liter + " L Solar berhasil diisi. Terpakai hari ini: " + terpakaiHariIni + " L.");
            return true;
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   -> Kuota harian: " + terpakaiHariIni + "/" + kuotaHarian + " L");
    }

    @Override
    public double hitungTotalBayar(double liter) {
        return hitungHarga(liter);
    }

    @Override
    public String getMetodePembayaran() {
        return "Tunai / Kartu Debit";
    }
}