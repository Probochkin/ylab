package second.task.snils;

public class SnilsValidatorImpl implements SnilsValidator {
    private static final int[] indexMass = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    @Override
    public boolean validate(String snils) {
        boolean result = false;

        if (!snils.matches("\\d{11}")) {
          return result;
        }

        String[] snilsNumber = snils.substring(0,9).split("");
        int snilsCheckSum = Integer.parseInt(snils.substring(9,11));
        int sumSnils = calcSum(snilsNumber);
        int controlNumber = getControlNumber(sumSnils);

        if (snilsCheckSum == controlNumber) {
            result = true;
        }

        return result;
    }

    private int getControlNumber (int sum) {
        //int result = 0;
        if (sum < 100) {
            return sum;
        }
        if (sum == 100) {
            return  0;
        } else {
            sum = sum % 101;
            if (sum == 100) {
                return  0;
            } else {
                return sum;
            }
        }
    }

    private static int calcSum(String[] snilsCheckSum) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += indexMass[i] * Integer.parseInt(snilsCheckSum[i]);
        }
        return sum;
    }
}
