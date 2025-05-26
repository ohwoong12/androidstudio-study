package com.cookandroid.kiosk_project

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
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
            .setPositiveButton("추가") { _, _ ->
                // 각 RadioGroup에서 선택된 값을 가져오기
                val temperature =
                    view.findViewById<RadioButton>(view.findViewById<RadioGroup>(R.id.radioGroupTemperature).checkedRadioButtonId).text.toString()
                val shot =
                    view.findViewById<RadioButton>(view.findViewById<RadioGroup>(R.id.radioGroupShot).checkedRadioButtonId).text.toString()
                val ice =
                    view.findViewById<RadioButton>(view.findViewById<RadioGroup>(R.id.radioGroupIce).checkedRadioButtonId).text.toString()
                val sugar =
                    view.findViewById<RadioButton>(view.findViewById<RadioGroup>(R.id.radioGroupSugar).checkedRadioButtonId).text.toString()
                val cup =
                    view.findViewById<RadioButton>(view.findViewById<RadioGroup>(R.id.radioGroupCup).checkedRadioButtonId).text.toString()

                val option = PersonalOption(temperature, shot, ice, sugar, cup)
                onOptionSelected(option)
            }
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }
}