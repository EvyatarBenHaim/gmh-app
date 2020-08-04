package com.gmhapp.enums;

public enum ValidationQuestions {
    WHAT_ARE_THE_LAST_4_DIGITS_OF_YOUR_ID,
    WHAT_IS_YOUR_FAVORITE_PETS_NAME,
    WHAT_IS_YOUR_FIRST_SCHOOL_NAME,
    WHAT_IS_YOUR_MOTHERS_PREVIOUS_LAST_NAME;


    public static ValidationQuestions getValidationQuestions(String question) {
        for (ValidationQuestions v : ValidationQuestions.values()) {
            if (v.name().equals(question))
                return v;
        }
        return null;
    }
}
