package Responsi;

public class Main {
    private static void judul(String teks) {
        System.out.println("\n=== " + teks + " ===");
    }

    public static void main(String[] args) {

        judul("REQ 2: Konstanta global & atribut final");
        System.out.println("Konstanta NAMA_SPBU = " + bahanBakar.NAMA_SPBU);
        System.out.println("Konstanta BATAS_LITER_PER_TRANSAKSI = " + bahanBakar.BATAS_LITER_PER_TRANSAKSI);

        judul("REQ 3: Overloaded constructor, getter/setter, this");
        Pertalite pertalite = new Pertalite("P-01", 10000);
        Pertamax pertamax = new Pertamax("X-01", 13500, 800, 0.10, 3);
        Solar solar = new Solar("S-01", 6800);
        System.out.println("Kode BBM (final, hanya getter): " + pertalite.getKodeBBM());
        pertalite.setHargaPerLiter(10200);
        System.out.println("Harga Pertalite setelah setter: Rp" + pertalite.getHargaPerLiter());

        judul("REQ 1: Method void & method return value");
        pertalite.tambahStok(200);
        double bayar = pertalite.hitungHarga(20);
        System.out.println("Harga 20 L Pertalite = Rp" + bayar);

        judul("REQ 4, 5, 6: Inheritance, super(...), super.tampilkanInfo(), @Override");
        pertalite.tampilkanInfo();
        pertamax.tampilkanInfo();
        solar.tampilkanInfo();

        judul("REQ 7 & 8: Abstract method prosesPengisian() dengan if/else & perubahan state");
        bahanBakar[] semuaBBM = { pertalite, pertamax, solar };
        for (bahanBakar d : semuaBBM) {
            System.out.println("Isi 30 L di " + d.getjenisbahanBakar() + ":");
            d.prosesPengisian(30);
        }
        System.out.println("Isi 150 L Pertalite (melebihi batas):");
        pertalite.prosesPengisian(150);
        System.out.println("Habiskan kuota subsidi Pertalite (500 L), lalu coba melebihi kuota:");
        pertalite.prosesPengisian(100);
        pertalite.prosesPengisian(100);
        pertalite.prosesPengisian(100);
        pertalite.prosesPengisian(100);
        pertalite.prosesPengisian(100);
        pertalite.tampilkanInfo();

        judul("REQ 9: Polimorfisme lewat variabel bertipe interface");
        Pembayaran pay1 = new Pertalite("P-02", 10200);
        Pembayaran pay2 = new Pertamax("X-02", 13500);
        Pembayaran pay3 = new Solar("S-02", 6800);
        Pembayaran[] daftarBayar = { pay1, pay2, pay3 };
        for (Pembayaran p : daftarBayar) {
            System.out.println("Metode: " + p.getMetodePembayaran()
                    + " | Total bayar 40 L = Rp" + p.hitungTotalBayar(40));
        }

        judul("REQ 10: Pertamax implements Pembayaran, Perawatan");
        Pembayaran sebagaiPembayaran = pertamax;
        Perawatan sebagaiPerawatan = pertamax;
        System.out.println("Via Pembayaran -> total 25 L = Rp" + sebagaiPembayaran.hitungTotalBayar(25));
        pertamax.prosesPengisian(10);
        pertamax.prosesPengisian(10);   // transaksi ke-3 (sudah 1 dari loop di atas)
        System.out.println("Perlu perawatan? " + sebagaiPerawatan.perluPerawatan());
        System.out.println("Coba isi saat butuh perawatan:");
        pertamax.prosesPengisian(10);
        pertamax.tampilkanInfo();
        sebagaiPerawatan.lakukanPerawatan();
        System.out.println("Setelah perawatan, isi lagi:");
        pertamax.prosesPengisian(10);
        pertamax.tampilkanInfo();

        judul("SELESAI");
    }
}