import java.time.ZoneOffset

class WallServices {
    private var posts = emptyArray<Post>()
    var comments = emptyArray<Comment>()
    var reportedComments = emptyArray<Comment>()

    private var newId = 1U/*LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toInt()*/

    fun add(post: Post): Post {
        val newPost = post.copy(id = newId)
        posts += newPost
        newId++
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, originPost) in posts.withIndex()) {
            if (originPost.id == post.id) {
                posts[index] = post.copy(ownerId = post.ownerId, date = post.date)
                return true
            }
        }
        return false
    }

    private fun findPostById(postId: UInt): Boolean {
        for (post in posts)
            if (post.id == postId) return true
        return false
    }

    fun createComment (postId: UInt, comment: Comment): Comment {
        if (findPostById(postId)) {
            comments += comment
            return comment
        } else {
            throw PostNotFoundException("Post not found")
        }
    }

    private fun findCommentById(commentId: UInt): Boolean {
        for (comment in comments)
            if (comment.id == commentId) return true
        return false
    }

    fun reportedComment(comment: Comment, reason: Int) {
        if (findCommentById(comment.id)) reportedComments += comment else
            throw CommentNotFoundException()
        if (reason < 0 || reason > 8 || reason == 7) throw FalseReasonException()
    }
}

open class PostNotFoundException(message: String): RuntimeException(message)
class CommentNotFoundException(): PostNotFoundException("Comment not found")
class FalseReasonException(): PostNotFoundException("Not found reason")