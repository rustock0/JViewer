package tk.jviewer.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.RegistrationDao;
import tk.jviewer.dao.ValidationDao;
import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.security.SecurityEncryptor;
import tk.jviewer.service.RegistrationService;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static tk.jviewer.messages.RegistrationMsg.*;

/**
 * Registration service implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationServiceImpl implements RegistrationService {

    private static final String USER_PERMISSIONS = "ROLE_USER";
    private static final String ADMIN_PERMISSIONS = "ROLE_ADMIN";

    private ValidationDao validationDao;
    private RegistrationDao registrationDao;

    @Override
    public RegistrationMsg createProfile(String name, String password, String invitationId, String department) {
        try{
            validationDao.checkUser(name);
            return EXIST;
        } catch (EmptyResultDataAccessException e){
            // Profile with such name does not exist in the database.
            if(isEmpty(invitationId) || registrationDao.getInvitationID().equals(invitationId)) {
                String role;
                if(isEmpty(invitationId)){
                    role = USER_PERMISSIONS;
                } else{
                    role = ADMIN_PERMISSIONS;
                }
                registrationDao.regProfile(name, SecurityEncryptor.encrypt(password), role, department);
                return SUCCESS;
            }
            return INVITATION_ID;
        }
    }

    //
    // Setters for Dependency Injection.
    //

    public void setValidationDao(ValidationDao validationDao) {
        this.validationDao = validationDao;
    }

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
