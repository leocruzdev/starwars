package com.dacruz.home.presentation.mapper

import com.dacruz.home.domain.model.Category as DomainCategory
import com.dacruz.home.presentation.model.Category as ViewCategory

class CategoryMapper {

    fun toView(domainCategory: DomainCategory): ViewCategory {
        val categories = listOf(
            "Pessoas" to domainCategory.people,
            "Planetas" to domainCategory.planets,
            "Filmes" to domainCategory.films,
            "Espécies" to domainCategory.species,
            "Veículos" to domainCategory.vehicles,
            "Naves" to domainCategory.starships
        )
        return ViewCategory(categories)
    }

    fun toView(domainCategoryList: List<DomainCategory>): List<ViewCategory> =
        domainCategoryList.map { toView(it) }

    fun toDomain(viewCategory: ViewCategory): DomainCategory {
        val categories = viewCategory.categories
        return DomainCategory(
            people = categories.firstOrNull { it.first == "Pessoas" }?.second ?: "",
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