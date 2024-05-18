package com.github.hemoptysisheart.sample.model

import android.util.Log
import com.github.hemoptysisheart.sample.domain.Maze
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MazeHolder {
    companion object {
        private const val TAG = "MazeHolder"
    }

    private val mutex = Mutex()

    var maze: Maze? = null
        private set(value) {
            Log.v(TAG, "#maze set : $value")
            field = value
        }

    suspend fun generate(width: Int, height: Int) {
        mutex.withLock {
            maze = Maze(width, height)
        }
    }

    override fun toString() = "$TAG(maze=$maze)"
}
