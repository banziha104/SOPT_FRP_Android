package com.example.iyeongjun.sopt_frp_sample

import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding2.widget.changes
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seekBarObserver = Observable
                .combineLatest( //combineLatest는 Observable 이벤트 발행의 흐름을 조합함
                        redbar.changes(),  // redbar가 변할때
                        bluebar.changes(),
                        greenbar.changes()
                        , Function3{ r:Int, g:Int ,b:Int ->  listOf(r,g,b)})// redbar, bluebar,greenbar를 어떻게 조합할지 Funtion 객체에 장입하여 반환

        seekBarObserver.subscribe{ // combineLastest로 조합된 red blue green이 list로 넘어옮
            colorCanvas.setBackgroundColor(Color.rgb(it[0],it[1],it[2])) //list로 rgb를만들어 color에 넣어줌
        }
    }

}
