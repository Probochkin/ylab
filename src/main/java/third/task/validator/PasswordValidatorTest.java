package third.task.validator;

public class PasswordValidatorTest {
    public static void main(String[] args) {
        //Логин не допустимые символы
        System.out.println(PasswordValidator.valdate("login_1в","password__1234567891","password__123456789"));
        //Слишком длинный логин
        System.out.println(PasswordValidator.valdate("login_1_2_3_4_567891","password__1234567891","password__123456789"));
        //Пароль не допустимые символы
        System.out.println(PasswordValidator.valdate("Login","пароль__12347891","пароль__12347891"));
        //Слишком длинный  пароль
        System.out.println(PasswordValidator.valdate("Login","password__1234567891","пароль__12347891"));
        //Пароль и подтверждение не совпадают
        System.out.println(PasswordValidator.valdate("Login","Password","Paassword"));
        //Корректно
      System.out.println(PasswordValidator.valdate("Login","Password","Password" ));
    }
}
