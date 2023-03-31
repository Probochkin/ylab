package third.task.validator;

public class PasswordValidator {

    public static boolean valdate(String login, String password, String confirmPassword) {
        try {
            if (!login.matches("\\w+")) {
                throw new WrongLoginException("Логин содержит недопустимые символы");
            }
            if (login.length() > 19) {
                throw new WrongLoginException("Логин слишком длинный");
            }
            if (!password.matches("\\w+")) {
                throw new WrongPasswordException("Пароль содержит недопустимые символы");
            }
            if (password.length() > 19) {
                throw new WrongPasswordException("Пароль слишком длинный");
            }
            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Пароль и подтверждение не совпадают");
            }
        } catch (WrongLoginException e) {
            e.printStackTrace();
            return false;
        } catch (WrongPasswordException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
