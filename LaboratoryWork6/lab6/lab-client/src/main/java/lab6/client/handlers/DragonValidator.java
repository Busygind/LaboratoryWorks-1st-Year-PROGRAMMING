package lab6.client.handlers;

import lab6.client.entities.Dragon;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class DragonValidator {

    static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    static Validator validator = validatorFactory.getValidator();

    public static boolean validateDragon(Dragon dragon) {
        Set<ConstraintViolation<Dragon>> validateResult = validator.validate(dragon);
        if (validateResult.size() > 0) {
            for (ConstraintViolation<Dragon> violation : validateResult) {
                TextFormatter.printErrorMessage(violation.getPropertyPath() + " " + violation.getMessage());
            }
            return false;
        }
        return true;
    }

    public static <T> boolean validateField(T t, String fieldName) {
        Set<ConstraintViolation<T>> validateResult = validator.validateProperty(t, fieldName);
        if (validateResult.size() > 0) {
            for (ConstraintViolation<T> violation : validateResult) {
                TextFormatter.printErrorMessage(violation.getPropertyPath() + " " + violation.getMessage());
            }
            return false;
        }
        return true;
    }
}
