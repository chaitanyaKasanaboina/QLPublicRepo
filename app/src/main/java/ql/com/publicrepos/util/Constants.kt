package ql.com.publicrepos.util

class Constants {
    companion object {
        const val URL = "URL"
        const val NAME = "NAME"
        const val ENVIRONMENT = "ENVIRONMENT"
        const val USERNAME = "USERNAME"
        const val PASSWORD = "PASSWORD"
        const val BLANK = ""
        const val PASSWORD_PATTERN = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                ".{9,}" +               //at least 9 characters
                "$"
    }
}