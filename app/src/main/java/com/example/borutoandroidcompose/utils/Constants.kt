package com.example.borutoandroidcompose.utils

object Constants {

    // Boruto API
    //const val BORUTO_API_BASE_URL = "http://10.0.2.2:8080" // Do not use localhost --> will not work on emulator
    const val BORUTO_API_BASE_URL = "https://boruto-server-ktor-6c8638842ddd.herokuapp.com/"

    const val DETAILS_SCREEN_ARG_KEY = "heroId"

    // ROOM
    const val BORUTO_DATABASE = "boruto_database"
    const val HERO_DATABASE_TABLE = "hero_table"
    const val HERO_REMOTE_KEY_DATABASE_TABLE = "hero_remote_key_table"

    const val LAST_ONBOARDING_PAGE = 2

    // Data store preferences
    const val PREFERENCES_NAME = "boruto_preferences"
    const val PREFERENCE_ONBOARDING_STATE = "preference_onboarding"

    // Paging 3
    const val PAGING_BATCH_SIZE = 3

    const val DESCRIPTION_TEXT_MAX_LINES = 8

    const val MIN_BOTTOMSHEET_BACKGROUND_IMAGE_FRACTION = 0.4f
}