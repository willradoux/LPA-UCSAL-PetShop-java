// Atitivade Feita por Willian e Romilton

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Professora, aqui abrimos o Scanner porque precisamos receber as informações digitadas no terminal
        Scanner scan = new Scanner(System.in);

        // Professora, a gente declarou as variaveis em uma linha, porque queriamos poupar espaço
        // Essas aqui são os contadores que vamos usando durante o dia inteiro
        int quantidadePedidos = 0, brinquedosVendidos = 0, clientesComRacaoEPetisco = 0, quantidadeContribuicoes = 0;

        // Essas são as variaveis de valores, usamos double porque estamos trabalhando com dinheiro
        // e podem existir valores quebrados, principalmente por causa dos descontos
        double totalRecebido = 0, somaContribuicoes = 0, maiorPedido = 0;

        // Aqui guardamos o nome de quem fez o maior pedido e deixamos o continuar começando com S
        // para o programa conseguir entrar no while pela primeira vez
        String clienteMaiorPedido = "", continuar = "S";

        System.out.println("Atividade feita por Willian e Romilton");
        System.out.println();
        System.out.println("PETIS SHOPIS");


        while (continuar.equalsIgnoreCase("S")) {
            // Professoa, a gente fez uma condição que recebe um caracter
            // e chamamos o metodo equals pra ignorar se o caracter é maiusculo ou minusculo

            // Toda vez que esse while começa significa que um novo pedido vai ser cadastrado
            System.out.println();
            System.out.print("Nome do cliente: ");
            String nomeCliente = scan.nextLine();

            // O total começa zerado porque cada cliente tem o seu proprio pedido
            double totalPedido = 0;

            // Aqui usamos boolean para depois saber se o cliente comprou esses dois produtos
            // começa falso porque até esse momento ele ainda não comprou nada
            boolean comprouRacao = false;
            boolean comprouPetisco = false;

            // Essa variavel vai receber o codigo escolhido no menu
            int codigo;

            // Professora, usamos o do while porque precisamos mostrar os produtos pelo menos uma vez
            // e continuar recebendo produtos até o usuario digitar zero
            do {
                mostrarProdutos();

                // Aqui chamamos a função de ler inteiro para não deixar o programa quebrar
                // se a pessoa resolver digitar uma letra onde deveria ser numero
                codigo = lerInteiro(scan, "Digite o codigo do produto: ");

                // O zero foi escolhido para encerrar o pedido, então usamos break para sair daqui
                if (codigo == 0) {break;}

                // Como só existem os codigos de 1 até 4, qualquer coisa fora disso é invalida
                if (codigo < 1 || codigo > 4) {
                    System.out.println("Codigo de produto invalido.");
                } else {

                    // Se o produto existe, agora perguntamos quantas unidades o cliente quer
                    int quantidade = lerInteiro(
                            scan,
                            "Digite a quantidade: "
                    );

                    // Não faria muito sentido comprar zero produtos ou uma quantidade negativa
                    // então tratamos isso aqui também
                    if (quantidade <= 0) {
                        System.out.println("Quantidade invalida.");

                    } else {

                        // Aqui pegamos o preço do produto de acordo com o codigo
                        // passamos a quantidade também porque o petisco tem uma promoção
                        double preco = calcularPreco(codigo, quantidade);

                        // Aqui fazemos a conta mais direta do pedido:
                        // preço do produto vezes a quantidade comprada
                        double valorItem = preco * quantidade;totalPedido = totalPedido + valorItem;

                        // Se o codigo for 1, marcamos que esse cliente comprou ração
                        if (codigo == 1) {comprouRacao = true;}

                        // Se for brinquedo, além do pedido normal a gente também vai somando
                        // quantos brinquedos foram vendidos no dia inteiro
                        if (codigo == 2) {brinquedosVendidos = brinquedosVendidos + quantidade;}

                        // Mesma ideia da ração, só que agora guardando se ele comprou petisco
                        if (codigo == 4) {comprouPetisco = true;}

                        // Aqui mostramos quanto custou esse item e quanto o pedido já acumulou
                        System.out.printf("Valor do item: R$ %.2f%n", valorItem);
                        System.out.printf("Total do pedido ate agora: R$ %.2f%n", totalPedido);
                    }
                }

            } while (codigo != 0);

            // Depois que acabou de escolher os produtos, mandamos o total para a função
            // que calcula qual desconto esse cliente vai receber
            double desconto = calcularDesconto(totalPedido);

            // Aqui simplesmente pegamos o total original e tiramos o desconto calculado
            double valorComDesconto = totalPedido - desconto;

            // Depois do pedido perguntamos se a pessoa quer colaborar com a campanha
            System.out.print("Deseja contribuir com a campanha? (S/N): ");
            String resposta = scan.nextLine();

            // Começa em zero porque nem todo cliente vai querer contribuir
            double contribuicao = 0;

            if (resposta.equalsIgnoreCase("S")) {
                // Professora, destrinchamos esse if aqui porque ficou dificil ler ele em uma linha só

                // A contribuição é 2% do valor que já passou pelo desconto
                contribuicao = valorComDesconto * 0.02;

                // Aqui vamos acumulando todo dinheiro arrecadado para a campanha
                somaContribuicoes += contribuicao;

                // E aqui contamos quantas pessoas realmente fizeram uma contribuição
                quantidadeContribuicoes++;
            }

            // Professora, fizemos uma conta aqui de matematica, que basicamente soma e encrementa os valores recebidos
            // numa logica de atualizar as estatisticas do dia, como quantidade de pedidos, total recebido,
            // quantidade de clientes que compraram racao e petisco e tambem guardar o maior pedido feito.
            double valorFinal = valorComDesconto + contribuicao; quantidadePedidos++; totalRecebido += valorFinal;

            // Aqui o && significa que as duas coisas precisam ser verdadeiras
            // então só contamos quem comprou ração E petisco no mesmo pedido
            if (comprouRacao && comprouPetisco) {clientesComRacaoEPetisco++;}

            // Aqui comparamos os pedidos para descobrir qual foi o maior do dia
            // se for o primeiro pedido ele já vira o maior, depois só troca se aparecer um maior que ele
            if (quantidadePedidos == 1 || valorFinal > maiorPedido) {maiorPedido = valorFinal; clienteMaiorPedido = nomeCliente;}

            // Aqui é só o comprovante/resumo que aparece depois que o pedido termina
            System.out.println();
            System.out.println("----- RESUMO DO PEDIDO -----");
            System.out.println("Cliente: " + nomeCliente);
            System.out.printf("Valor antes do desconto: R$ %.2f%n", totalPedido);
            System.out.printf("Desconto: R$ %.2f%n", desconto);
            System.out.printf("Contribuicao: R$ %.2f%n", contribuicao);
            System.out.printf("Valor final: R$ %.2f%n", valorFinal);
            System.out.println("----------------------------");

            // Aqui decidimos se voltamos para o começo do while com outro cliente
            // ou se encerramos o expediente do Petis Shopis
            System.out.print("\nDeseja registrar outro pedido? (S/N): ");
            continuar = scan.nextLine();
        }

        // A média começa em zero porque existe a possibilidade de ninguém ter contribuido
        double mediaContribuicoes = 0;

        // Professora, colocamos essa condição porque dividir por zero não seria uma ideia muito boa
        // então só calculamos a média se pelo menos uma contribuição tiver acontecido
        if (quantidadeContribuicoes > 0) {mediaContribuicoes = somaContribuicoes / quantidadeContribuicoes;}

        // Também começamos o percentual em zero caso nenhum pedido tenha sido realizado
        double percentualRacaoEPetisco = 0;

        // Aqui calculamos qual porcentagem dos clientes comprou os dois produtos
        // usamos double para a divisão conseguir ter casas decimais
        if (quantidadePedidos > 0) {
            percentualRacaoEPetisco = ((double) clientesComRacaoEPetisco / quantidadePedidos) * 100;
        }

        // Professora, chegando aqui significa que o dia acabou
        // então agora a gente só pega tudo que foi acumulando durante o programa e mostra na tela
        System.out.println();
        System.out.println("===== ENCERRAMENTO DO DIA =====");
        System.out.println("Quantidade de pedidos: " + quantidadePedidos);
        System.out.printf("Valor total recebido: R$ %.2f%n", totalRecebido);
        System.out.println("Brinquedos vendidos: " + brinquedosVendidos);
        System.out.printf("Media das contribuicoes: R$ %.2f%n", mediaContribuicoes);
        System.out.println("Cliente com maior pedido: " + clienteMaiorPedido);
        System.out.printf("Valor do maior pedido: R$ %.2f%n", maiorPedido);

        System.out.printf("Clientes que compraram racao e petisco: %.2f%%%n", percentualRacaoEPetisco);

        // Terminamos de usar o Scanner então fechamos ele aqui no final
        scan.close();
    }

    // Professora, essa função existe só para não deixar esse monte de println no meio da Main
    // toda vez que precisamos do menu é só chamar mostrarProdutos()
    public static void mostrarProdutos() {

        System.out.println();
        System.out.println("----- PRODUTOS -----");
        System.out.println("1 - Pacote de racao - R$ 25,00");
        System.out.println("2 - Brinquedo - R$ 15,00");
        System.out.println("3 - Shampoo - R$ 18,00");
        System.out.println("4 - Petisco - R$ 8,00");
        System.out.println("0 - Encerrar pedido");
    }

    // Professora, fizemos essa função para tratar aquela situação classica:
    // o programa pede um numero e a pessoa resolve digitar uma palavra
    // desse jeito ele avisa e pergunta novamente ao inves de simplesmente quebrar
    public static int lerInteiro(Scanner scan, String mensagem) {

        // O numero vai guardar o valor digitado e o valido controla quando conseguimos uma entrada certa
        int numero = 0;
        boolean valido = false;

        // Enquanto não recebermos um numero inteiro válido continuamos perguntando
        while (!valido) {

            try {

                System.out.print(mensagem);

                // Como estamos lendo com nextLine, transformamos o texto recebido em inteiro aqui
                numero = Integer.parseInt(scan.nextLine());

                // Se chegou nessa linha sem dar erro significa que conseguimos um numero de verdade
                valido = true;

            } catch (NumberFormatException erro) {

                // Se não conseguiu transformar em inteiro, o catch segura o erro
                // e o programa continua vivo para perguntar de novo
                System.out.println("Entrada invalida. Digite apenas numeros inteiros.");
            }
        }

        // Depois que conseguimos um numero certo devolvemos ele para onde a função foi chamada
        return numero;
    }

    // Professora, essa função basicamente funciona como nossa tabela de preços
    // recebe o codigo e devolve quanto aquele produto custa
    public static double calcularPreco(int codigo, int quantidade) {

        // Os tres primeiros produtos têm preço fixo
        if (codigo == 1) {return 25.00;}
        if (codigo == 2) {return 15.00;}
        if (codigo == 3) {return 18.00;}

        // O petisco ficou separado porque ele é o unico que tem uma promoção dependendo da quantidade
        if (codigo == 4) {

            // Se comprar 5 ou mais, cada unidade deixa de ser 8 reais e passa a ser 6,50
            if (quantidade >= 5) {return 6.50;}

            // Se não chegou em 5 unidades continua com o preço normal
            return 8.00;
        }

        // Esse zero fica como retorno de segurança caso chegue algum codigo que não existe
        return 0;
    }

    // Professora, essa função recebe o valor total da compra e descobre quanto deve ser descontado
    public static double calcularDesconto(double totalPedido) {

        // Passou de 150 reais recebe 10%, por isso multiplicamos por 0.10
        if (totalPedido > 150) {return totalPedido * 0.10;}

        // Se não passou de 150 mas chegou em pelo menos 80 reais, recebe 5%
        if (totalPedido >= 80) {return totalPedido * 0.05;}

        // Se não entrou em nenhuma condição significa que não ganhou desconto
        return 0;
    }
}