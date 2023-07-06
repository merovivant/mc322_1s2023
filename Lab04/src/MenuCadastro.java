import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public enum MenuCadastro{
    Cliente(1){
        @Override
        public void cadastrar(MenuOperacoes menu) {
            this.menuOperacoes = menu;
            this.scanner = menu.scanner;
            menu.cabecalho("Escolha uma opçao: ");
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
                    break;
                default:
                    this.cadastrar(menuOperacoes);
                    break;
            }
        }
    },
    Veiculo(2){
        @Override
        public void cadastrar(MenuOperacoes menu) {
            this.menuOperacoes = menu;
            this.scanner = menu.scanner;
            scanner.nextLine();
            Veiculo veiculo = cadastrarVeiculo(1);
            Boolean flag = false;
            while(true){
                menu.cabecalho("Cadastrar Veículo: ");
                if(flag) System.out.println("Cliente nao encontrado!");
                System.out.println("Insira um CPF ou CNPJ válido:");
                menu.horizontalLine();
                String cpfCnpj = scanner.nextLine();
                cpfCnpj = cpfCnpj.replaceAll("[^0-9]", "");
                for(Cliente cliente: menuOperacoes.seguradora.getListaClientes()){
                    if(cliente instanceof ClientePF){
                        if(((ClientePF) cliente).getCPF().equals(cpfCnpj)){
                            cliente.getListaVeiculos().add(veiculo);
                            menu.cabecalho("Veículo cadastrado com sucesso ao cliente!");
                            System.out.println("[1] Cadastrar outro veículo");
                            System.out.println("[0] Voltar");
                            menu.horizontalLine();
                            int opcao = scanner.nextInt();
                            switch (opcao){
                                case 1:
                                    this.cadastrar(menuOperacoes);
                                    break;
                                case 0:
                                    menu.cadastrar(scanner);
                                    return;
                                default:
                                    this.cadastrar(menuOperacoes);
                                    break;
                            }
                        }
                    }else if(cliente instanceof ClientePJ){
                        if(((ClientePJ) cliente).getCNPJ().equals(cpfCnpj)){
                            cliente.getListaVeiculos().add(veiculo);
                            menu.cabecalho("Veículo cadastrado com sucesso ao cliente!");
                            System.out.println("[1] Cadastrar outro veículo");
                            System.out.println("[0] Voltar");
                            menu.horizontalLine();
                            int opcao = scanner.nextInt();
                            switch (opcao){
                                case 1:
                                    this.cadastrar(menuOperacoes);
                                    break;
                                case 0:
                                    menu.cadastrar(scanner);
                                    break;
                                default:
                                    this.cadastrar(menuOperacoes);
                                    break;
                            }
                        }
                    }
                }
                flag = true;
            }
        }
    },
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
        String educacao="";
        String dataNascimento="";
        int rendaMensalPerCapta=0;
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        while(mudar!=-2){
            scanner.nextLine();
            if(mudar==0){
                try {
                    Cliente cliente = new ClientePF(nome, endereco, veiculos, cpf, genero, dataLicenca, educacao, dataNascimento, rendaMensalPerCapta);
                    menuOperacoes.seguradora.cadastrarCliente(cliente);
                    menuOperacoes.cabecalho("Cliente cadastrado com sucesso!");
                    System.out.println("[1] Cadastrar outro cliente");
                    System.out.println("[0] Voltar");
                    menuOperacoes.horizontalLine();
                    int opcao = scanner.nextInt();
                    if (opcao==0){
                        mudar = -2;
                        continue;
                    }
                    else{
                        this.cadastrar(menuOperacoes);
                    }
                } catch(ExceptionCadastro e) { 
                    menuOperacoes.cabecalho("Erro ao cadastrar cliente: "+e.getMessage());
                    System.out.println();
                    System.out.println("[1] Corrigir dados");
                    System.out.println("[0] Cancelar cadastro");
                    menuOperacoes.horizontalLine();
                    int opcao = scanner.nextInt();
                    if (opcao==0){
                        mudar = -2;
                    }
                    else{
                        mudar = e.getCodigoMudar();
                    }
                    continue;
                }
            }
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
            if(mudar==-1||mudar==6){
                menuOperacoes.cabecalho("Digite o nível de educaçao:");
                System.out.println("[1] Ensino Fundamental");
                System.out.println("[2] Ensino Médio");
                System.out.println("[3] Ensino Superior");
                System.out.println("[4] Pós-Graduação");
                System.out.println("[5] Mestrado");
                System.out.println("[6] Doutorado");
                menuOperacoes.horizontalLine();
                educacao = scanner.nextLine();
                switch(educacao){
                    case "1":
                        educacao = "Ensino Fundamental";
                        break;
                    case "2":
                        educacao = "Ensino Médio";
                        break;
                    case "3":
                        educacao = "Ensino Superior";
                        break;
                    case "4":
                        educacao = "Pós-Graduação";
                        break;
                    case "5":
                        educacao = "Mestrado";
                        break;
                    case "6":
                        educacao = "Doutorado";
                        break;
                    default:
                        mudar = 6;
                        break;}
            }
            if(mudar==-1||mudar==7){
                menuOperacoes.cabecalho("Digite a data de nascimento:");
                System.out.println("[dd/MM/aa]");
                dataNascimento = scanner.nextLine();
            }
            if(mudar==-1||mudar==8){
                menuOperacoes.cabecalho("Digite a renda mensal per capta:");
                System.out.print("R$");
                rendaMensalPerCapta = scanner.nextInt();
                scanner.nextLine();
            }
            if(mudar==-1||mudar==9){
                menuOperacoes.cabecalho(nome+", quantos veículos estao em sua posse?");
                int quantidadeVeiculos = scanner.nextInt();
                scanner.nextLine();
                if(quantidadeVeiculos>0 && quantidadeVeiculos < 1000){
                    for(int i=0;i<quantidadeVeiculos;i++){
                        Veiculo veiculo = this.cadastrarVeiculo(i+1);
                        if(veiculo != null) veiculos.add(veiculo);
                        else this.menuOperacoes.telaInicial();
                    }
                }
                else mudar = 9;
            }
            menuOperacoes.cabecalho("Deseja alterar algum valor?");
            System.out.println("[1] Nome: "+nome);
            System.out.println("[2] CPF: "+cpf);
            System.out.println("[3] Endereço: "+endereco);
            System.out.println("[4] Genero: "+genero);
            System.out.println("[5] Data de Licença: "+dataLicenca);
            System.out.println("[6] Nível de Educação: "+educacao);
            System.out.println("[7] Data de Nascimento: "+dataNascimento);
            System.out.println("[8] Renda Mensal Per Capta: R$"+rendaMensalPerCapta);
            System.out.println("[9] Veículos: "+veiculos.size());
            System.out.println("[0] Tudo está correto!");
            menuOperacoes.horizontalLine();
            mudar = scanner.nextInt();
        }
        menuOperacoes.telaInicial();
    }

    public void cadastrarClientePJ(){
        int mudar = -1;
        String nome="";
        String cnpj="";
        String endereco="";
        String dataFundacao="";
        int qtdeFuncionarios=0;
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        while(mudar!=-2){
            scanner.nextLine();
            if(mudar==0){
                try {
                    Cliente cliente = new ClientePJ(nome, endereco, veiculos, cnpj, dataFundacao, qtdeFuncionarios);
                    menuOperacoes.seguradora.cadastrarCliente(cliente);
                    menuOperacoes.cabecalho("Cliente cadastrado com sucesso!");
                    System.out.println("[1] Cadastrar outro cliente");
                    System.out.println("[0] Voltar");
                    menuOperacoes.horizontalLine();
                    int opcao = scanner.nextInt();
                    if (opcao==0){
                        mudar = -2;
                        continue;
                    }
                    else{
                        this.cadastrar(menuOperacoes);
                    }
                } catch(ExceptionCadastro e) { 
                    menuOperacoes.cabecalho("Erro ao cadastrar cliente: "+e.getMessage());
                    System.out.println();
                    System.out.println("[1] Corrigir dados");
                    System.out.println("[0] Cancelar cadastro");
                    menuOperacoes.horizontalLine();
                    int opcao = scanner.nextInt();
                    if (opcao==0){
                        mudar = -2;
                    }
                    else{
                        mudar = e.getCodigoMudar();
                    }
                    continue;
                }
            }
            if(mudar==-1||mudar==1){
                menuOperacoes.cabecalho("Digite o nome: ");
                nome = scanner.nextLine();
            }
            if(mudar==-1||mudar==2){
                menuOperacoes.cabecalho("Digite o CNPJ: ");
                cnpj = scanner.nextLine();
            }
            if(mudar==-1||mudar==3){
                menuOperacoes.cabecalho("Digite o Endereço: ");
                endereco = scanner.nextLine();
            }
            if(mudar==-1||mudar==4){
                menuOperacoes.cabecalho("Digite a data de fundação: ");
                System.out.println("[dd/MM/aa]");
                dataFundacao = scanner.nextLine();
            }
            if(mudar==-1||mudar==5){
                menuOperacoes.cabecalho("Digite a quantidade de funcionários: ");
                qtdeFuncionarios = scanner.nextInt();
                scanner.nextLine();
            }
            if(mudar==-1||mudar==6){
                menuOperacoes.cabecalho("Quantos veículos estao em sua posse?");
                int quantidadeVeiculos = scanner.nextInt();
                scanner.nextLine();
                if(qtdeFuncionarios>0 && qtdeFuncionarios < 1000){
                    for(int i=0;i<quantidadeVeiculos;i++){
                        Veiculo veiculo = this.cadastrarVeiculo(i+1);
                        if(veiculo != null) veiculos.add(veiculo);
                        else this.menuOperacoes.telaInicial();
                    }
                }
                else mudar = 6;
            }
            menuOperacoes.cabecalho("Deseja alterar algum valor?");
            System.out.println("[1] Nome: "+nome);
            System.out.println("[2] CNPJ: "+cnpj);
            System.out.println("[3] Endereço: "+endereco);
            System.out.println("[4] Data de Fundação: "+dataFundacao);
            System.out.println("[5] Quantidade de Funcionários: "+qtdeFuncionarios);
            System.out.println("[6] Veículos: "+veiculos.size());
            System.out.println("[0] Tudo está correto!");
            menuOperacoes.horizontalLine();
            mudar = scanner.nextInt();
        }
        menuOperacoes.telaInicial();
    }

    public Veiculo cadastrarVeiculo(int i){
        int mudar = -1;
        String placa="";
        String marca="";
        String modelo="";
        int anoFabricacao=0;
        while(mudar!=-2){
            if(mudar==0){
                try{
                   Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
                    return veiculo;
                }
                catch(ExceptionCadastro e){
                    System.out.println("Erro ao cadastrar veículo: "+e.getMessage());
                    menuOperacoes.horizontalLine();
                    System.out.println("[1] Corrigir dados");
                    System.out.println("[0] Cancelar cadastro");
                    menuOperacoes.horizontalLine();
                    int opcao = scanner.nextInt();
                    if (opcao==0){
                        mudar = -2;
                    }
                    else{
                        mudar = e.getCodigoMudar();
                    }
                    continue;
                }
            }
            if(mudar==-1||mudar==1){
                menuOperacoes.cabecalho("Digite a placa do seu veículo "+i+": ");
                placa = scanner.nextLine();
            }
            if(mudar==-1||mudar==2){
                menuOperacoes.cabecalho("Digite a marca do seu veículo "+i+": ");
                marca = scanner.nextLine();
            }
            if(mudar==-1||mudar==3){
                menuOperacoes.cabecalho("Digite o modelo do seu veículo "+i+": ");
                modelo = scanner.nextLine();
            }
            if(mudar==-1||mudar==4){
                menuOperacoes.cabecalho("Digite o ano de fabricaçao do seu veículo "+i+": ");
                anoFabricacao = scanner.nextInt();    
                scanner.nextLine();
            }
            
            menuOperacoes.cabecalho("Deseja alterar algum valor?");
            System.out.println("[1] Placa: "+placa);
            System.out.println("[2] Marca: "+marca);
            System.out.println("[3] Modelo: "+modelo);
            System.out.println("[4] Ano de Fabricaçao: "+anoFabricacao);
            System.out.println("[0] Tudo está correto!");
            menuOperacoes.horizontalLine();
            mudar = scanner.nextInt();
            scanner.nextLine();
        }
        return null;
    }
}