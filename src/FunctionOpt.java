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
            // Insufficient balance for the transaction
            return -1; // You can return any value indicating failure
        }
    }

    public double SetorTunai(double transaksi) {
        return saldo += transaksi;
    }
}