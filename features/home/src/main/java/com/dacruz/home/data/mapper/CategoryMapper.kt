package com.dacruz.home.data.mapper

import com.dacruz.home.data.model.Category as DataCategory
import com.dacruz.home.domain.model.Category as DomainCategory

class CategoryMapper {

    fun toDomain(dataCategory: DataCategory): DomainCategory =
        DomainCategory(
            people = dataCategory.people,
            planets = dataCategory.planets,
            films = dataCategory.films,
            species = dataCategory.species,
            vehicles = dataCategory.vehicles,
            starships = dataCategory.starships
        )

    fun toDomain(dataCategoryList: List<DataCategory>): List<DomainCategory> =
        dataCategoryList.map { toDomain(it) }

    fun toData(domainCategory: DomainCategory): DataCategory =
        DataCategory(
            people = domainCategory.people,
            planets = domainCategory.planets,
            films = domainCategory.films,
            species = domainCategory.species,
            vehicles = domainCategory.vehicles,
            starships = domainCategory.starships
        )

    fun toData(domainCategoryList: List<DomainCategory>): List<DataCategory> =
        domainCategoryList.map { toData(it) }
}