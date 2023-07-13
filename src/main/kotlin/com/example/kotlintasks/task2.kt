package com.example.kotlintasks

val users: MutableList<User> = mutableListOf()

data class AddressRequest(
        val houseNo: Int,
        val streetName: String
)

data class Address(
        val id: Long?,
        val houseNo: Int,
        val streetName: String
)

data class UserRequest(
        val firstName: String,
        val lastName: String,
        val age: Int? = null,
        val gender: String? = null,
        val address: List<AddressRequest>
)

data class User(
        val id: Long?,
        val firstName: String,
        val lastName: String,
        val age: Int? = null,
        val gender: String? = null,
        val address: List<Address>
)

fun AddressRequest.toModel() = Address(
        id = 1,
        houseNo = houseNo,
        streetName = streetName
)

fun UserRequest.toModel() = User(
        id = 1,
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

fun main() {
    val addressRequest1 = AddressRequest(387,"MCC")
    val addressRequest2 = AddressRequest(443,"MCC")
    val addressRequests: List<AddressRequest> = listOf(addressRequest1, addressRequest2)

    val userRequest1 = UserRequest("Lokesh","Shetty",22,"male", addressRequests)
    val userRequest2 = UserRequest("Arjun","Reddy",22,"male", addressRequests)

    create(userRequest1)
    create(userRequest2)
    println(users)
}
