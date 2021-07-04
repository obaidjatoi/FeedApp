package com.android.feedapp


import com.android.feedapp.repository.FeedRepo
import com.android.feedapp.utils.MockResponseFileReader
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class MockApiTest {
    private var mockWebServer: MockWebServer = MockWebServer()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader("response")
        assertNotNull(reader.content)
    }

    @Test
    fun checkApiStatus() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("response").content)
        mockWebServer.enqueue(response)
        val  actualResponse = FeedRepo.getFullFeedFromServer()
        assertEquals(response.toString().contains("OK"),actualResponse?.status?.equals("OK", ignoreCase = true ))
    }


}