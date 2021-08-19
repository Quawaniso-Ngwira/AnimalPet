package com.example.animalpet;

public class model {


    String petname, petcolor, petorigin, pettype, vaccinationstatus, rabiesdue, healthscheme, vetname, address, treatments, vetrecomendation;

    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public model(String petname, String petcolor, String petorigin, String pettype, String vaccinationstatus, String rabiesdue, String healthscheme, String vetname, String address, String treatments, String vetrecomendation) {
        this.petname = petname;
        this.petcolor = petcolor;
        this.petorigin = petorigin;
        this.pettype = pettype;
        this.vaccinationstatus = vaccinationstatus;
        this.rabiesdue = rabiesdue;
        this.healthscheme = healthscheme;
        this.vetname = vetname;
        this.address = address;
        this.treatments = treatments;
        this.vetrecomendation = vetrecomendation;
        this.expandable = false;
    }


    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getPetcolor() {
        return petcolor;
    }

    public void setPetcolor(String petcolor) {
        this.petcolor = petcolor;
    }

    public String getPetorigin() {
        return petorigin;
    }

    public void setPetorigin(String petorigin) {
        this.petorigin = petorigin;
    }

    public String getPettype() {
        return pettype;
    }

    public void setPettype(String pettype) {
        this.pettype = pettype;
    }

    public String getVaccinationstatus() {
        return vaccinationstatus;
    }

    public void setVaccinationstatus(String vaccinationstatus) {
        this.vaccinationstatus = vaccinationstatus;
    }

    public String getRabiesdue() {
        return rabiesdue;
    }

    public void setRabiesdue(String rabiesdue) {
        this.rabiesdue = rabiesdue;
    }

    public String getHealthscheme() {
        return healthscheme;
    }

    public void setHealthscheme(String healthscheme) {
        this.healthscheme = healthscheme;
    }

    public String getVetname() {
        return vetname;
    }

    public void setVetname(String vetname) {
        this.vetname = vetname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getVetrecomendation() {
        return vetrecomendation;
    }

    public void setVetrecomendation(String vetrecomendation) {
        this.vetrecomendation = vetrecomendation;
    }

    /**
     * public model(String petname, String petcolor, String petorigin, String pettype) {
     * this.petname = petname;
     * this.petcolor = petcolor;
     * this.petorigin = petorigin;
     * this.pettype = pettype;
     * this.expandable = false;
     * }
     * <p>
     * public String getPetname() {
     * return petname;
     * }
     * <p>
     * public void setPetname(String petname) {
     * this.petname = petname;
     * }
     * <p>
     * public String getPetcolor() {
     * return petcolor;
     * }
     * <p>
     * public void setPetcolor(String petcolor) {
     * this.petcolor = petcolor;
     * }
     * <p>
     * public String getPetorigin() {
     * return petorigin;
     * }
     * <p>
     * public void setPetorigin(String petorigin) {
     * this.petorigin = petorigin;
     * }
     * <p>
     * public String getPettype() {
     * return pettype;
     * }
     * <p>
     * public void setPettype(String pettype) {
     * this.pettype = pettype;
     * }
     */
    @Override
    public String toString() {
        return "model{" +
                "petname='" + petname + '\'' +
                ", petcolor='" + petcolor + '\'' +
                ", petorigin='" + petorigin + '\'' +
                ", pettype='" + pettype + '\'' +
                ", vaccinationstatus='" + vaccinationstatus + '\'' +
                ", rabiesdue='" + rabiesdue + '\'' +
                ", healthscheme='" + healthscheme + '\'' +
                ", vetname='" + vetname + '\'' +
                ", address='" + address + '\'' +
                ", treatments='" + treatments + '\'' +
                ", vetrecomendation='" + vetrecomendation + '\'' +
                ", expandable=" + expandable +
                '}';
    }
}
