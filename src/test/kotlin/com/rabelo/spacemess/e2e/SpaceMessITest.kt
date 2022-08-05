package com.rabelo.spacemess.e2e

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockServletContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@SpringBootTest
class SpaceMessITest {

    @Autowired
    private val webApplicationContext: WebApplicationContext? = null

    private var mockMvc: MockMvc? = null

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext!!).build()
    }

    @Test
    fun `test servlet context should return controller beans`() {
        val servletContext = webApplicationContext!!.servletContext
        assertThat(servletContext).isNotNull
        assertThat(servletContext is MockServletContext).isTrue
        assertThat(webApplicationContext.getBean("spaceProbeController")).isNotNull
        assertThat(webApplicationContext.getBean("planetController")).isNotNull
    }

    @Test
    fun `test probe movement WHEN try to move into an empty planet SHOULD do it succesfully`() {

        // Given a planet with 5x5 area
        this.mockMvc!!
            .perform(
                post("/planets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"width\":5, \"height\":5}")
            )
            .andExpect(status().isCreated)

        // And a crated probe
        this.mockMvc!!
            .perform(
                post("/space-probes")
            )
            .andExpect(status().isCreated)

        // When try to land
        this.mockMvc!!
            .perform(
                patch("/space-probes/{id}/land", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"planetId\":1, \"x\":1, \"y\":2, \"direction\":\"NORTH\"}")
            )
            .andExpect(status().isOk)

        this.mockMvc!!
            .perform(
                patch("/space-probes/{id}/command", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"command\":\"LMLMLMLMM\"}")
            )
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpectAll(
                jsonPath("$.id").value("1"),
                jsonPath("$.direction").value("NORTH"),
                jsonPath("$.position.x").value("1.0"),
                jsonPath("$.position.y").value("3.0"),
                jsonPath("$.planet.id").value("1"),
                jsonPath("$.planet.width").value("5"),
                jsonPath("$.planet.height").value("5")

            )
    }
}