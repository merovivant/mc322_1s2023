import java.util.Scanner;

public enum MenuOperacoes {
    INICIAR (-1){
        @Override
        public void fazOperacao(Scanner scanner, Seguradora seguradora) {
            this.scanner = scanner;
            this.seguradora = seguradora;
            telaInicial();
        }    
    },
    SAIR (0){
        @Override
        public void fazOperacao(Scanner scanner, Seguradora seguradora){
            limparTela();
        }
    },
    CADASTRAR (1){
        @Override
        public void fazOperacao(Scanner scanner, Seguradora seguradora) {
            this.scanner = scanner;
            this.seguradora = seguradora;
            cadastrar(scanner);
        }    
    },
    LISTAR (2){
        @Override
        public void fazOperacao(Scanner scanner, Seguradora seguradora){
            this.scanner = scanner;
            this.seguradora = seguradora;
        }
    },
    EXCLUIR (3){
        @Override
        public void fazOperacao(Scanner scanner, Seguradora seguradora) {
            return;
        }    
    };
    public final int operacao;
    public Seguradora seguradora;
    public Scanner scanner;

    MenuOperacoes (int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao () {
        return this.operacao;
    }   
    
    public static MenuOperacoes fromValue(int value) {
        for (MenuOperacoes operacao : MenuOperacoes.values()) {
            if (operacao.getOperacao() == value) {
                return operacao;
            }
        }
        System.out.println("Insira um valor válido!");
        return INICIAR;
    }

    public abstract void fazOperacao(Scanner scanner, Seguradora seguradora);

    public void limparTela(){
        System.out.print("\033[H\033[2J");
    }

    public void horizontalLine(){
        System.out.println("___________________________________________");
    }

    public void cabecalho(String string){
        limparTela();
        horizontalLine();
        System.out.println(string);
        horizontalLine();
    }

    public void telaInicial(){
        cabecalho("Seguradora "+seguradora.getNome());
        System.out.println("O que deseja fazer?");
        for(MenuOperacoes operacao : MenuOperacoes.values()){
            if(operacao!=INICIAR && operacao !=SAIR)
                System.out.println("["+ operacao.getOperacao()+"] "+ operacao);            
        }
        System.out.println("[0] SAIR");            
        horizontalLine();
    }

    public void cadastrar(Scanner scanner){
        limparTela();
        horizontalLine();
        System.out.println("Cadastrar");
        horizontalLine();
        for(MenuCadastro cadastro : MenuCadastro.values()){
            System.out.println("["+cadastro.getCadastro()+"] " + cadastro);
        }
        horizontalLine();
        MenuCadastro cadastro = MenuCadastro.fromValue(scanner.nextInt());
        cadastro.cadastrar(this);
        
    }
}