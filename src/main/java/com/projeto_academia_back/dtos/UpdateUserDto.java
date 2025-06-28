package com.projeto_academia_back.dtos;

public class UpdateUserDto {
    private String fistName;
    private String lastName;
    private String phone;
    private Boolean profileConfigured;

    public Boolean getProfileConfigured() {
        return profileConfigured;
    }

    public void setProfileConfigured(Boolean profileConfigured) {
        this.profileConfigured = profileConfigured;
    }

    public String getFirstName() {
        return fistName;
    }

    public void setFirstName(String firstName) {
        this.fistName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
