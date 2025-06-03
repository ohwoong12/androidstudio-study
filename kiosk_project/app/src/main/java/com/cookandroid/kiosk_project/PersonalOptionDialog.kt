package com.cookandroid.kiosk_project

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.cookandroid.kiosk_project.PersonalOption

class PersonalOptionDialog(
    context: Context,
    private val onOptionSelected: (PersonalOption) -> Unit
) {
    private val view = LayoutInflater.from(context).inflate(R.layout.dialog_personal_option, null)

    fun show() {
        val dialog = AlertDialog.Builder(view.context)
            .setTitle("퍼스널 옵션 선택")
            .setView(view)
            .setPositiveButton("추가", null)
            .setNegativeButton("취소", null)
            .create()

        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val temperatureGroup = view.findViewById<RadioGroup>(R.id.radioGroupTemperature)
                val shotGroup = view.findViewById<RadioGroup>(R.id.radioGroupShot)
                val iceGroup = view.findViewById<RadioGroup>(R.id.radioGroupIce)
                val sugarGroup = view.findViewById<RadioGroup>(R.id.radioGroupSugar)
                val cupGroup = view.findViewById<RadioGroup>(R.id.radioGroupCup)

                // 퍼스널 옵션이 전부 선택되지 않은 경우 토스트 메시지 출력
                if (temperatureGroup.checkedRadioButtonId == -1 ||
                    shotGroup.checkedRadioButtonId == -1 ||
                    iceGroup.checkedRadioButtonId == -1 ||
                    sugarGroup.checkedRadioButtonId == -1 ||
                    cupGroup.checkedRadioButtonId == -1
                ) {
                    Toast.makeText(view.context, "모든 옵션을 선택해주세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener   // 람다식 내부만 빠져나가게 return 값 조정
                    // return 쓰게 되면 아래 코드가 실행되지 않음
                }

                val temperature =
                    view.findViewById<RadioButton>(temperatureGroup.checkedRadioButtonId).text.toString()
                val shot =
                    view.findViewById<RadioButton>(shotGroup.checkedRadioButtonId).text.toString()
                val ice =
                    view.findViewById<RadioButton>(iceGroup.checkedRadioButtonId).text.toString()
                val sugar =
                    view.findViewById<RadioButton>(sugarGroup.checkedRadioButtonId).text.toString()
                val cup =
                    view.findViewById<RadioButton>(cupGroup.checkedRadioButtonId).text.toString()

                onOptionSelected(PersonalOption(temperature, shot, ice, sugar, cup))
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}