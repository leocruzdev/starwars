package com.dacruz.home.presentation.mapper

import com.dacruz.home.domain.model.Category as DomainCategory
import com.dacruz.home.presentation.model.Category as ViewCategory

class CategoryMapper {

    fun toView(domainCategory: DomainCategory): ViewCategory {
        val categories = listOf(
            Triple("Personagens", domainCategory.people,  "https://starwars-visualguide.com/assets/img/categories/character.jpg"),
            //Triple("Planetas", domainCategory.planets, "https://starwars-visualguide.com/assets/img/categories/planets.jpg")
            //Triple("Filmes", domainCategory.films, "https://starwars-visualguide.com/assets/img/categories/films.jpg"),
            //Triple("Espécies", domainCategory.species, "https://starwars-visualguide.com/assets/img/categories/species.jpg"),
            //Triple("Veículos", domainCategory.vehicles, "https://starwars-visualguide.com/assets/img/categories/vehicles.jpg"),
            //Triple("Naves", domainCategory.starships, "https://starwars-visualguide.com/assets/img/categories/starships.jpg")
        )
        return ViewCategory(categories)
    }

    fun toView(domainCategoryList: List<DomainCategory>): List<ViewCategory> =
        domainCategoryList.map { toView(it) }

    fun toDomain(viewCategory: ViewCategory): DomainCategory {
        val categories = viewCategory.categories
        return DomainCategory(
            people = categories.firstOrNull { it.first == "Personagens" }?.second ?: "",
            planets = categories.firstOrNull { it.first == "Planetas" }?.second ?: "",
            films = categories.firstOrNull { it.first == "Filmes" }?.second ?: "",
            species = categories.firstOrNull { it.first == "Espécies" }?.second ?: "",
            vehicles = categories.firstOrNull { it.first == "Veículos" }?.second ?: "",
            starships = categories.firstOrNull { it.first == "Naves" }?.second ?: ""
        )
    }

    fun toDomain(viewCategoryList: List<ViewCategory>): List<DomainCategory> =
        viewCategoryList.map { toDomain(it) }
}