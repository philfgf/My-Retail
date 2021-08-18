package com.target.myRetail

import com.target.myRetail.config.Configuration
import com.target.myRetail.util.getMyRetailData
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyRetailTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var configuration: Configuration

    @Test
    fun `Return server ok status`() {
        val result = testRestTemplate.getForEntity("/health", String::class.java)
        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode, "Get health call successful, returns 200")
        assertEquals("server ok", result.body, "server ok message should be returned")
    }

    @Test
    fun `Get My Retail Data`() {
        val myRetailData = getMyRetailData(
            "13860428",
            configuration
        )
        println(myRetailData)
    }
}
