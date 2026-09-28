// Atitivade Feita por Willian e Romilton


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int quantidadePedidos = 0, brinquedosVendidos = 0, clientesComRacaoEPetisco = 0, quantidadeContribuicoes = 0;

        double totalRecebido = 0, somaContribuicoes = 0, maiorPedido = 0;

        String clienteMaiorPedido = "", continuar = "S";

        System.out.println("Atividade feita por Willian e Romilton");
        System.out.println();
        System.out.println("PET AMIGO - SISTEMA DE VENDAS");

        while (continuar.equalsIgnoreCase("S")) {

            System.out.println();
            System.out.print("Nome do cliente: ");
            String nomeCliente = scan.nextLine();

            double totalPedido = 0;

            boolean comprouRacao = false;
            boolean comprouPetisco = false;

            int codigo;

            do {
                mostrarProdutos();

                codigo = lerInteiro(scan, "Digite o codigo do produto: ");

                if (codigo == 0) {break;}

                if (codigo < 1 || codigo > 4) {
                    System.out.println("Codigo de produto invalido.");
                } else {

                    int quantidade = lerInteiro(
                            scan,
                            "Digite a quantidade: "
                    );

                    if (quantidade <= 0) {
                        System.out.println("Quantidade invalida.");

                    } else {
                        double preco = calcularPreco(codigo, quantidade);
                        double valorItem = preco * quantidade;totalPedido = totalPedido + valorItem;

                        if (codigo == 1) {comprouRacao = true;}
                        if (codigo == 2) {brinquedosVendidos = brinquedosVendidos + quantidade;}
                        if (codigo == 4) {comprouPetisco = true;}

                        System.out.printf("Valor do item: R$ %.2f%n", valorItem);
                        System.out.printf("Total do pedido ate agora: R$ %.2f%n", totalPedido);
                    }
                }

            } while (codigo != 0);

            double desconto = calcularDesconto(totalPedido);

            double valorComDesconto = totalPedido - desconto;

            System.out.print("Deseja contribuir com a campanha? (S/N): ");
            String resposta = scan.nextLine();

            double contribuicao = 0;

            if (resposta.equalsIgnoreCase("S")) {
                // Professora, destrinchamos esse if aqui porque ficou dificil ler ele em uma linha só
                contribuicao = valorComDesconto * 0.02;
                somaContribuicoes += contribuicao;
                quantidadeContribuicoes++;
            }

            double valorFinal = valorComDesconto + contribuicao; quantidadePedidos++; totalRecebido += valorFinal;

            if (comprouRacao && comprouPetisco) {clientesComRacaoEPetisco++;}

            if (quantidadePedidos == 1 || valorFinal > maiorPedido) {maiorPedido = valorFinal; clienteMaiorPedido = nomeCliente;}

            System.out.println();
            System.out.println("----- RESUMO DO PEDIDO -----");
            System.out.println("Cliente: " + nomeCliente);
            System.out.printf("Valor antes do desconto: R$ %.2f%n", totalPedido);
            System.out.printf("Desconto: R$ %.2f%n", desconto);
            System.out.printf("Contribuicao: R$ %.2f%n", contribuicao);
            System.out.printf("Valor final: R$ %.2f%n", valorFinal);
            System.out.println("----------------------------");

            System.out.print("\nDeseja registrar outro pedido? (S/N): ");
            continuar = scan.nextLine();
        }

        double mediaContribuicoes = 0;

        if (quantidadeContribuicoes > 0) {mediaContribuicoes = somaContribuicoes / quantidadeContribuicoes;}

        double percentualRacaoEPetisco = 0;

        if (quantidadePedidos > 0) {
            percentualRacaoEPetisco = ((double) clientesComRacaoEPetisco / quantidadePedidos) * 100;
        }

        System.out.println();
        System.out.println("===== ENCERRAMENTO DO DIA =====");
        System.out.println("Quantidade de pedidos: " + quantidadePedidos);
        System.out.printf("Valor total recebido: R$ %.2f%n", totalRecebido);
        System.out.println("Brinquedos vendidos: " + brinquedosVendidos);
        System.out.printf("Media das contribuicoes: R$ %.2f%n", mediaContribuicoes);
        System.out.println("Cliente com maior pedido: " + clienteMaiorPedido);
        System.out.printf("Valor do maior pedido: R$ %.2f%n", maiorPedido);

        System.out.printf("Clientes que compraram racao e petisco: %.2f%%%n", percentualRacaoEPetisco);
        scan.close();
    }

    public static void mostrarProdutos() {

        System.out.println();
        System.out.println("----- PRODUTOS -----");
        System.out.println("1 - Pacote de racao - R$ 25,00");
        System.out.println("2 - Brinquedo - R$ 15,00");
        System.out.println("3 - Shampoo - R$ 18,00");
        System.out.println("4 - Petisco - R$ 8,00");
        System.out.println("0 - Encerrar pedido");
    }

    public static int lerInteiro(Scanner scan, String mensagem) {

        int numero = 0;
        boolean valido = false;

        while (!valido) {

            try {

                System.out.print(mensagem);

                numero = Integer.parseInt(scan.nextLine());
                valido = true;

            } catch (NumberFormatException erro) {
                System.out.println("Entrada invalida. Digite apenas numeros inteiros.");
            }
        }

        return numero;
    }

    public static double calcularPreco(int codigo, int quantidade) {

        if (codigo == 1) {return 25.00;}
        if (codigo == 2) {return 15.00;}
        if (codigo == 3) {return 18.00;}

        if (codigo == 4) {
            if (quantidade >= 5) {return 6.50;}

            return 8.00;
        }

        return 0;
    }

    public static double calcularDesconto(double totalPedido) {

        if (totalPedido > 150) {return totalPedido * 0.10;}
        if (totalPedido >= 80) {return totalPedido * 0.05;}

        return 0;
    }
}