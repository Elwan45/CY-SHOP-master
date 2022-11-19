package com.ecommerce.metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Compte {
    private String login;
    private String mdp;
    private String role;
    private String question;
    private String reponse;
    private Date dateCreation;
    private Collection<Client> clientsByLogin;

    public Compte(String login, String mdp, String role, String question, String reponse, Date dateCreation, Collection<Client> clientsByLogin) {
        this.login = login;
        this.mdp = mdp;
        this.role = role;
        this.question = question;
        this.reponse = reponse;
        this.dateCreation = dateCreation;
        this.clientsByLogin = clientsByLogin;
    }

    public Compte() {
        super();
    }

    @Id
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "mdp")
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "reponse")
    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Basic
    @Column(name = "date_creation")
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Compte compte = (Compte) o;

        if (login != null ? !login.equals(compte.login) : compte.login != null) return false;
        if (mdp != null ? !mdp.equals(compte.mdp) : compte.mdp != null) return false;
        if (role != null ? !role.equals(compte.role) : compte.role != null) return false;
        if (question != null ? !question.equals(compte.question) : compte.question != null) return false;
        if (reponse != null ? !reponse.equals(compte.reponse) : compte.reponse != null) return false;
        if (dateCreation != null ? !dateCreation.equals(compte.dateCreation) : compte.dateCreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (reponse != null ? reponse.hashCode() : 0);
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "compteByLogin")
    public Collection<Client> getClientsByLogin() {
        return clientsByLogin;
    }

    public void setClientsByLogin(Collection<Client> clientsByLogin) {
        this.clientsByLogin = clientsByLogin;
    }
}
