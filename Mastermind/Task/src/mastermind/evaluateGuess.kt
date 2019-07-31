package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = evaluateRightPosition(secret, guess)
    val wrongPosition = evaluatedWrongPosition(secret, guess)

    return Evaluation(rightPosition, wrongPosition)
}


fun evaluateRightPosition(secret: String, guess: String): Int {

    if (secret == guess) {
        return secret.length
    }

    var countValid = 0

    for(i in 0 until secret.length) {
        if(secret[i] == guess[i]) {
            countValid++
        }
    }


    return countValid
}

fun evaluatedWrongPosition(secret: String, guess: String): Int {

    var countNoValidos = 0
    var newSecret = ""
    var newGuess = ""

    for(i in 0 until secret.length) {
        if(secret[i] != guess[i]) {
            newSecret += secret[i]
            newGuess += guess[i]
        }
    }

    val wordsValuated = mutableListOf<Char>()

    for (i in 0 until newGuess.length) {

        if (!wordsValuated.contains(newGuess[i])) {
            val secretCount = getCountLetter(newSecret, newGuess[i])
            val guessCount = getCountLetter(newGuess, newGuess[i])

            if (secretCount > 0) {
                countNoValidos += if (secretCount == guessCount || secretCount > guessCount) guessCount
                else secretCount
            }

            wordsValuated.add(newGuess[i])
        }
    }

    return countNoValidos


}

fun getCountLetter(word: String, letter: Char) : Int {
    var contador = 0
    for(i in 0 until word.length) {
        if (word[i] == letter) {
            contador++
        }
    }
    return contador
}