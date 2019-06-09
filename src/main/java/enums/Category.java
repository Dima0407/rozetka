package enums;

/**
 * Created by Dmytro Torlop
 * on 30.05.19
 */
public enum Category {

    //categories level 1
    TELEFONY_TV_EHLECTRONIKA("telefony-tv-i-ehlektronika"),
    TOVARY_DLYA_DOMU("tovary-dlya-doma"),

    //categories level 2
    TELEFONY("telefony"),
    POBUTOVA_HIMIYA("bytovaya-himiya"),

    //categories level 3
    SMARTFONY("smartfon"),
    POROSHOK(".//li[position() = 1]/ul[@class = 'm-cat-subl' and position() = 1]/li[@class = 'm-cat-subl-i' and position() = 1]/a");

    String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
