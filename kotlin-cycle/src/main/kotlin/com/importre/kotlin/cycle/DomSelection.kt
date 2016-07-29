package com.importre.kotlin.cycle

import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.TextView
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxCompoundButton
import com.jakewharton.rxbinding.widget.RxSeekBar
import com.jakewharton.rxbinding.widget.RxTextView
import rx.Observable

open class DomSelection(val view: View) {

    fun clicks(): Observable<Void> {
        return RxView.clicks(view)
    }

    fun longClicks(): Observable<Void> {
        return RxView.longClicks(view)
    }

    fun textChanges(): Observable<CharSequence> {
        return try {
            RxTextView.textChanges(view as TextView)
        } catch (e: Exception) {
            Log.w(Cycle.name, e.toString())
            Observable.just("")
        }
    }

    fun refreshes(): Observable<Void?> {
        return try {
            val v = view as SwipeRefreshLayout
            RxSwipeRefreshLayout.refreshes(v)
        } catch (e: Exception) {
            Log.w(Cycle.name, e.toString())
            Observable.just(null)
        }
    }

    fun changes(): Observable<Int> {
        return try {
            RxSeekBar.changes(view as SeekBar)
        } catch (e: Exception) {
            Log.w(Cycle.name, e.toString())
            Observable.just(0)
        }
    }

    fun userChanges(): Observable<Int> {
        return try {
            RxSeekBar.userChanges(view as SeekBar)
        } catch (e: Exception) {
            Log.w(Cycle.name, e.toString())
            Observable.just(0)
        }
    }

    fun systemChanges(): Observable<Int> {
        return try {
            RxSeekBar.systemChanges(view as SeekBar)
        } catch (e: Exception) {
            Log.w(Cycle.name, e.toString())
            Observable.just(0)
        }
    }

    fun checkedChanges(): Observable<Boolean> {
        return try {
            RxCompoundButton.checkedChanges(view as CompoundButton)
        } catch (e: Exception) {
            Log.w(Cycle.name, e.toString())
            Observable.just(false)
        }
    }
}
