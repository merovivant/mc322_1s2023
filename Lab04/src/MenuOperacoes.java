import java.util.ArrayList;
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
            listar(scanner);
        }
    },
    RECEITA (3){
        @Override
        public void fazOperacao(Scanner scanner, Seguradora seguradora){
            this.scanner = scanner;
            this.seguradora = seguradora;
            receita();
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
        cabecalho("Cadastrar");
        for(MenuCadastro cadastro : MenuCadastro.values()){
            System.out.println("["+cadastro.getCadastro()+"] " + cadastro);
        }
        horizontalLine();
        MenuCadastro cadastro = MenuCadastro.fromValue(scanner.nextInt());
        if(cadastro == MenuCadastro.Voltar){
            telaInicial();
            return;
        }
        cadastro.cadastrar(this);
        
        
    }

    public void listar(Scanner scanner){
        limparTela();
        horizontalLine();
        System.out.println("Escolha uma opcao para listar: ");
        horizontalLine();
        System.out.println("[1] Clientes da "+ seguradora.getNome());
        System.out.println("[2] Veiculos da "+ seguradora.getNome());
        System.out.println("[3] Sinistros da "+ seguradora.getNome());
        System.out.println("[0] Voltar");
        horizontalLine();
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch(opcao){
            case 1:
                alterarClientes();
                break;
            case 2:
                listarVeiculos();
                break;
            case 3:
                listarSinistros();
                break;
            case 0:
                telaInicial();
                return;
            default:
                listar(scanner);
                break;
        }
    }

    public void alterarClientes(){
        ArrayList<Cliente> listaClientes = seguradora.getListaClientes();
        for(int i=0; i<listaClientes.size(); i+=5){;
            cabecalho("Clientes da "+ seguradora.getNome());
            System.out.println("Escolha um cliente para mais opçoes: ");
            int j=i;
            for(; j<i+5&&j<listaClientes.size(); j++){
                System.out.println("["+j+"] "+listaClientes.get(j).getNome());    
            }
            horizontalLine();
            System.out.println("[V] Voltar");
            if(j<listaClientes.size()){
                System.out.println("[P] Proxima pagina");
            }
            horizontalLine();
            String opcao = scanner.nextLine();
            if(opcao.equals("V") || opcao.equals("v")){
                if(j>5){
                    i-=10;
                }
                else{
                    listar(scanner);
                    return;
                }
            } else if(!(opcao.equals("P") || opcao.equals("p"))){
                try{
                    int opcaoInt = Integer.parseInt(opcao);
                    if(opcaoInt>=i && opcaoInt<j){
                        selecionarCliente(listaClientes.get(opcaoInt));
                        return;
                    } 
                    i-=5;
                } catch(NumberFormatException e){
                    i-=5;
                }
            }
        }
    }
    public void listarVeiculos(){
        cabecalho("Veículos da "+ seguradora.getNome());
        int cont=0;
        for(Cliente cliente:seguradora.getListaClientes()){
            for(Veiculo veiculo: cliente.getListaVeiculos()){
                System.out.println("    "+veiculo.toString());
                cont++;
            }
        }
        if(cont==0){
            System.out.println("Nenhum veículo cadastrado na seguradora");
        }
        horizontalLine();
        System.out.println("[V] Voltar");
        horizontalLine();
        String opcao = scanner.nextLine();
        if(opcao.equals("V")||opcao.equals("v")){
            listar(scanner);
            return;
        }
        else {
            listarVeiculos();
        }
    }
    public void listarSinistros(){
        cabecalho("Sinistros da "+ seguradora.getNome());
        int cont=0;
        for(Sinistro sinistro:seguradora.getListaSinistros()){
            System.out.println(sinistro.toString());
            cont++;
            horizontalLine();
        }
        if(cont==0){
            System.out.println("Nenhum sinistro cadastrado na seguradora");
            horizontalLine();
        }
        System.out.println("[V] Voltar");
        horizontalLine();
        String opcao = scanner.nextLine();
        if(opcao.equals("V")||opcao.equals("v")){
            listar(scanner);
            return;
        }
        else {
            listarSinistros();
        }
    }

    public void selecionarCliente(Cliente cliente){
        cabecalho(cliente.toString());
        System.out.println("[1] Listar veículos");
        System.out.println("[2] Listar sinistros");
        System.out.println("[V] Voltar");
        horizontalLine();
        String opcao = scanner.nextLine();
        if(opcao.equals("V")||opcao.equals("v")){
            alterarClientes();
            return;
        }else{
            try{
                int opcaoInt = Integer.parseInt(opcao);
                opcaoLista: while(opcaoInt==2 || opcaoInt==1){
                    cabecalho("Cliente: "+cliente.getNome());
                    switch(opcaoInt){
                        case 1:
                            int cont = 0;
                            for(Veiculo veiculo: cliente.getListaVeiculos()){
                                System.out.println("    "+veiculo.toString());
                                cont++;
                            }
                            
                            if(cont==0){
                                System.out.println("Nenhum veículo cadastrado!");
                                horizontalLine();
                            } else {
                                horizontalLine();
                                System.out.println("[E] Excluir veículo");
                            }
                            
                            
                            System.out.println("[V] Voltar");
                            horizontalLine();
                            opcao = scanner.nextLine();
                            while((opcao.equals("E")||opcao.equals("e")) && cont>0){
                                cabecalho("Digite o numero do veículo que deseja excluir: ");
                                cont = 0;
                                for(Veiculo veiculo: cliente.getListaVeiculos()){
                                    System.out.println("["+cont+"] "+veiculo.toString());
                                    cont++;
                                }
                                horizontalLine();
                                System.out.println("[V] Voltar");
                                horizontalLine();
                                opcao=scanner.nextLine();
                                try{
                                    opcaoInt = Integer.parseInt(opcao);
                                    if(opcaoInt>=0 && opcaoInt<cont){
                                        cliente.getListaVeiculos().remove(opcaoInt);
                                        opcaoInt=1;
                                        continue opcaoLista;
                                    }  
                                } catch(NumberFormatException e){
                                    if(opcao.equals("V")||opcao.equals("v")){
                                        opcaoInt=1;
                                        continue opcaoLista;
                                    }
                                }
                                opcao="E";
                            }
                            if(opcao.equals("V")||opcao.equals("v")){
                                selecionarCliente(cliente);
                                return;
                            }
                            continue;
                        case 2:
                            if(seguradora.visualizarSinistro(cliente)>0) {
                                horizontalLine();
                                System.out.println("[E] Excluir sinistro");
                            }
                            else {
                                System.out.println("Nenhum sinistro encontrado!");
                                horizontalLine();
                            }
                            System.out.println("[V] Voltar");
                            horizontalLine();
                            opcao = scanner.nextLine();
                            if(opcao.equals("V")||opcao.equals("v")){
                                selecionarCliente(cliente);
                                return;
                            }else if(opcao.equals("E")||opcao.equals("e")){
                                Boolean flag = false;
                                while(true){
                                    cabecalho("Entre com o ID do sinistro");
                                    if(flag) System.out.println("ID inválido!");
                                    System.out.println("[V] Voltar");
                                    horizontalLine();
                                    opcao = scanner.nextLine();
                                    if(opcao.equals("V")||opcao.equals("v")){
                                        selecionarCliente(cliente);
                                        return;
                                    }else{
                                        try{
                                            opcaoInt = Integer.parseInt(opcao);
                                            for(Sinistro sinistro : seguradora.getListaSinistros()){
                                                if(sinistro.getId() == opcaoInt){
                                                    seguradora.getListaSinistros().remove(sinistro);
                                                    System.out.println("Sinistro removido com sucesso!");
                                                    horizontalLine();
                                                    System.out.println("[V] Voltar");
                                                    horizontalLine();
                                                    opcao = scanner.nextLine();
                                                    if(opcao.equals("V")||opcao.equals("v")){
                                                        selecionarCliente(cliente);
                                                        return;
                                                    }
                                                    break;
                                                } else {
                                                    selecionarCliente(cliente);
                                                    return;
                                                }
                                            }
                                            flag = true;
                                        } catch(NumberFormatException e){
                                            selecionarCliente(cliente);
                                            return;
                                        }
                                    }
                                }
                            }
                            break;
                        default:
                            selecionarCliente(cliente);
                            break;
                    }
                    scanner.nextLine();   
                }
            } catch(NumberFormatException e){
                selecionarCliente(cliente);
                return;
            }
        }
    }
    public void receita(){
        cabecalho("Receita da "+ seguradora.getNome());
        seguradora.calcularReceita();
        horizontalLine();
        System.out.println("[V] Voltar");
        horizontalLine();
        String opcao = scanner.nextLine();
        if(opcao.equals("V")||opcao.equals("v")){
            telaInicial();
            return;
        }
        else {
            receita();
        }
    }
}