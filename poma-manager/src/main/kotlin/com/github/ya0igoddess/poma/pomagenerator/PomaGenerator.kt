package com.github.ya0igoddess.poma.pomagenerator

import com.github.ya0igoddess.poma.model.Poma
import kotlin.random.Random


class PomaGenerator {
    private val nameGenerator = PomaNameGenerator()

    fun generateRandomPoma(rarity: Int): Poma {
        val strengthAddition = Random.nextInt(rarity)
        val agilAddition = Random.nextInt(rarity)
        val intAddition = Random.nextInt(rarity)
        val name = nameGenerator.generateName()
        return Poma(
            id = null,
            accountId = -1L,
            name = name,
            rarity = rarity.toInt(),
            level = 1,
            strength = 1 + strengthAddition,
            agility = 1 + agilAddition,
            intelligence = 1 + intAddition
        )
    }
}