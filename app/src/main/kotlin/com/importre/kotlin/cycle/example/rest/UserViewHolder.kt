package com.importre.kotlin.cycle.example.rest

import android.support.v7.widget.RecyclerView
import android.view.View
import com.importre.kotlin.cycle.cycle
import com.importre.kotlin.cycle.example.ext.loadUrl
import com.importre.kotlin.cycle.example.ext.toast
import com.importre.kotlin.cycle.example.rest.model.User
import com.importre.kotlin.cycle.example.rest.util.ImageUtils
import kotlinx.android.synthetic.main.layout_user_item.view.*
import rx.Observable

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    enum class ButtonType {
        CALL,
        EMAIL,
    }

    fun setUser(user: User) {
        val geo = user.address.geo
        val lat = geo.lat.toFloat()
        val lng = geo.lng.toFloat()
        val url = ImageUtils.getGoogleMapUrl(800, 300, lat, lng, 3)

        itemView.locationImage.loadUrl(url)
        itemView.nameText.text = user.name

        cycle {
            val emailChange = dom.select(itemView.emailButton).clicks().map { ButtonType.EMAIL }
            val callChange = dom.select(itemView.callButton).clicks().map { ButtonType.CALL }
            val change = Observable.merge(emailChange, callChange)
            change.map { type -> show(user, type) }
        }
    }

    private fun show(user: User, type: ButtonType) = {
        toast(when (type) {
            ButtonType.EMAIL -> user.email
            ButtonType.CALL -> user.phone
        })
    }
}

