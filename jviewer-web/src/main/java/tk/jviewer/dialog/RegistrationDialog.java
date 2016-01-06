package tk.jviewer.dialog;

import org.springframework.dao.DataIntegrityViolationException;
import tk.jviewer.service.RegistrationService;
import tk.jviewer.service.ResourceService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

/**
 * Serves for "registration" use case.
 */
public class RegistrationDialog implements Serializable {

    private static final long serialVersionUID = -6429124304116355292L;

    private String name;
    private String email;
    private String password;

    private RegistrationService registrationService;
    private ResourceService resourceService;

    public String createProfile() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        try {
            registrationService.createProfile(name, email, password);
            currentInstance.getExternalContext().getFlash().put("success", getResource("J6"));
            return "login?faces-redirect=true";
        } catch (DataIntegrityViolationException e) {
            currentInstance.addMessage("registrationForm:name", new FacesMessage(getResource("J22")));
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //
    // Helper methods
    //

    private String getResource(String resourceId) {
        return resourceService.getValue(Locale.ENGLISH, resourceId);
    }

    //
    // Dependency Injection
    //

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
