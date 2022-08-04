package com.rabelo.spacemess.repository

import com.rabelo.spacemess.domain.Planet
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanetRepository: CrudRepository<Planet, Int>