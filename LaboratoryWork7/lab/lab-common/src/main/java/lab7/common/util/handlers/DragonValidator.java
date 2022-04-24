package lab7.common.util.handlers;

import lab7.common.util.entities.Dragon;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public final class DragonValidator {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    private DragonValidator() {

    }

    public static boolean validateDragon(Dragon dragon) {
        Set<ConstraintViolation<Dragon>> validateResult = VALIDATOR.validate(dragon);
        if (validateResult.size() > 0) {
            for (ConstraintViolation<Dragon> violation : validateResult) {
                TextFormatter.printErrorMessage(violation.getPropertyPath() + " " + violation.getMessage());
            }
            return false;
        }
        return true;
    }

    public static <T> boolean validateField(T t, String fieldName) {
        Set<ConstraintViolation<T>> validateResult = VALIDATOR.validateProperty(t, fieldName);
        if (validateResult.size() > 0) {
            for (ConstraintViolation<T> violation : validateResult) {
                TextFormatter.printErrorMessage(violation.getPropertyPath() + " " + violation.getMessage());
            }
            return false;
        }
        return true;
    }
}
