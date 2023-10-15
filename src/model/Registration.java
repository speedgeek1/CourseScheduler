package model;

public class Registration {

    public enum RegistrationStatus { ACCEPTED, CANCEL_ACCEPTED, CANCEL_REJECTED, CONFIRMED, COURSE_CANCELLED }
    private String id;
    private String email;
    private RegistrationStatus registrationStatus;

    public Registration(String id, String email) {
        this.id = id;
        this.email = email;
        this.registrationStatus = RegistrationStatus.ACCEPTED;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
