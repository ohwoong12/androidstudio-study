package com.cookandroid.ch13_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

// RecyclerView의 어댑터 클래스 정의
class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {
    var listData = mutableListOf<Memo>()    // RecyclerView에 표시할 데이터 리스트

    /**
     * 뷰 홀더 생성
     * XML을 메모리에 올림
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return Holder(view)
    }

    // 전체 아이템 개수 반환
    override fun getItemCount(): Int {
        return listData.size
    }

    // 해당 위치의 데이터를 ViewHolder에 바인딩
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData[position]
        holder.setMemo(memo)

    }

    // 아이템 뷰 하나를 담당하는 Holder 클래스
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 각 TextView 연결 (Private와 변하지 않는 val 사용)
        private val textNo: TextView = itemView.findViewById(R.id.textNo)
        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textDate: TextView = itemView.findViewById(R.id.textDate)

        // 아이템 클릭시 토스트 메시지 출력
        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "item = $(textTitle.text)", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // 데이터(Memo)를 각 TextView에 바인딩하는 함수
        fun setMemo(memo: Memo) {
            //textNo.text = "$(memo.no)"
            textNo.text = "${memo.no}"
            textTitle.text = memo.title

            // 시간 형식을 한눈에 들어오게 변환
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
            val formattedDate = sdf.format(memo.timestamp)
            textDate.text = formattedDate   // 날짜 표시
        }
    }
}
