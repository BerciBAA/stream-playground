package brickset;

import repository.Repository;

import java.util.Comparator;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     * Megszámolja hogy egy lego készletből hány ilyen tipusú
     * @param themename megtudsz adni egy lego témát
     * @return vissza adja hogy egy adott témában hány olyan tipusú lego szett van
     */
    public long countLegotheme(String themename) {
        return getAll().stream().filter(theme -> theme.getTheme() != null && theme.getTheme().contains(themename)).count();

    }

    /**
     * Kilistázza egy adott témához tartozó lego készleteknek az adatait
     * @param themename Adott témájú lego
     */
    public  void printLegotheme(String themename) {
        getAll().stream().filter(theme -> theme.getTheme() != null && theme.getTheme().contains(themename)).forEach(System.out::println);

    }

    /**
     * Vissza adja azokat a lego készleteket amikben több darab van mint a megadott szám
     * @param moreThanPieces Egy int tipusú szám
     * @return Vissza adja azokat a lego készleteket amikben több darab van mint a megadott szám
     */
    public long countMoreLegoPieciesthan(int moreThanPieces)
    {
        return getAll().stream().filter(pieces -> pieces.getPieces() > moreThanPieces ).count();
    }

    /**
     * Kíírja a paraméternél nagyobb számú darabokból álló lego készletek nevét
     * @param moreThanPieces Egy int tipusú szám
     */
    public void printMoreLegoPieciesthan(int moreThanPieces)
    {
        getAll().stream().filter(pieces -> pieces.getPieces() > moreThanPieces).sorted().map(legoSet -> legoSet.getPieces()+" "+legoSet.getName() ).forEach(System.out::println);
    }

    /**
     * Az összes lészletnek a darabjainak az átlagát adja vissza
     * @return Az összes lészletnek a darabjainak az átlagát adja vissza
     */
    public double averagePieceNumber(){
        return getAll().stream().mapToLong(LegoSet::getPieces).average().getAsDouble();
    }

    /**
     * Névszerint kilistázza a lego készleteket
     */
    public void printNamesSorted(){
        getAll().stream().map(LegoSet::getName).sorted().forEach(System.out::println);
    }
    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        //System.out.println(repository.countLegoSetsWithTag("Microscale"));
        System.out.println(repository.countLegotheme("Duplo"));
        //repository.printLegotheme("Duplo");
        //repository.printMoreLegoPieciesthan(100);
        //System.out.println(repository.countMoreLegoPieciesthan(100));
        //System.out.println(repository.averagePieceNumber());
        //repository.printNamesSorted();
    }
}
