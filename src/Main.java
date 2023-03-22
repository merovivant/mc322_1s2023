public class Main {
    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("9999999999 OI EU SOU O JONATAS");
        System.out.println(cliente.validarCPF(cliente.getCpf()));
    }
}
