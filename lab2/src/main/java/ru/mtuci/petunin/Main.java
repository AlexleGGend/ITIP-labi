package ru.mtuci.petunin;

public class Main {
    public static void main(String[] args) {
        PasswordInspector inspector = new PasswordInspector();
        String password = "Spring2026!";

        System.out.println("Пароль: " + password);
        System.out.println("Надежность: " + inspector.score(password));
        System.out.println("Можно использовать: " + inspector.isAcceptable(password));
    }
}
