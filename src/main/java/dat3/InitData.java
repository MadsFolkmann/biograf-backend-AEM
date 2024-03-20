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

        Biograf biograf1 = new Biograf("Mads Seje Biograf", "Mads vej 2", 2);
        biografRepository.save(biograf1);

        Biograf biograf2 = new Biograf("Woo Back Bio", "Woo City 3", 2);
        biografRepository.save(biograf2);

        Sal sal1 = new Sal(1,25,16, SalType.STOR, biograf1);
        Sal sal2 = new Sal(2,25,16, SalType.STOR, biograf1);

        Sal sal3 = new Sal(3,25,16, SalType.STOR, biograf2);
        Sal sal4 = new Sal(4,12,8, SalType.LILLE, biograf2);




        salRepository.save(sal1);
        salRepository.save(sal2);
        salRepository.save(sal3);
        salRepository.save(sal4);

        Film film1 = new Film("The Matrix",120, "Action", "https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/The_Matrix_soundtrack_cover.jpg/220px-The_Matrix_soundtrack_cover.jpg", false, "The Matrix er en amerikansk science fiction-actionfilm fra 1999 instrueret af The Wachowskis og produceret af Joel Silver. ", "English");
        filmRepository.save(film1);

        Film film2 = new Film("Kung Fu Panda 4", 90, "Animation", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRRTP-j51D5LbT3V_xXtI7L639TWsN2KABuVeSyMRqZZDzoccDE", false, "Kung Fu Panda 4 is an American computer-animated action comedy film produced by DreamWorks Animation and distributed by Universal Pictures. It is the fourth installment in the Kung Fu Panda franchise and the sequel to Kung Fu Panda 3 (2016).", "English");
        filmRepository.save(film2);

        Film film3 = new Film("Dune: del 2", 155, "Science Fiction", "https://www.kultunaut.dk/images/film/7102461/plakat.jpg", false, "Dune is a science fiction film directed by Denis Villeneuve, based on the novel of the same name by Frank Herbert.", "English");
        filmRepository.save(film3);

        Film film4 = new Film("The Batman", 170, "Action", "https://www.kultunaut.dk/images/film/7100552/plakat.jpg", false, "The Batman is a superhero film directed by Matt Reeves, starring Robert Pattinson as Bruce Wayne / Batman.", "English");
        filmRepository.save(film4);

        Film film5 = new Film("Anyone But You", 103, "Romance", "https://api.kino.dk/sites/kino.dk/files/styles/isg_focal_point_356_534/public/2023-10/anyonebutyouplakat.webp?h=7881f276&itok=tLMuTcUA", false, "Anyone But You is a romantic comedy film directed by Simon Curtis, starring Sam Claflin and Holliday Grainger.", "English");
        filmRepository.save(film5);

        Film film6 = new Film(
                "Ghostbusters: Frozen Empire",
                120,
                "Action",
                "https://all.web.img.acsta.net/img/13/27/132720d038a9b773c3c1ec26fcbbb205.jpg/r_500_x",
                true,
                "Ghostbusters: Frozen Empire is an action-packed comedy where our heroes confront a new ice age brought upon by a vengeful spirit. The team must brave the frozen tundra and face off against chilling spectres and frostbitten phantoms to save the world.",
                "English"
        );
        filmRepository.save(film6);

        Film film7 = new Film(
                "Godzilla x Kong: The New Empire",
                130,
                "Action",
                "https://all.web.img.acsta.net/img/24/e3/24e3c8592a929dddd994ef0b826a0880.jpg/r_500_x",
                true,
                "Godzilla x Kong: The New Empire showcases the epic battle between the titanic forces of nature, Godzilla and Kong, as they clash in a new chapter of their ancient rivalry.",
                "English"
        );
        filmRepository.save(film7);

        Film film8 = new Film(
                "Jagtsæson 2 - I medgang og modgang",
                95,
                "Comedy",
                "https://www.nfbio.dk/sites/nfbio.dk/files/styles/movie_poster/public/media-images/2024-02/gmnt-e16f7e56b7-1031431-vst-65d35a1e45079.jpeg?itok=1lj1oOp6",
                false,
                "Jagtsæson 2 - I medgang og modgang fortsætter med at følge livet af de tre venner, og deres udfordringer og komiske oplevelser. I denne fortsættelse står de over for nye personlige og fælles prøvelser, som tester deres venskab på nye måder.",
                "Danish"
        );
        filmRepository.save(film8);

        Film film9 = new Film(
                "Drengen og Hejren",
                90,
                "Animation",
                "https://www.nfbio.dk/sites/nfbio.dk/files/styles/movie_poster/public/media-images/2023-12/DOH_Plakat_LOW-scaled.jpg?itok=fi_qWHPt",
                false,
                "Drengen og Hejren er en hjertevarmende animationsfilm, der fortæller historien om et usædvanligt venskab mellem en ung dreng og en hejre. Sammen oplever de eventyr og lærer vigtige livslektioner om venskab, tillid og naturens kredsløb.",
                "Danish"
        );
        filmRepository.save(film9);

        Film film10 = new Film(
                "Den Grænseløse",
                110,
                "Thriller",
                "https://www.nfbio.dk/sites/nfbio.dk/files/styles/movie_poster/public/media-images/2023-12/gmnt-4ca4beaaca-1365388-vst-6581778fd8121.jpeg?itok=OCZZ82XD",
                false,
                "Den Grænseløse er en fængslende fortælling, der udforsker menneskets natur og de valg, vi står overfor, når grænserne mellem rigtigt og forkert bliver sløret. Filmen tager seerne med på en intens rejse gennem de mørke sider af ambition og hævn.",
                "Danish"
        );
        filmRepository.save(film10);






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

        Bestilling bestilling1 = new Bestilling("Mads", "mads@gmail.com", forestilling1, new ArrayList<>(Arrays.asList(sæde401)), 100, LocalDateTime.now(), false);
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
