package com.picpay.desafio.android

import androidx.appcompat.app.AppCompatActivity
import androidx.core.bundle.Bundle

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contacts_fragment, ContactsFragment())
            .commit()
    }
}
