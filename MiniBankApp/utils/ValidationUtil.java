package utils;

public class ValidationUtil {
    public static boolean isValidNIK(String nik) {
        return nik != null && nik.matches("\\d{16}");
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{10,13}");
    }

    public static boolean isValidAmount(String amount) {
        try {
            long value = Long.parseLong(amount);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidPIN(String pin) {
        return pin != null && pin.matches("\\d{6}");
    }

    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    public static boolean isMultipleOf50000(long amount) {
        return amount % 50000 == 0;
    }
}
