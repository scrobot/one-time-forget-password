class PasswordGenerator {
    fun generateRandomPassword(
        length: Int = 12,
        includeNumbers: Boolean = true,
        includeSpecialChars: Boolean = true
    ): String {
        val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        val numbers = "0123456789"
        val specialChars = "!@#\$%^&*()_-+=<>?/{}~|"

        val allowedChars = StringBuilder(letters)
        if (includeNumbers) allowedChars.append(numbers)
        if (includeSpecialChars) allowedChars.append(specialChars)

        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    companion object {
        fun randomPassword(
            length: Int = 12,
            includeNumbers: Boolean = true,
            includeSpecialChars: Boolean = true
        ): String {
            return PasswordGenerator().generateRandomPassword(length, includeNumbers, includeSpecialChars)
        }
    }
}