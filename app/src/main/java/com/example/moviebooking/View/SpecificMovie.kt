package com.example.moviebooking.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.moviebooking.R

class SpecificMovie : AppCompatActivity() {


    lateinit var backArrowImageView : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_movie)


        backArrowImageView = findViewById(R.id.backArrowImageView2)

        //when click on back arrow
        backArrowImageView.setOnClickListener{
            onBackPressed()
        }



    }


}