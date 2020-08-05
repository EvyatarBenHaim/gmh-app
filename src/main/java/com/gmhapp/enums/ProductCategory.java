package com.gmhapp.enums;

public enum ProductCategory {
    CLOTHING("הלבשה"),
    FOOT_WEAR("הנעלה"),
    WEDDINGS_AND_EVENT("אירועים וחתונות"),
    MEDICINE("רפואה/תרופות"),
    FINANCIAL_LOANS("הלוואות פיננסיות"),
    STUDIES("לימודים והוראה"),
    BOOKS("ספרים"),
    BABYSITTER("בייבי-סיטר"),
    HOSPITALITY("אירוח");

    public static ProductCategory getProductCategory(String category) {
        for (ProductCategory p : ProductCategory.values()) {
            if (p.name().equals(category))
                return p;
        }
        return null;
    }

    private final String hebrew;

    ProductCategory(String hebrewVal) {
        this.hebrew = hebrewVal;
    }

    public String getHebrew() {
        return hebrew;
    }


}
