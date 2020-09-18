package com.prike.tutorship.cache

import android.content.SharedPreferences
import android.util.Log
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_TOKEN         = "account_token"
        const val ACCOUNT_ID            = "account_id"
        const val ACCOUNT_FIRST_NAME    = "account_first_name"
        const val ACCOUNT_LAST_NAME     = "account_last_name"
        const val ACCOUNT_EMAIL         = "account_email"
        const val ACCOUNT_TYPE          = "account_type"
        const val ACCOUNT_SEX           = "account_sex"
        const val ACCOUNT_PHONE         = "account_phone"
        const val ACCOUNT_IMAGE         = "account_image"
        const val ACCOUNT_LAST_SEEN     = "account_last_seen"
    }

    fun saveToken(token: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }

    fun getToken(): Either<Failure, String> = Either.Right(prefs.getString(ACCOUNT_TOKEN, "") ?: "")

    fun saveAccount(account: AccountEntity): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_ID,           account.id)
            putString(ACCOUNT_FIRST_NAME,   account.firstName)
            putString(ACCOUNT_LAST_NAME,    account.lastName)
            putString(ACCOUNT_EMAIL,        account.email)
            putString(ACCOUNT_TYPE,         account.type)
            putString(ACCOUNT_SEX,          account.sex)
            putString(ACCOUNT_PHONE,        account.phone)
            putString(ACCOUNT_IMAGE,        account.image)
            putString(ACCOUNT_LAST_SEEN,    account.lastSeen)
        }.apply()

        return Either.Right(None())
    }

    fun getAccount(): Either<Failure, AccountEntity> {
        val id = prefs.getString(ACCOUNT_ID, "")

        if (id.isNullOrEmpty()) {
            return Either.Left(Failure.NoSavesAccountsError)
        }

        val account = AccountEntity(
            prefs.getString(ACCOUNT_ID, "") ?: "",
            prefs.getString(ACCOUNT_FIRST_NAME, "") ?: "",
            prefs.getString(ACCOUNT_LAST_NAME, "") ?: "",
            "",
            prefs.getString(ACCOUNT_EMAIL, "") ?: "",
            prefs.getString(ACCOUNT_TOKEN, "") ?: "",
            prefs.getString(ACCOUNT_TYPE, "teacher") ?: "teacher",
            prefs.getString(ACCOUNT_PHONE, "") ?: "",
            prefs.getString(ACCOUNT_IMAGE, "") ?: "",
            "",
            prefs.getString(ACCOUNT_SEX, "man") ?: "man",
            "",
            "",
            "",
            prefs.getString(ACCOUNT_LAST_SEEN, "") ?: ""
        )

        return Either.Right(account)
    }

    fun updateLastSeen(lastSeen: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_LAST_SEEN, lastSeen)
        }.apply()

        return Either.Right(None())
    }

    fun removeAccount(): Either<Failure, None> {
        prefs.edit().apply {
            remove(ACCOUNT_ID)
            remove(ACCOUNT_FIRST_NAME)
            remove(ACCOUNT_LAST_NAME)
            remove(ACCOUNT_EMAIL)
            remove(ACCOUNT_TYPE)
            remove(ACCOUNT_PHONE)
            remove(ACCOUNT_IMAGE)
            remove(ACCOUNT_LAST_SEEN)
        }.apply()

        return Either.Right(None())
    }

    fun containsAnyAccount(): Boolean {
        val id = prefs.getString(ACCOUNT_ID, "") ?: ""
        return id.isNotEmpty()
    }
}