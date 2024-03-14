package dat3;

import dat3.entity.*;
import dat3.enums.SalType;
import dat3.enums.SædeType;
import dat3.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class InitData implements CommandLineRunner {

    private final BiografRepository biografRepository;
    private final SalRepository salRepository;
    private final ForestillingRepository forestillingRepository;
    private final FilmRepository filmRepository;
    private final SædeRepository sædeRepository;
    private final BestillingRepository bestillingRepository;

    public InitData(BiografRepository biografRepository, SalRepository salRepository, ForestillingRepository forestillingRepository, FilmRepository filmRepository, SædeRepository sædeRepository, BestillingRepository bestillingRepository) {
        this.biografRepository = biografRepository;
        this.salRepository = salRepository;
        this.forestillingRepository = forestillingRepository;
        this.filmRepository = filmRepository;
        this.sædeRepository = sædeRepository;
        this.bestillingRepository = bestillingRepository;
    }

    @Override
    public void run(String... args) {

        Sal sal1 = new Sal(1,25,16, SalType.STOR);
        Sal sal2 = new Sal(2,25,16, SalType.STOR);

        salRepository.save(sal1);
        salRepository.save(sal2);

        Biograf biograf1 = new Biograf("Mads Seje Biograf", "Mads vej 2", 2, new ArrayList<>(Arrays.asList(sal1, sal2)));
        biografRepository.save(biograf1);

        Film film1 = new Film("The Matrix",120, "Action", "1999", false, "The Matrix er en amerikansk science fiction-actionfilm fra 1999 instrueret af The Wachowskis og produceret af Joel Silver. ", "English");
        filmRepository.save(film1);

        Forestilling forestilling1 = new Forestilling(biograf1, film1, sal1, LocalDateTime.now());

        Set<Sæde> sæderForestilling1 = new HashSet<>();
        for (int række = 1; række <= 25; række++) {
            for (int sædeNummer = 1; sædeNummer <= 16; sædeNummer++) {
                SædeType sædeType;
                if (række <= 2) {
                    sædeType = SædeType.COWBOY;
                } else if (række == 25) {
                    sædeType = SædeType.VIP;
                } else {
                    sædeType = SædeType.STANDARD;
                }
                double pris;
                if (sædeType == SædeType.COWBOY) {
                    pris = 50.0; // Billigere pris for cowboy-sæder
                } else if (sædeType == SædeType.VIP) {
                    pris = 150.0; // Dyrere pris for VIP-sæder
                } else {
                    pris = 100.0; // Standard pris for øvrige sæder
                }
                Sæde sæde = new Sæde(række, sædeNummer, sædeType, pris, false); // Optaget sat til false som standard
                sædeRepository.save(sæde);
                sæderForestilling1.add(sæde);
            }
        }
        forestilling1.setSæder(sæderForestilling1);
        forestillingRepository.save(forestilling1);

        Sæde sæde401 = new Sæde(1,1, SædeType.STANDARD, 100, false);
        sædeRepository.save(sæde401);

        Bestilling bestilling1 = new Bestilling("Mads", "mads@gmail.com", new ArrayList<>(Arrays.asList(forestilling1)), new ArrayList<>(Arrays.asList(sæde401)), 100, LocalDateTime.now(), false);
        bestillingRepository.save(bestilling1);

        // Tilføj sæder til sal2 (på samme måde som sal1)
//        Set<Sæde> sæderSal2 = new HashSet<>();
//        for (int række = 1; række <= 25; række++) {
//            for (int sædeNummer = 1; sædeNummer <= 16; sædeNummer++) {
//                SædeType sædeType;
//                if (række <= 2) {
//                    sædeType = SædeType.COWBOY;
//                } else if (række == 25) {
//                    sædeType = SædeType.VIP;
//                } else {
//                    sædeType = SædeType.STANDARD;
//                }
//                double pris;
//                if (sædeType == SædeType.COWBOY) {
//                    pris = 50.0; // Billigere pris for cowboy-sæder
//                } else if (sædeType == SædeType.VIP) {
//                    pris = 150.0; // Dyrere pris for VIP-sæder
//                } else {
//                    pris = 100.0; // Standard pris for øvrige sæder
//                }
//                Sæde sæde = new Sæde(række, sædeNummer, sædeType, pris, false); // Optaget sat til false som standard
//                sædeRepository.save(sæde);
//
//                sæderSal2.add(sæde);
//            }
//        }
//        sal2.setSæder(sæderSal2);

    }
}
