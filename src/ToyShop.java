import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ToyShop {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyShop() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void changeWeight(int toyId, int weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(weight);
                break;
            }
        }
        System.out.println("Нет такой игрушки!!");
    }

    public void organizeGiveaway() {
        Random random = new Random();
        int randomNum = random.nextInt(100);
        for (Toy toy : toys) {
            if (randomNum < toy.getWeight() && toy.getQuantity() > 0) {
                prizeToys.add(toy);
                toy.setQuantity(toy.getQuantity() - 1);
                System.out.printf("Вы выиграли '%s'\n", toy.getName());
                System.out.printf("Игрушка '%s' осталась количеством: %s\n", toy.getName(), toy.getQuantity());
                break;
            }
        }
        if(prizeToys.size() == 0) System.out.println("Приз не выпал.");
    }

    public void getPrizeToy() {
        while (prizeToys.size() > 0) {
            Toy prizeToy_new = prizeToys.get(0);
            prizeToys.remove(0);
            System.out.println("Вы получили призовую игрушку: " + toString(prizeToy_new));
            writeToFile(prizeToy_new);
        }
        System.out.println("Все игрушки выдали");
    }
    public void getPrizeToy(int prize){
        if(prizeToys.size() >= prize){
            Toy prizeToy_new = prizeToys.get(prize);
            prizeToys.remove(prize);
            System.out.println("Вы получили призовую игрушку: " + toString(prizeToy_new));
            writeToFile(prizeToy_new);
        }
    }

    private String toString(Toy prizeToyNew) {
        return prizeToyNew.getName();}


    private void writeToFile(Toy toy) {
        try (FileWriter writer = new FileWriter("prize_toys.txt", true)) {
            writer.write(toy.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
