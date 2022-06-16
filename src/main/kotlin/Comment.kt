class Comment (
    val id: UInt = 0u,
    val fromId: Int = 0,
    val date: Int = 20122012,
    val text: String = "text",
    val donut: Donut = Donut(),
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Attachments? = null,
    val parentsStack: ArrayList<Int>? = null,
    val thread: ThreadComment = ThreadComment()
        )

data class ThreadComment (
    val count: Int = 0,
    val items: ArrayList<Int>? = null,
    val canPost: Boolean = true,
    val showReplyButton: Boolean = true,
    val groupsCanPost: Boolean = true
        )