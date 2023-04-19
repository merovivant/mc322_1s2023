public class Main {
    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente("Jonatas Santos", "53922410855 OI EU SOU O JONATAS", "05/10/2003", 19, "Avenida Aguia de Haia, 1200");
        System.out.println(cliente.toString());
        cliente.setNome("Jonatas de Sousa Santos");
        System.out.println(cliente.toString());

        Seguradora seguradora = new Seguradora("Safelife", "11990112760", "safecar@gmail.com", "Rua dos Salvos, 183");
        System.out.println(seguradora.toString());
        seguradora.setNome("SafeCar");
        System.out.println(seguradora.toString());

        Veiculo veiculo = new Veiculo("FRJ2345", "Hyundai", "I30");
        System.out.println(veiculo.toString());
        veiculo.setModelo("HB20");
        System.out.println(veiculo.toString());

        Sinistro sinistro = new Sinistro("hoje", "Rua Doutor Shigeo Mori, 2009");
        System.out.println(sinistro.toString());
        sinistro.setData("03/04/2023");
        System.out.println(sinistro.toString());

        Sinistro sinistro2 = new Sinistro("07/04/2023", "Rua Doutor Ruy Vicente Mello, 908");
        System.out.println(sinistro2.toString());
    }
}
