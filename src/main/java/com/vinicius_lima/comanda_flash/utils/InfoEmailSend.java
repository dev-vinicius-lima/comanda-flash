package com.vinicius_lima.comanda_flash.utils;

public class InfoEmailSend {
    private String responsibleName;
    private String responsibleEmail;
    private String titleEmail;

    public InfoEmailSend(String responsibleName, String responsibleEmail, String titleEmail) {
        this.responsibleName = responsibleName;
        this.responsibleEmail = responsibleEmail;
        this.titleEmail = titleEmail;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public String getTitleEmail() {
        return titleEmail;
    }

    public void setTitleEmail(String titleEmail) {
        this.titleEmail = titleEmail;
    }

}
