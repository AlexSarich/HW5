import java.time.LocalDateTime
import java.time.ZoneOffset

class WallServices {
    private var posts = emptyArray<Post>()

    private var newId = 0/*LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toInt()*/

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
}