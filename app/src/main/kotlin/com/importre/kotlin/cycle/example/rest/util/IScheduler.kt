package com.importre.kotlin.cycle.example.rest.util

import rx.Scheduler

interface IScheduler {

    fun main(): Scheduler

    fun background(): Scheduler
}
