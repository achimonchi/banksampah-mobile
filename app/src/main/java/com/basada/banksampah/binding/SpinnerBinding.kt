package com.basada.banksampah.binding

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.adapters.AdapterViewBindingAdapter.*

@BindingAdapter("setEntries")
fun Spinner.setEntries(entries: List<Any>?) {
    val adapter = entries?.let {
        ArrayAdapter(context, android.R.layout.simple_spinner_item, entries)
    }
    adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    setAdapter(adapter)
}

@BindingAdapter("android:selectedItemPosition")
fun Spinner.setSelectedItemPosition(position: Int) {
    if (selectedItemPosition != position) {
        setSelection(position)
    }
}

@BindingAdapter(
    value = ["android:onItemSelected", "android:onNothingSelected", "android:selectedItemPositionAttrChanged"],
    requireAll = false
)
fun setOnItemSelectedListener(
    view: AdapterView<*>, selected: OnItemSelected?,
    nothingSelected: OnNothingSelected?, attrChanged: InverseBindingListener?
) {
    if (selected == null && nothingSelected == null && attrChanged == null) {
        view.setOnItemSelectedListener(null)
    } else {
        view.setOnItemSelectedListener(
            OnItemSelectedComponentListener(selected, nothingSelected, attrChanged)
        )
    }
}

@BindingAdapter("android:selectedValueAttrChanged")
fun setSelectedValueListener(
    view: AdapterView<*>,
    attrChanged: InverseBindingListener?
) {
    if (attrChanged == null) {
        view.setOnItemSelectedListener(null)
    } else {
        view.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                attrChanged.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                attrChanged.onChange()
            }
        })
    }
}

@BindingAdapter("android:selectedValue")
fun setSelectedValue(view: AdapterView<*>, selectedValue: Any) {
    val adapter = view.adapter ?: return
    // I haven't tried this, but maybe setting invalid position will
    // clear the selection?
    var position = AdapterView.INVALID_POSITION
    for (i in 0 until adapter.count) {
        if (adapter.getItem(i) === selectedValue) {
            position = i
            break
        }
    }
    view.setSelection(position)
}