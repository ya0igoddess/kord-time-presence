package com.github.ya0igoddess.poma.pomagenerator

import kotlin.random.Random

class PomaNameGenerator {

    fun generateName(): String {
        val firstName = namesVariant.random()
        val suffix = nameSuffixes.random()
        return firstName + suffix
    }

    private companion object {
        val romanSuffixes = """
            ius us aius eius eus aeus is iis as anas enas inas
        """.trimIndent().split(' ')

        val englishSuffixes = """
            ment ion ness ity er or ist ian able ible ive al ic ed ing ise ate en ly
        """.trimIndent().split(' ')

        val frenchFemaleSuffixes = """
            ie ine que elle ette anne
        """.trimIndent().split(' ')

        val germanSuffixes = """
            ach au bach berg bergen brücken bühl burg dorf torf ey egg feld felde fels furt ford hagen hain haussen hain
            halde heim hof inf ünde tal thurm weil wend werder
        """.trimIndent().split(' ')

        val slavicSuffixes = """
            slav skiy skaya ský ská ović ević ovna ich owitz ko enko czyk čak ček čik czak
        """.trimIndent().split(' ')

        val spanishSuffixes = """
            ito illo cico uelo ote ucho azo udo ada dor al ante ario ero ía mente ista acho ajo anza ible ísimo oso
            ano dero adzio grafo iento
        """.trimIndent()

        val chineeseSuffixes = """
            lǎo xiǎo ā dì chū
        """.trimIndent()

        val nameSuffixes = romanSuffixes + englishSuffixes + frenchFemaleSuffixes + germanSuffixes + slavicSuffixes + spanishSuffixes + chineeseSuffixes

        val namesVariant = "Pom Rom Chrom Ryom Pöm Ryöm".split(' ')
    }
}