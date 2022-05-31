import org.junit.Test

import org.junit.Assert.*

class WallServicesTest {

    @Test
    fun add_idNotEqualZero() {
        val service = WallServices()
        var post = Post(id = 0,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text",
        )
        val unexpectedValue = 0
        assertNotEquals(unexpectedValue, service.add(post).id)
    }

    @Test
    fun update_exist() {
        val service = WallServices()
        service.add(Post(id = 1,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text"))
        service.add(Post(id = 2,
            ownerId = 2,
            fromId = 1,
            date = 20122012,
            text = "extra text"))
        service.add(Post(id = 3,
            ownerId = 3,
            fromId = 1,
            date = 20122012,
            text = "more extra text"))

        val update = Post(id = 2,
            ownerId = 2,
            fromId = 1,
            date = 20122012,
            text = "extra text")

        val result = service.update(update)
        assertTrue(result)
    }


    @Test
    fun update_notExist() {
        val service = WallServices()
        service.add(Post(id = 1,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text"))
        service.add(Post(id = 2,
            ownerId = 2,
            fromId = 1,
            date = 20122012,
            text = "extra text"))
        service.add(Post(id = 3,
            ownerId = 3,
            fromId = 1,
            date = 20122012,
            text = "more extra text"))

        val update = Post(id = 10,
            ownerId = 6,
            fromId = 1,
            date = 20122012,
            text = "much more extra text")

        val result = service.update(update)
        assertFalse(result)
    }
}
