import entities.*;
import exceptions.*;
import utilities.*;

public class Main {
    public static void main(String[] args) {

        Sea sea = new Sea("Море");
        sea.createBay("Залив");
        Guy guy = new Guy("Чувак");

        //Анонимный класс
        Observable hill = new Observable() {
            @Override
            public void observedBy(Person person) {
                System.out.println("Персонаж" + person.getName() + " любуется холмами");
            }

            @Override
            public String getName() {
                return "Холмы";
            }
        };
        guy.changeLocation(hill); //Типо на холмы пришел

        City lp = new City("Лос-Паганос");

        lp.addCitizen(new Rich("a"));
        lp.addCitizen(new Rich("b"));
        lp.addCitizen(new Rich("c"));
        lp.addCitizen(new Rich("d"));
        lp.addCitizen(new Rich("e"));

        Street street = new Street("Самая большая и красивая ", lp, true);
        street.drag();

        Institution[] institutions = new Institution[]{new Institution("магазины", WalkablePlace.StreetSideType.RIGHT_SIDE),
                new Institution("рестораны", WalkablePlace.StreetSideType.RIGHT_SIDE),
                new Institution("столовые", WalkablePlace.StreetSideType.RIGHT_SIDE),
                new Institution("гостиницы", WalkablePlace.StreetSideType.RIGHT_SIDE),
                new Institution("кинотеатры", WalkablePlace.StreetSideType.RIGHT_SIDE),
                new Institution("весёлые балаганчики", WalkablePlace.StreetSideType.RIGHT_SIDE),
                new Institution("подземные гаражи", WalkablePlace.StreetSideType.RIGHT_SIDE),
                new Institution("бензозаправочные станции", WalkablePlace.StreetSideType.RIGHT_SIDE)};
        House house = new House();
        house.fillHouse(institutions);
        house.showHouseContent();

        Walker shorty = new Walker("Коротышка");
        Infrastructure[] infrastructure = new Infrastructure[]{new Infrastructure("Пляжи", WalkablePlace.StreetSideType.LEFT_SIDE),
                new Infrastructure("купальни", WalkablePlace.StreetSideType.LEFT_SIDE),
                new Infrastructure("ныряльные вышки", WalkablePlace.StreetSideType.LEFT_SIDE),
                new Infrastructure("лодочные и пароходные пристани", WalkablePlace.StreetSideType.LEFT_SIDE),
                new Infrastructure("плавучие рестораны", WalkablePlace.StreetSideType.LEFT_SIDE),
                new Infrastructure("морские качели и карусели", WalkablePlace.StreetSideType.LEFT_SIDE),
                new Infrastructure("чертовы водяные колеса", WalkablePlace.StreetSideType.LEFT_SIDE),
                new Infrastructure("параболоиды", WalkablePlace.StreetSideType.LEFT_SIDE)};
        for (Infrastructure inf : infrastructure) {
            shorty.walkBy(inf);
            shorty.stopWalking(inf);
        }

        CommonRestaurant cr = new CommonRestaurant("Обычный ресторан");
        FoodStation fs = new FoodStation();

        MainCharacter ponchik = new MainCharacter("Пончик");
        Infrastructure shore = new Infrastructure("побережье");
        ponchik.walkBy(shore);
        ponchik.stopNearTheRestaurant(fs);
        try {
            ponchik.getHome();
        } catch (HasNotHomeException e) {
            System.out.println(e.getMessage());
        }

        MainCharacter waiter = new MainCharacter("Официант");

        cr.getTerraceAvailability();
        fs.getTerraceAvailability();
        fs.getOutsideServiceAvialability(waiter);

    }
}
