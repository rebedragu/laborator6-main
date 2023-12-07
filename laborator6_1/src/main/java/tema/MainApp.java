package tema;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class MainApp {
    public static void scriere(List<Angajat> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file= new File("src/main/resources/angajati.json");
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Angajat> citire() throws IOException {
        try {
            File file = new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<Angajat> angajati = mapper
                    .readValue(file, new TypeReference<List<Angajat>>() {});
            return angajati;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    static void afisareF(List<Angajat> ang,Filtru<Angajat>f)
    {
        for(Angajat a:ang)
            if(f.test(a))
            {
                System.out.println(a);
            }

    }
   /* static Filtru<Angajat> filtruAngajatiAprilieAnulTrecut = new Filtru<Angajat>() {
        @Override
        public boolean test(Angajat p) {
            LocalDate dataAngajarii = p.getData_angajarii();
            return dataAngajarii.getYear() == LocalDate.now().getYear() - 1 &&
                    dataAngajarii.getMonthValue() == 4 &&
                    (p.getPostul().contains("Sef") ||
                            p.getPostul().contains("Director"));

        }
    };*/


    public static void main(String[] args) throws IOException  {
        List<Angajat>angajati=new ArrayList<>();

        angajati.add(new Angajat("Ion","Programator", LocalDate.of(2012,10,30),2500));
        angajati.add(new Angajat("ALEX","Programator", LocalDate.of(2022,8,15),3000));
        angajati.add(new Angajat("DIANA","Manager", LocalDate.of(2010,1,3),8000));
        angajati.add(new Angajat("ION","Sef", LocalDate.of(2022,4,3),1500));
        angajati.add(new Angajat("Stefan","Director", LocalDate.of(2022,4,3),2000));
        angajati.add(new Angajat("Andrei","Director", LocalDate.of(2022,7,3),4000));
        angajati.add(new Angajat("Maria","Sef", LocalDate.of(2017,4,3),1800));


        scriere(angajati);

        List<Angajat>angajatii=citire();
        angajati.forEach(System.out::println);
        System.out.println("\n");
        System.out.println("Angjati care au peste 2500 RON:");

       /* afisareF(angajati, new Filtru<Angajat>() {
            @Override
            public boolean test(Angajat p) {
                return p.getSalariu()>2500;
            }
        });*/

        //afisarea buna
        angajati.stream()
                .filter(a->a.getSalariu()>2500)
                .forEach(System.out::println);



            System.out.println("\nAngajati din aprilie anul trecut cu functie de sef sau director:");
            int anCurent= LocalDate.now().getYear();
            List<Angajat> angajatiAprilie=angajati.stream()
                    .filter(a->a.getData_angajarii().getYear()==(anCurent-1)
                            && a.getData_angajarii().getMonthValue()==4
                            &&(a.getPostul().contains("Sef")||a.getPostul().contains("Director")))
                    .collect(Collectors.toList());
            angajatiAprilie.forEach(System.out::println);



        System.out.println("\nAngajati fara functie de conducere:");
        angajati.stream()
                .filter(a->!a.getPostul().contains("Sef") && !a.getPostul().contains("Director"))
                .sorted(Comparator.comparingDouble(Angajat::getSalariu).reversed())

                .forEach(System.out::println);


        System.out.println("\nNumele angajatilor scrise cu majuscule: ");
        List<String> angajatiMajuscule=angajati.stream()
                .filter(a->a.getNume().equals(a.getNume().toUpperCase()))
                .map(Angajat::getNume)
                .collect(Collectors.toList());
        angajatiMajuscule.forEach(System.out::println);


        List<Float> salariiMaiMiciDe3000 = angajati.stream()
                .filter(a -> a.getSalariu() < 3000)
                .map(Angajat::getSalariu)
                .collect(Collectors.toList());

        System.out.println("\nSalariile mai mici de 3000 RON:");
        salariiMaiMiciDe3000.forEach(System.out::println);



        Optional<Angajat> primulAngajat = angajati.stream()
                .min(Comparator.comparing(Angajat::getData_angajarii));


        if (primulAngajat.isPresent()) {
            Angajat primulAngajatGasit = primulAngajat.get();
            System.out.println("\nDatele primului angajat:");
            System.out.println(primulAngajatGasit);
        } else {
            System.out.println("\nNu exista angajati in firma!");
        }


       angajati.stream()
                .filter(a -> a.getNume().toLowerCase().contains("Ion"))
                .findAny()
               .ifPresentOrElse(
                a -> System.out.println("\nFirma are cel puțin un angajat numit Ion."),
                () -> System.out.println("\nFirma nu are niciun angajat numit Ion.")
        );



        long numarAngajatiVaraAnulPrecedent = angajati.stream()
                .filter(a -> a.getData_angajarii().getYear() == anCurent - 1 &&
                                a.getData_angajarii().getMonthValue() >= 6 &&
                                a.getData_angajarii().getMonthValue() <= 8)
                .count();

        System.out.println("\nNumărul de persoane care s-au angajat în vara anului precedent: " + numarAngajatiVaraAnulPrecedent);




    }}


