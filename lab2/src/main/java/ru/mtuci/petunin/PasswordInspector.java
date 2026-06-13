package ru.mtuci.petunin;

public class PasswordInspector {
    public int score(String password) {
        if (password == null || password.isBlank()) {
            return 0;
        }
        int score = 0;
        if (password.length() >= 8) {
            score++;
        }
        if (password.matches(".*[A-Z].*")) {
            score++;
        }
        if (password.matches(".*[a-z].*")) {
            score++;
        }
        if (password.matches(".*\\d.*")) {
            score++;
        }
        if (password.matches(".*[^A-Za-z0-9].*")) {
            score++;
        }
        return score;
    }

    public boolean isAcceptable(String password) {
        return score(password) >= 4;
    }
}
