package com.jefte;

import java.util.List;
import java.util.Scanner;
import com.jefte.applications.PassageiroApplication;
import com.jefte.entities.Passageiro;
import com.jefte.facade.PassageiroFacade;
import com.jefte.repositories.PassageiroRepository;

/**
 * Ola mundo!
 */
public class Main {
    private static PassageiroRepository passageiroRepository;
    private static PassageiroApplication passageiroApplication;
    private static PassageiroFacade passageiroFacade;
    private static List<Passageiro> passageiros;
    private static Scanner scanner;

    public static void resolveDependencies() {
        passageiroRepository = new PassageiroRepository();
        passageiroApplication = new PassageiroApplication(passageiroRepository);
        passageiroFacade = new PassageiroFacade(passageiroApplication);
        scanner = new Scanner(System.in);
    }

    public static void initializePassageiros() {
        Passageiro passageiro1 = new Passageiro(1, "Jefte", "12345678900", "tMw0L@example.com");
        passageiroFacade.append(passageiro1);
    }

    public static void showMenu() {
        System.out.println("\n1 - Novo Passageiro");
        System.out.println("2 - Atualizar Passageiro");
        System.out.println("3 - Listar Passageiros");
        System.out.println("5 - Remover Passageiro");
        System.out.println("6 - Sair");
    }

    public static int getUserInput() {
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public static void listAllPassageiros() {
        String formatText = "%-10s %-20s %-20s%n";
        System.out.printf(formatText, "Id", "Nome", "CPF");
        passageiroFacade.getAll().forEach(p -> {
            // Supondo que o toString de Passageiro já exiba id, nome, cpf e email
            System.out.print(p.getId() + " " + p.getNome() + " " + p.getCpf() + "\n");
        });
    }

    public static void newPassageiro() {
        System.out.print("Informe o ID do novo passageiro: ");
        int id = scanner.nextInt();

        System.out.print("Informe o nome do passageiro: ");
        String nome = scanner.next();

        System.out.print("Informe o CPF do passageiro: ");
        String cpf = scanner.next();

        System.out.print("Informe o e‑mail do passageiro: ");
        String email = scanner.next();

        Passageiro passageiro = new Passageiro(id, nome, cpf, email);
        passageiroFacade.append(passageiro);

        System.out.println("Passageiro cadastrado com sucesso!");
    }

    public static void updatePassageiro() {
        System.out.print("Informe o ID do passageiro que deseja atualizar: ");
        int id = scanner.nextInt();

        System.out.print("Informe o novo ID do passageiro: ");
        int newId = scanner.nextInt();

        System.out.print("Informe o novo nome do passageiro: ");
        String nome = scanner.next();

        System.out.print("Informe o novo CPF do passageiro: ");
        String cpf = scanner.next();

        System.out.print("Informe o novo e‑mail do passageiro: ");
        String email = scanner.next();

        Passageiro passageiro = new Passageiro(newId, nome, cpf, email);
        passageiroFacade.update(id, passageiro);

        System.out.println("Passageiro atualizado com sucesso!");
    }

    public static void removePassageiro() {
        System.out.print("Informe o ID do passageiro que deseja remover: ");
        int id = scanner.nextInt();

        passageiroFacade.remove(id);
        System.out.println("Passageiro removido com sucesso!");
    }

    public static void run() {
        int menuOption;
        do {
            showMenu();
            menuOption = getUserInput();

            switch (menuOption) {
                case 1:
                    newPassageiro();
                    break;
                case 2:
                    updatePassageiro();
                    break;
                case 3:
                    listAllPassageiros();
                    break;
                case 5:
                    removePassageiro();
                    break;
                case 6:
                    System.out.println("Encerrando o programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (menuOption != 6);
    }

    public static void main(String[] args) {
        resolveDependencies();
        // initializePassageiros();
        run();
    }
}
