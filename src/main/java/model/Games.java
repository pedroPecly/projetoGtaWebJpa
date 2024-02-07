package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private Boolean zerado;
    private int idPerfil;
    
    public Games(String nome, Boolean zerado, int idPerfil) {
        this.nome = nome;
        this.zerado = zerado;
        this.idPerfil = idPerfil;
    }

    public Games() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Boolean getZerado() {
        return zerado;
    }

    public void setZerado(Boolean zerado) {
        this.zerado = zerado;
    }

    
}
