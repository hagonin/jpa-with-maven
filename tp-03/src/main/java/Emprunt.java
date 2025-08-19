import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE_DEBUT")
    private LocalDate dateDebut;
    @Column(name = "DATE_FIN")
    private LocalDate dateFin;
    @Column(name = "DELAI")
    private int delai;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;
    @ManyToMany
    @JoinTable(
            name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_EMP"),
            inverseJoinColumns = @JoinColumn(name = "ID_LIV")
    )
    private Set<Livre> livres;

    {
        livres = new HashSet<>();
    }

    public Emprunt() {
    }

    public Emprunt(LocalDate dateDebut, LocalDate dateFin, int delai, Client client) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.delai = delai;
        setClient(client);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (this.client != null) {
            this.client.getEmprunts().remove(this);
        }
        this.client = client;
        if (this.client != null) {
            this.client.getEmprunts().add(this);
        }
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    public void addLivre(Livre livreDeRecette) {

        if(livreDeRecette != null) {
            this.livres.add(livreDeRecette);
            livreDeRecette.getEmprunts().add(this);
        }
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Emprunt{");
        sb.append("id=").append(id);
        sb.append(", dateDebut=").append(dateDebut);
        sb.append(", dateFin=").append(dateFin);
        sb.append(", delai=").append(delai);
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }

}