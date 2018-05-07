package com.example.iyeongjun.sopt_frp_sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        recyclerView.adapter = RecyclerAdapter(countryList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        editCountry
                .textChanges() //editText가 변환될때마다 이벤트 발행
                .map { it.toString() } // charSequence 타입이 넘어옮으로, 해당 객체를 String으로 변환
                .subscribe{
                    var tempList = countryList.filter { s -> s.startsWith(it) } // 해당 이름으로 시작하는 데이터로만 이루어진 리스트를 만듬
                    recyclerView.adapter = RecyclerAdapter(tempList) // 걸러진 리스트를 데이터로 받는 어댑터를 장입
                    recyclerView.adapter.notifyDataSetChanged() // Recyclerview 갱신
                }
    }
}


