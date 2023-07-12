package com.example.kotlintasks

data class User(
        val name: String,
        val email: String,
        val gender: String,
        val age: Int,
        val company: String,
        val address: String,
        var role: String,
        val salary: Int
) {
    operator fun get(s: String): String {
        return this::class.members.find { it.name == s }?.call(this) as? String ?: ""
    }
}

fun getUser(): List<User> {
    val user1 = User("John", "john@gmail.com", "male", 24, "google", "Chennai, Tamilnadu", "Developer", 40000)
    val user2 = User("Doe", "doe@yahoo.com", "male", 35, "yahoo", "Denmark, Canada", "Tester", 30000)
    val user3 = User("Ajith", "ajith@gmail.com", "male", 22, "zoho", "Chengalpattu, Tamilnadu", "QA", 25000)
    val user4 = User("Praveena", "praveena@gmail.com", "female", 22, "HTC", "Banglore, Karnataka", "Developer", 50000)
    val user5 = User("Jagdesh", "jagdesh@gmail.com", "male", 22, "HCL", "Hosur, Karnataka", "Developer", 35000)
    val user6 = User("Naveen kumar", "naveen@gmail.com", "male", 30, "TCS", "Taramani, Tamilnadu", "Tester", 38000)

    val myUsers: List<User> = listOf(user1, user2, user3, user4, user5, user6)
    return myUsers
}

fun main() {
    val myUsers = getUser()

    fun groupByBasedOnkey(key: String): Map<String, List<User>> {
        val groupedUserData = mutableMapOf<String, MutableList<User>>()
        myUsers.forEach { user ->
            val groupingkey = user[key]
            if (groupedUserData.containsKey(groupingkey)) {
                groupedUserData[groupingkey]?.add(user)
            } else {
                groupedUserData[groupingkey] = mutableListOf(user)
            }
        }
        return groupedUserData
    }

    fun orderSalaryAsc(): List<User> {
        val salaryOrderUsers = myUsers.sortedBy { it.salary }
        return salaryOrderUsers;
    }

    fun orderSalaryDsc(): List<User> {
        val salaryOrderUsers = myUsers.sortedByDescending { it.salary }
        return salaryOrderUsers;
    }

    fun workingIn(state: String): List<User> {
        val output = myUsers.filter { it.address.contains(state) }
        return output;
    }

    fun maleSalary() : Int {
        val maleUsers = myUsers.filter { it.gender == "male" }
        var maleSalary: Int = 0;
        for(user in maleUsers) {
            maleSalary += user.salary;
        }
        return maleSalary;
    }

    fun allSalary(): Int {
        var totalSalary: Int = 0;
        for(user in myUsers) {
            totalSalary += user.salary;
        }
        return totalSalary;
    }

    fun findEmail(email: String): User? {
        val user = myUsers.find { it.email == email }
        return user;
    }

    println(groupByBasedOnkey("role"))
    println(groupByBasedOnkey("gender"))
    println("Order by Salary Ascending: ${orderSalaryAsc()}")
    println("Order by Salary Descending: ${orderSalaryDsc()}")
    println(workingIn("Tamilnadu"))
    println(workingIn("Karnataka"))
    println("Total Male Salary: ${maleSalary()}")
    println("All People Total Salary:  ${allSalary()}")
    println("The given email Holder is:  ${findEmail("doe@yahoo.com")}")

}
