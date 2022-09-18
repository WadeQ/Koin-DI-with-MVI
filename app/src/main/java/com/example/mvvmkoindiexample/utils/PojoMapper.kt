package com.example.mvvmkoindiexample.utils

interface PojoMapper<Entity,Domain> {

    fun mapToDomain(entity: Entity) : Domain

    fun mapToEntity(domain: Domain) : Entity
}