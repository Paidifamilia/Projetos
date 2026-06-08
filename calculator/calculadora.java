package calculator;

import java.util.Scanner;

public class calculadora {

    static Scanner scanner = new Scanner(System.in);

    public static void somar() {
        System.out.print("Digite o primeiro número: ");
        int num1 = scanner.nextInt();
        System.out.print("Digite o segundo número: ");
        int num2 = scanner.nextInt();
        System.out.print(num1 + num2);
    }

    public static void subtrair() {
        System.out.print("Digite o primeiro número: ");
        int num1 = scanner.nextInt();
        System.out.print("Digite o segundo número: ");
        int num2 = scanner.nextInt();
        System.out.print(num1 - num2);
    }

    public static void multiplicar() {
        System.out.print("Digite o primeiro número: ");
        int num1 = scanner.nextInt();
        System.out.print("Digite o segundo número: ");
        int num2 = scanner.nextInt();
        System.out.print(num1 * num2);
    }

    public static void dividir() {
        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();
        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();
        System.out.print(num1 / num2);
    }

    public static void main(String[] args) throws Exception{
        
        int op;
        
        do{
            System.out.println("=== Calculadora Java ===\n");
            Thread.sleep(1000);
            System.out.println("=== Escolha uma opção ===");
            Thread.sleep(1000);
            System.out.println("1 - Somar");
            Thread.sleep(200);
            System.out.println("2 - Subtrair");
            Thread.sleep(200);
            System.out.println("3 - Multiplicar");
            Thread.sleep(200);
            System.out.println("4 - Dividir");
            Thread.sleep(200);
            System.out.println("0 - Sair");
            Thread.sleep(200);
            System.out.print("Opção: ");
            Thread.sleep(200);
            op = scanner.nextInt();

            switch(op){
                case 1:
                    Thread.sleep(500);
                    somar();
                    break;
                case 2:
                    Thread.sleep(500);
                    subtrair();
                    break;
                case 3:
                    Thread.sleep(500);
                    multiplicar();
                    break;
                case 4:
                    Thread.sleep(500);
                    dividir();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println("\n");
        }while(op != 0);

        scanner.close();
    }
}
