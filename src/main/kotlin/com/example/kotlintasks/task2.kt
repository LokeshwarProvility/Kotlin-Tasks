package com.example.kotlintasks

val users: MutableList<User> = mutableListOf()
var aid: Int = 1
var uid: Int = 1

data class AddressRequest(
        val id: Int,
        val houseNo: Int,
        val streetName: String
)

data class Address(
        val id: Int,
        val houseNo: Int,
        val streetName: String
)

data class UserRequest(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val age: Int? = null,
        val gender: String? = null,
        val address: List<AddressRequest>
)

data class User(
        val id: Int,
        val firstName: String,
        var lastName: String,
        var age: Int? = null,
        var gender: String? = null,
        var address: List<Address>
)

fun AddressRequest.toModel() = Address(
        id = id,
        houseNo = houseNo,
        streetName = streetName
)

fun UserRequest.toModel() = User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        address = address.map { it.toModel() }
)


fun create(userRequest: UserRequest) {
    val user: User = userRequest.toModel()
    users.add(user)
}

fun getAll() {
    println(users)
}

fun getOne(firstName: String) {
    users.forEach { user ->
        if (user.firstName == firstName) {
            println(user)
        }
    }
}

fun update(userRequest: UserRequest) {
    val updateUser: User = userRequest.toModel()
    users.forEach { user ->
        if (updateUser.firstName == user.firstName) {
            user.lastName = updateUser.lastName
            user.age = updateUser.age
            user.gender = updateUser.gender
            user.address = updateUser.address
        }
    }
}

fun delete(firstName: String) {
    users.forEach { user ->
        if (user.firstName == firstName) {
            users.remove(user)
        }
    }
}

fun main() {
    val addressRequest1 = AddressRequest(aid++,387, "MCC")
    val addressRequest2 = AddressRequest(aid++,443, "MCC")
    val addressRequests: List<AddressRequest> = listOf(addressRequest1, addressRequest2)

    val userRequest1 = UserRequest(uid++,"Lokesh", "Shetty", 22, "male", addressRequests)
    val userRequest2 = UserRequest(uid++,"Arjun", "Reddy", 22, "male", addressRequests)
    val userRequest3 = UserRequest(uid++,"Lokesh", "Reddy", 22, "male", addressRequests)

    create(userRequest1)
    create(userRequest2)

    getAll()
    update(userRequest3)
    getAll()
    delete("Lokesh")
    getAll()
    create(userRequest1)
    getOne("Lokesh")
}
