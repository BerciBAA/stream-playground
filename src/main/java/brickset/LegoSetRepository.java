package brickset;

import repository.Repository;

import java.util.Map;

import static java.util.stream.Collectors.*;


/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }
    public void printAll() {
         getAll().stream().forEach(System.out::println);

    }

    /**
     *
     * @param name String tipus. Nevet kell megadni a készletekből.
     * @return Leerenőrzi hogy a name paraméter szerepel-e a lego készletekben.
     */
    public boolean anyMatchName(String name){
        return getAll()
                .stream()
                .anyMatch(l -> l.getName().contains(name));
    }

    /**
     * Kiírja az összes tag-et egyedi módon tehát nem szerepel benne kétszer ugyan az.
     */
    public void printAllTags() {
        getAll().stream()
                .filter(l -> l.getTags()!=null)
                .flatMap(l -> l.getTags().stream())
                .distinct()
                .forEach(System.out::println);

    }

    /**
     *
     * @return Vissza adja a lego készleteknek a darbjainak az összegét
     */
    public int legoSetPiecesSum(){
        return getAll().stream()
                       .map(l -> l.getPieces())
                       .reduce(0, (a, b) -> a + b);
    }

    /**
     *
     * @param greater Int tipusú. nagyobbnak kell lenni az adott értéknél
     * @return Megadja hogy a greater változónál hány kisebb és hány nagyyobb elem van
     */
    public Map<Boolean, Long> greaterThan(int greater) {
        return getAll().stream()
                .collect(partitioningBy(l -> l.getPieces() > greater, counting()));
    }

    /**
     *
     * @param smaller Int tipusú. kissebbnek kell lenni az adott értéknél
     * @return Megadja hogy a smaller változónál hány kisebb és hány nagyyobb elem van
     */
    public Map<Boolean, Long> smallerThan(int smaller) {
        return getAll().stream()
                .collect(partitioningBy(l -> l.getPieces() < smaller, counting()));
    }

    /**
     *
     * @return Visszaadja hogy hány nulla elemű lego készlet van
     */
    public Map<Boolean, Long> piecesZero() {
        return getAll().stream()
                .collect(partitioningBy(l -> l.getPieces() == 0 , counting()));
    }
    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        repository.printAll();
        System.out.println("----");
        System.out.println(repository.anyMatchName("Lighthouse"));
        System.out.println("----");
        System.out.println(repository.legoSetPiecesSum());
        System.out.println("----");
        repository.printAllTags();
        System.out.println("----");
        System.out.println(repository.greaterThan(100));
        System.out.println("----");
        System.out.println(repository.smallerThan(100));
        System.out.println("----");
        System.out.println(repository.piecesZero());
        System.out.println("----");


    }
}
