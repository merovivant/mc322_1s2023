import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //Instanciamento de Veículos
        Veiculo veiculo01 = new Veiculo("FRJ2345", "Hyundai", "I30", 2015);
        veiculo01.setModelo("HB20"); 
        Veiculo veiculo02 = new Veiculo("MOI9283", "Renault", "I30",2012);
        veiculo02.setMarca("Hyundai");
        Veiculo veiculo03 = new Veiculo("3", "Hyundai", "I30",2020);
        veiculo03.setPlaca("JHN0928");
        Veiculo veiculo04 = new Veiculo("KJE0938","Renault", "Sandero",2022);
        Veiculo veiculo05 = new Veiculo("VEI0505", "Renault", "Duster", 2018);

        ArrayList<Veiculo> listaVeiculos01 = new ArrayList<Veiculo>(); 
        ArrayList<Veiculo> listaVeiculos02 = new ArrayList<Veiculo>(); 
        ArrayList<Veiculo> listaVeiculos03 = new ArrayList<Veiculo>();
        listaVeiculos01.add(veiculo05);
        listaVeiculos02.add(veiculo01);
        listaVeiculos02.add(veiculo02);
        listaVeiculos03.add(veiculo03);
        listaVeiculos03.add(veiculo04);

        //Instanciamento da Seguradora
        Seguradora safeCar = new Seguradora("Safelife", "11990112760", "safecar@gmail.com", "Rua dos Salvos, 183");
        safeCar.setNome("SafeCar");
        
        //Instanciamento de Clientes
        ClientePF cliente01 = new ClientePF("Lucas", "Avenida Aguia de Haia, 1200", listaVeiculos01, 
        "643.113.761-20", "M", LocalDate.now(),"Técnico",LocalDate.of(2003, 03, 25),"C");
        cliente01.setNome("Lucas Alberto dos Santos");
        ClientePF cliente02 = new ClientePF("Ana Maria Silva", "Rua das Flores, 123", listaVeiculos01,"613.185.865-77", "F", LocalDate.of(1992, 5, 12), "Ensino Superior Completo", LocalDate.of(2022, 3, 1), "D");
        ClientePF cliente03 = new ClientePF("Luiz Fernando Santos", "Avenida Paulista, 456", listaVeiculos01,"129.639.697-81", "M", LocalDate.of(1985, 11, 10), "Ensino Médio Completo", LocalDate.of(2022, 4, 5), "B");
        ClientePJ cliente04 = new ClientePJ("Empresa de Tecnologia XYZ Ltda", "Rua das Acácias, 789", listaVeiculos02, "75.525.966/0001-20", LocalDate.of(1995, 10, 15),200);
        ClientePJ cliente05 = new ClientePJ("Indústria de Alimentos ABC S.A.", "Avenida das Oliveiras, 456", listaVeiculos03, "88.215.509/0001-08", LocalDate.of(1980, 8, 22),100);
            //OBS: os métodos validarCPF e validarCNPJ são chamados nos respectivos construtores das classes CPF e CNPJ.

        //Cadastrando alguns clientes na seguradora SafeCar:
        safeCar.CadastrarCliente(cliente01);
        safeCar.CadastrarCliente(cliente02);
        safeCar.CadastrarCliente(cliente04);
        safeCar.CadastrarCliente(cliente05);
        safeCar.RemoverCliente("Ana Maria Silva");

        //Instanciando um sinsistro e gerando o mesmo:
        Sinistro sinistro = new Sinistro(LocalDate.now(), "Rua Doutor Shigeo Mori, 2009", safeCar, veiculo05, cliente01);
        safeCar.GerarSinistro(veiculo05, cliente01, "Rua Doutor Shigeo Mori, 2009");

        // //Chamando o método toString de cada classe:
        // System.out.println(cliente01.toString()+"\n");
        // System.out.println(veiculo05.toString()+"\n");
        // System.out.println(safeCar.toString()+"\n");
        // safeCar.visualizarSinistro("Lucas Alberto dos Santos");
        //     //O toString do sinistro é chamado quando se visualiza o mesmo.
        // System.out.println("\n"+cliente04.toString()+"\n");

        GerarMenu(safeCar);
    }

    public static void GerarMenu(Seguradora seguradora){
        //Criando o menu interativo
        Scanner scanner = new Scanner(System.in);
        MenuOperacoes menu = MenuOperacoes.INICIAR;
        menu.fazOperacao(scanner,seguradora);
        do {
            menu = MenuOperacoes.fromValue(scanner.nextInt());
            menu.fazOperacao(scanner,seguradora);
        } while (menu != MenuOperacoes.SAIR);
        scanner.close();
    }
}
