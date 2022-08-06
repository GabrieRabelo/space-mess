package com.rabelo.spacemess.utils

import com.rabelo.spacemess.domain.Planet
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException


class PlanetRowMapper : RowMapper<Planet> {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Planet {
        val planet = Planet()
        planet.id = rs.getInt("ID")
        planet.height = rs.getInt("HEIGHT")
        planet.width = rs.getInt("WIDTH")
        return planet
    }
}