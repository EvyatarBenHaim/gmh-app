package com.gmhapp.enums;

public enum ValidationQuestions {
    WHAT_ARE_THE_LAST_4_DIGITS_OF_YOUR_ID("מה הם 4 הספרות האחרונות של תעודת הזהות שלך?"),
    WHAT_IS_YOUR_FAVORITE_PETS_NAME("מה השם של החיה האהובה עליך?"),
    WHAT_IS_YOUR_FIRST_SCHOOL_NAME("מה שם בית הספר שלמדת בו?"),
    WHAT_IS_YOUR_MOTHERS_PREVIOUS_LAST_NAME("מה שם המשפחה הקודם של אמך?");


    public static ValidationQuestions getValidationQuestions(String question) {
        for (ValidationQuestions v : ValidationQuestions.values()) {
            if (v.name().equals(question))
                return v;
        }
        return null;
    }

    private final String hebrew;

    ValidationQuestions(String hebrewVal) {
        this.hebrew = hebrewVal;
    }

    public String getHebrew() {
        return hebrew;
    }
}
