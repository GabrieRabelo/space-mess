package com.rabelo.spacemess.repository

import com.rabelo.spacemess.domain.SpaceProbe
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SpaceProbeRepository: CrudRepository<SpaceProbe, Int>