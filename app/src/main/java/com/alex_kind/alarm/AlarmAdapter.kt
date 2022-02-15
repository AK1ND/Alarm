package com.alex_kind.alarm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alex_kind.alarm.db.Alarms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlarmAdapter(var alarms: List<Alarms>, mainActivity: CoroutineScope) :
    RecyclerView.Adapter<AlarmAdapter.AlarmHolder>() {

    class AlarmHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvAlarm: TextView? = null

        init {
            tvAlarm = itemView.findViewById(R.id.tv_recycler)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.rc_item, parent, false)
        val holder = AlarmHolder(layout)

        holder.itemView.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    override fun onBindViewHolder(holder: AlarmHolder, position: Int) {
        val alarm = alarms[position]

        var minute = alarm.minute.toString()
        if (alarm.minute < 10) {
            minute = "0" + alarm.minute.toString()
        }

        holder.tvAlarm?.text = alarm.hour.toString() + ":" + minute

    }

}

