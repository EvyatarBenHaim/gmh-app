package com.gmhapp.enums;

public enum ProductCategory {
    CLOTHING,
    FOOT_WEAR,
    WEDDINGS,
    MEDICINE,
    FINANCIAL_LOANS,
    STUDIES,
    BOOKS,
    BABYSITTER,
    HOSPITALITY;

    public static ProductCategory getProductCategory(String category) {
        for (ProductCategory p : ProductCategory.values()) {
            if (p.name().equals(category))
                return p;
        }
        return null;
    }


}
