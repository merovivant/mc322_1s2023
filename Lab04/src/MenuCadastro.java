import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public enum MenuCadastro{
    Cliente(1){
        @Override
        public void cadastrar(MenuOperacoes menu) {
            this.menuOperacoes = menu;
            this.scanner = menu.scanner;
            menu.cabecalho("Escolha uma opção: ");
            System.out.println("[1] Cadastrar pessoa física");
            System.out.println("[2] Cadastrar pessoa jurídica");
            System.out.println("[0] Voltar");
            menu.horizontalLine();
            int opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    cadastrarClientePF();
                    break;
                case 2:
                    cadastrarClientePJ();
                    break;
                case 0:
                    menu.cadastrar(scanner);
                default:
                    this.cadastrar(menuOperacoes);
                    break;
            }
        }
    },
    Veiculo(2){
        @Override
        public void cadastrar(MenuOperacoes menu) {
            
        }
    },
    Seguradora(3){
        @Override
        public void cadastrar(MenuOperacoes menu) {
            
        }},
    Voltar(0){
        @Override
        public void cadastrar(MenuOperacoes menu) {
            menu.telaInicial();;
        }
    };
    public final int cadastro;
    public MenuOperacoes menuOperacoes;
    public Scanner scanner;

    MenuCadastro(int cadastro){
        this.cadastro = cadastro;
    }

    public int getCadastro() {
        return cadastro;
    }

    public static MenuCadastro fromValue(int value) {
        for (MenuCadastro cadastro : MenuCadastro.values()) {
            if (cadastro.getCadastro() == value) {
                return cadastro;
            }
        }
        System.out.println("Insira um valor válido!");
        return Voltar;
    }

    public abstract void cadastrar(MenuOperacoes menu);

    public void cadastrarClientePF(){
        int mudar = -1;
        String nome="";
        String cpf="";
        String endereco="";
        String genero="";
        String dataLicenca ="";
        while(mudar!=0){
            scanner.nextLine();
            if(mudar==-1||mudar==1){
                menuOperacoes.cabecalho("Digite o nome: ");
                nome = scanner.nextLine();
            }
            if(mudar==-1||mudar==2){
                menuOperacoes.cabecalho("Digite o CPF: ");
                cpf = scanner.nextLine();
            }
            if(mudar==-1||mudar==3){
                menuOperacoes.cabecalho("Digite o Endereço: ");
                endereco = scanner.nextLine();
            }
            if(mudar==-1||mudar==4){
                menuOperacoes.cabecalho("Entre com gênero: ");
                System.out.println("[M] Masculino");
                System.out.println("[F] Feminino");
                System.out.println("[O] Outro");
                menuOperacoes.horizontalLine();
                genero = scanner.nextLine();
            }
            if(mudar==-1||mudar==5){
                menuOperacoes.cabecalho("Digite a data de licença:");
                System.out.println("[dd/MM/aa]");
                dataLicenca = scanner.nextLine();    
            }
            menuOperacoes.cabecalho("Deseja alterar algum valor?");
            System.out.println("[1] Nome: "+nome);
            System.out.println("[2] CPF: "+cpf);
            System.out.println("[3] Endereço: "+endereco);
            System.out.println("[4] Genero: "+genero);
            System.out.println("[5] Data de Licença: "+dataLicenca);
            System.out.println("[0] Tudo está correto!");
            menuOperacoes.horizontalLine();
            mudar = scanner.nextInt();
        }
        
    }

    public void cadastrarClientePJ(){

    }
}