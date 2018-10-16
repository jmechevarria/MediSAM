/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medisam.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import medisam.model.User;

/**
 *
 * @author jm
 */
public class PasswordsMatchImpl implements ConstraintValidator<PasswordsMatch, Object> {

    @Override
    public void initialize(PasswordsMatch a) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        User user = (User) obj;
        return user.getPassword().equals(user.getPasswordConfirm());
    }

}
