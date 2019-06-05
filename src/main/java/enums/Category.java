package enums;

/**
 * Created by Dmytro Torlop
 * on 30.05.19
 */
public enum Category {

    //categories level 1
    TELEFONY_TV_EHLECTRONIKA("Смартфони, ТВ і електроніка"),
    TOVARY_DLYA_DOMU("Товари для дому"),

    //categories level 2
    TELEFONY("Телефони"),
    POBUTOVA_HIMIYA("Побутова хімія"),

    //categories level 3
    SMARTFONY("Смартфони"),
    POROSHOK("Порошок");

    String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
