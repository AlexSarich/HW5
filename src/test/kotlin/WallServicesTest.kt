import org.junit.Test

import org.junit.Assert.*

class WallServicesTest {

    @Test
    fun add_idNotEqualZero() {
        val service = WallServices()
        val post = Post(id = 0U,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text",
            postSource = null,
            copyHistory = null,
            attachments = null
        )
        val unexpectedValue = 0
        assertNotEquals(unexpectedValue, service.add(post).id)
    }

    @Test
    fun update_exist() {
        val service = WallServices()
        service.add(Post(id = 1U,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text",
            postSource = null,
            copyHistory = null,
            attachments = null))
        service.add(Post(id = 2U,
            ownerId = 2,
            fromId = 1,
            date = 20122012,
            text = "extra text",
            postSource = null,
            copyHistory = null,
            attachments = null))
        service.add(Post(id = 3U,
            ownerId = 3,
            fromId = 1,
            date = 20122012,
            text = "more extra text",
            postSource = null,
            copyHistory = null,
            attachments = null))

        val update = Post(id = 2U,
            ownerId = 2,
            fromId = 1,
            date = 20122012,
            text = "extra text",
            postSource = null,
            copyHistory = null,
            attachments = null)

        val result = service.update(update)
        assertTrue(result)
    }


    @Test
    fun update_notExist() {
        val service = WallServices()
        service.add(Post(id = 1U,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text",
            postSource = null,
            copyHistory = null,
            attachments = null))
        service.add(Post(id = 2U,
            ownerId = 2,
            fromId = 1,
            date = 20122012,
            text = "extra text",
            postSource = null,
            copyHistory = null,
            attachments = null))
        service.add(Post(id = 3U,
            ownerId = 3,
            fromId = 1,
            date = 20122012,
            text = "more extra text",
            postSource = null,
            copyHistory = null,
            attachments = null))

        val update = Post(id = 10U,
            ownerId = 6,
            fromId = 1,
            date = 20122012,
            text = "much more extra text",
            postSource = null,
            copyHistory = null,
            attachments = null)

        val result = service.update(update)
        assertFalse(result)
    }

    @Test (expected = PostNotFoundException::class)
    fun createComment_throw() {
        val service = WallServices()
        service.add(Post(id = 1U,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text",
            postSource = null,
            copyHistory = null,
            attachments = null))
        service.createComment(6U, Comment())
    }

    @Test
    fun createComment_addComment() {
        val service = WallServices()
        val arrayWithNoComments = service.comments.size
        service.add(Post(id = 1U,
            ownerId = 1,
            fromId = 1,
            date = 20122012,
            text = "text",
            postSource = null,
            copyHistory = null,
            attachments = null))
        service.createComment(1U, Comment())
        val actualValue = service.comments.size
        val expectedValue = arrayWithNoComments + 1
        assertEquals(expectedValue, actualValue)
    }

    @Test (expected = CommentNotFoundException::class)
    fun reportedComment_throwCommentNotFound() {
        val service = WallServices()
        service.comments += Comment (id = 7U)
        service.reportedComment(Comment(id = 14U), 2)
    }

    @Test (expected = FalseReasonException::class)
    fun reportedComment_throwFalseReason_bigNumber() {
        val service = WallServices()
        service.comments += Comment (id = 7U)
        service.reportedComment(Comment(id = 7U), 123)
    }

    @Test (expected = FalseReasonException::class)
    fun reportedComment_throwFalseReason_lessNumber() {
        val service = WallServices()
        service.comments += Comment (id = 7U)
        service.reportedComment(Comment(id = 7U), -123)
    }

    @Test (expected = FalseReasonException::class)
    fun reportedComment_throwFalseReason_seven() {
        val service = WallServices()
        service.comments += Comment (id = 7U)
        service.reportedComment(Comment(id = 7U), 7)
    }

    @Test
    fun reportedComment_addComment() {
        val service = WallServices()
        val arrayWithNoComments = service.reportedComments.size
        service.comments += Comment (id = 7U)
        service.reportedComment(Comment(id = 7U), 1)
        val actualValue = service.reportedComments.size
        val expectedValue = arrayWithNoComments + 1
        assertEquals(expectedValue, actualValue)
    }
}
