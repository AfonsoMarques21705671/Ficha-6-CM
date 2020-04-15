package com.example.acalculator

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage private constructor(){
    private val storage = mutableListOf<Operation>()
    private val users = mutableListOf<User>()

    companion object {
        private var instance: ListStorage? = null

        fun getInstance () : ListStorage {
            synchronized(this) {
                if (instance == null) {
                    instance = ListStorage()
                }
                return instance as ListStorage
            }
        }
    }
    suspend fun insert(operation: Operation) {
//        withContext(Dispatchers.IO) {
//            Thread.sleep(30000)
            storage.add(operation)
//        }
    }
    fun insertUser(user: User) {
        users.add(user)
    }

    fun getAll(): List<Operation> {
        return storage.toList()
    }

    fun getDataUsers() : List<User> {
        return users
    }

    fun deleteStorage(index: Int) {
        storage.removeAt(index)
    }
}