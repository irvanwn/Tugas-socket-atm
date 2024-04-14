public class FunctionOpt {

    private double saldo = 1000;

    public double cekSaldo() {
        return saldo;
    }

    public double TarikTunai(double transaksi) {
        if (saldo >= transaksi) {
            saldo -= transaksi;
            return saldo;
        } else {
            return -1;
        }
    }

    public double SetorTunai(double transaksi) {
        return saldo += transaksi;
    }
}