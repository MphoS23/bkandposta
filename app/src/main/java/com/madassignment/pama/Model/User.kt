package com.madassignment.pama.Model

data class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var gender: String = "",
    var address: String = "",
    var DOB: String = "",
    var phoneNumber: String = "",
    var password: String = "",
    var rePassword: String = ""
) {
    // equals()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (gender != other.gender) return false
        if (address != other.address) return false
        if (DOB != other.DOB) return false
        if (phoneNumber != other.phoneNumber) return false
        if (password != other.password) return false
        if (rePassword != other.rePassword) return false
        return true
    }

    // hashCode()
    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + DOB.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + rePassword.hashCode()
        return result
    }

    // toString()
    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', email='$email', gender='$gender', address='$address', DOB='$DOB', phoneNumber='$phoneNumber', password='$password', rePassword='$rePassword')"
    }


}
