import entities.*;
import utilities.StreetSideType;

public class Main {
    public static void main(String[] args) {
        Street lp = new Street("Лос-Паганоса", true);
        lp.drag();

        Institution[] institutions = new Institution[]{new Institution("магазины", StreetSideType.RIGHT_SIDE),
                                                        new Institution("рестораны", StreetSideType.RIGHT_SIDE),
                                                        new Institution("столовые", StreetSideType.RIGHT_SIDE),
                                                        new Institution("гостиницы", StreetSideType.RIGHT_SIDE),
                                                        new Institution("кинотеатры", StreetSideType.RIGHT_SIDE),
                                                        new Institution("весёлые балаганчики", StreetSideType.RIGHT_SIDE),
                                                        new Institution("подземные гаражи", StreetSideType.RIGHT_SIDE),
                                                        new Institution("бензозаправочные станции", StreetSideType.RIGHT_SIDE)};
        House house = new House();
        house.fillHouse(institutions);
        house.showHouseContent();

        Walker shorty = new Walker("Коротышка");
        Infrastructure[] infrastructure = new Infrastructure[]{new Infrastructure("Пляжи"),
                                                                new Infrastructure("купальни"),
                                                                new Infrastructure("ныряльные вышки"),
                                                                new Infrastructure("лодочные и пароходные пристани"),
                                                                new Infrastructure("плавучие рестораны"),
                                                                new Infrastructure("морские качели и карусели"),
                                                                new Infrastructure("чертовы водяные колеса"),
                                                                new Infrastructure("параболоиды")};
        for (Infrastructure inf : infrastructure) {
            shorty.walkBy(inf);
            shorty.stopWalking();
        }

        CommonRestaurant cr = new CommonRestaurant("Обычный ресторан", StreetSideType.LEFT_SIDE);
        FoodStation fs = new FoodStation(StreetSideType.LEFT_SIDE);

        MainCharacter ponchik = new MainCharacter("Пончик");
        Infrastructure shore = new Infrastructure("побережье");
        ponchik.walkBy(shore);
        ponchik.stopNearTheRestaurant(fs);

        MainCharacter waiter = new MainCharacter("Официант");
        cr.getTerraceAvailability();
        fs.getTerraceAvailability();
        fs.getOutsideServiceAvialability(waiter);

    }
}
