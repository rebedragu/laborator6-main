package tema;

import java.time.LocalDate;
import java.util.ArrayList;
@FunctionalInterface
interface Filtru<T>
{
    public boolean test(T p);
}
public class Angajat {
    private String nume;
    private String postul;
    private LocalDate data_angajarii;
    private float salariu;

    public Angajat(String nume, String postul, LocalDate data_angajarii, float salariu) {
        this.nume = nume;
        this.postul = postul;
        this.data_angajarii = data_angajarii;
        this.salariu = salariu;
    }

    public Angajat() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPostul() {
        return postul;
    }

    public void setPostul(String postul) {
        this.postul = postul;
    }

    public LocalDate getData_angajarii() {
        return data_angajarii;
    }

    public void setData_angajarii(LocalDate data_anhajarii) {
        this.data_angajarii = data_anhajarii;
    }

    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }

    @Override
    public String toString() {
        return "Angajati{" +
                "nume='" + nume + '\'' +
                ", postul='" + postul + '\'' +
                ", data_angajarii=" + data_angajarii +
                ", salariu=" + salariu +
                '}';
    }
}