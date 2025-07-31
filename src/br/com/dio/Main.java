package br.com.dio;
import br.com.dio.dao.UserDAO;
import br.com.dio.exception.EmptyStorageExeption;
import br.com.dio.exception.UserNotFoundExeption;
import br.com.dio.exception.ValidatorException;
import br.com.dio.model.MenuOption;
import br.com.dio.model.UserModel;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static br.com.dio.validator.UserValidator.verifyModel;

public class Main {

    private final static UserDAO dao = new UserDAO();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Bem vindo ao cadastro");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar por identificador");
            System.out.println("5 - listar");
            System.out.println("6 - Sair");
            var userInput = scanner.nextInt();
            var selectedOption = MenuOption.values()[userInput - 1];
            switch (selectedOption) {
                case SAVE -> {
                    try {
                        var user = dao.save(requestToSave());
                        System.out.printf("Usuário cadastrado %s", user);
                    }catch (ValidatorException ex){
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                case UPDATE -> {
                    try {
                    var user = dao.update(requestToUdate());
                    System.out.printf("Usuário atualizado %s", user);
                    } catch (UserNotFoundExeption | EmptyStorageExeption ex) {
                        System.out.println(ex.getMessage());
                    }catch (ValidatorException ex){
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }finally {
                        System.out.println("====================");
                    }
                }
                case DELETE -> {
                    try {
                        dao.delete(requestID());
                        System.out.println("Usuário excluído");
                    }catch (UserNotFoundExeption | EmptyStorageExeption ex) {
                        System.out.println(ex.getMessage());
                    } finally {
                        System.out.println("====================");
                    }
                }
                case FIND_BY_ID -> {
                    try {
                        var id = requestID();
                        var user = dao.findById(id);
                        System.out.printf("Usuário com id %s", id);
                        System.out.println(user);
                    } catch (UserNotFoundExeption | EmptyStorageExeption ex){
                        System.out.println(ex.getMessage());
                    }finally {
                        System.out.println("====================");
                    }
                }
                case FIND_ALL -> {
                    var users  = dao.findAll();
                    System.out.println("Usuários cadastrados");
                    System.out.println("====================");
                    users.forEach(System.out::println);
                    System.out.println("==========Fim==========");
                }
                case EXIT -> System.exit(0);
            }
        }
    }

    private static long requestID(){
        System.out.println("Informe o identificador do usuario");
        return scanner.nextLong();
    }

    private static UserModel requestToSave() throws ValidatorException {
        System.out.println("Informe o nome do usuario");
        var name = scanner.next();
        System.out.println("Informe o e-mail do usário");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento do usuário (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter);
        return validateInputs(0, name, email, birthday);
    }

    public static UserModel validateInputs(final long id, final String name,
                               final String email, final LocalDate birthday) throws ValidatorException {
        var user = new UserModel(0, name, email, birthday);
            verifyModel(user);
            return user;
    }
    private static UserModel requestToUdate() throws ValidatorException {
        System.out.println("Informe o identificador do usuario");
        var id = scanner.nextLong();
        System.out.println("Informe o nome do usuario");
        var name = scanner.next();
        System.out.println("Informe o e-mail do usário");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento do usuário (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter);
        return validateInputs(0, name, email, birthday);

    }

}
